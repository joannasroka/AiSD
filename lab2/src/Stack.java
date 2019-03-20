import sun.invoke.empty.Empty;

import java.lang.annotation.ElementType;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private Element<T> top;
    private int size;

    public Stack() {
        size = 0;
        top = null;
    }

    public void push(T value) {
        Element<T> element = new Element<>(value);
        element.setNext(top);
        top = element;
        size++;
    }

    public T pop() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        T value = top.getValue();
        top = top.getNext();
        size--;
        return value;
    }

    public T peek() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        return top.getValue();
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public boolean contains(T element){
        for (T t : this) {
            if(t.equals(element)) return true;
        }
        return false;
    }

    public int indexOf(T element){
        int index=0;
        for (T t: this){
            if(t.equals(element)) return index;
            index++;
        }
        return -1;
    }

    public T get (int index){
        if(index>=size) throw new IndexOutOfBoundsException();
        int aux = 0; // auxiliary - pomocniczy
        for(T t: this){
            if (aux == index) return t;
            aux++;
        }
        return null;
    }

    public T set (int index, T value){
        if (index>=size) throw new IndexOutOfBoundsException();
        Element <T> current = top;
        for(int i=0; i<index; i++){
            current = current.getNext();
        }
        T oldValue = current.getValue();
        current.setValue(value);
        return oldValue;
    }


    @Override
    public Iterator<T> iterator() {
        return new QSIterator<>(top);
    }
}
