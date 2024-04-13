package zad1;

import java.util.function.Function;

public class InputConverter<T> {

    private T val;

    public InputConverter(T val) {
        this.val = val;
    }

    public <R>R convertBy(Function... functions) {
        Object val = this.val;
        Object res = null;
        for (Function fun : functions) {
            res = fun.apply(val);
            val = res;
        }
        return (R)res;
    }
}
