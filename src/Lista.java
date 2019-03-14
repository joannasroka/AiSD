import java.util.Iterator;

public class Lista <T> implements Iterable <T> {
    private T [] lista;
    private int aktualnyRozmiar;

    public Lista(int rozmiar) {
        this.lista = (T[]) new Object [rozmiar];
        aktualnyRozmiar = 0;
    }

    public int ileAktualnieZmiesci () {return lista.length;}

    public int size(){
        return aktualnyRozmiar;
    }

    public boolean isEmpty(){
        return (aktualnyRozmiar==0);
    }

    public boolean contains (Object o){
         Iterator<T> iterator = this.iterator();
        /*if(isEmpty()) return false;
        for(int i=0; i<lista.length; i++){
            if(lista[i].equals(o)) return true;
        }
        return false;
        */
        while(iterator.hasNext()){
            if(iterator.next().equals(o)) return true;
        }
        return false;
    }

    public int indexOf (Object o){
        Iterator<T> iterator = this.iterator();
        if(!contains(o)) return -1;
        else{/*
            for(int i=0; i<lista.length; i++){
                if(lista[i].equals(o)) return i;
            }*/
            int i=0;
            while(iterator.hasNext()){
                if(iterator.next().equals(o)) return i;
                i++;
            }
        }
        return -1;
    }

    public T get (int index){
        if(index>=aktualnyRozmiar) return null;
        else return lista[index];
    }

    public T set (int index, T element){
        if(index>=aktualnyRozmiar) return null;
        else lista[index]=element;
        return element;
    }

    public boolean add (T element){
        if(aktualnyRozmiar<lista.length) {
            lista[aktualnyRozmiar]=element;
            aktualnyRozmiar++;
        }
        else {
            T [] nowaLista = (T[]) new Object [aktualnyRozmiar*2];
            for(int i=0; i<aktualnyRozmiar; i++){
                nowaLista[i] = lista[i];
            }
            nowaLista[aktualnyRozmiar]=element;
            lista = nowaLista;
            aktualnyRozmiar++;
        }
        return true;
    }

    public void add (int index, T element){
        T [] nowaLista;
        if(aktualnyRozmiar<lista.length){
             nowaLista = (T[]) new Object[aktualnyRozmiar];
        }
        else {
            nowaLista = (T[]) new Object [aktualnyRozmiar*2];
        }

        for(int i=0; i<index; i++){
            nowaLista[i]=lista[i];
        }
        nowaLista[index]=element;
        for(int j=index+1; j<aktualnyRozmiar; j++){
            nowaLista[j]=lista[j-1];
        }
        lista = nowaLista;
        aktualnyRozmiar++;
    }

    public boolean remove (int index){
        if(index>=aktualnyRozmiar) return false;
        else {
            T [] nowaLista = (T[]) new Object [lista.length];
            for(int i=0; i<index; i++){
                nowaLista[i] = lista[i];
            }
            for(int j=index+1; j<aktualnyRozmiar; j++){
                nowaLista[j-1] = lista[j];
            }
            aktualnyRozmiar--;
            lista=nowaLista;
            return true;
        }
    }

    public T remove (Object o){
        T pomocniczy;
        if(!contains(o)) return null;
        pomocniczy = (T) o;
        remove(indexOf(o));
        return pomocniczy;
    }

    public void clear (){
        this.lista =  (T[] )new Object [0];
        aktualnyRozmiar=0;
    }

    @Override
    public Iterator<T> iterator(){
        Iterator<T> iterator = new Iterator<T>() {
            public int aktualnyIndeks = 0;

            @Override
            public boolean hasNext() {
                return(aktualnyIndeks<aktualnyRozmiar-1);
            }


            @Override
            public T next() {
                if(!hasNext()) return null;
                else {
                    return lista[aktualnyIndeks++];
                }
            }

            @Override
            public void remove(){
                Lista.this.remove(--aktualnyIndeks);
            }
        };
        return  iterator;
    }


}

