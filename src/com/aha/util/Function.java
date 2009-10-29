package com.aha.util;

/**
 * Encapsulates a function with one argument
 *
 * @author Armin Häberling (armin.aha@gmail.com)
 * @param <T> the type of the function argument
 * @param <TResult> the type of the function result
 */
public interface Function<T, TResult> {
    /**
     * Applies the funtion to the given argument
     * @param parameter the function argument
     * @return the function return value
     */
    TResult apply(T parameter);
}
