package com.santander.bnc.bsn049.bncbsn049msprspctcntctptnt.domain.customer.contactpoint.response;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ContactPointResponseDtosTest {

    private static final List<Class<?>> DTO_CLASSES = List.of(
            PostalAddressDTO.class,
            ConsentDTO.class,
            LinksDTO.class,
            BestContactTimeDTO.class,
            RootDTO.class,
            RegionIdentificationDTO.class,
            CountryDTO.class,
            AuditDTO.class,
            StateDTO.class,
            ProvinceDTO.class,
            CountyIdentificationDTO.class,
            ValidityPeriodDTO.class,
            PhoneAddressDTO.class,
            FirstDTO.class,
            LastDTO.class,
            NextDTO.class,
            WebAddressDTO.class,
            PrevDTO.class,
            UseTypeDTO.class,
            ContactPointDTO.class,
            ContactPointsResponseDTO.class,
            ElectronicAddressDTO.class
    );

    @Test
    void shouldCoverAllDtoMethods() {
        DTO_CLASSES.forEach(dtoClass ->
                assertDoesNotThrow(() -> coverDto(dtoClass)));
    }

    private void coverDto(Class<?> dtoClass) throws Exception {

        Object dto = createInstance(dtoClass);

        assertNotNull(dto);

        for (Method method : dtoClass.getMethods()) {

            if (isSetter(method)) {

                Object value = getValue(
                        method.getParameterTypes()[0],
                        method.getGenericParameterTypes()[0]
                );

                method.invoke(dto, value);
            }
        }

        for (Method method : dtoClass.getMethods()) {

            if (isGetter(method)) {
                method.invoke(dto);
            }
        }

        dto.toString();
        dto.hashCode();
        dto.equals(dto);
        dto.equals(null);
        dto.equals(new Object());
    }

    private Object createInstance(Class<?> clazz) throws Exception {

        Constructor<?> constructor = clazz.getDeclaredConstructor();

        constructor.setAccessible(true);

        return constructor.newInstance();
    }

    private boolean isSetter(Method method) {

        return method.getName().startsWith("set")
                && method.getParameterCount() == 1
                && Void.TYPE.equals(method.getReturnType());
    }

    private boolean isGetter(Method method) {

        return method.getParameterCount() == 0
                && !Void.TYPE.equals(method.getReturnType())
                && (method.getName().startsWith("get")
                || method.getName().startsWith("is"))
                && !method.getName().equals("getClass");
    }

    private Object getValue(Class<?> type, Type genericType) throws Exception {

        if (String.class.equals(type)) {
            return "test";
        }

        if (Integer.class.equals(type) || int.class.equals(type)) {
            return 1;
        }

        if (Long.class.equals(type) || long.class.equals(type)) {
            return 1L;
        }

        if (Double.class.equals(type) || double.class.equals(type)) {
            return 1D;
        }

        if (Float.class.equals(type) || float.class.equals(type)) {
            return 1F;
        }

        if (Boolean.class.equals(type) || boolean.class.equals(type)) {
            return true;
        }

        if (BigDecimal.class.equals(type)) {
            return BigDecimal.ONE;
        }

        if (LocalDate.class.equals(type)) {
            return LocalDate.now();
        }

        if (LocalDateTime.class.equals(type)) {
            return LocalDateTime.now();
        }

        if (OffsetDateTime.class.equals(type)) {
            return OffsetDateTime.now();
        }

        if (Date.class.equals(type)) {
            return new Date();
        }

        if (List.class.equals(type)) {
            return getListValue(genericType);
        }

        if (Map.class.equals(type)) {
            return new HashMap<>();
        }

        if (type.isEnum()) {

            Object[] values = type.getEnumConstants();

            return values.length > 0
                    ? values[0]
                    : null;
        }

        if (DTO_CLASSES.contains(type)) {
            return createInstance(type);
        }

        if (!type.isPrimitive()) {
            return createInstance(type);
        }

        return null;
    }

    private Object getListValue(Type genericType) throws Exception {

        if (genericType instanceof ParameterizedType parameterizedType) {

            Type actualType =
                    parameterizedType.getActualTypeArguments()[0];

            if (actualType instanceof Class<?> actualClass) {

                return new ArrayList<>(
                        Collections.singletonList(
                                getValue(actualClass, actualClass)
                        )
                );
            }
        }

        return new ArrayList<>();
    }
}

