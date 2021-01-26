package com.github.sarvesh27sharma.ScalableWebApp.exception;

import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebAppExceptionHandler;
import com.github.sarvesh27sharma.scalablewebapp.service.ErrorKeys;
import com.github.sarvesh27sharma.v1.model.ErrorDTO;
import com.github.sarvesh27sharma.v1.model.ErrorsDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScalableWebAppExceptionHandlerTest {

    private ScalableWebAppExceptionHandler handler;
    @BeforeEach
    void setUp() {
        handler= new ScalableWebAppExceptionHandler();
    }

    @Test
    void handleInternalExceptionTest() {
        //given
        Exception exception= new Exception("server down");
        ErrorsDTO expected= new ErrorsDTO();
        List<ErrorDTO> errors = new ArrayList<>();
        ErrorDTO error = new ErrorDTO();
        error.setCode(ErrorKeys.INTERNAL_SERVER_ERROR.getCode());
        error.setMessage(ErrorKeys.INTERNAL_SERVER_ERROR.getMessage());
        errors.add(error);
        expected.setErrors(errors);
        //when
        ErrorsDTO actual= handler.handleInternalException(exception);
        //then
            assertEquals(actual, expected);
    }

    @Test
    void handleScalableWebApiExceptionTestForNotFound() {
        //given
        ErrorsDTO errorsDTO = new ErrorsDTO();
        ErrorDTO error = new ErrorDTO();
        error.setCode(ErrorKeys.ID_DOESNOT_EXISTS.getCode());
        error.setMessage(ErrorKeys.ID_DOESNOT_EXISTS.getMessage());
        errorsDTO.errors(Collections.singletonList(error));
        ScalableWebApiException exception= new ScalableWebApiException(errorsDTO, HttpStatus.NOT_FOUND);
        //when
        ResponseEntity<ErrorsDTO> responseEntity= handler.handleScalableWebApiException(exception);
        //then
        assertAll(
                ()->assertNotNull(responseEntity),
                ()->assertEquals(404,responseEntity.getStatusCode().value())
        );
    }
    @Test
    void handleScalableWebApiExceptionTestForBadRequest() {
        //given
        ErrorsDTO errorsDTO = new ErrorsDTO();
        ErrorDTO error = new ErrorDTO();
        error.setCode(ErrorKeys.LEFT_SIDE_DATA_DOESNOT_EXISTS.getCode());
        error.setMessage(ErrorKeys.LEFT_SIDE_DATA_DOESNOT_EXISTS.getMessage());
        errorsDTO.errors(Collections.singletonList(error));
        ScalableWebApiException exception= new ScalableWebApiException(errorsDTO, HttpStatus.BAD_REQUEST);
        //when
        ResponseEntity<ErrorsDTO> responseEntity= handler.handleScalableWebApiException(exception);
        //then
        assertAll(
                ()->assertNotNull(responseEntity),
                ()->assertEquals(400,responseEntity.getStatusCode().value())
        );
    }
}