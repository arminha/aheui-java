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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Matrix<T> {

    private final Integer initialRowLength;

    private int rowLength;

    private final List<List<T>> rows;

    private List<T> getRow(int rowInd, boolean createNew) {
        if (!createNew && rowInd >= rows.size()) {
            return null;
        } else {
            while (rowInd >= rows.size()) {
                List<T> newRow = initialRowLength == null ? new ArrayList<>() : new ArrayList<>(initialRowLength);
                rows.add(newRow);
            }
            return rows.get(rowInd);
        }
    }

    public Matrix() {
        initialRowLength = null;
        this.rows = new ArrayList<>();
    }

    public Matrix(int rows, int columns) {
        initialRowLength = columns;
        this.rows = new ArrayList<>(rows);
    }

    public T get(int rowInd, int columnInd) {
        List<T> row = getRow(rowInd, false);
        if (row == null) {
            return null;
        }
        if (columnInd >= row.size()) {
            return null;
        }
        return row.get(columnInd);
    }

    public void set(int rowInd, int columnInd, T value) {
        List<T> row = getRow(rowInd, true);
        while (columnInd >= row.size()) {
            row.add(null);
        }
        if (row.size() > rowLength) {
            rowLength = row.size();
        }
        row.set(columnInd, value);
    }

    public int getRows() {
        return rows.size();
    }

    public int getColumns() {
        return rowLength;
    }

    public <U> Matrix<U> map(Function<T, U> function) {
        Matrix<U> result = new Matrix<>(getRows(), 0);
        for (List<T> tRow : rows) {
            List<U> uRow = new ArrayList<>();
            for (T item : tRow) {
                U uItem = item == null ? null : function.apply(item);
                uRow.add(uItem);
            }
            result.rows.add(uRow);
        }
        return result;
    }

    public void apply(Consumer<T> consumer) {
        for (List<T> tRow : rows) {
            for (T item : tRow) {
                consumer.accept(item);
            }
        }
    }
}
