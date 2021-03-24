package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import  static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class RaadbeurtTest {

    @ParameterizedTest
    @DisplayName("compare testing")
    @MethodSource("raadbeurt")
    void comparefase(String rb, String testWoord, List<Mark> resultaat){
        Word raadbeurt=new Word(rb);
        Word word=new Word(testWoord);
        assertEquals(Raadbeurt.compare(raadbeurt,word),resultaat);
    }
    static Stream<Arguments>raadbeurt(){
        return  Stream.of(
                Arguments.of("testje","mesten",List.of(ABSENT, CORRECT,CORRECT, CORRECT,PRESENT,ABSENT)),
                Arguments.of("testje","testen",List.of(CORRECT, CORRECT,CORRECT, CORRECT,PRESENT,ABSENT)),
                Arguments.of("testen","mesten",List.of(ABSENT, CORRECT,CORRECT, CORRECT,CORRECT,CORRECT)),
                Arguments.of("testen","testen",List.of(CORRECT, CORRECT,CORRECT, CORRECT,CORRECT,CORRECT)),
                Arguments.of("feest","testen",List.of(INVALID,INVALID,INVALID,INVALID,INVALID)),
                Arguments.of("feest","test",List.of(INVALID,INVALID,INVALID,INVALID,INVALID))
        );
    }



}