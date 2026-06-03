org.opentest4j.AssertionFailedError: Unexpected exception thrown: java.lang.NullPointerException: Cannot read the array length because "value" is null
	at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
	at org.junit.jupiter.api.AssertDoesNotThrow.createAssertionFailedError(AssertDoesNotThrow.java:84)
	at org.junit.jupiter.api.AssertDoesNotThrow.assertDoesNotThrow(AssertDoesNotThrow.java:53)
	at org.junit.jupiter.api.AssertDoesNotThrow.assertDoesNotThrow(AssertDoesNotThrow.java:36)
	at org.junit.jupiter.api.Assertions.assertDoesNotThrow(Assertions.java:3199)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtilsTest.shouldCoverTermDepositsInputValidationOk(TermDepositUtilsTest.java:534)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
Caused by: java.lang.NullPointerException: Cannot read the array length because "value" is null
	at java.base/java.lang.String.<init>(String.java:275)
	at java.base/java.lang.String.valueOf(String.java:4234)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtilsTest.lambda$0(TermDepositUtilsTest.java:123)
	at org.mockito.internal.stubbing.StubbedInvocationMatcher.answer(StubbedInvocationMatcher.java:42)
	at org.mockito.internal.handler.MockHandlerImpl.handle(MockHandlerImpl.java:103)
	at org.mockito.internal.handler.NullResultGuardian.handle(NullResultGuardian.java:29)
	at org.mockito.internal.handler.InvocationNotifierHandler.handle(InvocationNotifierHandler.java:34)
	at org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:82)
	at org.mockito.internal.creation.bytebuddy.MockMethodAdvice.handle(MockMethodAdvice.java:134)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService.serviceExceptionBuilder(ErrorService.java:36)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils.purposeCodeValidation(TermDepositUtils.java:444)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils.validatePurposeCode(TermDepositUtils.java:328)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils.termDepositsInputValidation(TermDepositUtils.java:153)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtilsTest.lambda$35(TermDepositUtilsTest.java:534)
	at org.junit.jupiter.api.AssertDoesNotThrow.assertDoesNotThrow(AssertDoesNotThrow.java:49)
	... 6 more



