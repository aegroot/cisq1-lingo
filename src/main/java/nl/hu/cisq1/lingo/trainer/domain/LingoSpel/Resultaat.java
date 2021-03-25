package nl.hu.cisq1.lingo.trainer.domain.LingoSpel;

import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.MockLingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.MockRaadbeurt;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Resultaat {
    private Long id;
    private List<MockLingoRonde>lingoRondes;
    private int currentLength;
    private Boolean done;

    public Resultaat(LingoSpel spel){
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
                mockRaadbeurts.add(new MockRaadbeurt(lingoRonde.getWoord().getValue(),
                        Raadbeurt.compare(new Word(lingoRonde.getWoord().getValue()),
                                new Word(raadbeurt.getIngeven_woord()))));
            }

         mockLingoRondes.add(new MockLingoRonde(mockRaadbeurts,string,lingoRonde.berekenPunten()));
        }
        this.id= spel.getId();

        this.lingoRondes=mockLingoRondes;
        this.currentLength=spel.currentLength();
        this.done=spel.checkDone();
    }
}
