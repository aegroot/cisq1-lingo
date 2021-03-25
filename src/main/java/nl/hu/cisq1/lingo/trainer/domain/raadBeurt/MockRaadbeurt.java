package nl.hu.cisq1.lingo.trainer.domain.raadBeurt;

import nl.hu.cisq1.lingo.trainer.domain.Mark;

import java.util.List;

public class MockRaadbeurt {
    private String ingegevenWoord;
    public List<Mark> marks;

    public MockRaadbeurt(String ingegevenWoord, List<Mark> marks) {
        this.ingegevenWoord = ingegevenWoord;
        this.marks = marks;
    }
}
