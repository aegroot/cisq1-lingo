package nl.hu.cisq1.lingo.lingo_game.domain.raadBeurt;

import nl.hu.cisq1.lingo.lingo_game.domain.Mark;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;



public class Raadbeurt {

    public static List<Mark> compare(Word woord, Word word) {
        ArrayList<Mark> respons = new ArrayList<>();
        if (woord.getLength() != word.getValue().length()) {

            for (int i = 0; i < word.getValue().length(); i++) {
                respons.add(Mark.INVALID);
            }
        } else {

            for (int i = 0; i < word.getValue().length(); i++) {
                char character = word.getValue().charAt(i);

                if (woord.getValue().contains(String.valueOf(character))) {

                    if (character == woord.getValue().charAt(i)) {
                        respons.add(Mark.CORRECT);
                    } else {
                        respons.add(Mark.PRESENT);
                    }
                } else {
                    respons.add(Mark.ABSENT);
                }
            }
        }
        return respons;
    }


}
