//Diego Chiesurin - 2111553

package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import myExceptions.IllegalStateException;
import myExceptions.UnsupportedOperationException;

/**
 * Adapter implementation of the {@code HMap} interface using the {@code Hashtable}
 * class provided in CLDC 1.1 as the underlying data structure.
 * <p>
 * This class is part of a compatibility layer that adapts a subset of the Java 2 Collections
 * Framework (as defined in J2SE 1.4.2) for use in the constrained Java ME environment, 
 * specifically CLDC 1.1.
 * <p>
 * The adapter provides a full implementation of the {@code HMap} interface, replicating
 * the behavior and contract of the {@code Map} interface from J2SE 1.4.2. All optional operations
 * are supported, and view-based backing behavior is preserved where applicable.
 * <p>
 * <strong>Important:</strong> this implementation of {@code HMap} can contain itself as a key, 
 * though it is highly reccomended against it. Generally it is not reccomended to use
 * a mutable object as a key. Even using this map as a value can cause errors.
 * <p>
 * <strong>Important:</strong> Due to limitations of the CLDC 1.1 {@code Hashtable} implementation,
 * both keys and values <em>must not</em> be {@code null}. Any attempt to insert a {@code null} key 
 * or value will result in a {@code NullPointerException}.
 * <p>
 * This adapter is implemented in compliance with the Test Driven Development methodology.
 * Unit tests are provided separately in the {@code myTest} package.
 *
 * @see HMap
 * @see java.util.Hashtable (CLDC 1.1)
 */
public class MapAdapter implements HMap {

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------- NESTED CLASSES -------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/**
	 * A simple implementation of the {@code HMap.HEntry} interface representing
	 * a key-value mapping stored in the {@code MapAdapter}.
	 * <p>
	 * <strong>Note:</strong> Since {@code Hashtable} in CLDC 1.1 does not allow
	 * {@code null} keys or values, this {@code Entry} will never contain {@code null}
	 * for either the key or the value.
	 *
	 * @see HMap.HEntry
	 */
	protected final class Entry implements HMap.HEntry {

		/**
		 * The key associated with this entry.
		 */
		private final Object key;
		/**
		 * The value associated with this entry.
		 */
		private Object value;

		/**
		 * Constructs a new {@code Entry} with the specified key and value.
		 *
		 * @param key   the key for this entry; must not be {@code null}
		 * @param value the value for this entry; must not be {@code null}
		 * @throws NullPointerException if either key or value is {@code null}
		 */
		private Entry(Object key, Object value){
			if(key == null || value == null)
				throw new NullPointerException("key or value must not be null.");
			this.key = key;
			this.value = value;
		}

		/**
		 * Compares the specified object with this entry for equality. 
		 * Returns {@code true} if the given object is also an {@code HMap.HEntry}
		 * and the two entries represent the same mapping. More formally, 
		 * two entries {@code e1} and {@code e2} represent the same mapping if
		 * {@code e1.getKey().equals(e2.getKey()) && e1.getValue().equals(e2.getValue())}.
		 * <p>
		 * This implementation guarantees that neither the key nor the value will be {@code null}
		 *
		 * @param o object to be compared for equality with this entry
		 * @return {@code true} if the specified object is equal to this entry
		 */
		public boolean equals(Object o) {
			if (!(o instanceof HEntry) || o == null) {
				return false;
			}

			HEntry e = (HEntry)o;
			
			return  this.getKey().equals(e.getKey())  
					&&
					this.getValue().equals(e.getValue());
		}

		/**
		 * Returns the key corresponding to this entry.
		 *
		 * @return the non-null key associated with this entry
		 */
		public Object getKey(){
			return this.key;
		}

		/**
		 * Returns the value corresponding to this entry.
		 *
		 * @return the non-null value associated with this entry
		 */
		public Object getValue(){
			return this.value;
		}

		/**
		 * Returns the hash code value for this map entry. The hash code of an entry is defined as the xor
		 * of the hash code of its key and the hash code of its value.
		 * <p>
		 * This implementation guarantees that neither the key nor the value will be {@code null}
		 *
		 * @return the hash code value for this map entry
		 */
		public int hashCode(){
			return  this.getKey().hashCode() 
					^ 
					this.getValue().hashCode();
		}
		/**
		 * Replaces the value corresponding to this entry with the specified value.
		 * <p>
		 * If this entry was previously removed from the underlying {@code MapAdapter}
		 * (e.g. via an iterator), calling this method will reinsert the entry into the
		 * map with the same key and the new value.
		 * <p>
		 * While the behavior of calling {@code setValue} on a removed entry is
		 * undefined in the standard J2SE 1.4.2 specification, this implementation
		 * explicitly reinserts the entry into the underlying map. This behavior is
		 * consistent with the overall design of {@code MapAdapter}.
		 *
		 * @param value new value to be stored; must not be {@code null}
		 * @return the old value associated with this entry
		 * @throws NullPointerException if the specified value is {@code null}. This exception
 		 * is thrown by the underlying {@code Hashtable.put()} method, as CLDC 1.1 does not
 		 * allow null keys or values.
 		 */
		public Object setValue(Object value){
			Object oldValue = hashTable.put(this.key, value);
			this.value = value;
			return oldValue;
		}

		/**
		 * Returns a string representation of this entry.
		 *
		 * @return a string representation of this entry
		 */
		public String toString() {
			return "" + key + "=" + value;
		}
	}

	/*-------------------------------------------------------------------------------------------*/
	/*----------------------------------- ITERATORS HIERARCHY -----------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/**
	 * An iterator over the entries contained in the {@code MapAdapter}'s entry set.
	 * <p>
	 * This class implements the {@code HIterator} interface and provides sequential
	 * access to each {@code HMap.HEntry} stored in the underlying {@code Hashtable}.
	 * <p>
	 * The iterator is backed by the map, meaning that modifications to the map during
	 * iteration (except through this iterator’s own {@code remove()} method) may result
	 * in inconsistent behavior or runtime exceptions.
	 * <p>
	 * This implementation supports the optional {@code remove()} operation, which removes
	 * from the map the last entry returned by the iterator. The {@code remove()} method
	 * may be called only once per call to {@code next()}, and only after a call to
	 * {@code next()}; otherwise, it will throw an {@code IllegalStateException}.
	 * <p>
	 * If an entry is removed during iteration and later its {@code setValue()} method is
	 * called, the entry will be reinserted into the map with the new value. This behavior
	 * is consistent with the semantics of {@code MapAdapter}, and allowed since the standard
	 * J2SE 1.4.2 specification does not define behavior for such cases.
	 *
	 * @see HIterator
	 * @see HMap.HEntry
	 * @see MapAdapter#entrySet()
	 */
	protected class EntrySetIterator implements HIterator {
		/**
		 * The enumeration used to iterate
		 */
		protected Enumeration enumeration;
		/**
		 * The element that should be removed when calling remove()
		 */
		protected Object current;

		/**
		 * Constructs a new {@code EntrySetIterator} for iterating over the entries
		 * in the {@code MapAdapter}.
		 * <p>
		 * The iterator is backed by the underlying {@code Hashtable} and uses its
		 * key enumeration to traverse the map. The state of the map at the time
		 * of construction determines the elements available for iteration.
		 * <p>
		 * Any structural modification to the {@code MapAdapter} after the iterator
		 * is created, other than through this iterator’s {@code remove()} method,
		 * may result in inconsistent behavior.
		 */
		protected EntrySetIterator() {
			this.enumeration = hashTable.keys();
			this.current = null;
		}

		/**
		 * Returns {@code true} if the iteration has more entries.
		 * <p>
		 * This method delegates to the underlying {@code Hashtable.keys()} enumeration.
		 * The result reflects whether additional entries are available for iteration.
		 *
		 * @return {@code true} if there are more entries to return
		 */
		public boolean hasNext() {
			return this.enumeration.hasMoreElements();
		}

		 /**
		 * Returns the next {@code HMap.HEntry} in the iteration.
		 * <p>
		 * The entry returned is constructed from the next key in the underlying
		 * {@code Hashtable}. The value is retrieved at the moment of calling this method,
		 * so any concurrent modifications to the map may be reflected in the returned entry.
		 *
		 * @return the next {@code HMap.HEntry} in the iteration
		 * @throws NoSuchElementException if the iteration has no more entries
		 */
		public Object next() {
			this.current = this.enumeration.nextElement();
			return new Entry(this.current, hashTable.get(current));
		}

		/**
		 * Removes from the underlying map the last entry returned by this iterator.
		 * <p>
		 * This method can only be called once per call to {@code next()}. If called multiple
		 * times without a new call to {@code next()}, it will throw an {@code IllegalStateException}.
		 * <p>
		 * Removal is performed by the {@code remove()} method from the underlying {@code Hashtable}.
		 *
		 * @throws IllegalStateException if {@code next()} has not yet been called,
		 *                               or if {@code remove()} has already been called
		 *                               after the last call to {@code next()}
		 */
		public void remove(){
			if(this.current == null)
				throw new IllegalStateException("next should be called first.");
			
			hashTable.remove(this.current);
			this.current = null;
		}
	}
	
	/**
	 * An iterator over the keys of the {@code MapAdapter}'s key set.
	 * <p>
	 * This iterator extends {@code EntrySetIterator} and overrides only the {@code next()}
	 * method, returning the current key instead of a key-value entry.
	 * <p>
	 * The iterator is backed by the underlying map, so modifications to the map
	 * after the iterator is created (except through this iterator's own {@code remove()} method)
	 * may result in inconsistent behavior.
	 * <p>
	 * This implementation supports the optional {@code remove()} operation, which removes
	 * from the map the last key returned by the iterator. The {@code remove()} method
	 * can only be called once per call to {@code next()}, otherwise it will throw an
	 * {@code IllegalStateException}.
	 *
	 * @see MapAdapter#keySet()
	 * @see EntrySetIterator
	 * @see HIterator
	 */
	protected final class KeySetIterator extends EntrySetIterator {

		/**
		 * Default constructor of {@code KeySetIterator}
		 */
		protected KeySetIterator(){
			//intentionally left blank
		}

		/**
		 * Returns the next key in the iteration.
		 * <p>
		 * This method advances the internal enumeration and sets the current key,
		 * which can be removed using {@code remove()}.
		 *
		 * @return the next key in the map
		 * @throws NoSuchElementException if there are no more keys to return
		 */
		public Object next() {
			this.current = this.enumeration.nextElement();
			return this.current;
		}
	}

	/**
	 * An iterator over the values of the {@code MapAdapter}'s value collection.
	 * <p>
	 * This iterator extends {@code EntrySetIterator} and overrides only the {@code next()}
	 * method, returning the value corresponding to the current key in the underlying map.
	 * <p>
	 * The iterator is backed by the underlying {@code Hashtable}, so structural modifications
	 * to the map after this iterator is created (other than through {@code remove()})
	 * may result in inconsistent behavior.
	 * <p>
	 * This implementation supports the optional {@code remove()} operation, which removes
	 * from the map the entry associated with the last returned value. This method may be
	 * called only once per call to {@code next()}, and will throw an {@code IllegalStateException}
	 * otherwise.
	 *
	 * @see MapAdapter#values()
	 * @see EntrySetIterator
	 * @see HIterator
	 */
	protected final class ValueCollectionIterator extends EntrySetIterator {

		/**
		 * Default constructor of {@code ValueCollectionIterator}
		 */
		protected ValueCollectionIterator(){
			//intentionally left blank
		}

		/**
		 * Returns the next value in the iteration.
		 * <p>
		 * This method advances the internal enumeration and returns the value
		 * associated with the current key in the map.
		 *
		 * @return the next value in the map
		 * @throws NoSuchElementException if there are no more values to return
		 */
		public Object next() {
			this.current = this.enumeration.nextElement();
			return hashTable.get(this.current);
		}
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*----------------------------------- COLLECTION HIERARCHY ----------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/**
	 * Abstract base implementation of the {@code HCollection} interface used within {@code MapAdapter}.
	 * <p>
	 * This class provides skeletal implementations of common collection operations such as
	 * {@code containsAll}, {@code isEmpty}, {@code size}, {@code toArray}, {@code equals}, {@code hashCode},
	 * {@code removeAll}, and {@code retainAll}. It is intended to be extended by concrete collection views
	 * such as {@code KeySet}, {@code EntrySet}, and {@code ValueCollection}.
	 * <p>
	 * As per CLDC 1.1 constraints, this class does not support reflection and does not provide support
	 * for typed array creation beyond basic array copying in {@code toArray(Object[])}.
	 * <p>
	 * The {@code add} and {@code addAll} methods are not supported and always throw
	 * {@code UnsupportedOperationException}, as the underlying map does not allow modification
	 * via these operations.
	 *
	 * @see HCollection
	 * @see KeySet
	 * @see EntrySet
	 * @see ValueCollection
	 */
	protected abstract class AbstractCollection implements HCollection {

		/**
		 * Default constructor of {@code AbstractCollection}
		 */
		protected AbstractCollection(){
			//intentionally left blank
		}

		/**
		 * This collection does not support the {@code add} operation.
		 *
		 * @param o the element to be added (ignored)
		 * @throws UnsupportedOperationException always thrown, as this operation is not supported
		 */
		public boolean add(Object o){
			throw new UnsupportedOperationException("add operation is not supported.");
		}

		/**
		 * This collection does not support the {@code addAll} operation.
		 *
		 * @param c the collection containing elements to add (ignored)
		 * @throws UnsupportedOperationException always thrown, as this operation is not supported
		 */
		public boolean addAll(HCollection c){
			throw new UnsupportedOperationException("addAll operation is not supported.");
		}

		/**
		 * Removes all elements from this collection by clearing the underlying map.
		 */
		public void clear() {
			hashTable.clear();
		}

		/**
		 * Returns {@code true} if this collection contains all elements of the specified collection.
		 *
		 * @param c the collection to check for containment
		 * @return {@code true} if all elements of {@code c} are contained in this collection
		 * @throws ClassCastException 	if the types of one or more elements
     	 * 								in the specified collection are incompatible with this collection
		 * @throws NullPointerException if {@code c} is {@code null} or contains {@code null} elements
		 */
		public boolean containsAll(HCollection c) {
			if(c == null)
				throw new NullPointerException("collection must not be null.");

			HIterator iterator = c.iterator();
			while(iterator.hasNext()){
				if(!this.contains(iterator.next()))
					return false;
			}
			return true;
		}

		/**
		 * Returns the hash code value for this collection.
		 * <p>
		 * The hash code is defined as the sum of the hash codes of all elements in the collection.
		 *
		 * @return the hash code value for this collection
		 */
		public int hashCode() {
			int hash = 0;
			HIterator iterator = this.iterator();
			while(iterator.hasNext()){
				hash += iterator.next().hashCode();
			}
			return hash;
		}

		/**
		 * Returns {@code true} if this collection contains no elements.
		 *
		 * @return {@code true} if the collection is empty
		 */
		public boolean isEmpty() {
			return hashTable.isEmpty();
		}

		/**
		 * Removes all elements in this collection that are also contained in the specified collection.
		 *
		 * @param c the collection containing elements to be removed
		 * @return {@code true} if this collection was modified
		 * @throws ClassCastException 	if the types of one or more elements
		 * 								in the specified collection are incompatible with this collection
		 * @throws NullPointerException if {@code c} is {@code null} or contains {@code null} elements
		 */
		public boolean removeAll(HCollection c) {
			if(c == null)
				throw new NullPointerException("collection must not be null.");

			boolean modified = false;
			HIterator iterator = c.iterator();
			while(iterator.hasNext()){
				if(this.remove(iterator.next()))
					modified = true;
			}
			return modified;
		}

		/**
		 * Retains only the elements in this collection that are also contained in the specified collection.
		 *
		 * @param c the collection containing elements to retain
		 * @return {@code true} if this collection was modified
		 * @throws ClassCastException 	if the types of one or more elements
		 * 								in the specified collection are incompatible with this collection
		 * @throws NullPointerException if {@code c} is {@code null}
		 */
		public boolean retainAll(HCollection c) {
			if(c == null)
				throw new NullPointerException("collection must not be null.");

			boolean modified = false;
			HIterator iterator = this.iterator();
			while(iterator.hasNext()){
				Object next = iterator.next();
				if(!c.contains(next)){
					iterator.remove();
					modified = true;
				}
			}
			return modified;
		}

		/**
		 * Returns the number of elements in this collection.
		 *
		 * @return the number of elements in the collection
		 */
		public int size() {
			return hashTable.size();
		}

		/**
		 * Returns an array containing all of the elements in this collection.
		 *
		 * @return an array containing all elements in this collection
		 */
		public Object[] toArray() {
			Object[] array = new Object[this.size()];
			HIterator iterator = this.iterator();
			int i = 0;
			while(iterator.hasNext()){
				array[i++] = iterator.next();
			}
			return array;
		}

		/**
		 * Returns an array containing all the elements in this collection; the runtime type
		 * of the returned array is that of the specified array, if large enough.
		 * <p>
		 * If the specified array is too small to hold all elements, a new array of type {@code Object[]}
		 * is returned instead. This differs from the standard behavior in J2SE 1.4.2, where an array
		 * of the same runtime type would be created using reflection. Due to limitations of CLDC 1.1,
		 * reflection is not supported and the method cannot dynamically create arrays of the
		 * same runtime type as {@code a}.
		 * <p>
		 * If the array has extra space, the element immediately after the last one is set to {@code null}.
		 *
		 * @param a the array into which the elements are to be stored
		 * @return an array containing all the elements of this collection
		 * @throws NullPointerException if the specified array is {@code null}
		 */
		public Object[] toArray(Object[] a) {
			if(a == null)
				throw new NullPointerException("array must not be null.");
			
			if(a.length < this.size())
				a = new Object[this.size()];

			HIterator iterator = this.iterator();
			int i = 0;
			while(iterator.hasNext()){
				a[i++] = iterator.next();
			}

			if(i < a.length)
				a[i] = null;

			return a;
		}

		/**
		 * Returns a string representation of this collection.
		 *
		 * @return a string representation of this collection
		 */
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			HIterator iterator = this.iterator();
			while(iterator.hasNext()){
				sb.append(iterator.next().toString());
				if(iterator.hasNext())
					sb.append(", ");
			}
			sb.append("]");
			return sb.toString();
		}
	}

	/**
	 * A view of the entries contained in the {@code MapAdapter}.
	 * <p>
	 * This class implements the {@code HSet} interface and provides a backed view
	 * over the key-value mappings of the underlying {@code Hashtable}.
	 * Modifications to this set (such as {@code remove}) directly affect the backing map,
	 * and vice versa. The collection is fully dynamic.
	 * <p>
	 * The collection does not allow {@code null} elements. Attempting to check containment or
	 * remove a {@code null} object will result in a {@code NullPointerException}.
	 * Similarly, the {@code contains} and {@code remove} methods require the argument to be
	 * of type {@code HEntry}, or a {@code ClassCastException} will be thrown.
	 * <p>
	 * This class supports the optional {@code remove()} operation on its iterator and implements
	 * all methods required by {@code HSet} through its superclass {@code AbstractCollection}.
	 *
	 * @see HMap#entrySet()
	 * @see HSet
	 * @see AbstractCollection
	 */
	protected final class EntrySet extends AbstractCollection implements HSet {

		/**
		 * Default constructor of {@code EntrySet}
		 */
		protected EntrySet(){
			//intentionally left blank
		}

		/**
		 * Compares the specified object with this set for equality.
		 * <p>
		 * The comparison is based on the entries contained in the set. Two sets are considered
		 * equal if they contain the same entries, regardless of their order.
		 *
		 * @param o the object to compare with this set
		 * @return {@code true} if the specified object is equal to this set
		 */
		public boolean equals(Object o) {
			if (!(o instanceof HSet) || o == null) {
				return false;
			}

			if(o == this)
				return true;

			HSet s = (HSet)o;

			try{
				return s.size() == this.size() && this.containsAll(s);
			} catch (ClassCastException | NullPointerException e) {
				return false;
			}
		}

		/**
		 * Returns {@code true} if this set contains the specified entry.
		 * <p>
		 * The entry is considered present if its key exists in the underlying map
		 * and the associated value is equal to the value of the entry.
		 *
		 * @param o the object to check for presence in the set
		 * @return {@code true} if this set contains the specified entry
		 * @throws NullPointerException if the argument is {@code null}
		 * @throws ClassCastException if the argument is not of type {@code HEntry}
		 */
		public boolean contains(Object o) {
			if(o == null)
				throw new NullPointerException("contained entry must not be null.");
			
			if(!(o instanceof HEntry))
				throw new ClassCastException("contained entry must be of type HEntry.");
			
			return  hashTable.containsKey(((HEntry)o).getKey()) 
					&& 
					hashTable.get(((HEntry)o).getKey()).equals(((HEntry)o).getValue());
		}

		/**
		 * Removes from the underlying map the entry that matches the specified object.
		 * <p>
		 * The entry is removed only if it exists in the map with the same key and value.
		 *
		 * @param o the object to be removed from the set
		 * @return {@code true} if the entry was present and removed
		 * @throws NullPointerException if the argument is {@code null}
		 * @throws ClassCastException if the argument is not of type {@code HEntry}
		 */
		public boolean remove(Object o) {
			if(o == null)
				throw new NullPointerException("contained entry must not be null.");

			if(this.contains(o))
				return hashTable.remove(((HEntry)o).getKey()) != null;
			return false;
		}

		/**
		 * Returns an iterator over the entries in this set.
		 * <p>
		 * The iterator supports the optional {@code remove()} operation, which removes
		 * from the map the entry most recently returned by {@code next()}.
		 *
		 * @return an {@code HIterator} over the entries in this set
		 */
		public HIterator iterator() {
			return new EntrySetIterator();
		}
	}

	/**
	 * A view of the keys contained in the {@code MapAdapter}.
	 * <p>
	 * This class implements the {@code HSet} interface and provides a backed view
	 * over the keys of the underlying {@code Hashtable}. Any change made to this set
	 * is reflected in the map and vice versa.
	 * <p>
	 * This view does not allow {@code null} keys. Attempting to call
	 * {@code contains(null)} or {@code remove(null)} will result in a
	 * {@code NullPointerException}.
	 * <p>
	 * <strong>Important:</strong> this {@code HSet} implementation can contain itself.
	 * Undefined behaviors are expected if this happens.
	 * This class supports the optional {@code remove()} operation on its iterator
	 * and implements all required methods from {@code HSet} through its abstract
	 * superclass {@code AbstractCollection}.
	 *
	 * @see MapAdapter#keySet()
	 * @see AbstractCollection
	 * @see HSet
	 */
	protected final class KeySet extends AbstractCollection implements HSet {

		/**
		 * Default constructor of {@code KeySet}
		 */
		protected KeySet(){
			//intentionally left blank
		}

		/**
		 * Compares the specified object with this set for equality.
		 * <p>
		 * The comparison is based on the keys contained in the set. Two sets are considered
		 * equal if they contain the same keys, regardless of their order.
		 *
		 * @param o the object to compare with this set
		 * @return {@code true} if the specified object is equal to this set
		 */
		public boolean equals(Object o) {
			if (!(o instanceof HSet) || o == null) {
				return false;
			}

			if(o == this)
				return true;

			HSet s = (HSet)o;

			try{
				return s.size() == this.size() && this.containsAll(s);
			}catch (NullPointerException e) {
				return false;
			}
		}

		/**
		 * Returns {@code true} if this set contains the specified key.
		 *
		 * @param o the key to check for presence in the map
		 * @return {@code true} if this map contains the specified key
		 * @throws NullPointerException if the key is {@code null}
		 */
		public boolean contains(Object o) {
			if(o == null)
				throw new NullPointerException("contained key must not be null.");
			
			return hashTable.containsKey(o);
		}

		/**
		 * Removes the specified key from the map if it is present.
		 * <p>
		 * This operation removes both the key and its associated value from the map.
		 *
		 * @param o the key to be removed
		 * @return {@code true} if the key was present and has been removed
		 * @throws NullPointerException if the key is {@code null}
		 */
		public boolean remove(Object o) {
			if(o == null)
				throw new NullPointerException("contained key must not be null.");
			
			return hashTable.remove(o) != null;
		}

		/**
		 * Returns an iterator over the keys in this set.
		 * <p>
		 * The iterator supports the optional {@code remove()} operation, which removes
		 * from the map the key most recently returned by {@code next()}.
		 *
		 * @return an {@code HIterator} over the keys in this set
		 */
		public HIterator iterator() {
			return new KeySetIterator();
		}
	}

	/**
	 * A view of the values contained in the {@code MapAdapter}.
	 * <p>
	 * This class implements the {@code HCollection} interface and provides a backed view
	 * over the values of the underlying {@code Hashtable}. Any change to this collection
	 * is reflected in the map, and vice versa.
	 * <p>
	 * The collection does not allow {@code null} elements, and the {@code contains} and
	 * {@code remove} methods operate on values, not on keys or entries.
	 * <p>
	 * This class supports the optional {@code remove()} operation on its iterator,
	 * which removes the entry associated with the last returned value.
	 * <p>
	 * Because multiple keys can map to the same value, removing a value using
	 * {@code remove(Object)} will only remove the first matching entry.
	 *
	 * @see MapAdapter#values()
	 * @see AbstractCollection
	 * @see HCollection
	 */
	protected final class ValueCollection extends AbstractCollection {

		/**
		 * Default constructor of {@code ValueCollection}
		 */
		protected ValueCollection(){
			//intentionally left blank
		}

		/**
		 * Compares the specified object with this collection for equality.
		 * <p>
		 * The comparison is based on the values contained in the collection. Two collections
		 * are considered equal if they contain the same values, regardless of their order.
		 * If the specified object is an instance of {@code HSet}, it will return {@code false}
		 * according to the contract of {@code HCollection.equals()}.
		 *
		 * @param o the object to compare with this collection
		 * @return {@code true} if the specified object is equal to this collection
		 */
		public boolean equals(Object o) {
			if (o instanceof HSet || !(o instanceof HCollection) || o == null) {
				return false;
			}

			if(o == this)
				return true;

			HCollection c = (HCollection)o;
			try{
				return c.size() == this.size() && this.containsAll(c);
			} catch (NullPointerException e) {
				return false;
			}
		}

		/**
		 * Returns {@code true} if this collection contains the specified value.
		 *
		 * @param o the value to check for presence in the map
		 * @return {@code true} if at least one mapping in the map has the specified value
		 * @throws NullPointerException if the value is {@code null}
		 */
		public boolean contains(Object o) {
			if(o == null)
				throw new NullPointerException("contained value must not be null.");
			
			return hashTable.contains(o);
		}

		/**
		 * Removes the first entry from the map that has the specified value.
		 * <p>
		 * Since multiple keys can be associated with the same value, only the
		 * first matching entry encountered will be removed.
		 *
		 * @param o the value to remove
		 * @return {@code true} if a value was found and its corresponding entry removed
		 * @throws NullPointerException if the value is {@code null}
		 */
		public boolean remove(Object o) {
			if(o == null)
				throw new NullPointerException("contained value must not be null.");
			
			HSet entrySet = entrySet();
			HIterator iterator = entrySet.iterator();
			while(iterator.hasNext()){
				HEntry entry = (HEntry) iterator.next();
				if(entry.getValue().equals(o)){
					hashTable.remove(entry.getKey());
					return true;
				}
			}

			return false;
		}

		/**
		 * Returns an iterator over the values in this collection.
		 * <p>
		 * The iterator supports the optional {@code remove()} operation, which removes
		 * from the map the entry associated with the last returned value.
		 *
		 * @return an {@code HIterator} over the values in this collection
		 */
		public HIterator iterator() {
			return new ValueCollectionIterator();
		}
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*--------------------------------------- PARAMETERS ----------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	private Hashtable hashTable;

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*--------------------------------------  CONSTRUCTORS --------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/


	/**
	 * Constructs an empty {@code MapAdapter} with the default initial capacity.
	 * <p>
	 * Internally, this creates a new {@code Hashtable} with default size and load factor,
	 * as defined by the CLDC 1.1 implementation. This is the most common
	 * constructor used when the number of key-value mappings is not known in advance.
	 * <p>
	 * This constructor corresponds to the default {@code Hashtable()} constructor
	 * available in CLDC 1.1.
	 *
	 * @see java.util.Hashtable#Hashtable()
	 */
	public MapAdapter(){
		this.hashTable = new Hashtable();
	}

	/**
	 * Constructs an empty {@code MapAdapter} with the specified initial capacity.
	 * <p>
	 * This constructor allows the user to specify an expected number of entries
	 * to optimize internal memory allocation and performance. It does not affect
	 * the logical behavior of the map.
	 * <p>
	 * Providing a larger initial capacity can reduce the cost of internal reallocation
	 * if a large number of mappings are to be added. However, the map will still grow
	 * as needed, and this value does not impose a strict limit.
	 * <p>
	 * This constructor corresponds to {@code Hashtable(int initialCapacity)} in CLDC 1.1.
	 *
	 * @param initialCapacity the initial capacity of the backing {@code Hashtable}
	 * @throws IllegalArgumentException if the specified initial capacity is negative
	 *
	 * @see java.util.Hashtable#Hashtable(int)
	 */
	public MapAdapter(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Initial capacity must be non-negative.");
		hashTable = new Hashtable(initialCapacity);
	}
	
	/**
	 * Constructs a new {@code MapAdapter} containing the same mappings as the specified map.
	 * <p>
	 * The entries are copied by calling {@code putAll(HMap)}, and the initial capacity is set
	 * to match the size of the provided map, to optimize internal allocation.
	 * <p>
	 * This constructor ensures that all entries are valid for use with the underlying
	 * {@code Hashtable}, which does not allow {@code null} keys or values.
	 *
	 * @param t the map whose entries are to be copied
	 * @throws NullPointerException if {@code t} is {@code null} or contains {@code null} keys or values
	 */
	public MapAdapter(HMap t){
		this(t.size());
		this.putAll(t);
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*------------------------------------ INTERFACE METHODS ------------------------------------*/ 
	/*-------------------------------------------------------------------------------------------*/

	/**
	 * Removes all key-value mappings from this map.
	 * <p>
	 * The map will be empty after this call returns.
	 */
	public void clear() {
		this.hashTable.clear();
	}

	/**
	 * Returns {@code true} if this map contains a mapping for the specified key.
	 * <p>
	 * In accordance with the restrictions of CLDC 1.1, keys cannot be {@code null}.
	 * 
 	 * @param key key whose presence in this map is to be tested
	 * @return {@code true} if this map contains a mapping for the specified key
	 * @throws NullPointerException if the key is {@code null}
	 */
	public boolean containsKey(Object key) {
		if(key == null)
			throw new NullPointerException("contained key must not be null.");
		
		return this.hashTable.containsKey(key);
	}

	/**
	 * Returns {@code true} if this map maps one or more keys to the specified value.
	 * <p>
	 * In accordance with the restrictions of CLDC 1.1, values cannot be {@code null}.
	 *
	 * @param value value whose presence in this map is to be tested
	 * @return {@code true} if this map maps one or more keys to the specified value
	 * @throws NullPointerException if the value is {@code null}
	 */
	public boolean containsValue(Object value) {
		if(value == null)
			throw new NullPointerException("contained value must not be null.");

		return this.hashTable.contains(value);
	}

	/**
	 * Returns a {@code HSet} view of the mappings contained in this map.
	 * <p>
	 * The set is backed by the map, so changes to the map are reflected in the set,
	 * and vice versa.
	 *
	 * @return a set view of the map’s entries
	 */
	public HSet entrySet() {
		return new EntrySet();
	}

	/**
	 * Compares the specified object with this map for equality.
	 * <p>
	 * Returns {@code true} if the given object is also a map and the two maps contain
	 * exactly the same key-value mappings.
	 *
	 * @param o the object to compare with
	 * @return {@code true} if the maps are equal
	 */
	public boolean equals(Object o) {
		if (!(o instanceof HMap) ||  o == null)
			return false;

		if(o == this)
			return true;

		HMap m = (HMap)o;
		
		return this.entrySet().equals(m.entrySet());
	}

	/**
	 * Returns the value to which the specified key is mapped,
	 * or {@code null} if this map contains no mapping for the key.
	 * <p>
	 * In accordance with the restrictions of CLDC 1.1, keys cannot be {@code null}.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or
	 *         {@code null} if this map contains no mapping for the key
	 * @throws NullPointerException if the key is {@code null}
	 */
	public Object get(Object key) {
		if(key == null)
			throw new NullPointerException("contained key must not be null.");

		return this.hashTable.get(key);
	}

	/**
	 * Returns the hash code value for this map.
	 * <p>
	 * The hash code is defined as the sum of the hash codes of each entry in the map.
	 *
	 * @return the hash code value for this map
	 */
	public int hashCode() {
		return this.entrySet().hashCode();
	}

	/**
	 * Returns {@code true} if this map contains no key-value mappings.
	 *
	 * @return {@code true} if the map is empty
	 */
	public boolean isEmpty() {
		return this.hashTable.isEmpty();
	}


	/**
	 * Returns a {@code HSet} view of the keys contained in this map.
	 * <p>
	 * The set is backed by the map, so changes to the map are reflected in the set.
	 *
	 * @return a set view of the keys in this map
	 */
	public HSet keySet() {
		return new KeySet();
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * If the map previously contained a mapping for the key, the old value is replaced.
	 * <p>
	 * In accordance with the restrictions of CLDC 1.1, neither keys nor values can be {@code null}.
	 * <p>
	 * <strong>Important:</strong> this map implementation can contain itself as a key. 
	 * Undefined behaviors might happen if this happens. 
	 * 
	 *
	 * @param key the key with which the specified value is to be associated
	 * @param value the value to be associated with the key
	 * @return the previous value associated with the key, or {@code null} if there was none
	 * @throws NullPointerException if the key or value is {@code null}
	 */
	public Object put(Object key, Object value) {
		if(key == null || value == null)
			throw new NullPointerException("neither keys nor values can be null.");
		return this.hashTable.put(key, value);
	}

	/**
	 * Copies all of the mappings from the specified map to this map.
	 * <p>
	 * This method functions identically to calling {@code put(key, value)} for each entry
	 * in the specified map. If a key already exists in this map, its value will be replaced.
	 * <p>
	 * In accordance with the restrictions of CLDC 1.1, neither keys nor values can be {@code null}.
	 *
	 * @param t the map whose entries are to be copied into this map
	 * @throws NullPointerException if the specified map is {@code null} or contains {@code null} keys or values
	 */
	public void putAll(HMap t) {
		if(t == null)
			throw new NullPointerException("target map must not be null.");

		HSet set = t.entrySet();
		HIterator iterator = set.iterator();
		while(iterator.hasNext()){
			HEntry entry = (HEntry) iterator.next();
			this.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * <p>
	 * In accordance with the restrictions of CLDC 1.1, neither keys nor values can be {@code null}.
	 *
	 * @param key the key whose mapping is to be removed
	 * @return the previous value associated with the key, or {@code null} if none
	 * @throws NullPointerException if the key is {@code null}
	 */
	public Object remove(Object key) {
		if(key == null)
			throw new NullPointerException("contained key must not be null.");

		return this.hashTable.remove(key);
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 *
	 * @return the size of the map
	 */
	public int size() {
		return this.hashTable.size();
	}

	/**
	 * Returns a {@code HCollection} view of the values contained in this map.
	 * <p>
	 * The collection is backed by the map, so changes to the map are reflected in the collection,
	 * and vice versa.
	 *
	 * @return a collection view of the values in this map
	 */
	public HCollection values() {
		return new ValueCollection();
	}

	/**
	 * Returns a string representation of the map.
	 *
	 * @return a string representation of the map
	 */
	public String toString() {
		return hashTable.toString();
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
}