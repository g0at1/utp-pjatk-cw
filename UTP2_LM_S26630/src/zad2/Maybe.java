package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T val;

    public Maybe(T val) {
        this.val = val;
    }

    public static <T> Maybe<T> of(T val) {
        return new Maybe<>(val);
    }

    public void ifPresent(Consumer<T> cons) {
        if (val != null) {
            cons.accept(val);
        }
    }

    public <R> Maybe<R> map(Function<T, R> func) {
        return val != null ? new Maybe<>(func.apply(val)) : new Maybe<>(null);
    }

    public T get() {
        if (val != null) {
            return val;
        } else {
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent() {
        return val != null;
    }

    public T orElse(T defVal) {
        return val != null ? val : defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        return val == null || pred.test(val) ? this : new Maybe<>(null);
    }

    @Override
    public String toString() {
        return val != null ? "Maybe has value " + val : "Maybe is empty";
    }
}
