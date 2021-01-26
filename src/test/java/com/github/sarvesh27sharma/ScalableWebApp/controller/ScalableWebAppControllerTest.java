package com.github.sarvesh27sharma.ScalableWebApp.controller;

import com.github.sarvesh27sharma.scalablewebapp.controller.ScalableWebAppController;
import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.scalablewebapp.service.ScalableWebAppService;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;
import com.github.sarvesh27sharma.v1.model.InputDataDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
@ExtendWith(MockitoExtension.class)
class ScalableWebAppControllerTest {

    private ScalableWebAppController scalableWebAppController;
    @Mock
    private ScalableWebAppService scalableWebAppService;
    @BeforeEach
    void setUp() {
        scalableWebAppController= new ScalableWebAppController(scalableWebAppService);
    }

    @Test
    void leftTest() throws ScalableWebApiException {
        //given
        long id =1l;
        InputDataDTO data= new InputDataDTO();
        data.setData("hello");
        //when
        Mockito
                .when(scalableWebAppService.saveLeftSideData(any(DifferedDTO.class)))
                .thenReturn(true);
        ResponseEntity<String> response= scalableWebAppController.left(1l,data);
        //then
        assertAll(
                ()->assertEquals(200,response.getStatusCode().value()),
                ()->assertEquals("data to be diff-ed submitted successfully",response.getBody())
        );
    }

    @Test
    void rightTest() throws ScalableWebApiException {
        //given
        long id =1l;
        InputDataDTO data= new InputDataDTO();
        data.setData("hello");
        //when
        Mockito
                .when(scalableWebAppService.saveRightSideData(any(DifferedDTO.class)))
                .thenReturn(true);
        ResponseEntity<String> response= scalableWebAppController.right(1l,data);
        //then
        assertAll(
                ()->assertEquals(200,response.getStatusCode().value()),
                ()->assertEquals("data to be diff-ed submitted successfully",response.getBody())
        );
    }

    @Test
    void getDiffByIdTest() throws ScalableWebApiException {
        //given
        long id=1l;
        ApiResponseDTO apiResponseDTO= new ApiResponseDTO();
        //when
        Mockito
                .when(scalableWebAppService.getById(anyLong()))
                .thenReturn(apiResponseDTO);

        ResponseEntity<ApiResponseDTO> apiResponseDTOResponseEntity=scalableWebAppController.getDiffById(1l);
        //then
        assertAll(
                ()->assertNotNull(apiResponseDTOResponseEntity),
                ()->assertEquals(200,apiResponseDTOResponseEntity.getStatusCode().value())
        );
    }
}