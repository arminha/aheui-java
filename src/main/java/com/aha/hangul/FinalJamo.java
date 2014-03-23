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

package com.aha.hangul;

/**
 * Represents the final Jamo in a Hangul syllable.
 * 
 * @see HangulSyllable
 */
public enum FinalJamo implements Jamo {
    //CHECKSTYLE IGNORE JavadocStyle
    /** No final Jamo */
    None,
    /** ㄱ */
    Giyeok('ㄱ'),
    /** ㄲ */
    SsangGiyeok('ㄲ'),
    /** ㄳ */
    GiyeokSiot('ㄳ'),
    /** ㄴ */
    Nieun('ㄴ'),
    /** ㄵ */
    NieunJieut('ㄵ'),
    /** ㄶ */
    NieunHieut('ㄶ'),
    /** ㄷ */
    Digeut('ㄷ'),
    /** ㄹ */
    Rieul('ㄹ'),
    /** ㄺ */
    RieulGiyeok('ㄺ'),
    /** ㄻ */
    RieulMieum('ㄻ'),
    /** ㄼ */
    RieulBieup('ㄼ'),
    /** ㄽ */
    RieulSiot('ㄽ'),
    /** ㄾ */
    RieulTieut('ㄾ'),
    /** ㄿ */
    RieulPieup('ㄿ'),
    /** ㅀ */
    RieulHieut('ㅀ'),
    /** ㅁ */
    Mieum('ㅁ'),
    /** ㅂ */
    Bieup('ㅂ'),
    /** ㅄ */
    BieupSiot('ㅄ'),
    /** ㅅ */
    Siot('ㅅ'),
    /** ㅆ */
    SsangSiot('ㅆ'),
    /** ㅇ */
    Ieung('ㅇ'),
    /** ㅈ */
    Jieut('ㅈ'),
    /** ㅊ */
    Chieut('ㅊ'),
    /** ㅋ */
    Kieuk('ㅋ'),
    /** ㅌ */
    Tieut('ㅌ'),
    /** ㅍ */
    Pieup('ㅍ'),
    /** ㅎ */
    Hieut('ㅎ');
    //CHECKSTYLE END IGNORE JavadocStyle

    private char character;

    private FinalJamo() {
        this.character = '\0';
    }

    private FinalJamo(char character) {
        this.character = character;
    }

    /**
     * Returns the char value of this Jamo.
     * 
     * @return a char representation of the Jamo
     */
    @Override
    public char character() {
        return character;
    }
}
