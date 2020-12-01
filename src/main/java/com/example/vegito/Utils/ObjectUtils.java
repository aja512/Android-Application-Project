package com.example.vegito.Utils;

/**
 * Operations on Object.
 * <p/>
 * This class tries to handle null input gracefully. An exception will generally not be thrown for a
 * null input. Each method documents its behaviour in more detail.
 */
public final class ObjectUtils {

    private ObjectUtils() {
        // Hide Constructor
    }

    /**
     * <p>Null safe comparison of Comparables.
     * {@code null} is assumed to be less than a non-{@code null} value.</p>
     *
     * @param <T> type of the values processed by this method
     * @param c1  the first comparable, may be null
     * @param c2  the second comparable, may be null
     * @return a negative value if c1 &lt; c2, zero if c1 = c2
     * and a positive value if c1 &gt; c2
     */
    public static <T extends Comparable<? super T>> int compare(final T c1, final T c2) {
        return compare(c1, c2, false);
    }

    /**
     * <p>Null safe comparison of Comparables.</p>
     *
     * @param <T>         type of the values processed by this method
     * @param c1          the first comparable, may be null
     * @param c2          the second comparable, may be null
     * @param nullGreater if true {@code null} is considered greater
     *                    than a non-{@code null} value or if false {@code null} is
     *                    considered less than a Non-{@code null} value
     * @return a negative value if c1 &lt; c2, zero if c1 = c2
     * and a positive value if c1 &gt; c2
     * @see java.util.Comparator#compare(Object, Object)
     */
    public static <T extends Comparable<? super T>> int compare(final T c1, final T c2,
                                                                final boolean nullGreater) {
        if (c1 == c2) {
            return 0;
        } else if (c1 == null) {
            return nullGreater ? 1 : -1;
        } else if (c2 == null) {
            return nullGreater ? -1 : 1;
        }
        return c1.compareTo(c2);
    }

    /**
     * Casts the supplied object to the supplied type if possible.
     *
     * @param o    the object to cast
     * @param type the type to cast the object to
     * @param <T>  the type of the class
     * @return the object cast as the type or null if the object is not of the type
     */
    public static <T> T asOptionalType(final Object o, final Class<T> type) {
        if (o == null) {
            return null;
        }
        if (!type.isAssignableFrom(o.getClass())) {
            return null;
        }
        @SuppressWarnings("unchecked") T co = (T) o;
        return co;
    }

    /**
     * Returns {@code true} if the provided reference is {@code null} otherwise
     * returns {@code false}.
     *
     * @param obj a reference to be checked against {@code null}
     * @return {@code true} if the provided reference is {@code null} otherwise
     * {@code false}
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

}
