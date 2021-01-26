package com.github.sarvesh27sharma.scalablewebapp.exception;

import com.github.sarvesh27sharma.scalablewebapp.service.ErrorKeys;
import com.github.sarvesh27sharma.v1.model.ErrorDTO;
import com.github.sarvesh27sharma.v1.model.ErrorsDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle exceptions across the application in one global handling
 * component
 *
 * @author Sarvesh Sharma
 */
@RestControllerAdvice
@Log4j2
public class ScalableWebAppExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * method to handle any server side exceptions for all request
     *
     * @param ex exception that has occur
     * @return list of error and details
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorsDTO handleInternalException(Exception ex) {
        log.error("5XX error :{}", ex.getMessage());
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorDTO error = new ErrorDTO();
        error.setCode(ErrorKeys.INTERNAL_SERVER_ERROR.getCode());
        error.setMessage(ErrorKeys.INTERNAL_SERVER_ERROR.getMessage());
        errors.add(error);
        return new ErrorsDTO().errors(errors);
    }

    /**
     * method to handle client side exceptions for api requests
     *
     * @param ex error details
     * @return list of error and respective http response code
     */
    @ExceptionHandler(ScalableWebApiException.class)
    public ResponseEntity<ErrorsDTO> handleScalableWebApiException(ScalableWebApiException ex) {
        log.error("4XX error :{}", ex.getMessage());
        log.error("4XX status code :{}", ex.getHttpStatus());
        ex.getErrorsDTO().getErrors().forEach(error -> log.error("error :{}", error));
        return new ResponseEntity<>(ex.getErrorsDTO(), ex.getHttpStatus());
    }

}
