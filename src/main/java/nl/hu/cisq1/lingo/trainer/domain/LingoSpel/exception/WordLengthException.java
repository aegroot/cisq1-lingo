package nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception;

public class WordLengthException extends RuntimeException {
    public WordLengthException(Integer length) {
        super("wrong word length:"+length);

    }
}
