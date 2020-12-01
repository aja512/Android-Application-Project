package com.example.vegito.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provides utility methods and decorators for {@link Collection} instances.
 */
public class CollectionUtils {

    private CollectionUtils() {
        // hide constructor
    }

    /**
     * Null-safe check if the specified collection is empty.
     * <p/>
     * Null returns true.
     *
     * @param coll the collection to check, may be null
     * @return true if empty or null
     */
    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * Returns the length for the supplied collection. If the collection is empty 0 will be returned
     *
     * @return the element, or null if the array is empty
     */
    public static int length(final Collection<?> coll) {
        return isEmpty(coll) ? 0 : coll.size();
    }

    /**
     * Returns the first element from the list. If the list is empty null will be returned.
     *
     * @param list the list
     * @param <T>  the type of Object in the list
     * @return the first element, or null if the list is empty
     */
    public static <T> T firstElement(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Filter the collection by applying a Predicate to each element. If the
     * predicate returns false, remove the element.
     * <p>
     * If the input collection or predicate is null, there is no change made.
     *
     * @param <T>       the type of object the {@link List} contains
     * @param target    the collection to get the input from, may be null
     * @param predicate the predicate to use as a filter, may be null
     * @return true if the collection is modified by this call, false otherwise.
     */
    public static <T> List<T> filter(List<T> target, IPredicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(target)) {
            return result;
        }
        for (T element : target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Filter the collection by applying a Predicate to each element till it finds the first.
     * If the predicate returns false, remove the element.
     * <p>
     * If the input collection or predicate is null, there is no change made.
     *
     * @param <T>       the type of object the {@link List} contains
     * @param target    the collection to get the input from, may be null
     * @param predicate the predicate to use as a filter, may be null
     * @return true if the collection is modified by this call, false otherwise.
     */
    public static <T> T filterFirst(List<T> target, IPredicate<T> predicate) {
        if (CollectionUtils.isEmpty(target)) {
            return null;
        }
        final List<T> result = new ArrayList<>();
        for (T element : target) {
            if (predicate.apply(element)) {
                result.add(element);
                break;
            }
        }
        return firstElement(result);
    }

    /**
     * Defines a functor interface implemented by classes that perform a predicate test on an object.
     * A Predicate is the object equivalent of an if statement. It uses the input object to return a
     * true or false value, and is often used in validation or filtering.
     *
     * @param <T> - the object to evaluate, should not be changed
     */
    public interface IPredicate<T> {
        boolean apply(T type);
    }
}
