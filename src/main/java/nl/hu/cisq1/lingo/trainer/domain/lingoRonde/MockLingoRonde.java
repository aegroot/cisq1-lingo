package nl.hu.cisq1.lingo.trainer.domain.lingoRonde;

import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.MockRaadbeurt;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;

import java.util.List;

public class MockLingoRonde {
    List<MockRaadbeurt> raadbeurts;
    String calcWord;
    int punten;

    public MockLingoRonde(List<MockRaadbeurt> raadbeurts, String calcWord, int punten) {
       this.raadbeurts=raadbeurts;
        this.calcWord = calcWord;
        this.punten = punten;
    }
}
