package nl.hu.cisq1.lingo.lingo_game.domain.LingoSpel;

import nl.hu.cisq1.lingo.lingo_game.domain.lingoRonde.LingoRonde;

import javax.persistence.*;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LingoSpel {
    @OneToMany
    private List<LingoRonde> lingoRondes;
    private int currentLength;
    @OneToOne
    private LingoRonde currentRound;
    private Long id;

    public LingoSpel(LingoRonde lingoRonde){
        this.lingoRondes=new ArrayList<>();
        this.lingoRondes.add(lingoRonde);
        this.currentLength=5;
    }

    public LingoSpel() {
    }

    public boolean addLingoRonde(LingoRonde lingoRonde){
        if(currentLength==lingoRonde.getWoord().getLength()){
            currentLength=nextLength();
            lingoRondes.add(lingoRonde); return  true;
        }
        return  false;
       }


    public boolean checkDone(){
        for (LingoRonde lingorond:lingoRondes) {
            if(lingorond.checkoltooid()&&
                    lingorond.calcWord().toString()!=lingorond.getWoord().getValue()){return  true;}
        }
        return  false;
    }

    public int nextLength(){

        if(currentLength==5){return  6;}
        else  if(currentLength==6){return  7;}
        else  if(currentLength==7){return  5;}
         return 5;


    }


    public void setId(Long id) {
        this.id = id;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentRound(LingoRonde currentRound) {
        this.currentRound = currentRound;
    }

    public void setLingoRondes(List<LingoRonde> lingoRondes) {
        this.lingoRondes = lingoRondes;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public List<LingoRonde> getLingoRondes() {
        return lingoRondes;
    }

    public LingoRonde getCurrentRound() {
        return currentRound;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
