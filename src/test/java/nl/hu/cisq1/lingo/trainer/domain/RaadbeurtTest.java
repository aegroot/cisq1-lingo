package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class RaadbeurtTest {
    @ParameterizedTest
    @DisplayName("hint geven op basis van raadbeurt en vorige hint")
    @MethodSource("hintGevenExamples")
    void hintGeven(String poging, List<Mark> marks, String vorigeHint, String expected) {
           Raadbeurt raadbeurt = new Raadbeurt(poging, marks);
           String nieuweHint = raadbeurt.geefHint(vorigeHint);
           assertEquals(expected, nieuweHint);
    }

    static Stream<Arguments> hintGevenExamples() {
        return Stream.of(
                Arguments.of("woord", List.of(CORRECT, CORRECT, ABSENT, ABSENT, ABSENT), "w....", "wo..."),
                Arguments.of("woord", List.of(CORRECT, CORRECT, CORRECT, PRESENT, ABSENT), "w....", "woo..")
        );
    }
}