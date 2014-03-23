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

public enum DirectionModifier {
    None,
    Left(0, -1),
    Up(-1, 0),
    Rigth(0, 1),
    Down(1, 0),
    DoubleLeft(0, -2),
    DoubleUp(-2, 0),
    DoubleRigth(0, 2),
    DoubleDown(2, 0),
    MirrorHorizontal,
    MirrorVertical,
    MirrorBoth;

    private final boolean isFixed;
    private final int lineDiff;
    private final int columnDiff;

    private DirectionModifier(int lineDiff, int columnDiff) {
        isFixed = true;
        assert lineDiff == 0 || columnDiff == 0;
        this.lineDiff = lineDiff;
        this.columnDiff = columnDiff;
    }

    private DirectionModifier() {
        isFixed = false;
        lineDiff = 0;
        columnDiff = 0;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public int getLineDiff() {
        return lineDiff;
    }

    public int getColumnDiff() {
        return columnDiff;
    }
}
