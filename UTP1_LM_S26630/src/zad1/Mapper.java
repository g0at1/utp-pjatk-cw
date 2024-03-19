/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;


public interface Mapper<T, R> { // Uwaga: interfejs musi być sparametrtyzowany
    R map(T item);
}  
