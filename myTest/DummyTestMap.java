//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import myExceptions.UnsupportedOperationException;

/**
 * Dummy implementation of {@code HMap} that can contain null keys and null values using HashMap.
 * This is used for testing purposes only.
 * Not all HMap operations are supported, and will throw UnsupportedOperationException.
 */
public class DummyTestMap implements HMap {

    /**
     * The underlying HashMap used for storing key-value pairs.
     * This allows for testing operations that do not support null keys or values.
     */
    private HashMap<Object, Object> map;

    /**
     * DummyEntry is a simple implementation of HMap.HEntry that allows for testing
     * without the need for a full implementation of HMap.Entry.
     * Not all HMap.HEntry operations are supported, and will throw UnsupportedOperationException.
     */
    protected class DummyEntry implements HMap.HEntry {
        private Object key;
        private Object value;

        /**
         * Constructor initializes the key and value of the entry.
         * @param key the key of the entry
         * @param value the value of the entry
         */
        public DummyEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key of the entry.
         * @return the key of the entry
         */
        public Object getKey() {
            return this.key;
        }

        /**
         * Returns the value of the entry.
         * @return the value of the entry
         */
        public Object getValue() {
            return this.value;
        }

        /**
         * @throws UnsupportedOperationException
         */
        public Object setValue(Object value) {
            throw new UnsupportedOperationException("setValue not supported for DummyEntry");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyEntry");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for DummyEntry");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public String toString() {
            throw new UnsupportedOperationException("toString not supported for DummyEntry");
        }
    }

    /**
     * DummyEntrySet is a simple implementation of HSet that allows for testing
     * without the need for a full implementation of HSet.
     * Not all HSet operations are supported, and will throw UnsupportedOperationException.
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
         * @throws UnsupportedOperationException
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean contains(Object o) {
            throw new UnsupportedOperationException("contains not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public int hashCode() {
            throw new UnsupportedOperationException("hashCode not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyEntrySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyEntrySet");
        }
        
        /**
         * @throws UnsupportedOperationException
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyEntrySet");
        }
    }

    /**
     * DummyEntrySetIterator is a simple implementation of HIterator that allows for
     * iterating over the entry set of the map.
     * Not all HIterator operations are supported, and will throw UnsupportedOperationException.
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
            HashMap.Entry next = (HashMap.Entry) this.iterator.next();

            return new DummyEntry(next.getKey(), next.getValue());
        }

        /**
         * @throws UnsupportedOperationException
         */
        public void remove() {
            throw new UnsupportedOperationException("remove not supported for DummyEntrySetIterator");
        }
    }

    /**
     * DummyKeySet is a simple implementation of HSet that allows for testing
     * without the need for a full implementation of HSet.
     * Not all HSet operations are supported, and will throw UnsupportedOperationException.
     */
    protected class DummyKeySet implements HSet {

        /**
         * Using the HashMap keySet to iterate over the keys.
         */
        private Set<Object> keySet;

        /**
         * Default constructor for DummyKeySet.
         * Initializes the keySet to the keySet of the map.
         */
        protected DummyKeySet() {
            this.keySet = map.keySet();
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
         * @return true if this set contains the specified element, false otherwise
         */
        public boolean contains(Object o) {
            return this.keySet.contains(o);
        }

        /**
         * @throws UnsupportedOperationException
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyKeySet");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyKeySet");
        }
    }

    /**
     * DummyValueCollection is a simple implementation of HCollection that allows for testing
     * without the need for a full implementation of HCollection.
     * Not all HCollection operations are supported, and will throw UnsupportedOperationException.
     */
    protected class DummyValueCollection implements HCollection {
        /**
         * Using the HashMap values to iterate over the values.
         */
        private Collection<Object> valueCollection;

        /**
         * Default constructor for DummyValueCollection.
         * Initializes the valueCollection to the values of the map.
         */
        protected DummyValueCollection() {
            this.valueCollection = map.values();
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
         * @throws UnsupportedOperationException
         */
        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean contains(Object o) {
            return this.valueCollection.contains(o);
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyValueCollection");
        }

        /**
         * @throws UnsupportedOperationException
         */
        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyValueCollection");
        }
    }

    /**
     * DummyKeySetIterator is a simple implementation of HIterator that allows for
     * iterating over the key set of the map.
     * Not all HIterator operations are supported, and will throw UnsupportedOperationException.
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
         * @throws UnsupportedOperationException
         */
        public void remove() {
            throw new UnsupportedOperationException("remove not supported for DummyKeySetIterator");
        }
    }

    /**
     * DummyValueCollectionIterator is a simple implementation of HIterator that allows for
     * iterating over the value collection of the map.
     * Not all HIterator operations are supported, and will throw UnsupportedOperationException.
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
         * @throws UnsupportedOperationException
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
     * @throws UnsupportedOperationException
     */
    public int size() {
        throw new UnsupportedOperationException("size not supported for DummyTestMap");
    }

    /**
     * @throws UnsupportedOperationException
     */
	public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException
     */
	public boolean containsKey(Object key) { 
        throw new UnsupportedOperationException("containsKey not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException
     */
	public boolean containsValue(Object value) { 
        throw new UnsupportedOperationException("containsValue not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException
     */
	public Object get(Object key) { 
        throw new UnsupportedOperationException("get not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException
     */
	public Object remove(Object key) { 
        throw new UnsupportedOperationException("remove not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException
     */
	public void clear() { 
        throw new UnsupportedOperationException("clear not supported for DummyTestMap"); 
    }

    /**
     * @throws UnsupportedOperationException
     */
	public void putAll(HMap m) { 
        throw new UnsupportedOperationException("putAll not supported for DummyTestMap"); 
    }
}
