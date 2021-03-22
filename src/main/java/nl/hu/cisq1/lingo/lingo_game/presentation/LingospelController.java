package nl.hu.cisq1.lingo.lingo_game.presentation;

import nl.hu.cisq1.lingo.lingo_game.application.domainimpl.LingorondeImpl;
import nl.hu.cisq1.lingo.lingo_game.application.domainimpl.LingospelImpl;
import nl.hu.cisq1.lingo.lingo_game.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lingospel")
public class LingospelController {
    private final WordService wordService;
    private  final LingorondeImpl lingorondeservice;
    private final LingospelImpl service;

    public LingospelController(WordService wordService, LingorondeImpl lingorondeservice, LingospelImpl service) {
        this.wordService = wordService;
        this.lingorondeservice = lingorondeservice;
        this.service = service;
    }


    @PostMapping("create")
    public LingoSpel newlingospel(){
        LingoRonde ronde=new LingoRonde(new Word(wordService.provideRandomWord(5)));
        lingorondeservice.save(ronde);
        return service.save(new LingoSpel((ronde)));
    }


    @PostMapping("nextronde")
    public LingoSpel nextronde(Long id){
        LingoSpel spel=service.findbyid(id);
        LingoRonde ronde=new LingoRonde(new Word(wordService.provideRandomWord(spel.nextLength())));
        spel.addLingoRonde(ronde);
        lingorondeservice.save(ronde);
        service.update(spel);
        return  spel;
    }


}
