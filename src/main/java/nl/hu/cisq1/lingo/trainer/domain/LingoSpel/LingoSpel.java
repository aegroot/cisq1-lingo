package nl.hu.cisq1.lingo.trainer.domain.LingoSpel;

import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lingospel")
public class LingoSpel {
    @OneToMany(mappedBy = "lingogame", targetEntity = LingoRonde.class, fetch = FetchType.EAGER)
    private List<LingoRonde> lingoRondes;
    private Long id;

    public LingoSpel(LingoRonde lingoRonde) {
        this.lingoRondes = new ArrayList<>();
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


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
