import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Lista <Integer> lista = new Lista <>(2);

        System.out.println(lista.ileAktualnieZmiesci());
        System.out.println(lista.size());

        lista.add(1);
        lista.add(2);
        lista.add(3);
       System.out.println(lista.ileAktualnieZmiesci());
        System.out.println(lista.size());

        lista.add(4);
        lista.add(5);
        System.out.println(lista.ileAktualnieZmiesci());
        System.out.println(lista.size());

        lista.remove(0);
        System.out.println(lista.get(0));
        lista.add(0, 1);
        System.out.println(lista.get(0));

        System.out.println(lista.contains(7));
        //System.out.println(lista.ileAktualnieZmiesci());
        //System.out.println(lista.size());

        //System.out.println(lista.contains(5)); // ?

        Iterator<Integer> iterator = lista.iterator();
        System.out.println(iterator.hasNext());



    }
}
