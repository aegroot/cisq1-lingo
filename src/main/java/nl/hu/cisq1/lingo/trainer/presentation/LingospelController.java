package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.domainimpl.LingospelImpl;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.Voortgang;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.CantContinueException;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.RoundNotFinishedException;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.WordLengthException;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.FinishedException;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("lingospel")
public class LingospelController {
    private final WordService wordService;
    private final LingospelImpl service;

    public LingospelController(WordService wordService, LingospelImpl service) {
        this.wordService = wordService;
        this.service = service;
    }



    @PostMapping("create")
    public Voortgang newlingospel() {
        LingoRonde ronde = new LingoRonde(new Word(wordService.provideRandomWord(5)));
        return new Voortgang(service.save(new LingoSpel((ronde))));
    }

    @PostMapping("nextronde")
    public Voortgang nextronde(@RequestParam("id") Long id) {
            return new Voortgang(service.nextronde(id));
    }
    @GetMapping("findall")
    public List<Voortgang>findall(){
        service.findall();
        List<Voortgang>voortgangs=new ArrayList<>();
        for (LingoSpel spel:service.findall()){
            voortgangs.add(new Voortgang(spel));
        }
        return  voortgangs;
    }

    
    @PostMapping("addraadbeurt")
    public Voortgang addRonde(@RequestParam("id")Long id, @RequestParam("woord")String woord){

            LingoSpel lingoSpel=service.addraadbeurt(id, woord);
            return  new Voortgang(lingoSpel);

    }

}
