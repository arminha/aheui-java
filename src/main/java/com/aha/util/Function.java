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
 * Encapsulates a function with one argument.
 * 
 * @param <T>
 *            the type of the function argument
 * @param <TResult>
 *            the type of the function result
 */
public interface Function<T, TResult> {
    /**
     * Applies the function to the given argument.
     * 
     * @param parameter
     *            the function argument
     * @return the function return value
     */
    TResult apply(T parameter);
}
