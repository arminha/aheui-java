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
