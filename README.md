package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.ConsentStatusInfo;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.PartiesConsentsRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.service.DataConsentManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataConsentManagementControllerTest {

    @Mock
    private DataConsentManagementService dataConsentManagementService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private DataConsentManagementController controller;

    @Test
    void getPartiesConsentShouldReturnCreated() throws Exception {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("S"))
                .build();

        when(objectMapper.writeValueAsString(request))
                .thenReturn("{\"statusInfo\":{\"statusCode\":\"S\"}}");

        var response = controller.getPartiesConsent(
                "Bearer token",
                "client-id",
                "12345678",
                request
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        verify(dataConsentManagementService)
                .getPartiesConsent("12345678", request);

        verify(objectMapper)
                .writeValueAsString(request);
    }

    @Test
    void getPartiesConsentShouldReturnCreatedWhenObjectMapperThrowsException() throws Exception {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("N"))
                .build();

        when(objectMapper.writeValueAsString(request))
                .thenThrow(new JsonProcessingException("error") {});

        var response = controller.getPartiesConsent(
                "Bearer token",
                "client-id",
                "12345678",
                request
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        verify(dataConsentManagementService)
                .getPartiesConsent("12345678", request);

        verify(objectMapper)
                .writeValueAsString(request);
    }

    @Test
    void getPartiesConsentShouldPropagateServiceException() {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("S"))
                .build();

        doThrow(new RuntimeException("service error"))
                .when(dataConsentManagementService)
                .getPartiesConsent("12345678", request);

        org.assertj.core.api.Assertions.assertThatThrownBy(() ->
                controller.getPartiesConsent(
                        "Bearer token",
                        "client-id",
                        "12345678",
                        request
                )
        ).isInstanceOf(RuntimeException.class);

        verify(dataConsentManagementService)
                .getPartiesConsent("12345678", request);
    }
}