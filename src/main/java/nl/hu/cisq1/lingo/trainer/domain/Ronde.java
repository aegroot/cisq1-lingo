package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Ronde {
    private String woord;
    private List<Raadbeurt> raadbeurten;

    public Ronde(String woord) {
        this.woord = woord;
    }

    public void raadWoord(String poging) {

    }

    public int berekenPunten() {
        return 5 * (5 - raadbeurten.size() + 5);
    }
}
