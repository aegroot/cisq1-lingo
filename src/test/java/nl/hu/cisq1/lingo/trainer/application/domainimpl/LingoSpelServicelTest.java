package nl.hu.cisq1.lingo.trainer.application.domainimpl;

import nl.hu.cisq1.lingo.trainer.application.domainimpl.LingospelImpl;
import nl.hu.cisq1.lingo.trainer.data.SpringLingoSpelRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.LingoSpel;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.CantContinueException;
import nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception.RoundNotFinishedException;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception.FinishedException;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LingoSpelServicelTest {
    LingoRonde r1;
    LingoSpel spel;

    SpringLingoSpelRepository mockrepository=mock(SpringLingoSpelRepository.class);
    SpringWordRepository mockWordrespository=mock(SpringWordRepository.class);
    WordService wordService=new WordService(mockWordrespository);
    LingospelImpl service=new LingospelImpl(mockrepository,wordService);

    @BeforeEach
    void init(){
        r1=new LingoRonde(new Word("maken"));
        r1.addRaadBeurt(new Word("maken"));
        spel=new LingoSpel(r1);
        spel.setId(100L);
    }

    @Test
    @DisplayName("save")
    void saveTest(){
        when(mockrepository.save(spel)).thenReturn(spel);
        assertEquals(service.save(spel),spel);
    }
    @Test
    @DisplayName("nextronde")
    void nextRondeTest(){
        when(mockrepository.findById(spel.getId())).thenReturn(Optional.of(spel));
        when(mockWordrespository.findRandomWordByLength(spel.nextLength())).
                thenReturn(Optional.of(new Word("gevaar")));
        LingoRonde ronde=service.nextronde(spel.getId()).getLastRonde();
        assertEquals("gevaar",ronde.getWoord().getValue());
    }
    @Test
    @DisplayName("nextronde throws RoundNotFinishedException")
    public void nextRondernfeTest(){
        spel.addLingoRonde(new LingoRonde(new Word("gevaar")));
        when(mockrepository.findById(spel.getId())).thenReturn(Optional.of(spel));
        when(mockWordrespository.findRandomWordByLength(spel.nextLength())).
                thenReturn(Optional.of(new Word("aanvoer")));
        assertThrows(RoundNotFinishedException.class,()->{
            service.nextronde(spel.getId());
        });
    }
    @Test
    @DisplayName("nextronde throws CantContinueException ")
    public  void nextRondecc(){
        LingoRonde lingoRonde=new LingoRonde(new Word("gevaar"));
        for (int i = 0; i < 5; i++) {
            lingoRonde.addRaadBeurt(new Word("gemaar"));
        }
        spel.addLingoRonde(lingoRonde);

        when(mockrepository.findById(spel.getId())).thenReturn(Optional.of(spel));
        when(mockWordrespository.findRandomWordByLength(spel.nextLength())).
                thenReturn(Optional.of(new Word("aanvoer")));
        assertThrows(CantContinueException.class,()->{
            service.nextronde(spel.getId());
        });

    }

    @Test
    @DisplayName("addraadbeurt")
    public  void addraadbeurtTest(){

        spel.addLingoRonde(new LingoRonde(new Word("gevaar")));
        when(mockrepository.findById(spel.getId())).thenReturn(Optional.of(spel));
        when(mockrepository.save(spel)).thenReturn(spel);
        assertEquals("gemaar",service.addraadbeurt(100L,"gemaar").
                getLastRonde().getRaadbeurts().get(0).getIngeven_woord());
    }

    @Test
    @DisplayName("addraadbeurt throws finishedException")
    public  void addraadbeurtTfeTest(){
        when(mockrepository.findById(spel.getId())).thenReturn(Optional.of(spel));
        when(mockWordrespository.findRandomWordByLength(spel.nextLength())).
                thenReturn(Optional.of(new Word("gevaar")));
        assertThrows(FinishedException.class,()->{
            service.addraadbeurt(spel.getId(),"kaken");
        });



    }



}
