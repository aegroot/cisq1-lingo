package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.FinishedException;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LingoRondeTest {
    Word woord=new Word("fiets");
    Word woordTest1=new Word("niets");
    Word woordTest2=new Word("meets");
    Word WoordTest3=new Word("klarinet");

    @ParameterizedTest
    @DisplayName("")
    @MethodSource("art")
    void addraadbeurtTestgood(String beginwoord,List<String> raadbeurten,String testwoord){
        LingoRonde lingoRonde=new LingoRonde(new Word(beginwoord));
        List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
        lingoRonde.addRaadBeurt(new Word(testwoord));
        assertNotEquals(lingoRonde.getRaadbeurts().size(),raadbeurten.size());
    }
    static Stream<Arguments>art(){
        return  Stream.of(
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar,aankant"),"verdenk"),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar"),"verdenk"),
                Arguments.of("aanvoer",List.of("uitvoer","bijvoer"),"verdenk"),
                Arguments.of("aanvoer",List.of("uitvoer"),"verdenk")


        );
    }
    @ParameterizedTest
    @DisplayName("berekenpunten")
    @MethodSource("bp")
    void berekenpunten(String beginwoord,List<String> raadbeurten,int outcome){
        LingoRonde lingoRonde=new LingoRonde(new Word(beginwoord));
        List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
        assertEquals(lingoRonde.berekenPunten(),outcome);
    }
    static Stream<Arguments>bp(){
        return  Stream.of(
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aankant","aanvoer"),5),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aanvoer"),10),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvoer"),15),
                Arguments.of("aanvoer",List.of("uitvoer","aanvoer"),20),
                Arguments.of("aanvoer",List.of("aanvoer"),25),
                Arguments.of("aanvoer", List.of("aanvoer","uitvoer","bijvoer","aanvaar","aankant"),25),
                Arguments.of("aanvoer", List.of("bijvoer","uitvoer","aanvoer","aanvaar","aankant"),15)


        );
    }



    @Test
    @DisplayName("round is done when the word is revealed")
    void checkvoltooid() {
        LingoRonde lingoRonde=new LingoRonde(woord);
        lingoRonde.addRaadBeurt(woordTest1);
        System.out.println(lingoRonde.calcWord());
        assertTrue(lingoRonde.checkvoltooid());
    }
    @Test
    @DisplayName("round is not done if the word is  not revealed")
    void checkonvoltooid(){
        LingoRonde lingoRonde=new LingoRonde(woord);
        lingoRonde.addRaadBeurt(woordTest2);
        System.out.println(lingoRonde.calcWord());
        assertFalse(lingoRonde.checkvoltooid());
    }
    @Test
    @DisplayName("round is done when the player tred 5 times")
    void check5tries(){
        LingoRonde lingoRonde=new LingoRonde(woord);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        assertTrue(lingoRonde.checkvoltooid());
    }
    @Test
    @DisplayName("round is done when the player tried 5 times")
    void checklessthan5tries(){
        LingoRonde lingoRonde=new LingoRonde(woord);
        lingoRonde.addRaadBeurt(woordTest2);
      assertFalse(lingoRonde.checkvoltooid());
    }

    @Test
    @DisplayName("round is done at 5 tries and the word is revealed")
    void checkBoth(){
        LingoRonde lingoRonde=new LingoRonde(woord);
        lingoRonde.addRaadBeurt(woordTest1);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        lingoRonde.addRaadBeurt(woordTest2);
        System.out.println(lingoRonde.calcWord());
        assertTrue(lingoRonde.checkvoltooid());
    }

}