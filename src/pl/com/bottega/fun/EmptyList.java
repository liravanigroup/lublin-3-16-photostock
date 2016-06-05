package pl.com.bottega.fun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by maciuch on 04.06.16.
 */
public class EmptyList<T> implements FunList<T> {
    @Override
    public FunList<T> add(T el) {
        return new NonEmptyList<>(el);
    }

    @Override
    public FunList<T> remove(T el) {
        return this;
    }

    @Override
    public FunList<T> filter(Predicate<T> predicate) {
        return null;
    }

    @Override
    public void each(Consumer<T> consumer) {

    }

    @Override
    public FunList<T> concat(FunList<T> other) {
        return null;
    }

    @Override
    public FunList<T> sublist(int startIndex, int endIndex) {
        return null;
    }

    @Override
    public boolean contains(T el) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T find(Predicate<T> predicate) {
        return null;
    }

    @Override
    public T get(int i) {
        return null;
    }

    public String toString() {
        return "";
    }

    public boolean empty() {
        return true;
    }

    @Override
    public <R> FunList<R> map(Function<T, R> mapper) {
        return new EmptyList<>();
    }

    @Override
    public <R> R reduce(R intial, BiFunction<R, T, R> reducotr) {
        return intial;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof FunList && ((FunList) other).empty();
    }

}
