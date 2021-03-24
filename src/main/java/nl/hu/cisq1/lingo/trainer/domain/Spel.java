package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Spel {
    private Long id;
    private Integer score;
    private List<Ronde> rondes = new ArrayList<>();

    public void startNieuweRonde(String woord) {
        Ronde ronde = new Ronde(woord);
        this.rondes.add(ronde);
    }

    public Voortgang geefVoortgang() {
        return null;
    }

    public void raadWoord(String poging) {
        Ronde huidigeRonde = this.getHuidigeRonde();
        huidigeRonde.raadWoord(poging);
    }

    private Ronde getHuidigeRonde() {
        return this.rondes.get(this.rondes.size() - 1);
    }
}
