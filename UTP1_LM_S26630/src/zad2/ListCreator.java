package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana
    private List<T> list;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Predicate<T> pred) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (pred.test(t)) {
                result.add(t);
            }
        }
        return new ListCreator<>(result);
    }

    public <R> List<R> mapEvery(Function<T, R> fun) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(fun.apply(t));
        }
        return result;
    }
}
