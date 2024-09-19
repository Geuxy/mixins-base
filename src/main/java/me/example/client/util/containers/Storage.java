package me.example.client.util.containers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Storage<T> extends CopyOnWriteArrayList<T> {

    /*
     * Called when the client starts
     */
    public abstract void onStart();


    /*
     * Get an element by its class
     */
    public T getByClass(Class<? extends T> clazz) {
        return this.findFirst(e -> e.getClass() == clazz);
    }

    /*
     * Simplifies getting the first element that matches the predicate
     */
    public T findFirst(Predicate<T> predicate) {
        return this.stream().filter(predicate).findFirst().orElse(null);
    }

    /*
     * Simplifies collecting elements that matches the predicate
     */
    public List<T> collectIf(Predicate<T> predicate) {
        return this.stream().filter(predicate).collect(Collectors.toList());
    }

    /*
     * Fancy way to add elements manually.
     * You cannot add multiple of the same elements with this.
     */
    @SafeVarargs
    public final void addAll(Supplier<T>... suppliers) {
        Stream.of(suppliers).map(Supplier::get).forEach(this::addIfAbsent);
    }

}
