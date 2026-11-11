// Diego Chiesurin

package myTest;

import myAdapter.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import myExceptions.UnsupportedOperationException;

/**
 * Dummy implementation of {@link myAdapter.HMap} that can contain null keys and null values using HashMap.
 * This is used for testing purposes only.
 * Not all HMap operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
 */
public class DummyTestMap implements HMap {

    /**
     * The underlying HashMap used for storing key-value pairs.
     * This allows for testing operations that do not support null keys or values.
     */
    private HashMap<Object, Object> map;

    /**
     * DummyEntry is a simple implementation of {@link myAdapter.HMap.HEntry} that allows for testing
     * without the need of a full implementation.
     * Not all {@link myAdapter.HMap.HEntry} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyEntry implements HMap.HEntry {
        /**
         * The underlying Map.Entry used for storing key-value pairs.
         */
        Map.Entry<Object, Object> entry;

        /**
         * Constructor initializes the key and value of the entry.
         * @param key the key of the entry
         * @param value the value of the entry
         */
        private DummyEntry(Object key, Object value) {
            entry = new HashMap.SimpleEntry<Object, Object>(key, value);
        }

        /**
         * Returns the key of the entry.
         * @return the key of the entry
         */
        public Object getKey() {
            return this.entry.getKey();
        }

        /**
         * Returns the value of the entry.
         * @return the value of the entry
         */
        public Object getValue() {
            return this.entry.getValue();
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntry
         */
        public Object setValue(Object value) {
            throw new UnsupportedOperationException("setValue not supported for DummyEntry");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntry
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyEntry");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntry
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for DummyEntry");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntry
         */
        public String toString() {
            throw new UnsupportedOperationException("toString not supported for DummyEntry");
        }
    }

    /**
     * DummyEntrySet is a simple implementation of {@link myAdapter.HSet} that allows for testing
     * without the need of a full implementation.
     * Not all {@link myAdapter.HSet} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyEntrySet implements HSet {

        /**
         * Default constructor for DummyEntrySet.
         */
        private DummyEntrySet() {
            //intentionally left blank
        }

        /**
         * Returns an iterator over the elements in this set.
         * The elements are of type DummyEntry.
         * @return an iterator over the elements in this set
         */
        public HIterator iterator() {
            return new DummyEntrySetIterator();
        }

        /**
         * Returns true if this set contains the specified element.
         * @param o the element to be checked for containment
         * @return {@code true} if this set contains the specified element
         * @throws ClassCastException if the type of the specified element is incompatible with this set
         * @throws NullPointerException if the specified element is null and this set does not permit null elements
         */
        public boolean contains(Object o) {
            return map.entrySet().contains(o);
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyEntrySet");
        }
        
        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySet
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyEntrySet");
        }
    }

    /**
     * DummyKeySet is a simple implementation of {@link myAdapter.HSet} that allows for testing
     * without the need of a full implementation.
     * Not all {@link myAdapter.HSet} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyKeySet implements HSet {

        /**
         * Default constructor for DummyKeySet.
         */
        protected DummyKeySet() {
            //intentionally left blank
        }

        /**
         * Returns an iterator over the elements in this set.
         * The elements are of type Object.
         * @return an iterator over the elements in this set
         */
        public HIterator iterator() {
            return new DummyKeySetIterator();
        }

        /**
         * Returns true if this set contains the specified element.
         * @param o the element to be checked for containment
         * @return {@code true} if this set contains the specified element
         * @throws ClassCastException if the type of the specified element is incompatible with this set
         * @throws NullPointerException if the specified element is null and this set does not permit null elements
         */
        public boolean contains(Object o) {
            return map.keySet().contains(o);
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySet
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for DummyKeySet");
        }
    }

    /**
     * DummyValueCollection is a simple implementation of {@link myAdapter.HCollection} that allows for testing
     * without the need of a full implementation.
     * Not all {@link myAdapter.HCollection} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyValueCollection implements HCollection {

        /**
         * Default constructor for DummyValueCollection.
         */
        protected DummyValueCollection() {
            //intentionally left blank
        }

        /**
         * Returns an iterator over the elements in this collection.
         * The elements are of type Object.
         * @return an iterator over the elements in this collection
         */
        public HIterator iterator() {
            return new DummyValueCollectionIterator();
        }

        /**
         * Returns true if this collection contains the specified element.
         * @param o the element to be checked for containment
         * @return {@code true} if this collection contains the specified element
         * @throws ClassCastException if the type of the specified element is incompatible with this collection
         * @throws NullPointerException if the specified element is null and this collection does not permit null elements
         */
        public boolean contains(Object o) {
            return map.values().contains(o);
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollection
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for DummyValueCollection");
        }
    }

    /**
     * DummyEntrySetIterator is a simple implementation of {@link myAdapter.HIterator} that allows for
     * iterating over the {@link DummyEntrySet} of the {@link DummyTestMap}.
     * Not all {@link myAdapter.HIterator} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyEntrySetIterator implements HIterator{

        /**
         * Using the HashMap iterator to iterate over the entry set.
         */
        private Iterator<Map.Entry<Object, Object>> iterator;

        /**
         * Constructor initializes the iterator to the entry set of the map.
         */
        public DummyEntrySetIterator() {
            this.iterator = map.entrySet().iterator();
        }

        /**
         * Returns true if the iteration has more elements.
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next DummyEntry in the iteration
         */
        public Object next() {
            Map.Entry<Object, Object> next = this.iterator.next();

            return new DummyEntry(next.getKey(), next.getValue());
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyEntrySetIterator
         */
        public void remove() {
            throw new UnsupportedOperationException("remove not supported for DummyEntrySetIterator");
        }
    }

    /**
     * DummyKeySetIterator is a simple implementation of {@link myAdapter.HIterator} that allows for
     * iterating over the {@link DummyKeySet} of the {@link DummyTestMap}.
     * Not all {@link myAdapter.HIterator} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyKeySetIterator implements HIterator {
        /**
         * Using the HashMap keySet iterator to iterate over the keys.
         */
        private Iterator<Object> iterator;

        /**
         * Constructor initializes the iterator to the keySet of the map.
         */
        public DummyKeySetIterator() {
            this.iterator = map.keySet().iterator();
        }

        /**
         * Returns true if the iteration has more elements.
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next key in the iteration
         */
        public Object next() {
            return this.iterator.next();
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyKeySetIterator
         */
        public void remove() {
            throw new UnsupportedOperationException("remove not supported for DummyKeySetIterator");
        }
    }

    /**
     * DummyValueCollectionIterator is a simple implementation of {@link myAdapter.HIterator} that allows for
     * iterating over the {@link DummyValueCollection} of the {@link DummyTestMap}.
     * Not all {@link myAdapter.HIterator} operations are supported, and will throw {@link myExceptions.UnsupportedOperationException}.
     */
    protected class DummyValueCollectionIterator implements HIterator {
        /**
         * Using the HashMap values iterator to iterate over the values.
         */
        private Iterator<Object> iterator;

        /**
         * Constructor initializes the iterator to the values of the map.
         */
        public DummyValueCollectionIterator() {
            this.iterator = map.values().iterator();
        }

        /**
         * Returns true if the iteration has more elements.
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next value in the iteration
         */
        public Object next() {
            return this.iterator.next();
        }

        /**
         * @throws UnsupportedOperationException operation not supported for DummyValueCollectionIterator
         */
        public void remove() {
            throw new UnsupportedOperationException("remove not supported for DummyValueCollectionIterator");
        }
    }

    /**
     * Constructor initializes the map to a new HashMap.
     * This allows for testing operations that do not support null keys or values.
     */
    public DummyTestMap() {
        this.map = new HashMap<>();
    }

    /**
     * Puts a key-value pair into the map.
     * @param key the key to be added
     * @param value the value to be associated with the key
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    public Object put(Object key, Object value) {
        return this.map.put(key, value);
    }

    /**
     * Returns a set view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * @return a set view of the mappings contained in this map
     */
    public HSet entrySet() { 
        return new DummyEntrySet(); 
    }

    /**
     * Returns a set view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * @return a set view of the keys contained in this map
     */
	public HSet keySet() { 
        return new DummyKeySet();
    }

    /**
     * Returns a collection view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa.
     * @return a collection view of the values contained in this map
     */
	public HCollection values() { 
        return new DummyValueCollection();
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
    public int size() {
        throw new UnsupportedOperationException("size not supported for DummyTestMap");
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public boolean containsKey(Object key) { 
        throw new UnsupportedOperationException("containsKey not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public boolean containsValue(Object value) { 
        throw new UnsupportedOperationException("containsValue not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public Object get(Object key) { 
        throw new UnsupportedOperationException("get not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public Object remove(Object key) { 
        throw new UnsupportedOperationException("remove not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public void clear() { 
        throw new UnsupportedOperationException("clear not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException operation not supported for DummyTestMap
     */
	public void putAll(HMap m) { 
        throw new UnsupportedOperationException("putAll not supported for DummyTestMap"); 
    }
}
