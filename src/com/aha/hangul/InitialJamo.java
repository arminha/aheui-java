/**
 * 
 */
package com.aha.hangul;

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
    
    private InitialJamo(char character) {
        this.character = character;
    }
    private char character;
    @Override
    public char character() {
        return character;
    }
}