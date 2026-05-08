when(errorService.serviceExceptionBuilder(any(HttpStatus.class), anyString(), any()))
        .thenAnswer(invocation -> {

            HttpStatus status = invocation.getArgument(0);
            String message = invocation.getArgument(1);

            ErrorDTO errorDTO = ErrorDTO.builder()
                    .code("PROSPECTS-P-F-9400")
                    .message(message)
                    .level("error")
                    .description(message)
                    .build();

            return new ServiceException(status, errorDTO);
        });