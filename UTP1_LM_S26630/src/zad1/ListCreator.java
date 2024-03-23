/**
 *
 *  @author Lendzion Michał S26630
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana
    private List<T> list;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Selector<T> sel) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (sel.select(t)) {
                result.add(t);
            }
        }
        return new ListCreator<>(result);
    }

    public <S> List<S> mapEvery(Mapper<T, S> map) {
        List<S> result = new ArrayList<>();
        for (T t : list) {
            result.add(map.map(t));
        }
        return result;
    }
}
