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
        if (this.val != null) {
            cons.accept(this.val);
        }
    }

    public <R> Maybe<R> map(Function<T, R> func) {
        return this.val != null ? new Maybe<>(func.apply(this.val)) : new Maybe<>(null);
    }

    public T get() {
        if (this.val != null) {
            return this.val;
        } else {
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent() {
        return this.val != null;
    }

    public T orElse(T defVal) {
        return this.val != null ? this.val : defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        return this.val == null || pred.test(this.val) ? this : new Maybe<>(null);
    }

    @Override
    public String toString() {
        return this.val != null ? "Maybe has value " + this.val : "Maybe is empty";
    }
}
