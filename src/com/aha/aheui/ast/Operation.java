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
