package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.BadWordException;
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

    @ParameterizedTest
    @DisplayName("addraadbeurtsucces")
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
    @DisplayName("calcwoord")
    @MethodSource("cw")
    public void calcwoord(String beginwoord,List<String> raadbeurten,List<Character> calcword){
        LingoRonde lingoRonde=new LingoRonde(new Word(beginwoord));
        List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
       assertEquals(calcword,lingoRonde.calcWord());


    }
    static Stream<Arguments>cw(){
        return  Stream.of(
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aankant")
                        ,List.of('a','a','n','v','o','e','r')),
                Arguments.of("aanvoer", List.of("uitvoer")
                        ,List.of('a',' ',' ','v','o','e','r')),
                Arguments.of("vervoer",List.of("uitvoer","bijvoer")
                        ,List.of('v',' ',' ','v','o','e','r')),
                Arguments.of("aanvoer",List.of("aanvoer")
                        ,List.of('a','a','n','v','o','e','r')),
                Arguments.of("aanvoer",List.of()
                        ,List.of('a',' ',' ',' ',' ',' ',' ')),
                Arguments.of("aanvoer",List.of("stelen")
                        ,List.of('a',' ',' ',' ',' ',' ',' ')),
                Arguments.of("aanvoer",List.of("executie")
                        ,List.of('a',' ',' ',' ',' ',' ',' '))

        );
    }
    @ParameterizedTest
    @DisplayName("addraadbeurtfailureBadWord")
    @MethodSource("arb")
    void addraadbeurtTestBadWord(String beginwoord,List<String> raadbeurten,String testwoord){
        LingoRonde lingoRonde=new LingoRonde(new Word(beginwoord));
        List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
        assertThrows(BadWordException.class,()->{
            lingoRonde.addRaadBeurt(new Word(testwoord));
        });

    }

    static Stream<Arguments>arb(){
        return  Stream.of(
                Arguments.of("aanvoer", List.of("bijvoer","aanvaar","aankant","bijkant"),"@@@@@"),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar"),"verdenking"),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvoer"),"r0mio33"),
                Arguments.of("aanvoer",List.of("uitvoer","aanvoer"),"verde8k"),
                Arguments.of("aanvoer",List.of("aanvoir"),"b3stPassword4ver"),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aankant"),"Verdenk")
                );
    }




    @ParameterizedTest
    @DisplayName("addraadbeurtfailureFinished")
    @MethodSource("arf")
    void addraadbeurtTestFinished(String beginwoord,List<String> raadbeurten,String testwoord){
        LingoRonde lingoRonde=new LingoRonde(new Word(beginwoord));
        List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
        assertThrows(FinishedException.class,()->{
            lingoRonde.addRaadBeurt(new Word(testwoord));});

    }
    static Stream<Arguments>arf(){
        return  Stream.of(
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aankant","bijkant"),"verdenk"),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aanvoer"),"verdenk"),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvoer"),"verdenk"),
                Arguments.of("aanvoer",List.of("uitvoer","aanvoer"),"verdenk",
                Arguments.of("aanvoer",List.of("aanvoer"),"verdenk"),
                Arguments.of("aanvoer", List.of("aanvoer","uitvoer","bijvoer","aanvaar","aankant"),"verdenk")

        ));
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

    @ParameterizedTest
    @DisplayName("checkvoltooid")
    @MethodSource("cv")
    public void checkvoltooid(String beginwoord,List<String> raadbeurten,boolean check){
        LingoRonde lingoRonde=new LingoRonde(new Word(beginwoord));
        List<Raadbeurt>beurts = new ArrayList<>();
        raadbeurten.forEach(e-> {
            beurts.add(new Raadbeurt(e,lingoRonde));});
        lingoRonde.setRaadbeurts(beurts);
        assertEquals(lingoRonde.checkvoltooid(),check);


    }
    static Stream<Arguments>cv(){
        return  Stream.of(
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aankant","bijkant"),true),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvaar","aanvoer"),true),
                Arguments.of("aanvoer", List.of("uitvoer","bijvoer","aanvoer"),true),
                Arguments.of("aanvoer",List.of("uitvoer","aanvoer"),true),
                Arguments.of("aanvoer",List.of("aanvoer"),true),
                Arguments.of("aanvoer", List.of("aanvoer","uitvoer","bijvoer","aanvaar","aankant"),true),
                Arguments.of("aanvoer", List.of("bijvoer","uitvoer","aanvaar","aankant"),false),
                Arguments.of("aanvoer", List.of("bijvoer","uitvoer","aanvaar"),false),
                Arguments.of("aanvoer", List.of("bijvoer","uitvoer"),false),
                Arguments.of("aanvoer", List.of("bijvoer"),false)

        );
    }






}