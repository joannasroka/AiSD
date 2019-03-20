import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
    private Element<T> head;
    private Element<T> tail;
    private int size;

    public Queue() {
        head = tail;
        size = 0;
    }

    public void enqueue(T value) {
        Element<T> element = new Element<>(value);
        if (size == 0) {
            head = element;
            tail = element;
        }
        else {
            element.setPrev(tail);
            tail.setNext(element);
            tail = element;
        }
        size++;
    }

    public T dequeue() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        T value = head.getValue();
        head = head.getNext();
        head.setPrev(null);
        if(size == 1) tail = null;
        size--;
        return value;
    }

    public T peek() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        return head.getValue();
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void clear() {
        size = 0;
        head = tail;
    }

    public int size() {
        return size;
    }

    public boolean contains(T element) {
        for (T t : this) {
            if (t.equals(element)) return true;
        }
        return false;
    }


    public int indexOf(T element) {
        int index = 0;
        for (T t : this) {
            if (t.equals(element)) return index;
            index++;
        }
        return -1;
    }

    public T get(int index) {
        if (index >= size) throw new IndexOutOfBoundsException();
        int aux = 0; // auxiliary - pomocniczy
        for (T t : this) {
            if (aux == index) return t;
            aux++;
        }
        return null;
    }


    public T set(int index, T value) {
        if (index >= size) throw new IndexOutOfBoundsException();
        Element<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        T oldValue = current.getValue();
        current.setValue(value);
        return oldValue;
    }


    @Override
    public Iterator<T> iterator() {
        return new QSIterator<>(head);
    }


}
