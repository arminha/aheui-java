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
