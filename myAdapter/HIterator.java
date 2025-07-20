//Diego Chiesurin - 2111553

package myAdapter;

/**
 * An iterator over a collection. {@code HIterator} takes the place of {@code Enumeration}
 * in the Java collections framework. Iterators differ from enumerations in two
 * ways: 
 * <ul>
 *	<li> Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
 *	<li> Method names have been improved.
 * </ul>
 */
public interface HIterator {
    
    /**
     * Returns {@code true} if the iteration has more elements. (In other
     * words, returns {@code true} if {@code next} would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the iterator has more elements.
     */
    public boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws java.util.NoSuchElementException if the iteration has no more elements.
     */
    public Object next();
    
    /**
     * Removes from the underlying collection the last element returned by the
     * iterator (optional operation). This method can be called only once per
     * every call to {@code next}. The behavior of an iterator is unspecified if
     * the underlying collection is modified during the iteration in
     * any way other than by calling this method.
     *
     * @throws UnsupportedOperationException if the {@code remove} operation
     *		  is not supported by this HIterator.
     * @throws IllegalStateException if the {@code next} method has not
     *		  yet been called, or the {@code remove} method has already
     *		  been called after the last call to the {@code next}
     *		  method.
     */
    public void remove();

}
