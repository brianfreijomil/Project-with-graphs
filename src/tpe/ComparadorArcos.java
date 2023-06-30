package tpe;

import java.util.Comparator;

public class ComparadorArcos implements Comparator<Arco> {

    @Override
    public int compare(Arco o1, Arco o2) {
        Integer a = (Integer) o1.getEtiqueta();
        Integer a2 = (Integer) o2.getEtiqueta();
        return a - a2;
    }

    
}
