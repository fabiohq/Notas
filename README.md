package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor; import java.time.LocalDate; import java.time.format.DateTimeFormatter; import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GUtilsTest {

@Test
void shouldReturnNullPropertyNames() {
    TestBean bean = new TestBean();
    bean.setName("Fabio");
    bean.setAge(null);
    bean.setEmail(null);

    String[] response = GUtils.getNullPropertyNames(bean);

    assertTrue(Arrays.asList(response).contains("age"));
    assertTrue(Arrays.asList(response).contains("email"));
    assertFalse(Arrays.asList(response).contains("name"));
}

@Test
void shouldExposeLogConstants() {
    assertEquals("--> Start ", GUtils.SLOG);
    assertEquals("<-- End ", GUtils.ELOG);
}

@Test
void shouldInstantiatePrivateConstructorByReflection() throws Exception {
    Constructor<GUtils> constructor = GUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    assertNotNull(constructor.newInstance());
}

static class TestBean {
    private String name;
    private Integer age;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

}

class StringUtilsTest {

@Test
void shouldReturnEmptyWhenBlankFieldReceivesNull() {
    assertEquals("", StringUtils.blankField(null));
}

@Test
void shouldReturnSameValueWhenBlankFieldReceivesText() {
    assertEquals("value", StringUtils.blankField("value"));
}

@Test
void shouldReturnSameEmptyValueWhenBlankFieldReceivesEmptyText() {
    assertEquals("", StringUtils.blankField(""));
}

@Test
void shouldCompleteWithSpacesAndConcatenateVariable() {
    assertEquals("ABC  1234567890", StringUtils.completarYConcatenar("ABC", "1234567890", 5));
}

@Test
void shouldTruncateAndConcatenateVariableWhenTextIsLongerThanFinalLength() {
    assertEquals("ABCDE1234567890", StringUtils.completarYConcatenar("ABCDEFGHIJ", "1234567890", 5));
}

@Test
void shouldConcatenateVariableWhenTextHasExactFinalLength() {
    assertEquals("ABCDE1234567890", StringUtils.completarYConcatenar("ABCDE", "1234567890", 5));
}

@Test
void shouldInstantiatePrivateConstructorByReflection() throws Exception {
    Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    assertNotNull(constructor.newInstance());
}

}

class TimeUtilsTest {

@Test
void shouldReturnCurrentDateTimeWithProvidedFormat() {
    String response = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");

    assertNotNull(response);
    assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), response);
}

@Test
void shouldReturnCurrentDateTimeWithCustomFormat() {
    String response = TimeUtils.getSlocalDateTimeByFormat("yyyyMMdd");

    assertNotNull(response);
    assertEquals(8, response.length());
    assertTrue(response.matches("\\d{8}"));
}

@Test
void shouldFormatDateUsingDefaultFormat() {
    assertEquals("2026-05-28", TimeUtils.formatDate("2026-05-28"));
}

@Test
void shouldThrowExceptionWhenFormatDateReceivesInvalidDate() {
    assertThrows(Exception.class, () -> TimeUtils.formatDate("28-05-2026"));
}

@Test
void shouldReturnMarkTimeWithExpectedPattern() {
    String response = TimeUtils.markTime();

    assertNotNull(response);
    assertTrue(response.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}:\\d{3}"));
}

@Test
void shouldInstantiatePrivateConstructorByReflection() throws Exception {
    Constructor<TimeUtils> constructor = TimeUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    assertNotNull(constructor.newInstance());
}

}


