package nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception;

public class RoundNotFinishedException extends RuntimeException{
    public RoundNotFinishedException(){
        super("previous round is not finished");
    }
}
