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
 * Represents the medial Jamo in a Hangul syllable.
 * 
 * @see HangulSyllable
 */
public enum MedialJamo implements Jamo {
    //CHECKSTYLE IGNORE JavadocStyle
    /** ㅏ */
    A('ㅏ'),
    /** ㅐ */
    Ae('ㅐ'),
    /** ㅑ */
    Ya('ㅑ'),
    /** ㅒ */
    Yae('ㅒ'),
    /** ㅓ */
    Eo('ㅓ'),
    /** ㅔ */
    E('ㅔ'),
    /** ㅕ */
    Yeo('ㅕ'),
    /** ㅖ */
    Ye('ㅖ'),
    /** ㅗ */
    O('ㅗ'),
    /** ㅘ */
    Wa('ㅘ'),
    /** ㅙ */
    Wae('ㅙ'),
    /** ㅚ */
    Oe('ㅚ'),
    /** ㅛ */
    Yo('ㅛ'),
    /** ㅜ */
    U('ㅜ'),
    /** ㅝ */
    Wo('ㅝ'),
    /** ㅞ */
    We('ㅞ'),
    /** ㅟ */
    Wi('ㅟ'),
    /** ㅠ */
    Yu('ㅠ'),
    /** ㅡ */
    Eu('ㅡ'),
    /** ㅢ */
    Ui('ㅢ'),
    /** ㅣ */
    I('ㅣ');
    //CHECKSTYLE END IGNORE JavadocStyle

    private char character;

    private MedialJamo(char character) {
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
