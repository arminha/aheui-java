package com.aha.util;

import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {

    private final Integer initialRowLength;

    private int rowLength;

    private List<List<T>> rows;

    private List<T> getRow(int rowInd, boolean createNew) {
        if (!createNew && rowInd >= rows.size()) {
            return null;
        } else {
            while (rowInd >= rows.size()) {
                List<T> newRow = initialRowLength == null ? new ArrayList<T>()
                        : new ArrayList<T>(initialRowLength);
                rows.add(newRow);
            }
            return rows.get(rowInd);
        }
    }

    public Matrix() {
        initialRowLength = null;
        this.rows = new ArrayList<List<T>>();
    }

    public Matrix(int rows, int colunms) {
        initialRowLength = colunms;
        this.rows = new ArrayList<List<T>>(rows);
    }

    public T get(int rowInd, int columnInd) {
        List<T> row = getRow(rowInd, false);
        if (row == null)
            return null;
        if (columnInd >= row.size())
            return null;
        return row.get(columnInd);
    }

    public void set(int rowInd, int columnInd, T value) {
        List<T> row = getRow(rowInd, true);
        while (columnInd >= row.size()) {
            row.add(null);
        }
        if (row.size() > rowLength)
            rowLength = row.size();
        row.set(columnInd, value);
    }

    public int getRows() {
        return rows.size();
    }

    public int getColumns() {
        return rowLength;
    }

    public <U> Matrix<U> map(Function<T, U> function) {
        Matrix<U> result = new Matrix<U>(getRows(), 0);
        for (List<T> tRow : rows) {
            List<U> uRow = new ArrayList<U>();
            for (T item : tRow) {
                U uItem = item == null ? null : function.apply(item);
                uRow.add(uItem);
            }
            result.rows.add(uRow);
        }
        return result;
    }
    
    public void apply(Method<T> method) {
        for (List<T> tRow : rows) {
            for (T item : tRow) {
                method.apply(item);
            }
        }
    }
}
