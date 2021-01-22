/**
 * 
 */
package com.github.sarvesh27sharma.scalablewebapp.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.sarvesh27sharma.v1.model.ErrorDTO;
import com.github.sarvesh27sharma.v1.model.ErrorsDTO;

import lombok.extern.log4j.Log4j2;

/**
 * Class to handle exceptions across the application in one global handling
 * component
 * 
 * @author Sarvesh Sharma
 *
 */
@RestControllerAdvice
@Log4j2
public class ScalableWebAppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorsDTO handleInternalException(Exception ex) {
		log.error("5XX error :{}",ex.getMessage());
		List<ErrorDTO> errors = new ArrayList<>();
		ErrorDTO error = new ErrorDTO();
		error.setCode("ERR_01");
		error.setMessage("INTERNAL_SERVER_ERROR");
		errors.add(error);
		return new ErrorsDTO().errors(errors);
	}
	
	@ExceptionHandler(ScalableWebApiException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDTO handleScalableWebApiException(ScalableWebApiException ex) {
		log.error("4XX error :{}",ex.getMessage());
		return ex.getErrorsDTO();
	}

}
