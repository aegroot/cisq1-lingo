package nl.hu.cisq1.lingo.trainer.application.domainimpl;

import nl.hu.cisq1.lingo.trainer.application.domainService.RaadbeurtService;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.trainer.data.SpringRaadbeurtRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RaadbeurtImpl implements RaadbeurtService {

    private final SpringRaadbeurtRepository repository;

    public RaadbeurtImpl(SpringRaadbeurtRepository springRaadbeurtRepository) {
        this.repository = springRaadbeurtRepository;
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
