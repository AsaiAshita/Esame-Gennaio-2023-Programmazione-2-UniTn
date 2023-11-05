package it.disi.unitn.surname;

import javafx.scene.shape.Rectangle;

import java.util.Comparator;
import java.util.Objects;

import static javafx.scene.paint.Color.AQUAMARINE;

public class Carta{
    int value;
    char seme;

    Carta(int a, char s){
        this.value = a;
        this.seme = s;
    }

    @Override
    public String toString() {
       return value+"-"+seme;
    }
    static class allComparator implements Comparator<Carta> {
        public int compare(Carta o1, Carta o2) {
            if (((Carta) o1).value < ((Carta) o2).value) {
                return -1;
            } else if (((Carta) o1).value > ((Carta) o2).value) {
                return 1;
            } else {
                if (((Carta) o1).seme < ((Carta) o2).seme) {
                    return -1;
                } else if (((Carta) o1).seme > ((Carta) o2).seme) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return value == carta.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
