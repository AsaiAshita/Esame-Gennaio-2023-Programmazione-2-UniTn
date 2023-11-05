package it.disi.unitn.surname;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Carta>{
    Deck(){
        char [] seme = {'F', 'P', 'Q', 'C'};
        for(int i=1; i<5; i++){
            for(int j=0; j<4; j++){
                this.add(new Carta(i, seme[j]));
            }
        }
        this.add(new Carta(0, 'J'));
    }

    void shuffle(){
        Collections.shuffle(this);
    }

    void ordina(){
        Collections.sort(this, new Carta.allComparator());
    }
}
