package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Raadbeurt {
    private String poging;
    private List<Mark> marks;

    public Raadbeurt(String poging, List<Mark> marks) {
        this.poging = poging;
        this.marks = marks;
    }
    
    public String geefHint(String vorigeHint) {
        String nieuweHint = "";

        for (int i = 0; i < this.marks.size(); i++) {
            Mark huidig = this.marks.get(i);
            if (huidig.equals(Mark.CORRECT)) {
                nieuweHint += poging.charAt(i);
            } else {
                nieuweHint += vorigeHint.charAt(i);
            }
        }

        return nieuweHint;
    }
}
