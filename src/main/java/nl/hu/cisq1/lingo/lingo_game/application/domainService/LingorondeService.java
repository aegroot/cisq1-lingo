package nl.hu.cisq1.lingo.lingo_game.application.domainService;

import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.List;

public interface LingorondeService {
    List<LingoRonde>findall();
    LingoRonde findById(Long id);
    List<Word>getRaadbeurts(LingoRonde lingoRonde);
    LingoRonde save(LingoRonde lingoRonde);
    LingoRonde update(LingoRonde lingoRonde);
    void delete(LingoRonde lingoRonde);
    Word getWord(LingoRonde lingoRonde);
}
