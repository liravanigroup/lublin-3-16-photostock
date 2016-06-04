package pl.com.bottega.fun;

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

}
