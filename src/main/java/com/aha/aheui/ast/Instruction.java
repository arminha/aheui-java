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

import com.aha.hangul.HangulSyllable;

public class Instruction {

    private Operation operation;
    private DirectionModifier directionModifier;
    private Integer number;
    private Storage storage;
    private HangulSyllable syllable;
    private int line;
    private int column;

    public Instruction(HangulSyllable syllable, int line, int column) {
        this.syllable = syllable;
        this.line = line;
        this.column = column;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public DirectionModifier getDirectionModifier() {
        return directionModifier;
    }

    public void setDirectionModifier(DirectionModifier directionModifier) {
        this.directionModifier = directionModifier;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public HangulSyllable getSyllable() {
        return syllable;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("%c: operation = %s, direction = %s, storage = %s, number = %d",
                getSyllable().character(), getOperation(), getDirectionModifier(), getStorage(), getNumber());
    }
}
