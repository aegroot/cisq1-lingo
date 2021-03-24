package nl.hu.cisq1.lingo.trainer.application.domainimpl;

import nl.hu.cisq1.lingo.trainer.application.domainService.LingospelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class LingoSpelServicelTest {


    LingospelImpl service=mock(LingospelImpl.class);

    @ParameterizedTest
    @DisplayName("")
    @MethodSource("sav")
    void save(){
        //service.save();

    }

    static Stream<Arguments> sav(){
        return Stream.of(
        );
    }

}
