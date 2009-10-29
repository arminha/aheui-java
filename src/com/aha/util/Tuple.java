package com.aha.util;

/**
 * This is a class to encapsulate two values in a typesafe way.
 * @author Armin Häberling (armin.aha@gmail.com)
 * @param <T> the type of the first value
 * @param <U> the type of the second value
 */
public class Tuple<T, U> {

    private final T first;
    private final U second;
    
    /**
     *
     * @param first the first value
     * @param second the second value
     */
    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    /**
     *
     * @return the first value
     */
    public T getFirst() {
        return first;
    }
    
    /**
     *
     * @return the second value
     */
    public U getSecond() {
        return second;
    }
}
