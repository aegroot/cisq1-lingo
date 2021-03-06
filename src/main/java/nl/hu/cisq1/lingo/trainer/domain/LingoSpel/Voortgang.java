package nl.hu.cisq1.lingo.trainer.domain.LingoSpel;

import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.MockLingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.MockRaadbeurt;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Voortgang {
    private Long id;
    private List<MockLingoRonde>lingoRondes;
    private int currentLength;
    private Boolean done;
    public Voortgang(LingoSpel spel){
        List<LingoRonde>lingoRondes=spel.getLingoRondes();
        List<MockLingoRonde>mockLingoRondes=new ArrayList<>();
        for(LingoRonde lingoRonde:lingoRondes){
            StringBuilder sb=new StringBuilder();
            for (Character ch : lingoRonde.calcWord()) {
                sb.append(ch);
            }
            String string = sb.toString();
            List<MockRaadbeurt>mockRaadbeurts=new ArrayList<>();
            for(Raadbeurt raadbeurt:lingoRonde.getRaadbeurts()){
                List<Mark> marks=Raadbeurt.compare(new Word(lingoRonde.getWoord().getValue()),
                        new Word(raadbeurt.getIngeven_woord()));
                mockRaadbeurts.add(new MockRaadbeurt(
                        raadbeurt.getIngeven_woord(), marks));
            }

         mockLingoRondes.add(new MockLingoRonde(mockRaadbeurts,string,lingoRonde.berekenPunten()));
        }
        this.id= spel.getId();
        this.lingoRondes=mockLingoRondes;
        this.currentLength=spel.currentLength();
        this.done=spel.checkDone();
    }

    public Long getId() {
        return id;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public Boolean getDone() {
        return done;
    }

    public List<MockLingoRonde> getLingoRondes() {
        return lingoRondes;
    }
}
