package nl.hu.cisq1.lingo.lingo_game.application.domainimpl;

import nl.hu.cisq1.lingo.lingo_game.application.domainService.LingorondeService;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.SpringLingoRondeRepository;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LingorondeImplTest {
    LingorondeService lingo=  mock(LingorondeImpl.class);
    LingoRonde lingoRonde=new LingoRonde(new Word("wagen"));


    @Test
    @DisplayName("save")
    public void saveTest(){

       when(lingo.save(lingoRonde)).thenReturn(lingoRonde);
       assertEquals(lingo.save(lingoRonde),lingoRonde);


    }
    @Test
    @DisplayName("update")
    public void update(){
        List<Word> lingoRondetemp=new ArrayList<>();
        lingoRonde.addRaadBeurt(new Word("maten"));
        lingoRondetemp.add(new Word("maten"));
        lingoRonde.addRaadBeurt(new Word("maken"));
        System.out.println(lingoRondetemp);
        System.out.println(lingoRonde.getRaadbeurts());
        when(lingo.update(lingoRonde)).thenReturn( lingoRonde);
        assertNotEquals(lingoRondetemp,
                lingo.update(lingoRonde).getRaadbeurts());
        assertEquals(lingoRondetemp.get(0).getValue(),
                lingoRonde.getRaadbeurts().get(0).getValue());


    }

    @Test
    @DisplayName("findAll")
    public void findAllTest(){
        List<LingoRonde>lingoRonden=new ArrayList<>();
        lingoRonden.add(lingoRonde);
        System.out.println(lingo.findall());
      when(lingo.findall()).thenReturn(lingoRonden);
      assertNotNull(lingo.findall());

    }
    @Test
    @DisplayName("delete")
    public void delete(){
        lingo.delete(lingoRonde);
    }




    }