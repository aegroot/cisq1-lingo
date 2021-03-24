package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.LingoRonde;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LingoSpel {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<LingoRonde> lingoRondes = new ArrayList<>();


    public LingoSpel(LingoRonde lingoRonde) {
        this.lingoRondes.add(lingoRonde);
    }

    public int currentLength() {
        if (lingoRondes.isEmpty()) {
            return 5;
        } else return lingoRondes.get(lingoRondes.size() - 1).getWoord().getLength();
    }

    public LingoSpel() {
    }


    public boolean addLingoRonde(LingoRonde lingoRonde) {
        if (currentLength() == lingoRonde.getWoord().getLength()) {
            lingoRondes.add(lingoRonde);
            return true;
        }
        return false;
    }


    public boolean checkDone() {
        for (LingoRonde lingorond : lingoRondes) {
            if (lingorond.checkoltooid() &&
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
}
