when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
        .thenAnswer(inv -> {
            HttpStatus status = inv.getArgument(0, HttpStatus.class);
            Object message = inv.getArgument(1, Object.class);

            ErrorDTO error = ErrorDTO.builder()
                    .message(message == null ? "error" : message.toString())
                    .build();

            return new ServiceException(status, error);
        });
