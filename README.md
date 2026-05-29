package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class GenericDtosTest {

    private static final List<Class<?>> DTO_CLASSES = List.of(
            PostalAddressDTO.class,
            Statistics.class,
            DocumentDTO.class,
            BankDTO.class,
            Parameters.class,
            CodeNameDTO.class
    );

    @Test
    void shouldCoverAllGenericDtos() {
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

        if (List.class.equals(type)) {
            return getListValue(genericType);
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
            Type actualType = parameterizedType.getActualTypeArguments()[0];

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
