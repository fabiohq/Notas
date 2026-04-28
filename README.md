package com.santander.bnc.bsn049.bncbsn049mscountries.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscountries.domain.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.CountriesResponse;
import com.santander.bnc.bsn049.bncbsn049mscountries.entity.DataList;
import com.santander.bnc.bsn049.bncbsn049mscountries.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049mscountries.mappers.ParametersMappers;
import com.santander.bnc.bsn049.bncbsn049mscountries.repository.DataListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountriesServiceImplTest {

    private DataListRepository dataListRepository;
    private ParametersMappers mapper;
    private CountriesServiceImpl service;

    @BeforeEach
    void setUp() {
        dataListRepository = mock(DataListRepository.class);
        mapper = mock(ParametersMappers.class);
        service = new CountriesServiceImpl(dataListRepository, mapper);
    }

    @Test
    void shouldThrowBadRequestWhenListCodeIsEmpty() {
        ServiceExceptionClient ex = assertThrows(
                ServiceExceptionClient.class,
                () -> service.getDataList("", null)
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
        verifyNoInteractions(dataListRepository);
    }

    @Test
    void shouldThrowBadRequestWhenListCodeIsInvalid() {
        ServiceExceptionClient ex = assertThrows(
                ServiceExceptionClient.class,
                () -> service.getDataList("9999", null)
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
        verifyNoInteractions(dataListRepository);
    }

    @Test
    void shouldGetDataListByListCodeWhenValueCodeIsNull() {
        List<DataList> entities = List.of(mock(DataList.class));
        List<DataListDTO> dtos = List.of(new DataListDTO("0112", "COL", "Colombia"));

        when(dataListRepository.getByListCode(ParametersEnums.COUNTRY.value()))
                .thenReturn(entities);
        when(mapper.dataListEntityToDTOArrays(entities))
                .thenReturn(dtos);

        List<DataListDTO> result = service.getDataList(ParametersEnums.COUNTRY.value(), null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CO", result.get(0).getCode());

        verify(dataListRepository).getByListCode(ParametersEnums.COUNTRY.value());
        verify(dataListRepository, never()).getByListCodeAndValueCode(any(), any());
    }

    @Test
    void shouldGetDataListByListCodeWhenValueCodeIsEmpty() {
        List<DataList> entities = List.of(mock(DataList.class));
        List<DataListDTO> dtos = List.of(new DataListDTO("0009", "05", "Antioquia"));

        when(dataListRepository.getByListCode(ParametersEnums.STATES.value()))
                .thenReturn(entities);
        when(mapper.dataListEntityToDTOArrays(entities))
                .thenReturn(dtos);

        List<DataListDTO> result = service.getDataList(ParametersEnums.STATES.value(), "");

        assertNotNull(result);
        assertEquals("05", result.get(0).getCode());

        verify(dataListRepository).getByListCode(ParametersEnums.STATES.value());
        verify(dataListRepository, never()).getByListCodeAndValueCode(any(), any());
    }

    @Test
    void shouldGetDataListByListCodeAndValueCodeWhenValueCodeHasValue() {
        List<DataList> entities = List.of(mock(DataList.class));
        List<DataListDTO> dtos = List.of(new DataListDTO("0009", "05", "Antioquia"));

        when(dataListRepository.getByListCodeAndValueCode(ParametersEnums.STATES.value(), "05"))
                .thenReturn(entities);
        when(mapper.dataListEntityToDTOArrays(entities))
                .thenReturn(dtos);

        List<DataListDTO> result = service.getDataList(ParametersEnums.STATES.value(), "05");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("05", result.get(0).getCode());

        verify(dataListRepository).getByListCodeAndValueCode(ParametersEnums.STATES.value(), "05");
        verify(dataListRepository, never()).getByListCode(any());
    }

    @Test
    void shouldReturnCountriesOkFilteringExtAndMoe() {
        CountriesServiceImpl spyService = spy(service);

        doReturn(List.of(
                new DataListDTO("0112", "COL", "Colombia"),
                new DataListDTO("0112", "EXT", "Exterior"),
                new DataListDTO("0112", "MOE", "Moneda extranjera")
        )).when(spyService).getDataList(ParametersEnums.COUNTRY.value(), null);

        ResponseEntity<CountriesResponse> response = spyService.getCountries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCountries().size());
        assertEquals("COL", response.getBody().getCountries().get(0).getCode());
    }

    @Test
    void shouldReturnNoContentWhenCountriesDataListIsNull() {
        CountriesServiceImpl spyService = spy(service);

        doReturn(null).when(spyService).getDataList(ParametersEnums.COUNTRY.value(), null);

        ResponseEntity<CountriesResponse> response = spyService.getCountries();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}