package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config.ParamsConfig;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.config.StandardType;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response.TrxBP17DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.TermDepositParameterResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TermDepositParametersMapperTest {

    private ParamsConfig paramsConfig;
    private TermDepositParametersMapper mapper;

    @BeforeEach
    void setUp() {
        paramsConfig = mock(ParamsConfig.class);
        mapper = new TermDepositParametersMapper(paramsConfig);
    }

    @Test
    @DisplayName("Debe construir request BP17 con datos de entrada")
    void getBP17RequestFromInputDataShouldBuildRequest() {
        when(paramsConfig.getServiceRouteBp17()).thenReturn("simulacionCDTBP17S171");
        when(paramsConfig.getDefaultProductId()).thenReturn("010001");
        when(paramsConfig.getDefaultCurrency()).thenReturn("COP");
        when(paramsConfig.getDefaultPeriodType()).thenReturn("V");
        when(paramsConfig.getDefaultPeriodicity()).thenReturn("90");
        when(paramsConfig.getDefaultAmount()).thenReturn("100000");

        TrxBP17Request request =
                mapper.getBP17RequestFromInputData("010001", "500000", "180");

        assertNotNull(request);
        assertNotNull(request.getCabecera());
        assertNotNull(request.getData());

        assertEquals("01", request.getData().getProducto());
        assertEquals("0001", request.getData().getSubProducto());
        assertEquals("COP", request.getData().getMoneda());
        assertEquals("V", request.getData().getPeriodoLiquidacion());
        assertEquals("180", request.getData().getPlazo());
        assertEquals("500000", request.getData().getValor());
        assertEquals("", request.getData().getPuntosAdicionales());
        assertEquals("", request.getData().getTarifa());
    }

    @Test
    @DisplayName("Debe reemplazar coma por punto en amount")
    void getBP17RequestFromInputDataShouldReplaceCommaInAmount() {
        when(paramsConfig.getServiceRouteBp17()).thenReturn("simulacionCDTBP17S171");
        when(paramsConfig.getDefaultProductId()).thenReturn("010001");
        when(paramsConfig.getDefaultCurrency()).thenReturn("COP");
        when(paramsConfig.getDefaultPeriodType()).thenReturn("V");
        when(paramsConfig.getDefaultPeriodicity()).thenReturn("90");
        when(paramsConfig.getDefaultAmount()).thenReturn("100000");

        TrxBP17Request request =
                mapper.getBP17RequestFromInputData("010001", "500000,25", "180");

        assertEquals("500000.25", request.getData().getValor());
    }

    @Test
    @DisplayName("Debe usar amount por defecto cuando amount es null")
    void getBP17RequestFromInputDataShouldUseDefaultAmountWhenAmountIsNull() {
        when(paramsConfig.getServiceRouteBp17()).thenReturn("simulacionCDTBP17S171");
        when(paramsConfig.getDefaultProductId()).thenReturn("010001");
        when(paramsConfig.getDefaultCurrency()).thenReturn("COP");
        when(paramsConfig.getDefaultPeriodType()).thenReturn("V");
        when(paramsConfig.getDefaultPeriodicity()).thenReturn("90");
        when(paramsConfig.getDefaultAmount()).thenReturn("999999");

        TrxBP17Request request =
                mapper.getBP17RequestFromInputData("010001", null, "180");

        assertEquals("999999", request.getData().getValor());
    }

    @Test
    @DisplayName("Debe usar periodicity por defecto cuando periodicity es null")
    void getBP17RequestFromInputDataShouldUseDefaultPeriodicityWhenPeriodicityIsNull() {
        when(paramsConfig.getServiceRouteBp17()).thenReturn("simulacionCDTBP17S171");
        when(paramsConfig.getDefaultProductId()).thenReturn("010001");
        when(paramsConfig.getDefaultCurrency()).thenReturn("COP");
        when(paramsConfig.getDefaultPeriodType()).thenReturn("V");
        when(paramsConfig.getDefaultPeriodicity()).thenReturn("360");
        when(paramsConfig.getDefaultAmount()).thenReturn("100000");

        TrxBP17Request request =
                mapper.getBP17RequestFromInputData("010001", "500000", null);

        assertEquals("360", request.getData().getPlazo());
    }

    @Test
    @DisplayName("Debe mapear respuesta BP17 a parámetros")
    void getParametersResponseFromBP17ShouldMapPeriods() {
        TrxBP17DataResponse data = new TrxBP17DataResponse();
        data.setPeriodosDisponibles("VC");

        TrxBP17Response bp17Response = new TrxBP17Response();
        bp17Response.setData(data);

        StandardType vencimiento = StandardType.builder()
                .code("V")
                .description("Vencimiento")
                .content("Pago Intereses al Vencimiento")
                .build();

        StandardType capitalizable = StandardType.builder()
                .code("C")
                .description("Capitalizable")
                .content("Capitalización e Intereses")
                .build();

        when(paramsConfig.getPeriodicity())
                .thenReturn(List.of(vencimiento, capitalizable));

        TermDepositParameterResponse response =
                mapper.getParametersResponseFromBP17(bp17Response);

        assertNotNull(response);
        assertNotNull(response.getParameters());
        assertEquals(2, response.getParameters().size());

        assertEquals("V", response.getParameters().get(0).getCode());
        assertEquals("Vencimiento", response.getParameters().get(0).getDescription());
        assertEquals("Pago Intereses al Vencimiento", response.getParameters().get(0).getContent());

        assertEquals("C", response.getParameters().get(1).getCode());
        assertEquals("Capitalizable", response.getParameters().get(1).getDescription());
        assertEquals("Capitalización e Intereses", response.getParameters().get(1).getContent());
    }

    @Test
    @DisplayName("Debe retornar lista hardcodeada de parámetros")
    void getListParametersHardCodeShouldReturnHardcodedList() {
        TermDepositParameterResponse response = mapper.getListParametersHardCode();

        assertNotNull(response);
        assertNotNull(response.getParameters());
        assertEquals(2, response.getParameters().size());

        assertEquals("V", response.getParameters().get(0).getCode());
        assertEquals("Vencimiento", response.getParameters().get(0).getContent());
        assertEquals("Pago Intereses al Vencimiento", response.getParameters().get(0).getDescription());

        assertEquals("C", response.getParameters().get(1).getCode());
        assertEquals("Capitalizable", response.getParameters().get(1).getContent());
        assertEquals("Capitalización e Intereses", response.getParameters().get(1).getDescription());
    }

    @Test
    @DisplayName("Debe retornar propuestas desde configuración")
    void getProposalsResponseShouldReturnConfiguredProposals() {
        StandardType proposalOne = StandardType.builder()
                .code("01")
                .description("Propuesta Uno")
                .content("Contenido Uno")
                .build();

        StandardType proposalTwo = StandardType.builder()
                .code("02")
                .description("Propuesta Dos")
                .content("Contenido Dos")
                .build();

        when(paramsConfig.getProposal()).thenReturn(List.of(proposalOne, proposalTwo));

        TermDepositParameterResponse response = mapper.getProposalsResponse();

        assertNotNull(response);
        assertNotNull(response.getParameters());
        assertEquals(2, response.getParameters().size());

        assertEquals("01", response.getParameters().get(0).getCode());
        assertEquals("Propuesta Uno", response.getParameters().get(0).getDescription());
        assertEquals("Propuesta Uno", response.getParameters().get(0).getContent());

        assertEquals("02", response.getParameters().get(1).getCode());
        assertEquals("Propuesta Dos", response.getParameters().get(1).getDescription());
        assertEquals("Propuesta Dos", response.getParameters().get(1).getContent());
    }

    @Test
    @DisplayName("Debe retornar descripción de periodo por código existente")
    void getPeriodDescriptionByCodeShouldReturnDescriptionWhenCodeExists() {
        StandardType period = StandardType.builder()
                .code("V")
                .description("Vencimiento")
                .content("Contenido")
                .build();

        when(paramsConfig.getPeriodicity()).thenReturn(List.of(period));

        String description = mapper.getPeriodDescriptionByCode("V");

        assertEquals("Vencimiento", description);
    }

    @Test
    @DisplayName("Debe retornar vacío cuando no existe código de periodo")
    void getPeriodDescriptionByCodeShouldReturnEmptyWhenCodeDoesNotExist() {
        StandardType period = StandardType.builder()
                .code("C")
                .description("Capitalizable")
                .content("Contenido")
                .build();

        when(paramsConfig.getPeriodicity()).thenReturn(List.of(period));

        String description = mapper.getPeriodDescriptionByCode("V");

        assertEquals("", description);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando BP17 retorna código no configurado")
    void getParametersResponseFromBP17ShouldThrowWhenCodeIsNotConfigured() {
        TrxBP17DataResponse data = new TrxBP17DataResponse();
        data.setPeriodosDisponibles("X");

        TrxBP17Response bp17Response = new TrxBP17Response();
        bp17Response.setData(data);

        StandardType period = StandardType.builder()
                .code("V")
                .description("Vencimiento")
                .content("Contenido")
                .build();

        when(paramsConfig.getPeriodicity()).thenReturn(List.of(period));

        assertThrows(
                java.util.NoSuchElementException.class,
                () -> mapper.getParametersResponseFromBP17(bp17Response)
        );
    }
}
