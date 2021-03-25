package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.domainimpl.LingospelImpl;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.Resultaat;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Resultaat nextronde(@RequestParam("id") Long id) {
        return new Resultaat(service.nextronde(id));

    }
    @PostMapping("addraadbeurt")
    public Resultaat addRonde(@RequestParam("id")Long id, @RequestParam("woord")String woord){
        LingoSpel lingoSpel=service.addraadbeurt(id, woord);
        return  new Resultaat(lingoSpel);}


}
