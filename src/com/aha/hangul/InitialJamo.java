package com.aha.hangul;

/**
 * Represents the initial Jamo in a Hangul syllable
 * @author Armin Häberling (armin.aha@gmail.com)
 * @see HangulSyllable
 */
public enum InitialJamo implements Jamo {
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
    
    private char character;

    private InitialJamo(char character) {
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