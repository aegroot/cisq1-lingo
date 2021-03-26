package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LingoSpelTest {
    LingoRonde r1;
    LingoSpel spel;


    @BeforeEach
    void init(){
        r1=new LingoRonde(new Word("maken"));
        r1.addRaadBeurt(new Word("maken"));
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
    @ParameterizedTest
    @DisplayName("addLingoRonde throws WordLengthNotSupportedException")
    @MethodSource("ar")
    public void addLingoRondeTestwlns(String ronde){
        assertThrows(WordLengthNotSupportedException.class,()->{
            spel.addLingoRonde(new LingoRonde(new Word( ronde)));});
    }
    static Stream<Arguments>ar(){
        return Stream.of(
                Arguments.of("bochten"),
                Arguments.of("stel"),
                Arguments.of(""),
                Arguments.of("kaken"),
                Arguments.of("vermaken")
        );
    }
    @ParameterizedTest
    @DisplayName("checkdone")
    @MethodSource("cd")
    public void checkdoneTest(String ronde,List<String>raadbeurten,boolean resultaat){
        LingoRonde lingoRonde=new LingoRonde(new Word(ronde)); List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
        spel.addLingoRonde(lingoRonde);
        assertEquals(resultaat,spel.checkDone());


    }
    static Stream<Arguments>cd(){
        return Stream.of(
                Arguments.of("breken",List.of("wekken","aambij","smeken"),false),
                Arguments.of("breken",List.of("wekken","aambij","smeken","vrijheid","brasem"),true),
                Arguments.of("breken",List.of("wekken","aambij","smeken","breken"),false)

        );
    }


    @Test
    void checkDoneFalse() {

        spel.addLingoRonde(r1);
        r1.addRaadBeurt(new Word("aanvo"));
        assertFalse(spel.checkDone());
    }

    @ParameterizedTest
    @MethodSource("nl")
    @DisplayName("nextlength")
    void nextLength(List<String> woorden, int outcome) {
        LingoRonde r11=new LingoRonde(new Word("maken"));
        r11.addRaadBeurt(new Word("maken"));
        LingoSpel spel1=new LingoSpel(r11);
        if (!woorden.isEmpty()){
        for(String word:woorden){
            LingoRonde ronde=new LingoRonde(new Word(word));
            ronde.setLingogame(spel1);
            ronde.addRaadBeurt(new Word(word));
            spel1.addLingoRonde(ronde);
        }}

        assertEquals(outcome,spel1.nextLength());
    }
    static Stream<Arguments>nl(){
        return Stream.of(
                Arguments.of(List.of("stelen","bochten"),5),
                Arguments.of(List.of("stelen"),7),
                Arguments.of(List.of(),6)

        );
    }

}