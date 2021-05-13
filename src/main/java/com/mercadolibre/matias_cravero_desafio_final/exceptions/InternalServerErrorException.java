package com.mercadolibre.matias_cravero_desafio_final.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends com.mercadolibre.matias_cravero_desafio_final.exceptions.ApiException {
	private static final long serialVersionUID = 1L;
	private static final String INTERNAL_ERROR_CODE = "internal_error";

	public InternalServerErrorException(Throwable e) {
		super(INTERNAL_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
	}

	public InternalServerErrorException(String message, Throwable e) {
		super(INTERNAL_ERROR_CODE, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
	}
}
