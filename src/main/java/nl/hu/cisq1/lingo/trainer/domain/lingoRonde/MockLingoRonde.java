package nl.hu.cisq1.lingo.trainer.domain.lingoRonde;

import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.MockRaadbeurt;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;

import java.io.Serializable;
import java.util.List;
public class MockLingoRonde implements Serializable {
    List<MockRaadbeurt> raadbeurts;
    String calcWord;
    int punten;

    public MockLingoRonde(List<MockRaadbeurt> raadbeurts, String calcWord, int punten) {
       this.raadbeurts=raadbeurts;
        this.calcWord = calcWord;
        this.punten = punten;
    }

    public int getPunten() {
        return punten;
    }

    public List<MockRaadbeurt> getRaadbeurts() {
        return raadbeurts;
    }

    public String getCalcWord() {
        return calcWord;
    }

    public void setRaadbeurts(List<MockRaadbeurt> raadbeurts) {
        this.raadbeurts = raadbeurts;
    }

    public void setCalcWord(String calcWord) {
        this.calcWord = calcWord;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }
}

