package com.acme.iot.platform.shared.application.result;

import org.jspecify.annotations.NullMarked;

import java.util.Optional;
import java.util.function.Function;

@NullMarked
public sealed interface Result<T, E> {

    record Success<T, E>(T value) implements Result<T, E> {}

    record Failure<T, E>(E error) implements Result<T, E> {}

    static <T, E> Result<T, E> success(T value) {
        return new Success<>(value);
    }

    static <T, E> Result<T, E> failure(E error) {
        return new Failure<>(error);
    }

    default boolean isSuccess() {
        return this instanceof Success;
    }

    default boolean isFailure() {
        return this instanceof Failure;
    }

    default Optional<T> toOptional() {
        if (this instanceof Success<T, E> s) {
            return Optional.of(s.value);
        }
        return Optional.empty();
    }

    default T getOrElse(T defaultValue) {
        if (this instanceof Success<T, E> s) {
            return s.value;
        }
        return defaultValue;
    }

    default <E2> Result<T, E2> mapError(Function<E, E2> f) {
        if (this instanceof Success<T, E> s) {
            return Result.success(s.value);
        }
        if (this instanceof Failure<T, E> failure) {
            return Result.failure(f.apply(failure.error));
        }
        throw new IllegalStateException("Unexpected state");
    }

    default <T2> Result<T2, E> flatMap(Function<T, Result<T2, E>> f) {
        if (this instanceof Success<T, E> s) {
            return f.apply(s.value);
        }
        if (this instanceof Failure<T, E> failure) {
            return Result.failure(failure.error);
        }
        throw new IllegalStateException("Unexpected state");
    }

    default <T2> Result<T2, E> map(Function<T, T2> f) {
        if (this instanceof Success<T, E> s) {
            return Result.success(f.apply(s.value));
        }
        if (this instanceof Failure<T, E> failure) {
            return Result.failure(failure.error);
        }
        throw new IllegalStateException("Unexpected state");
    }

    default Result<T, E> recover(Function<E, Result<T, E>> f) {
        if (this instanceof Success<T, E> s) {
            return this;
        }
        if (this instanceof Failure<T, E> failure) {
            return f.apply(failure.error);
        }
        throw new IllegalStateException("Unexpected state");
    }
}