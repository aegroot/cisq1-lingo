package nl.hu.cisq1.lingo.lingo_game.application.domainimpl;

import nl.hu.cisq1.lingo.lingo_game.application.domainService.LingospelService;
import nl.hu.cisq1.lingo.lingo_game.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.lingo_game.domain.LingoSpel.SpringLingoSpelRepository;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LingospelImpl implements LingospelService {
    private  final SpringLingoSpelRepository repository;

    public LingospelImpl(SpringLingoSpelRepository repository) {
        this.repository = repository;
    }

    @Override
    public LingoSpel save(LingoSpel lingospel) {
        return repository.save(lingospel);
    }

    @Override
    public LingoSpel update(LingoSpel lingoSpel) {
        Optional<LingoSpel>optional=repository.findById(lingoSpel.getId());
        if(optional.isPresent()){
            LingoSpel lingoSpel1=optional.get();
            lingoSpel1.setLingoRondes(lingoSpel.getLingoRondes());
            return  repository.save(lingoSpel1);
        }
        return null;
    }

    @Override
    public LingoSpel findbyid(Long id) {
        Optional<LingoSpel>optional=repository.findById(id);
       return optional.orElse(null);

    }

    @Override
    public List<LingoSpel> findall() {
        return repository.findAll();
    }

    @Override
    public void delete(LingoSpel lingoSpel) {
        repository.delete(lingoSpel);

    }
}
