package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class LingoRaadbeurtTest {


    @Test
    @DisplayName("wrong length")
    void compareInvalid() {
        Word raadbeurt=new Word("test");
        Word word=new Word("where");
        System.out.println(LingoRaadbeurt.compare(word,raadbeurt));
        assertSame(Mark.INVALID, LingoRaadbeurt.compare(word,raadbeurt).get(0));
    }
    @Test
    @DisplayName("same word")
    void compareGood(){
        Word raadbeurt=new Word("test");
        Word word=new Word("test");
        LingoRaadbeurt.compare(word,raadbeurt);
        System.out.println(LingoRaadbeurt.compare(word,raadbeurt));

    }
    @Test
    @DisplayName("wrong word,good length")
    void comparefase(){

    }


}