package nl.hu.cisq1.lingo.lingo_game.presentation;

import nl.hu.cisq1.lingo.lingo_game.application.domainimpl.LingorondeImpl;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("lingoronde")
public class LingorondeController {
   private final LingorondeImpl service;

    public LingorondeController(LingorondeImpl service) {
        this.service = service;
    }
    @PostMapping("/save")
    public LingoRonde save(@RequestBody LingoRonde lingoRonde){
        return  service.save(lingoRonde);
    }
    @GetMapping("/findall")
    public List<LingoRonde> findall()
    {return  service.findall();}

    @GetMapping("/findById/{id}")
    public LingoRonde findById(@PathVariable("id") Long id ){
        return  service.findById(id);
    }


    @PostMapping("/addraadbeurt")
    public LingoRonde addRaadbeurt(@RequestBody Word woord, LingoRonde lingoRonde){
        LingoRonde lingoRonde1=service.findById(lingoRonde.getId());
        lingoRonde1.addRaadBeurt(woord);
        return service.update(lingoRonde1);
    }

    @GetMapping("/berekenPunten")
    public int berekenPunten(@RequestBody LingoRonde lingoRonde){
        LingoRonde lingoRonde1=service.findById(lingoRonde.getId());
        return lingoRonde1.berekenPunten();
    }
    @GetMapping("calcWord")
    public ArrayList<Character> calcWord(@RequestBody LingoRonde lingoRonde){
        LingoRonde lingoRonde1=service.findById(lingoRonde.getId());
        return  lingoRonde1.calcWord();

    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody LingoRonde lingoRonde) {
        service.delete(lingoRonde);
    }

}
