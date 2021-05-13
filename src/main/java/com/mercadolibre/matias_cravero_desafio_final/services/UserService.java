package com.mercadolibre.matias_cravero_desafio_final.services;

import com.mercadolibre.matias_cravero_desafio_final.dto.responses.BasicResponseDto;

public interface UserService {

    BasicResponseDto createUser(String username, String password) throws Exception;
    BasicResponseDto changeRole(String s, String username) throws Exception;


}
