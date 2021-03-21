package nl.hu.cisq1.lingo.lingo_game.application.domainService;

import nl.hu.cisq1.lingo.lingo_game.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;

import java.util.List;

public interface LingospelService {
    LingoSpel save(LingoSpel lingospel);
    LingoSpel update(LingoSpel lingoSpel);
    LingoSpel findbyid(Long id);
    List<LingoSpel>findall();
    void delete(LingoSpel lingoSpel);
}
