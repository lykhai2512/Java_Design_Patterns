package Adapter;

public interface Iterator<E> {

    boolean hasNext();

    E next();

    default void remove() {
        System.out.println("Remove operation is not supported.");
    }
    
}
