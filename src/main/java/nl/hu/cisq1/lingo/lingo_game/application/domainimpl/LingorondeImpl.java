package nl.hu.cisq1.lingo.lingo_game.application.domainimpl;

import nl.hu.cisq1.lingo.lingo_game.application.domainService.LingorondeService;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.SpringLingoRondeRepository;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.SpringRaadbeurtRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class LingorondeImpl implements LingorondeService {

    private SpringLingoRondeRepository lingoRondeRepository;

    public LingorondeImpl(SpringLingoRondeRepository lingoRondeRepository) {
        this.lingoRondeRepository = lingoRondeRepository;
    }

    @Override
    public List<LingoRonde> findall() {
        return lingoRondeRepository.findAll();
    }

    @Override
    public LingoRonde findById(Long id) {
        Optional<LingoRonde>lingoRonde=lingoRondeRepository.findById(id);

        return lingoRonde.orElse(null) ;
    }

    @Override
    public List<Raadbeurt> getRaadbeurts(LingoRonde lingoRonde) {
        Optional<LingoRonde> rondeOptional=lingoRondeRepository.findById(lingoRonde.getId());
        if (rondeOptional.isPresent()){
        LingoRonde lingoRonde1=rondeOptional.get();
            return lingoRonde1.getRaadbeurts();
        }
        return  null;
    }


    @Override
    public LingoRonde save(LingoRonde lingoRonde) {

        return lingoRondeRepository.save(lingoRonde);
    }

    @Override
    public LingoRonde update(LingoRonde lingoRonde) {
        Optional<LingoRonde>lingoRondedb=lingoRondeRepository.
                findById(lingoRonde.getId());
        if(lingoRondedb.isPresent()){
            LingoRonde lingoRonde1=lingoRondedb.get();
            lingoRonde1.setRaadbeurts(lingoRonde.getRaadbeurts());
            lingoRonde1.setWoord(lingoRonde.getWoord());
            return lingoRondeRepository.save(lingoRonde1);

        }



        return null;
    }

    @Override
    public void delete(LingoRonde lingoRonde) {
        lingoRondeRepository.delete(lingoRonde);

    }

    @Override
    public Word getWord(LingoRonde lingoRonde) {
        Optional<LingoRonde> rondeOptional=lingoRondeRepository.findById(lingoRonde.getId());
        if (rondeOptional.isPresent()){
            LingoRonde lingoRonde1=rondeOptional.get();
            return lingoRonde1.getWoord();
        }

        return  null;

    }
}
