package nl.hu.cisq1.lingo.trainer.domain.lingoRonde;

import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.FinishedException;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lingoronde")
public class LingoRonde {
    @ManyToOne
    @JoinColumn(name = "spel_id")
    private LingoSpel lingogame;
    @ManyToOne
    private Word woord;
    @OneToMany
    private List<Raadbeurt> raadbeurts;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    public LingoRonde(Word woord) {
        this.woord = woord;
        raadbeurts = new ArrayList<>();
        this.id = getId();

    }

    public LingoRonde() {
    }

    private int countTries() {
        return raadbeurts.size();
    }

    public int berekenPunten() {
        List<Raadbeurt> mockraadbeurts=new ArrayList<>();
        for(Raadbeurt raadbeurt:this.raadbeurts){
            mockraadbeurts.add(raadbeurt);
            if(raadbeurt.getIngeven_woord() == woord.getValue()){
                break;
            }
        }
        return 5 * (5 - mockraadbeurts.size() )+5;
    }

    public boolean checkvoltooid() {
        /*
        StringBuilder sb = new StringBuilder();
        for (Character ch : calcWord()) {
            sb.append(ch);
        }
        String string = sb.toString();

         */
        for (Raadbeurt raadbeurt:raadbeurts) {
            if (raadbeurt.getIngeven_woord().equals(woord.getValue())){return  true;}
        }
        return countTries() >= 5;
    }

    public void addRaadBeurt(Word word) {
        if (checkvoltooid()){
            throw new FinishedException();
        }
        if (countTries() < 5) {
                raadbeurts.add(new Raadbeurt(word.getValue(), this));

        }

    }

    public ArrayList<Character> calcWord() {
        ArrayList<Character> woord1 = new ArrayList<>();
        for (int i = 0; i < woord.getLength(); i++) {
            ArrayList<Mark> resultaten = new ArrayList<>();
            for (Raadbeurt raadbeurt : raadbeurts) {
                if(i<raadbeurt.getIngeven_woord().length()){
                    Mark mark1 = Raadbeurt.compare(woord, new Word(raadbeurt.getIngeven_woord())).get(i);
                    resultaten.add(mark1);
                }

            }
            if (resultaten.contains(Mark.CORRECT)) {
                woord1.add(woord.getValue().charAt(i));
            } else {
                woord1.add(' ');
            }
        }

        woord1.set(0, woord.getValue().charAt(0));
        return woord1;
    }

    public void setLingogame(LingoSpel lingospel) {
        this.lingogame = lingospel;
    }


    public Word getWoord() {
        return woord;
    }

    public void setRaadbeurts(List<Raadbeurt> raadbeurts) {
        this.raadbeurts = raadbeurts;
    }



    public Long getId() {
        return id;
    }

    public List<Raadbeurt> getRaadbeurts() {
        return raadbeurts;
    }

    @Override
    public String toString() {
        return
                "{lingogame=" + lingogame +
                ", woord=" + woord +
                ", raadbeurts=" + raadbeurts +
                '}';
    }
}
