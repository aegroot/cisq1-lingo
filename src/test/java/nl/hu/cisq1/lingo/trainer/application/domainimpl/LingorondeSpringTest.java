package nl.hu.cisq1.lingo.trainer.application.domainimpl;


import nl.hu.cisq1.lingo.trainer.domain.lingoRonde.LingoRonde;
import nl.hu.cisq1.lingo.trainer.data.SpringLingoRondeRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LingorondeSpringTest {
    @Autowired
    private LingorondeImpl service;
    @MockBean
    private SpringLingoRondeRepository repository;


    @Test
    @DisplayName("right word")
    public  void quesTestTrue(){
        when(repository.findById(1L))
                .thenReturn(Optional.of(new LingoRonde(new Word("aanvoer"))));
        LingoRonde lingoRonde= service.findById(1L);
        Word word=new Word("aanvoer");
        lingoRonde.addRaadBeurt(word);
        assertTrue(lingoRonde.checkvoltooid());

    }
    @Test
    @DisplayName("right wrong word, right length")
    public  void quesTestFalse(){
        when(repository.findById(1L))
                .thenReturn(Optional.of(new LingoRonde(new Word("aanvoer"))));
        LingoRonde lingoRonde= service.findById(1L);
        Word word=new Word("uitvoer");
        lingoRonde.addRaadBeurt(word);
        ArrayList<Character>list=new ArrayList<>();
        list.add('a');
        list.add(' ');
        list.add(' ');
        list.add('v');
        list.add('o');
        list.add('e');
        list.add('r');
        System.out.println(list.toString());
        assertEquals(list,lingoRonde.calcWord());

    }
    @Test
    @DisplayName("wrong word,wrong length")
    public  void quesTestInvalid(){

    }
    @Test
    @DisplayName("findall")
    public void findall(){
        ArrayList<LingoRonde> list=new ArrayList<>();
        list.add(new LingoRonde(new Word("aagje")));
        list.add(new LingoRonde(new Word("aaibaar")));
        when(repository.findAll()).thenReturn(list);
        assertEquals(list,repository.findAll());
    }



}
