package com.aha.hangul;

/**
 * Represents the medial Jamo in a Hangul syllable
 * @author Armin Häberling (armin.aha@gmail.com)
 * @see HangulSyllable
 */
public enum MedialJamo implements Jamo {
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

    private char character;

    private MedialJamo(char character) {
        this.character = character;
    }

    /**
     * Returns the char value of this Jamo
     * @return a char representation of the Jamo
     */
    @Override
    public char character() {
        return character;
    }
}