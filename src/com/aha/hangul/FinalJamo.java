/**
 * 
 */
package com.aha.hangul;

public enum FinalJamo implements Jamo {
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
    
    private FinalJamo() {
        this.character = '\0';
    }
    private FinalJamo(char character) {
        this.character = character;
    }
    private char character;
    @Override
    public char character() {
        return character;
    }
}