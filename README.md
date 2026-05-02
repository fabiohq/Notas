package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request;

import org.junit.jupiter.api.Test;
import java.lang.reflect.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class BasicDataTest {

    @Test
    void shouldCoverFullClass() throws Exception {
        BasicData dto = new BasicData();
        assertBean(dto);
        assertAllArgs(BasicData.class);
        assertBuilder(BasicData.class);
    }

    private static void assertBean(Object bean) throws Exception {
        for (Method setter : bean.getClass().getMethods()) {
            if (setter.getName().startsWith("set") && setter.getParameterCount() == 1) {
                Object value = valueFor(setter.getParameterTypes()[0]);
                setter.invoke(bean, value);

                String prop = setter.getName().substring(3);
                Method getter = findGetter(bean.getClass(), prop);
                assertNotNull(getter);
                assertEquals(value, getter.invoke(bean));
            }
        }
    }

    private static Method findGetter(Class<?> clazz, String prop) {
        for (String name : List.of("get" + prop, "is" + prop)) {
            try {
                return clazz.getMethod(name);
            } catch (NoSuchMethodException ignored) {}
        }
        return null;
    }

    private static void assertAllArgs(Class<?> clazz) throws Exception {
        Constructor<?> constructor = Arrays.stream(clazz.getDeclaredConstructors())
                .max(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow();

        Object[] args = Arrays.stream(constructor.getParameterTypes())
                .map(BasicDataTest::valueFor)
                .toArray();

        assertNotNull(constructor.newInstance(args));
    }

    private static void assertBuilder(Class<?> clazz) throws Exception {
        Object builder = clazz.getMethod("builder").invoke(null);

        for (Method method : builder.getClass().getMethods()) {
            if (method.getParameterCount() == 1 && method.getReturnType().isAssignableFrom(builder.getClass())) {
                method.invoke(builder, valueFor(method.getParameterTypes()[0]));
            }
        }

        Object dto = builder.getClass().getMethod("build").invoke(builder);
        assertNotNull(dto);
    }

    private static Object valueFor(Class<?> type) {
        if (type.equals(String.class)) return "value";
        if (type.equals(int.class) || type.equals(Integer.class)) return 1;
        if (type.equals(boolean.class) || type.equals(Boolean.class)) return true;
        if (List.class.isAssignableFrom(type)) return new ArrayList<>();
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}