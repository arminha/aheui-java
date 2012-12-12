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

package com.aha.aheui.ast;

public enum Operation {
    /** ㅇ */
    Null,
    /** ㅎ */
    Terminate,
    /** ㄷ */
    Add,
    /** ㄸ */
    Multiply,
    /** ㄴ */
    Divide,
    /** ㅌ */
    Subtract,
    /** ㄹ */
    Modulo,
    /** ㅁ */
    Pop,
    /** ㅁ and ㅇ */
    PrintInteger,
    /** ㅁ and ㅎ */
    PrintCharacter,
    /** ㅂ */
    Push(true),
    /** ㅂ and ㅇ */
    ReadInteger,
    /** ㅂ and ㅎ */
    ReadCharacter,
    /** ㅃ */
    Duplicate,
    /** ㅍ */
    Swap,
    /** ㅅ */
    Select(true),
    /** ㅆ */
    Transfer(true),
    /** ㅈ */
    Compare,
    /** ㅊ */
    Decide,
    /** ㄱ, ㄲ, ㅉ or ㅋ */
    InvalidOperation;
    
    private final boolean hasParameters;

    private Operation() {
        this(false);
    }

    private Operation(boolean hasParameters) {
        this.hasParameters = hasParameters;
    }

    public boolean hasParameters() {
        return hasParameters;
    }
}
