package com.acme.iot.platform.shared.interfaces.rest.transform;

import com.acme.iot.platform.shared.application.result.ApplicationError;
import com.acme.iot.platform.shared.application.result.Result;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

@NullMarked
public class ResponseEntityAssembler {

    private ResponseEntityAssembler(){}

    public static <T, R> ResponseEntity<?> toResponseEntityFromResult(
            Result<T, ApplicationError> result,
            Function<T, R> successResourceAssembler,
            HttpStatusCode successStatus
    ){

        if (result instanceof Result.Success<T, ApplicationError> success) {
            return new ResponseEntity<>(
                    successResourceAssembler.apply(success.value()),
                    successStatus
            );
        }

        if (result instanceof Result.Failure<T, ApplicationError> failure) {
            return ErrorResponseAssembler
                    .toErrorResponseFromApplicationError(failure.error());
        }

        throw new IllegalStateException("Unknown Result type");
    }
}