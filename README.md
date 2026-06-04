package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DomainDtoTest {

    private static final String BASE_PACKAGE =
            "com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain";

    @Test
    void shouldCoverDomainDtosGettersSettersBuildersAndConstructors() throws Exception {
        for (Class<?> clazz : findClasses(BASE_PACKAGE)) {
            if (!isTestable(clazz)) {
                continue;
            }

            testNoArgsConstructorAndAccessors(clazz);
            testBuilder(clazz);
            testAllArgsConstructors(clazz);
        }
    }

    private void testNoArgsConstructorAndAccessors(Class<?> clazz) throws Exception {
        Constructor<?> constructor;
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException ex) {
            return;
        }

        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        assertNotNull(instance);

        for (Method setter : clazz.getMethods()) {
            if (!isSetter(setter)) {
                continue;
            }

            Object value = sampleValue(setter.getParameterTypes()[0]);
            setter.invoke(instance, value);

            Method getter = findGetter(clazz, setter);
            if (getter != null) {
                Object result = getter.invoke(instance);
                assertEquals(value, result);
            }
        }
    }

    private void testBuilder(Class<?> clazz) throws Exception {
        Method builderMethod;
        try {
            builderMethod = clazz.getDeclaredMethod("builder");
        } catch (NoSuchMethodException ex) {
            return;
        }

        Object builder = builderMethod.invoke(null);
        assertNotNull(builder);

        for (Method method : builder.getClass().getDeclaredMethods()) {
            if (method.getParameterCount() == 1 && !method.getName().equals("build")) {
                method.setAccessible(true);
                method.invoke(builder, sampleValue(method.getParameterTypes()[0]));
            }
        }

        Method build = builder.getClass().getDeclaredMethod("build");
        build.setAccessible(true);

        Object built = build.invoke(builder);
        assertNotNull(built);
        assertEquals(clazz, built.getClass());
    }

    private void testAllArgsConstructors(Class<?> clazz) throws Exception {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0) {
                continue;
            }

            if (hasUnsupportedParameter(constructor)) {
                continue;
            }

            constructor.setAccessible(true);

            Object[] args = Arrays.stream(constructor.getParameterTypes())
                    .map(DomainDtoTest::sampleValue)
                    .toArray();

            Object instance = constructor.newInstance(args);
            assertNotNull(instance);
        }
    }

    private static boolean hasUnsupportedParameter(Constructor<?> constructor) {
        return Arrays.stream(constructor.getParameterTypes())
                .anyMatch(type -> type.getName().contains("TrxPersonHeader"));
    }

    private static boolean isTestable(Class<?> clazz) {
        int modifiers = clazz.getModifiers();
        return !clazz.isInterface()
                && !clazz.isEnum()
                && !Modifier.isAbstract(modifiers)
                && clazz.getName().startsWith(BASE_PACKAGE)
                && !clazz.getSimpleName().contains("$");
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set")
                && method.getParameterCount() == 1
                && method.getReturnType().equals(void.class);
    }

    private static Method findGetter(Class<?> clazz, Method setter) {
        String fieldName = setter.getName().substring(3);

        try {
            return clazz.getMethod("get" + fieldName);
        } catch (NoSuchMethodException ignored) {
            try {
                return clazz.getMethod("is" + fieldName);
            } catch (NoSuchMethodException ignoredAgain) {
                return null;
            }
        }
    }

    private static Object sampleValue(Class<?> type) {
        if (type.equals(String.class)) return "value";
        if (type.equals(Integer.class) || type.equals(int.class)) return 1;
        if (type.equals(Long.class) || type.equals(long.class)) return 1L;
        if (type.equals(Boolean.class) || type.equals(boolean.class)) return true;
        if (type.equals(Double.class) || type.equals(double.class)) return 1.0D;
        if (type.equals(Float.class) || type.equals(float.class)) return 1.0F;
        if (type.equals(BigDecimal.class)) return BigDecimal.ONE;
        if (List.class.isAssignableFrom(type)) return new ArrayList<>();
        if (ArrayList.class.isAssignableFrom(type)) return new ArrayList<>();
        if (Map.class.isAssignableFrom(type)) return new HashMap<>();
        if (Set.class.isAssignableFrom(type)) return new HashSet<>();

        try {
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception ex) {
            return null;
        }
    }

    private static List<Class<?>> findClasses(String packageName) throws Exception {
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        List<Class<?>> classes = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(resource.toURI());
            classes.addAll(findClasses(directory, packageName));
        }

        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}
