package com.aha.util;

/**
 * Encapsulates a method with one argument and no return value
 * @author Armin Häberling (armin.aha@gmail.com)
 * @param <T> the type of the argument value
 */
public interface Method<T> {

    /**
     * Applies the method with the given argument
     * @param object the method argument
     */
    public void apply(T object);
    
}
