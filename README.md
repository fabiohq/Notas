
package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;

class RegexUtilsTest {

    @Test
    void shouldValidateRegexOk() {
        RegexUtils utils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "^[0-9]+$");

        utils.setType(type);

        assertDoesNotThrow(() ->
                utils.validateRegex("only_numbers", "12345", "field"));
    }

    @Test
    void shouldThrowWhenRegexDoesNotMatch() {
        RegexUtils utils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "^[0-9]+$");
        type.put("only_numbers_error", "Only numbers allowed");

        utils.setType(type);

        ReflectionTestUtils.setField(utils, "MS_NAME", "MS");
        ReflectionTestUtils.setField(utils, "MS_VERSION", "v1");
        ReflectionTestUtils.setField(utils, "LEVEL", "ERROR");
        ReflectionTestUtils.setField(utils, "CODE", "400");

        assertThrows(ServiceException.class,
                () -> utils.validateRegex("only_numbers", "ABC", "field"));
    }

    @Test
    void shouldCoverLombokAccessors() {
        RegexUtils utils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("key", "value");

        utils.setType(type);

        assertSame(type, utils.getType());
        assertNotNull(utils.toString());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;

class TermDepositUtilsTest {

    @Test
    void shouldCreateTermDepositUtils() {
        RegexUtils regexUtils = mock(RegexUtils.class);
        ErrorService errorService = mock(ErrorService.class);
        ProductDirectoryService productDirectoryService = mock(ProductDirectoryService.class);
        TermDepositParametersService termDepositParametersService = mock(TermDepositParametersService.class);
        BanksService banksService = mock(BanksService.class);

        TermDepositUtils utils = new TermDepositUtils(
                regexUtils,
                errorService,
                productDirectoryService,
                termDepositParametersService,
                banksService
        );

        assertNotNull(utils);
    }
}
