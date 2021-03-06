package com.mercadolibre.matias_cravero_desafio_final.services;

//import com.google.common.reflect.TypeToken;
//import com.google.protobuf.Api;

import com.mercadolibre.matias_cravero_desafio_final.dto.NewPartDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.PartDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.StockPartDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.PartResponseDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.StockPartResponseDto;
import com.mercadolibre.matias_cravero_desafio_final.exceptions.ApiException;
import com.mercadolibre.matias_cravero_desafio_final.models.*;
import com.mercadolibre.matias_cravero_desafio_final.repositories.*;
import com.mercadolibre.matias_cravero_desafio_final.util.DateMapper;
import com.mercadolibre.matias_cravero_desafio_final.util.PartMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PartsServiceImpl implements com.mercadolibre.matias_cravero_desafio_final.services.PartsService {

    private PartRepository repoParts;
    private PartRecordRepository repoPartRecords;
    private PartMapper mapper;
    private ProviderRepository repoProvider;
    private StockCentralHouseRepository stockCentralHouseRepository;
    private CentralHouseRepository centralHouseRepository;

    public PartsServiceImpl(PartRepository repoParts, PartRecordRepository repoPartRecords,
                            PartMapper mapper, ProviderRepository repoProvider,
                            StockCentralHouseRepository stockCentralHouseRepo,
                            CentralHouseRepository centralHouseRepository){
        this.repoParts = repoParts;
        this.repoPartRecords = repoPartRecords;
        this.mapper = mapper;
        this.repoProvider = repoProvider;
        this.stockCentralHouseRepository = stockCentralHouseRepo;
        this.centralHouseRepository = centralHouseRepository;
    }

    // receives controller input and returns the dto response object back to controller
    @Transactional(readOnly=true)
    @Override
    public PartResponseDto getParts(String queryType, String date, String order) throws Exception {
        LocalDate dateLocal = null;
        Integer orderInt = null;
        if (!queryType.equals("C")){
            dateLocal = DateMapper.mappearFecha(date);
            orderInt = validateOrder(order);
        }
        else{
            if (!date.isEmpty() || !order.isEmpty()){
                throw new ApiException(HttpStatus.BAD_REQUEST.name(), "No filters allow with queryType C", HttpStatus.BAD_REQUEST.value());
            }
        }
        List<PartDto> listParts = queryParts(queryType, dateLocal, orderInt);

        if(listParts != null && listParts.isEmpty())
        {
            throw new ApiException(HttpStatus.NOT_FOUND.name(),"404 Not Found", HttpStatus.NOT_FOUND.value());
        }

        return new PartResponseDto(listParts);
    }

    public void updateStock(Part part, Integer newStock){
        Integer currentStock = part.getStock().getQuantity();
        part.getStock().setQuantity(currentStock + newStock);
        repoParts.save(part);
    }

    public Integer updateStock(String partCode, Integer quantity){
        if (quantity<0){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "Negative quantity", HttpStatus.BAD_REQUEST.value());
        }
        Part part = repoParts.findPartByPartCode(partCode).orElse(null);
        if (part == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "No such part exists", HttpStatus.BAD_REQUEST.value());
        }
        Integer currentStock = part.getStock().getQuantity();
        Integer newStock = currentStock + quantity;
        part.getStock().setQuantity(newStock);
        repoParts.save(part);
        return newStock;
    }

    public Provider validateProvider(Long providerId){
        Provider provider = repoProvider.findProviderById(providerId).orElse(null);
        if (provider == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "No such provider exists", HttpStatus.BAD_REQUEST.value());
        }
        else return provider;
    }

    public void updateCentralHouseStock(Part newPart){
        List<CentralHouse> centralHouses = centralHouseRepository.findAll();
        for (CentralHouse ch : centralHouses){
            StockCentralHouse stockCentralHouse = new StockCentralHouse();
            stockCentralHouse.setCentralHouse(ch);
            stockCentralHouse.setPart(newPart);
            stockCentralHouse.setQuantity(0);
            stockCentralHouseRepository.save(stockCentralHouse);
        }
    }

    @Transactional
    @Override
    public NewPartDto createPart(NewPartDto newPart) {
        String partCode = newPart.getPartCode();
        Part part = repoParts.findPartByPartCode(partCode).orElse(null);
        // updates an existing part
        if (part != null){
            Integer newStock = newPart.getStock();
            updateStock(part, newStock);
        }
        // creates and saves the new part
        else{
            Long providerId = newPart.getProviderId();
            Provider provider =  validateProvider(providerId);
            part = mapper.reverseMap(newPart, provider);
            repoParts.save(part);
            updateCentralHouseStock(part);
        }
        return newPart;
    }

    // get the parts according the query type
    public List<PartDto> queryParts(String query, LocalDate date, Integer order) throws Exception {
        List<PartDto> listParts = null;
        switch (query){
            case "C":
                listParts = getAllParts();
                break;
            case "P":
                listParts = getAllPartsModify(date, order);
                break;
            case "V":
                listParts = getAllPartsPriceMod(date,order);
                break;
        }
        return listParts;
    }
    // validates order input
    public Integer validateOrder(String order){
        Integer orderInt = null;
        try {
            orderInt = Integer.parseInt(order);
        }
        catch (Exception ex){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "invalid order format", HttpStatus.BAD_REQUEST.value());
        }
        if (orderInt < 1 || orderInt > 3){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "order must be between 1 or 3", HttpStatus.BAD_REQUEST.value());
        }
        return orderInt;
    }

    // gets all parts without filtering
    public List<PartDto> getAllParts(){
        var result =  this.repoParts.findAll();
        ArrayList<PartDto> parts = new ArrayList<>();
        return mapper.mapList(result, false);
    }

    // gets every part which price was modified after the date input
    public List<PartDto> getAllPartsPriceMod(LocalDate date, Integer order) throws Exception {
        List<PartRecord> result = this.repoPartRecords.findByLastModificationAfter(date);
        if (order > 0){
            orderPartsRecords(order, result);
        }
        List<Part> parts = getRelatedParts(result);
        return mapper.mapList(parts, true);
    }

    // gets the parts related to the part records
    public List<Part> getRelatedParts(List<PartRecord> records) {
        List<Part> parts = new ArrayList<>();
        for(PartRecord p: records){
            parts.add(p.getPart());
        }
        return parts;
    }

    // gets every part modified after the date input
    public List<PartDto> getAllPartsModify(LocalDate date, Integer order) throws Exception {
        List<Part> result = this.repoParts.findByLastModificationAfter(date);
        if(order > 0){
            orderParts(order, result);
        }
        return mapper.mapList(result, false);
    }

    // order a list of Parts
    public void orderParts(Integer order, List<Part> parts) throws Exception {
        switch (order){
            case 1:
                parts.sort(Comparator.comparing(Part::getDescription));
                break;
            case 2:
                parts.sort(Comparator.comparing(Part::getDescription).reversed());
                break;
            case 3:
                parts.sort(Comparator.comparing(Part::getLastModification));
                break;
        }
    }

    // order a list of PartsRecords
    public void orderPartsRecords(Integer order, List<PartRecord> parts) throws Exception {
        switch (order){
            case 1:
                parts.sort((p1, p2) -> p1.getPart().getDescription().compareTo(p2.getPart().getDescription()));
                break;
            case 2:
                parts.sort((p1, p2) -> p2.getPart().getDescription().compareTo(p1.getPart().getDescription()));
                break;
            case 3:
                parts.sort(Comparator.comparing(PartRecord::getLastModification));
        }
    }

    public StockPartResponseDto validateMinStock(Integer minStock, String countryCentralHouse){
        if(minStock < 0){
            throw  new ApiException(HttpStatus.BAD_REQUEST.name(), "Minimum stock cannot be negative", HttpStatus.BAD_REQUEST.value());
        }
        CentralHouse centralHouse = centralHouseRepository.findByCountryEquals(countryCentralHouse).orElse(null);
        if (centralHouse == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "Central house not found", HttpStatus.BAD_REQUEST.value());
        }

        List<StockCentralHouse> stocksCentralHouse = stockCentralHouseRepository.findByCentralHouseId(centralHouse.getId());
        if (stocksCentralHouse.isEmpty()){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "the central house does not have parts", HttpStatus.BAD_REQUEST.value());
        }

        List<Part> parts = validateMinStock(minStock, stocksCentralHouse);
        if (parts.isEmpty())
        {
            throw new ApiException(HttpStatus.NOT_FOUND.name(), "There are no parts with stock less than the minimum", HttpStatus.NOT_FOUND.value());
        }
        List<StockPartDto> listStockPart = new ArrayList<>();
        for(Part part: parts){
            StockPartDto stockPartDto = new StockPartDto();
            stockPartDto.setPartCode(part.getPartCode());
            stockPartDto.setDescription(part.getDescription());
            StockCentralHouse sch = stockCentralHouseRepository.findByPartIdEqualsAndCentralHouseIdEquals(part.getId(), centralHouse.getId());
            Integer units = minStock - sch.getQuantity();
            stockPartDto.setMessage("The part is " + units + " units below the minimum stock, you should place an order");

            listStockPart.add(stockPartDto);
        }


        StockPartResponseDto resp = new StockPartResponseDto(listStockPart);

        return resp;

    }

    public List<Part> validateMinStock(Integer minStock, List<StockCentralHouse> stocks){
        List<Part> resp = new ArrayList<>();
        for (StockCentralHouse sch: stocks){
            if (sch.getQuantity() < minStock){
                resp.add(sch.getPart());
            }
        }

        return resp;
    }
}
