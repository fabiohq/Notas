

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor; import java.lang.reflect.InvocationTargetException; import java.time.LocalDate; import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

@Test
void shouldReturnEmptyWhenBlankFieldReceivesNull() {
    String response = StringUtils.blankField(null);

    assertEquals("", response);
}

@Test
void shouldReturnSameValueWhenBlankFieldReceivesText() {
    String response = StringUtils.blankField("value");

    assertEquals("value", response);
}

@Test
void shouldReturnSameEmptyValueWhenBlankFieldReceivesEmptyText() {
    String response = StringUtils.blankField("");

    assertEquals("", response);
}

@Test
void shouldCompleteWithSpacesAndConcatenateVariable() {
    String response = StringUtils.completarYConcatenar("ABC", "1234567890", 5);

    assertEquals("ABC  1234567890", response);
}

@Test
void shouldTruncateAndConcatenateVariableWhenTextIsLongerThanFinalLength() {
    String response = StringUtils.completarYConcatenar("ABCDEFGHIJ", "1234567890", 5);

    assertEquals("ABCDE1234567890", response);
}

@Test
void shouldConcatenateVariableWhenTextHasExactFinalLength() {
    String response = StringUtils.completarYConcatenar("ABCDE", "1234567890", 5);

    assertEquals("ABCDE1234567890", response);
}

@Test
void shouldNotAllowStringUtilsInstantiation() throws Exception {
    Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    StringUtils instance = constructor.newInstance();

    assertNotNull(instance);
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
    String response = TimeUtils.formatDate("2026-05-28");

    assertEquals("2026-05-28", response);
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
void shouldNotAllowTimeUtilsInstantiation() throws Exception {
    Constructor<TimeUtils> constructor = TimeUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    TimeUtils instance = constructor.newInstance();

    assertNotNull(instance);
}

}
