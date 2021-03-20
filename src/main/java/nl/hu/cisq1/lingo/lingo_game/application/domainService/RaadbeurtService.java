package nl.hu.cisq1.lingo.lingo_game.application.domainService;

import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.Raadbeurt;

import java.util.List;

public interface RaadbeurtService {
    Raadbeurt save(String woord, LingoRonde lingoRonde);
}
