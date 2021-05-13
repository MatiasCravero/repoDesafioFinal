package com.mercadolibre.matias_cravero_desafio_final.util;

import com.mercadolibre.matias_cravero_desafio_final.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {
    public static LocalDate mappearFecha(String date) throws Exception{
        try{
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, format);
        }
        catch (Exception ex){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "Date mapping error. Should be yyyy-MM-dd", HttpStatus.BAD_REQUEST.value());
        }
    }
}
