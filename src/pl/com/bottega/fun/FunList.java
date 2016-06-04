package pl.com.bottega.fun;

import java.util.function.Predicate;

/**
 * Created by maciuch on 04.06.16.
 */
public interface FunList<T> {

    FunList<T> add(T el);

    FunList<T> remove(T el);

    boolean contains(T el);

    int size();

    T find(Predicate<T> predicate);

    T get(int i);

    boolean empty();

}
