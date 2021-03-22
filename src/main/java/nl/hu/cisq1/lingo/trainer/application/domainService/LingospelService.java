package nl.hu.cisq1.lingo.trainer.application.domainService;

import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;

import java.util.List;

public interface LingospelService {
    LingoSpel save(LingoSpel lingospel);
    LingoSpel update(LingoSpel lingoSpel);
    LingoSpel findbyid(Long id);
    List<LingoSpel>findall();
    void delete(LingoSpel lingoSpel);
}
