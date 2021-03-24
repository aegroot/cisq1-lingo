package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringSpelRepository;
import nl.hu.cisq1.lingo.trainer.domain.Spel;
import nl.hu.cisq1.lingo.trainer.domain.Voortgang;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TrainerService {
    private WordService wordService;
    private SpringSpelRepository spelRepository;

    public TrainerService(WordService wordService) {
        this.wordService = wordService;
    }

    public Voortgang spelStarten() {
        String woord = this.wordService.provideRandomWord(5);

        Spel spel = new Spel();
        spel.startNieuweRonde(woord);

        this.spelRepository.save(spel);

        return spel.geefVoortgang();
    }

    public Voortgang woordRaden(Long spelId, String poging) {
        Optional<Spel> spelOptional = this.spelRepository.findById(spelId);
        if (!spelOptional.isPresent()) {
           throw new GameNotFoundException();
        }

        Spel spel = spelOptional.get();
        spel.raadWoord(poging);

        this.spelRepository.save(spel);

        return spel.geefVoortgang();
    }

    public Voortgang voortgangWeergeven(Long spelId) {
        Optional<Spel> spelOptional = this.spelRepository.findById(spelId);
        if (!spelOptional.isPresent()) {
            throw new GameNotFoundException();
        }

        Spel spel = spelOptional.get();

        return spel.geefVoortgang();
    }
}
