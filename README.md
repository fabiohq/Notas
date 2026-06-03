
when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
        .thenAnswer(inv -> new ServiceException(
                inv.getArgument(0),
                ErrorDTO.builder()
                        .message(inv.getArgument(1) == null ? "error" : String.valueOf(inv.getArgument(1)))
                        .build()
        ));
*******
errors.put("purposeCode_not_found", "purpose error");

******
errors.put("purposecode_not_found", "purpose error");
errors.put("purposeCode_not_found", "purpose error");
