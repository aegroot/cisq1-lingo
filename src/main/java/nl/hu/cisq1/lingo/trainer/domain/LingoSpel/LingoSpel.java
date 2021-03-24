package nl.hu.cisq1.lingo.trainer.domain.LingoSpel;

import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.CantContinueException;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.RoundNotFinishedException;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lingospel")
public class LingoSpel {
    @OneToMany(mappedBy = "lingogame", targetEntity = LingoRonde.class, fetch = FetchType.EAGER)
    private List<LingoRonde> lingoRondes;
    @Id
    @GeneratedValue
    private Long id;

    public LingoSpel(LingoRonde lingoRonde) {
        this.lingoRondes = new ArrayList<>();
        this.lingoRondes.add(lingoRonde);
    }

    private int currentLength() {
        if (lingoRondes.isEmpty()) {
            return 5;
        } else return lingoRondes.get(lingoRondes.size() - 1).getWoord().getLength();
    }
    public LingoSpel() {
    }


    public void addLingoRonde(LingoRonde lingoRonde) {
        for (LingoRonde lingoRonde1:lingoRondes){
            if (!lingoRonde1.checkvoltooid()){
                throw new RoundNotFinishedException();
            }
        }
        if (checkDone()){
                throw  new CantContinueException();
        }
        if (currentLength() == lingoRonde.getWoord().getLength()) {
            lingoRondes.add(lingoRonde);
        }
        else throw new WordLengthNotSupportedException(currentLength());
    }

    public LingoRonde getLastRonde(){
        return  lingoRondes.get(lingoRondes.size()-1);
    }



    public boolean checkDone() {

        for (LingoRonde lingorond : lingoRondes) {
            if (lingorond.checkvoltooid() &&
                    lingorond.calcWord().toString() != lingorond.getWoord().getValue()) {
                return true;
            }
        }
        return false;
    }


    public int nextLength() {

        if (currentLength() == 5) {
            return 6;
        } else if (currentLength() == 6) {
            return 7;
        } else if (currentLength() == 7) {
            return 5;
        }
        return 5;


    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setLingoRondes(List<LingoRonde> lingoRondes) {
        this.lingoRondes = lingoRondes;
    }

    public List<LingoRonde> getLingoRondes() {
        return lingoRondes;
    }

    public Long getId() {
        return id;
    }
}
