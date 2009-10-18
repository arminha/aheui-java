package com.aha.aheui.ast;

public enum Storage {
    DefaultStack,
    /** ㄱ */
    GiyeokStack,
    /** ㄲ */
    SsangGiyeokStack,
    /** ㄳ */
    GiyeokSiotStack,
    /** ㄴ */
    NieunStack,
    /** ㄵ */
    NieunJieutStack,
    /** ㄶ */
    NieunHieutStack,
    /** ㄷ */
    DigeutStack,
    /** ㄹ */
    RieulStack,
    /** ㄺ */
    RieulGiyeokStack,
    /** ㄻ */
    RieulMieumStack,
    /** ㄼ */
    RieulBieupStack,
    /** ㄽ */
    RieulSiotStack,
    /** ㄾ */
    RieulTieutStack,
    /** ㄿ */
    RieulPieupStack,
    /** ㅀ */
    RieulHieutStack,
    /** ㅁ */
    MieumStack,
    /** ㅂ */
    BieupStack,
    /** ㅄ */
    BieupSiotStack,
    /** ㅅ */
    SiotStack,
    /** ㅆ */
    SsangSiotStack,
    /** ㅇ */
    Queue(StorageType.Queue),
    /** ㅈ */
    JieutStack,
    /** ㅊ */
    ChieutStack,
    /** ㅋ */
    KieukStack,
    /** ㅌ */
    TieutStack,
    /** ㅍ */
    PieupStack,
    /** ㅎ */
    Extension(StorageType.Extension);

    private final StorageType type;

    private Storage() {
        this(StorageType.Stack);
    }

    private Storage(StorageType type) {
        this.type = type;
    }

    public StorageType getType() {
        return type;
    }

    public enum StorageType {
        Stack,
        Queue,
        Extension
    }

}
