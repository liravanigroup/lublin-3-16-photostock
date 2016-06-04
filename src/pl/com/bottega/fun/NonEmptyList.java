package pl.com.bottega.fun;

import java.util.function.Predicate;

/**
 * Created by maciuch on 04.06.16.
 */
public class NonEmptyList<T> implements FunList<T> {

    private T head;
    private FunList<T> tail;

    public NonEmptyList(T el) {
        head = el;
        tail = new EmptyList<>();
    }

    public NonEmptyList(T head, FunList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public FunList<T> add(T el) {
        return new NonEmptyList<>(head, tail.add(el));
    }

    @Override
    public FunList<T> remove(T el) {
        return null;
    }

    @Override
    public boolean contains(T el) {
        return head.equals(el) || tail.contains(el);
    }

    @Override
    public int size() {
        return 1 + tail.size();
    }

    @Override
    public T find(Predicate<T> predicate) {
        return null;
    }

    @Override
    public T get(int i) {
        if(i == 0)
            return head;
        else
            return tail.get(i - 1);
    }

    public boolean empty() {
        return false;
    }

    public String toString() {
        if(tail.empty())
            return head.toString();
        else
            return head.toString() + ", " + tail.toString();
    }

}
