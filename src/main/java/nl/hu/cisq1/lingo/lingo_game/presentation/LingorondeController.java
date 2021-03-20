package nl.hu.cisq1.lingo.lingo_game.presentation;

import nl.hu.cisq1.lingo.lingo_game.application.domainimpl.LingorondeImpl;
import nl.hu.cisq1.lingo.lingo_game.application.domainimpl.RaadbeurtImpl;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt.Raadbeurt;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("lingoronde")
public class LingorondeController {
   private final LingorondeImpl service;
   private  final WordService wordService;
   private final RaadbeurtImpl raadbeurtservice;

    public LingorondeController(LingorondeImpl service, WordService wordService, RaadbeurtImpl raadbeurtservice) {
        this.service = service;
        this.wordService = wordService;
        this.raadbeurtservice = raadbeurtservice;
    }
    @PostMapping("/newronde/{length}")
    public LingoRonde save(@PathVariable("length") int length) throws WordLengthNotSupportedException {


        String w1=wordService.provideRandomWord(length);
        return  service.save(new LingoRonde(new Word(w1)));
    }

    @GetMapping("/findall")
    public List<LingoRonde> findall()
    {return  service.findall();}

    @GetMapping("/findById/{id}")
    public LingoRonde findById(@PathVariable("id") Long id ){
        return  service.findById(id);
    }

    @PostMapping("/addraadbeurt")
    public Raadbeurt addRaadbeurt(@RequestParam("woord") String woord, @RequestParam("id")Long id){
        LingoRonde lingoRonde1=service.findById(id);
        lingoRonde1.addRaadBeurt(new Word(woord));
        return raadbeurtservice.save(woord,lingoRonde1);
    }

    @GetMapping("/berekenPunten")
    public int berekenPunten(@RequestParam("id") Long id){
        LingoRonde lingoRonde1=service.findById(id);
        return lingoRonde1.berekenPunten();
    }
    @GetMapping("calcWord")
    public ArrayList<Character> calcWord(@RequestParam("id") Long id){
        LingoRonde lingoRonde1=service.findById(id);
        return  lingoRonde1.calcWord();
    }
    @GetMapping("getRaadbeurts")
    public List<Raadbeurt>getraadbeurts(@RequestParam("id") Long id) {
        LingoRonde lingoRonde=service.findById(id);
        return  service.getRaadbeurts(lingoRonde);}

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        service.delete(service.findById(id));
    }

}
