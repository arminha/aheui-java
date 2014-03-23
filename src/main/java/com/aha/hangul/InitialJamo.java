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
 * Represents the initial Jamo in a Hangul syllable.
 * 
 * @see HangulSyllable
 */
public enum InitialJamo implements Jamo {
    //CHECKSTYLE IGNORE JavadocStyle
    /** ㄱ */
    Giyeok('ㄱ'),
    /** ㄲ */
    SsangGiyeok('ㄲ'),
    /** ㄴ */
    Nieun('ㄴ'),
    /** ㄷ */
    Digeut('ㄷ'),
    /** ㄸ */
    SsangDigeut('ㄸ'),
    /** ㄹ */
    Rieul('ㄹ'),
    /** ㅁ */
    Mieum('ㅁ'),
    /** ㅂ */
    Bieup('ㅂ'),
    /** ㅃ */
    SsangBieup('ㅃ'),
    /** ㅅ */
    Siot('ㅅ'),
    /** ㅆ */
    SsangSiot('ㅆ'),
    /** ㅇ */
    Ieung('ㅇ'),
    /** ㅈ */
    Jieut('ㅈ'),
    /** ㅉ */
    SsangJieut('ㅉ'),
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

    private InitialJamo(char character) {
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
