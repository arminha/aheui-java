/*
 * Copyright (C) 2012  Armin Häberling
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.aha.util;

/**
 * This is a class to encapsulate two values in a typesafe way.
 * 
 * @param <T>
 *            the type of the first value
 * @param <U>
 *            the type of the second value
 */
public class Tuple<T, U> {

    private final T first;
    private final U second;

    /**
     * 
     * @param first
     *            the first value
     * @param second
     *            the second value
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
