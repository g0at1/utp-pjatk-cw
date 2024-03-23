/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;


public interface Selector<T> { // Uwaga: interfejs musi być sparametrtyzowany
    boolean select(T item);
}
