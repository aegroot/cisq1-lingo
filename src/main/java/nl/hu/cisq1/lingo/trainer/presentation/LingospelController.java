package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.domainimpl.LingospelImpl;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.CantContinueException;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.RoundNotFinishedException;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.FinishedException;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public LingoSpel newlingospel() {
        LingoRonde ronde = new LingoRonde(new Word(wordService.provideRandomWord(5)));
        return service.save(new LingoSpel((ronde)));
    }


    @PostMapping("nextronde")
    public LingoSpel nextronde(@RequestParam("id") Long id) {
        LingoSpel spel = service.findbyid(id);
        LingoRonde ronde = new LingoRonde(new Word(wordService.provideRandomWord(spel.nextLength())));
        try{ spel.addLingoRonde(ronde);
            service.update(spel);}
        catch (RoundNotFinishedException | CantContinueException r){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, r.getMessage());}

        return spel;
    }
    @PostMapping("addraadbeurt")
    public  LingoSpel addRonde(@RequestParam("id")Long id,@RequestParam("woord")String woord){
        LingoSpel lingoSpel=service.findbyid(id);

        try {
            LingoRonde lingoRonde= lingoSpel.getLastRonde();
            lingoRonde.addRaadBeurt(new Word(woord));
            service.update(lingoSpel);
        }
        catch (FinishedException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());}


        return  null;}


}
