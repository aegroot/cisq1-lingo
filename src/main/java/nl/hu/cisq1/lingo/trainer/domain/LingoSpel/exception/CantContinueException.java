package nl.hu.cisq1.lingo.trainer.domain.LingoSpel.exception;

public class CantContinueException  extends RuntimeException {
    public CantContinueException(){
        super("game has finished, player can not continue");
    }
}
