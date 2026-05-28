package com.santander.bnc.bsn049.bncbsn049msprspctcntctptnt.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ServiceExceptionTest {

    @Test
    void shouldCreateServiceExceptionWithMessage() {
        ServiceException exception = new ServiceException("Error test");

        assertNotNull(exception);
        assertEquals("Error test", exception.getMessage());
    }

    @Test
    void shouldCreateServiceExceptionWithMessageAndCause() {
        Throwable cause = new RuntimeException("Cause test");

        ServiceException exception = new ServiceException("Error test", cause);

        assertNotNull(exception);
        assertEquals("Error test", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctptnt.exception;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class GlobalExceptionHandlerTest {

    @Test
    void shouldCreateGlobalExceptionHandler() {
        assertDoesNotThrow(GlobalExceptionHandler::new);
    }
}

