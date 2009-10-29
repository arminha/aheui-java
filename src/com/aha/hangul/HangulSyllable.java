package com.aha.hangul;

/**
 * A Hangul syllable. Contains methods for composing and decomposing of
 * Hangul syllables from/into Jamos
 * @author Armin Häberling (armin.aha@gmail.com)
 */
public class HangulSyllable {
    private static final int FIRST_SYLLABLE = 44032;
    private static final int INITIAL_INTERVAL = 588;
    private static final int MEDIAL_INTERVAL = 28;
    private static final int LASTSYLLABLE = FIRST_SYLLABLE
            + (InitialJamo.values().length - 1) * INITIAL_INTERVAL
            + (MedialJamo.values().length - 1) * MEDIAL_INTERVAL 
            + (FinalJamo.values().length - 1); 
    
    private final InitialJamo initialJamo;
    private final MedialJamo medialJamo;
    private final FinalJamo finalJamo;
    
    /**
     * Returns true if the given character is a Hangul syllable
     * @param character a char value
     * @return true if the given character is a Hangul syllable
     */
    public static boolean isHangulSyllable(char character) {
        return FIRST_SYLLABLE <= character && LASTSYLLABLE >= character;
    }
    
    /**
     *
     * @param initialJamo
     * @param medialJamo
     * @param finalJamo
     */
    public HangulSyllable(InitialJamo initialJamo, MedialJamo medialJamo,
            FinalJamo finalJamo) {
        this.initialJamo = initialJamo;
        this.medialJamo = medialJamo;
        this.finalJamo = finalJamo;
    }
    /**
     *
     * @param character
     */
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
    
    /**
     * Returns the initial Jamo of this Hangul syllable
     * @return the initial Jamo of this Hangul syllable
     */
    public InitialJamo getInitialJamo() {
        return initialJamo;
    }

    /**
     * Returns the medial Jamo of this Hangul syllable
     * @return the medial Jamo of this Hangul syllable
     */
    public MedialJamo getMedialJamo() {
        return medialJamo;
    }

    /**
     * Returns the final Jamo of this Hangul syllable
     * @return the final Jamo of this Hangul syllable
     */
    public FinalJamo getFinalJamo() {
        return finalJamo;
    }
    
    /**
     * Returns a character representation of this Hangul syllable
     * @return a character representation of this Hangul syllable
     */
    public char character() {
        char[] characters = Character.toChars(initialJamo.ordinal() * INITIAL_INTERVAL
                + medialJamo.ordinal() * MEDIAL_INTERVAL
                + finalJamo.ordinal() + FIRST_SYLLABLE);
        assert characters.length == 1;
        return characters[0];
    }
}