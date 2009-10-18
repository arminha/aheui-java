package com.aha.hangul;

public class HangulSyllable {
    private static final int FIRST_SYLLABLE = 44032;
    private static final int INITIAL_INTERVAL = 588;
    private static final int MEDIAL_INTERVAL = 28;
    private static final int LASTSYLLABLE = FIRST_SYLLABLE
            + (InitialJamo.values().length - 1) * INITIAL_INTERVAL
            + (MedialJamo.values().length - 1) * MEDIAL_INTERVAL 
            + (FinalJamo.values().length - 1); 
    
    private InitialJamo initialJamo;
    private MedialJamo medialJamo;
    private FinalJamo finalJamo;
    
    public static boolean isHangulSyllable(char character) {
        return FIRST_SYLLABLE <= character && LASTSYLLABLE >= character;
    }
    
    public HangulSyllable(InitialJamo initialJamo, MedialJamo medialJamo,
            FinalJamo finalJamo) {
        this.initialJamo = initialJamo;
        this.medialJamo = medialJamo;
        this.finalJamo = finalJamo;
    }
    public HangulSyllable(char character) {
        if (!isHangulSyllable(character)) {
            throw new IllegalArgumentException("character is no Hangul syllable.");
        }
        int charOffset = character - FIRST_SYLLABLE;
        int inital = charOffset / INITIAL_INTERVAL;
        initialJamo = InitialJamo.values()[inital];
        int medial = (charOffset % INITIAL_INTERVAL) / MEDIAL_INTERVAL;
        medialJamo = MedialJamo.values()[medial];
        int last = (charOffset % MEDIAL_INTERVAL);
        finalJamo = FinalJamo.values()[last];
    }
    
    public InitialJamo getInitialJamo() {
        return initialJamo;
    }
    public MedialJamo getMedialJamo() {
        return medialJamo;
    }
    public FinalJamo getFinalJamo() {
        return finalJamo;
    }
    public char character() {
        char[] characters = Character.toChars(initialJamo.ordinal() * INITIAL_INTERVAL
                + medialJamo.ordinal() * MEDIAL_INTERVAL
                + finalJamo.ordinal() + FIRST_SYLLABLE);
        assert characters.length == 1;
        return characters[0];
    }
}