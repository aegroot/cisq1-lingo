package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.lingo_game.domain.Mark;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class RaadbeurtTest {


    @Test
    @DisplayName("wrong length")
    void compareInvalid() {
        Word raadbeurt=new Word("test");
        Word word=new Word("where");
        System.out.println(Raadbeurt.compare(word,raadbeurt));
        assertSame(Mark.INVALID,Raadbeurt.compare(word,raadbeurt).get(0));
    }
    @Test
    @DisplayName("same word")
    void compareGood(){
        Word raadbeurt=new Word("test");
        Word word=new Word("test");
        Raadbeurt.compare(word,raadbeurt);
        System.out.println(Raadbeurt.compare(word,raadbeurt));

    }
    @Test
    @DisplayName("wrong word,good length")
    void comparefase(){

    }


}