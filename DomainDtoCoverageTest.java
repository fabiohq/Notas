package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Cobertura reflectiva para DTOs/modelos del paquete domain.
 *
 * Cubre, cuando existen:
 * - constructor vacío
 * - constructor con argumentos
 * - getters
 * - setters
 * - builder de Lombok
 * - toString, equals y hashCode generados por Lombok @Data
 */
class DomainDtoCoverageTest {

    static Stream<Arguments> domainClasses() {
        return Stream.of(
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.ParticipantsRequestDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.PeriodRequestDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.CalculateDepositSummaryResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.DepositSummaryResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.TotalInvestedAmountResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.request.DepositPlacementRequestDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Account",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Amount",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Contract",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Currency",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DestinationFunds",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.InitialTotalInvested",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.OriginIdentifier",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Periodicity",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Placement",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.PlacementIdentification",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Product",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.ProfitabilityAtMaturity",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Settlement",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.SettlementConcept",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.SettlementCondition",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.StatusInfo",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Subproduct",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.Bp01DataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.Bp01HeaderRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.Bp01SesionRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.TrxBp01Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.response.TrxBp01Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request.TrxBp02DataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request.TrxBp02Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto.Bp01BGMP010ResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto.Bp01DataResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto.TrxBp02BGMP020Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto.TrxBp02DataResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request.TrxBP13DataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request.TrxBP13Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.ErrorResponseTrxDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13DataResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request.TrxBP17DataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request.TrxBP17Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.ErrorResponseTrxDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17DataResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request.TrxBP21DataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request.TrxBP21Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response.TrxBP21DataResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response.TrxBP21Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request.TrxBP31DataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request.TrxBP31Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.AvisosDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request.Bp92DataRequestDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request.TrxBp92Request",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.response.TrxBp92Response",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto.PepfAditionalInfoDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto.PepfDataDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto.PepfHeaderDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto.PepfSessionDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.TrxPEPFDataRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfDataResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfPEMFV0AResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfSessionResponseDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration.ApiEntry",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.MaxAndMinAmountDto",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdeposit.request.GetListDepositsRequestDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails.TermDepositPersonDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails.TermDepositPersonNameDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersDTO",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse",
            "com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.updatecdt.request.UpdateCdtRequestDTO"
        ).map(Arguments::of);
    }

    @ParameterizedTest(name = "DTO coverage: {0}")
    @MethodSource("domainClasses")
    void shouldCoverDomainDtoBoilerplate(String className) throws Exception {
        Class<?> clazz = Class.forName(className);

        Object instance = instantiate(clazz);
        assertNotNull(instance);

        coverSettersAndGetters(clazz, instance);
        coverAllArgsConstructors(clazz);
        coverBuilder(clazz);
        coverObjectMethods(instance);
    }

    private static Object instantiate(Class<?> clazz) throws Exception {
        Constructor<?> noArgs = findNoArgsConstructor(clazz);
        if (noArgs != null) {
            noArgs.setAccessible(true);
            return noArgs.newInstance();
        }
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return constructor.newInstance(valuesFor(constructor.getParameterTypes()));
    }

    private static Constructor<?> findNoArgsConstructor(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() == 0)
                .findFirst()
                .orElse(null);
    }

    private static void coverSettersAndGetters(Class<?> clazz, Object instance) throws Exception {
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String suffix = capitalize(field.getName());
            Method setter = findMethod(clazz, "set" + suffix, field.getType());
            Method getter = findMethod(clazz, "get" + suffix);
            if (getter == null && (field.getType() == boolean.class || field.getType() == Boolean.class)) {
                getter = findMethod(clazz, "is" + suffix);
            }
            Object value = valueFor(field.getType());
            if (setter != null) {
                setter.setAccessible(true);
                setter.invoke(instance, value);
            }
            if (getter != null) {
                getter.setAccessible(true);
                Object result = getter.invoke(instance);
                if (setter != null) {
                    assertEquals(value, result, clazz.getSimpleName() + "." + field.getName());
                } else {
                    assertDoesNotThrow(() -> getter.invoke(instance));
                }
            }
        }
    }

    private static void coverAllArgsConstructors(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() > 0)
                .forEach(constructor -> assertDoesNotThrow(() -> {
                    constructor.setAccessible(true);
                    Object built = constructor.newInstance(valuesFor(constructor.getParameterTypes()));
                    assertNotNull(built);
                }));
    }

    private static void coverBuilder(Class<?> clazz) {
        Method builderMethod = findMethod(clazz, "builder");
        if (builderMethod == null) {
            return;
        }
        assertDoesNotThrow(() -> {
            Object builder = builderMethod.invoke(null);
            assertNotNull(builder);
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Method builderSetter = findMethod(builder.getClass(), field.getName(), field.getType());
                if (builderSetter != null) {
                    builderSetter.setAccessible(true);
                    builderSetter.invoke(builder, valueFor(field.getType()));
                }
            }
            Method buildMethod = findMethod(builder.getClass(), "build");
            Object built = buildMethod.invoke(builder);
            assertNotNull(built);
        });
    }

    private static void coverObjectMethods(Object instance) {
        assertDoesNotThrow(instance::toString);
        assertDoesNotThrow(instance::hashCode);
        assertTrue(instance.equals(instance));
    }

    private static Object[] valuesFor(Class<?>[] types) {
        Object[] values = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            values[i] = valueFor(types[i]);
        }
        return values;
    }

    private static Object valueFor(Class<?> type) {
        if (type == String.class) return "test";
        if (type == int.class || type == Integer.class) return 1;
        if (type == long.class || type == Long.class) return 1L;
        if (type == double.class || type == Double.class) return 1D;
        if (type == float.class || type == Float.class) return 1F;
        if (type == boolean.class || type == Boolean.class) return true;
        if (type == BigDecimal.class) return BigDecimal.ONE;
        if (type == LocalDate.class) return LocalDate.now();
        if (type == LocalDateTime.class) return LocalDateTime.now();
        if (List.class.isAssignableFrom(type)) return new ArrayList<>();
        if (type.isEnum()) return type.getEnumConstants().length == 0 ? null : type.getEnumConstants()[0];
        if (type.isArray()) return java.lang.reflect.Array.newInstance(type.getComponentType(), 0);

        try {
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception ignored) {
            return null;
        }
    }

    private static Method findMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException ignored) {
            try {
                return clazz.getMethod(name, parameterTypes);
            } catch (NoSuchMethodException ignoredAgain) {
                return null;
            }
        }
    }

    private static String capitalize(String value) {
        if (value == null || value.isBlank()) return value;
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
