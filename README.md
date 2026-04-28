package com.santander.bnc.bsn049.bncbsn049mscountries.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscountries.domain.CountryDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.CountriesResponse;
import com.santander.bnc.bsn049.bncbsn049mscountries.entity.DataList;
import com.santander.bnc.bsn049.bncbsn049mscountries.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscountries.mappers.ParametersMappers;
import com.santander.bnc.bsn049.bncbsn049mscountries.repository.DataListRepository;

import com.santander.bnc.bsn049.bncbsn049mscountries.service.CountriesService;
import com.santander.bnc.bsn049.bncbsn049mscountries.utils.DataUtils;
import com.santander.bnc.bsn049.bncbsn049mscountries.utils.GUtils;
import lombok.extern.java.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {


   final  DataListRepository dataListRepository;


   final  ParametersMappers mapper;

    /**
     * This method gets all DataList registers by code.
     *
     * @param listCode
     * @return List<DataListDTO>
     */
    @Override
    public List<DataListDTO> getDataList(String listCode, String valueCode) {
        log.info(GUtils.SLOG + "service getDataList for list_code {} and valueCode {}", listCode, valueCode);
        List<DataList> response;
       
        if(listCode.isEmpty()){
            throw new ServiceExceptionClient(HttpStatus.BAD_REQUEST, ErrorCatalog.getState0006());
        }

        if(Boolean.FALSE.equals(ParametersEnums.isValidEnumValue(listCode))){
            throw new ServiceExceptionClient(HttpStatus.BAD_REQUEST, ErrorCatalog.getState0006());
        }
    

        if(valueCode != null && !valueCode.isEmpty()){
            response  = dataListRepository.getByListCodeAndValueCode(listCode,valueCode);
        }else{
            response  = dataListRepository.getByListCode(listCode);
        }
        List<DataListDTO> dto = mapper.dataListEntityToDTOArrays(response);
        dto = dto.stream()
                .map(y -> {
                    y.setCode(DataUtils.translateValueCode(y.getListCode(), y.getCode()));
                    return y;
                }).collect(Collectors.toList());
        log.info(GUtils.ELOG + "service getDataList.");       
        return dto;
    }//method closure

    
    public ResponseEntity<CountriesResponse> getCountries(){

        List<DataListDTO> response = getDataList(ParametersEnums.COUNTRY.value(),null);
        List<CountryDTO> countries = new ArrayList<>();    
        if(response == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        response.forEach(_country -> {
            if (!_country.getCode().equals("EXT") && !_country.getCode().equals("MOE")) {
                countries.add(new CountryDTO(_country.getCode(), _country.getDescription(),DataUtils.getInternalByCountryCode(_country.getCode()) , DataUtils.getIsoAlphaCodeByCountryCode(_country.getCode())));
            }
        });

        return ResponseEntity.status(HttpStatus.OK).body(new CountriesResponse(countries));

    }




}//class closure
