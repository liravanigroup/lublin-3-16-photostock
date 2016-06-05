package pl.com.bottega.fun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by maciuch on 04.06.16.
 */
public interface FunList<T> {

    FunList<T> add(T el);

    boolean contains(T el);

    int size();

    T find(Predicate<T> predicate);

    T get(int i);

    boolean empty();

    <R> FunList<R> map(Function<T, R> mapper);

    <R> R reduce(R intial, BiFunction<R, T, R> reducotr);

    static <T> FunList<T> create() {
        return new EmptyList<>();
    }

    //TODO Praca domowa

    FunList<T> remove(T el);

    // Finds all elements on the list matching predicate
    FunList<T> filter(Predicate<T> predicate);

    // Iterates over all elements and calls consumer with each element
    void each(Consumer<T> consumer);

    // Concatenates list with the other list
    FunList<T> concat(FunList<T> other);

    // * returns sublist of elements starting at startIndex and ending at endIndex (inclusive)
    FunList<T> sublist(int startIndex, int endIndex);

}
