package nl.hu.cisq1.lingo.lingo_game.application.domainimpl;

import nl.hu.cisq1.lingo.lingo_game.application.domainService.RaadbeurtService;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.SpringRaadbeurtRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RaadbeurtImpl implements RaadbeurtService {

    private final SpringRaadbeurtRepository repository;

    public RaadbeurtImpl(SpringRaadbeurtRepository springRaadbeurtRepository) {
        this.repository=springRaadbeurtRepository;
    }

    @Override
    public Raadbeurt save(String woord, LingoRonde lingoRonde) {
        return repository.save(new Raadbeurt(woord, lingoRonde));
    }

    @Override
    public void delete(Raadbeurt raadbeurt) {
        repository.delete(raadbeurt);
    }


}
