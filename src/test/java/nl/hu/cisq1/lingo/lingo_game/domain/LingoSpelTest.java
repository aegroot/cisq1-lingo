package nl.hu.cisq1.lingo.lingo_game.domain;

import nl.hu.cisq1.lingo.lingo_game.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LingoSpelTest {
    LingoRonde r1;
    LingoSpel spel;


    @BeforeEach
    public void init(){
         r1=new LingoRonde(new Word("maken"));
         spel=new LingoSpel(r1);

    }

    @Test
    @DisplayName("game is done")
    void checkDonerue() {


        spel.addLingoRonde(r1);
        r1.addRaadBeurt(new Word("aanvo"));
        r1.addRaadBeurt(new Word("aanvo"));
        r1.addRaadBeurt(new Word("aanvo"));
        r1.addRaadBeurt(new Word("aanvo"));
        r1.addRaadBeurt(new Word("aanvo"));
        assertTrue( spel.checkDone());
    }

    @Test
    void checkDoneFalse() {

        spel.addLingoRonde(r1);
        r1.addRaadBeurt(new Word("aanvo"));
        assertFalse(spel.checkDone());
    }

    @Test
    void nextLength() {
        LingoRonde r2=new LingoRonde(new Word("makend"));
        spel.addLingoRonde(r1);
       spel.addLingoRonde(r2);
        assertEquals(5,spel.nextLength());
    }

}