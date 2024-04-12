package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T value;

    public Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> of(T value) {
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<T> cons) {
        if (value != null) {
            cons.accept(value);
        }
    }

    public <R> Maybe<R> map(Function<T, R> func) {
        return value != null ? new Maybe<>(func.apply(value)) : new Maybe<>(null);
    }

    public T get() {
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent() {
        return value != null;
    }

    public T orElse(T defVal) {
        return value != null ? value : defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        return value == null || pred.test(value) ? this : new Maybe<>(null);
    }

    @Override
    public String toString() {
        return value != null ? "Maybe has value " + value : "Maybe is empty";
    }
}
