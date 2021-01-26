package com.github.sarvesh27sharma.ScalableWebApp.service.impl;

import com.github.sarvesh27sharma.scalablewebapp.dto.DifferedDTO;
import com.github.sarvesh27sharma.scalablewebapp.entity.DifferedEntity;
import com.github.sarvesh27sharma.scalablewebapp.exception.ScalableWebApiException;
import com.github.sarvesh27sharma.scalablewebapp.mapper.ScalableWebAppMapper;
import com.github.sarvesh27sharma.scalablewebapp.repository.DifferedRepository;
import com.github.sarvesh27sharma.scalablewebapp.service.ScalableWebAppService;
import com.github.sarvesh27sharma.scalablewebapp.service.impl.ScalableWebAppServiceImpl;
import com.github.sarvesh27sharma.v1.model.ApiResponseDTO;
import com.github.sarvesh27sharma.v1.model.DifferenceDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
@ExtendWith(MockitoExtension.class)
class ScalableWebAppServiceImplTest {

    private ScalableWebAppService service;
    @Mock
    private ScalableWebAppMapper mapper;
    @Mock
    private DifferedRepository repository;
    @BeforeEach
    void setUp() {
        service= new ScalableWebAppServiceImpl(mapper,repository);
    }

    @Test
    void saveLeftSideDataTest(){
        //given
        DifferedDTO dto= new DifferedDTO("hello",null,1);
        DifferedEntity entity=new DifferedEntity();
        //when
        Mockito
                .when(repository.findById(anyLong()))
                .thenReturn(Optional.of(entity));
        Mockito
                .when(repository.save(any()))
                .thenReturn(entity);
        boolean actual=  service.saveLeftSideData(dto);
        //then
        assertTrue(actual);
    }

    @Test
    void saveRightSideDataTest(){
        //given
        DifferedDTO dto= new DifferedDTO(null,"hello",1);
        DifferedEntity entity=new DifferedEntity();
        //when
        Mockito
                .when(repository.findById(anyLong()))
                .thenReturn(Optional.of(entity));
        Mockito
                .when(repository.save(any()))
                .thenReturn(entity);
        boolean actual=  service.saveRightSideData(dto);
        //then
        assertTrue(actual);
    }

    @Test
    void getByIdTestForEqual() throws ScalableWebApiException {
        //given
        ApiResponseDTO expected = new ApiResponseDTO();
        expected.setStatus(ApiResponseDTO.StatusEnum.EQUAL);
        DifferedEntity entity=new DifferedEntity("hello","hello",1);
        DifferedDTO dto= new DifferedDTO("hello","hello",1);
        //when
        Mockito
                .when(repository.findById(anyLong()))
                .thenReturn(Optional.of(entity));
        Mockito
                .when(mapper.mapDifferedEntityToDTO(any(DifferedEntity.class)))
                .thenReturn(dto);

        ApiResponseDTO actual = service.getById(anyLong());
        //then
        assertEquals(actual,expected);
    }
    @Test
    void getByIdTestForDifferentSize() throws ScalableWebApiException {
        //given
        ApiResponseDTO expected = new ApiResponseDTO();
        expected.setStatus(ApiResponseDTO.StatusEnum.DIFFERENT_SIZE);
        DifferedEntity entity=new DifferedEntity("hello world","hello",1);
        DifferedDTO dto= new DifferedDTO("hello world","hello",1);
        //when
        Mockito
                .when(repository.findById(anyLong()))
                .thenReturn(Optional.of(entity));
        Mockito
                .when(mapper.mapDifferedEntityToDTO(any(DifferedEntity.class)))
                .thenReturn(dto);

        ApiResponseDTO actual = service.getById(anyLong());
        //then
        assertEquals(actual,expected);
    }

    @Test
    void getByIdTestForDifferent() throws ScalableWebApiException {
        //given
        ApiResponseDTO expected = new ApiResponseDTO();
        DifferenceDTO differenceDTO= new DifferenceDTO();
        differenceDTO.setFinalOffset(3l);
        differenceDTO.setInitialOffset(2l);
        differenceDTO.setLength(1l);
        expected.setStatus(ApiResponseDTO.StatusEnum.DIFFERENT);
        expected.setDifferences(Collections.singletonList(differenceDTO));
        DifferedEntity entity=new DifferedEntity("heklo","hello",1);
        DifferedDTO dto= new DifferedDTO("heklo","hello",1);
        //when
        Mockito
                .when(repository.findById(anyLong()))
                .thenReturn(Optional.of(entity));
        Mockito
                .when(mapper.mapDifferedEntityToDTO(any(DifferedEntity.class)))
                .thenReturn(dto);

        ApiResponseDTO actual = service.getById(anyLong());
        //then
        assertEquals(actual,expected);
    }

    @AfterEach
    void tearDown() {
    }
}