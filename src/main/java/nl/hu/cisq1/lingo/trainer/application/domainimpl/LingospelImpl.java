package nl.hu.cisq1.lingo.trainer.application.domainimpl;

import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.data.SpringLingoSpelRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.CantContinueException;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.RoundNotFinishedException;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.FinishedException;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.presentation.RandomWordController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LingospelImpl{
    private final SpringLingoSpelRepository repository;
    private  final WordService wordRepository;

    public LingospelImpl(SpringLingoSpelRepository repository, WordService wordRepository) {
        this.repository = repository;
        this.wordRepository = wordRepository;
    }


    public LingoSpel save(LingoSpel lingospel) {
        return repository.save(lingospel);
    }

    public LingoSpel update(LingoSpel lingoSpel) {
        Optional<LingoSpel> optional = repository.findById(lingoSpel.getId());
        LingoSpel lingoSpel1= optional.orElseThrow();
        lingoSpel1.setLingoRondes(lingoSpel.getLingoRondes());
        return repository.save(lingoSpel1);
    }

    public LingoSpel nextronde(Long id){
        Optional<LingoSpel> optional = repository.findById(id);
        LingoSpel spel= optional.orElseThrow();
        LingoRonde ronde = new LingoRonde(new Word(wordRepository.provideRandomWord(spel.nextLength())));
        spel.addLingoRonde(ronde);
        update(spel);
        return  spel;
    }

    public LingoSpel addraadbeurt(Long id,String woord){
        Optional<LingoSpel> lingoSpelOptional=repository.findById(id);
       LingoSpel lingoSpel= lingoSpelOptional.orElseThrow();
        LingoRonde lingoRonde= lingoSpel.getLastRonde();
        lingoRonde.addRaadBeurt(new Word(woord));
        return update(lingoSpel);

    }

}
