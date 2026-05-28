
package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDirectoryTest {

@Test
void shouldExposeCustomersEndpoint() {
    assertEquals("/v1/prospect_contact_points", ServiceDirectory.CUSTOMERS);
}

@Test
void shouldExposeCustomersSearchEndpoint() {
    assertEquals("/v1/prospect_contact_points/search", ServiceDirectory.CUSTOMERS_SEARCH);
}

@Test
void shouldInstantiatePrivateConstructorByReflection() throws Exception {
    Constructor<ServiceDirectory> constructor = ServiceDirectory.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    ServiceDirectory instance = constructor.newInstance();

    assertNotNull(instance);
}

}
