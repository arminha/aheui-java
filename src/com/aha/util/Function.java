package com.aha.util;

public interface Function<T, TResult> {
    TResult apply(T parameter);
}
