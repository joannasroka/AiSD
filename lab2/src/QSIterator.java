import java.util.Iterator;
import java.util.NoSuchElementException;

public class QSIterator <T> implements Iterator<T> {

    Element <T> current;

    QSIterator(Element <T> element){
        current = element;
    }

    @Override
    public boolean hasNext() {
        return (current!=null);
    }

    @Override
    public T next() {
        if(!hasNext()) throw new NoSuchElementException();
        T value = current.getValue();
        current = current.getNext();
        return value;
    }
}
