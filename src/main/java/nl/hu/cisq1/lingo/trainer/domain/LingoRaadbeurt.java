package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.LingoRonde;
import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;


@Entity
public class LingoRaadbeurt {
    private String ingeven_woord;
    @OneToOne
    private LingoRonde lingoRonde;
    private Long id;

    public LingoRaadbeurt(String woord, LingoRonde lingoRonde) {
        this.ingeven_woord = woord;
        this.lingoRonde = lingoRonde;
    }

    public LingoRaadbeurt() {

    }


    public static List<Mark> compare(Word woord, Word word) {
        ArrayList<Mark> respons = new ArrayList<>();
        if (woord.getLength() != word.getValue().length()) {

            for (int i = 0; i < word.getValue().length(); i++) {
                respons.add(Mark.INVALID);
            }
        } else {

            for (int i = 0; i < word.getValue().length(); i++) {
                char character = word.getValue().charAt(i);

                if (woord.getValue().contains(String.valueOf(character))) {

                    if (character == woord.getValue().charAt(i)) {
                        respons.add(Mark.CORRECT);
                    } else {
                        respons.add(Mark.PRESENT);
                    }
                } else {
                    respons.add(Mark.ABSENT);
                }
            }
        }
        return respons;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getIngeven_woord() {
        return ingeven_woord;
    }

    public void setIngeven_woord(String ingev_woord) {
        this.ingeven_woord = ingev_woord;
    }

    public void setLingoRonde(LingoRonde lingoRonde) {
        this.lingoRonde = lingoRonde;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Raadbeurt{" +
                "ingev_woord='" + ingeven_woord + '\'' +
                ", lingoRonde=" + lingoRonde +
                '}';
    }
}
