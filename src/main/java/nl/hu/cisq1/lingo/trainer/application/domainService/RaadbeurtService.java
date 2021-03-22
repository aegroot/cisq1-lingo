package nl.hu.cisq1.lingo.trainer.application.domainService;

import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;

public interface RaadbeurtService {
    Raadbeurt save(String woord, LingoRonde lingoRonde);

    void delete(Raadbeurt raadbeurt);
}
