package it.disi.unitn.surname;

import java.util.ArrayList;
import java.util.Collections;

public class Mano extends ArrayList {
    Mano(){
        ArrayList<Carta> hand = new ArrayList<>();
    }

    void sort(){
        Collections.sort(this, new Carta.allComparator());
    }
    boolean rmPair(Mano m){
        boolean discarded = false;
        int i=0;
        while(i<m.size()-1){
            if(m.get(i).equals(m.get(i+1))){
                m.remove(i);
                m.remove(i);
                i = Math.max(0, (i-1));
                discarded = true;
            }
            else{
                i++;
            }
        }
        return discarded;
    }
}
