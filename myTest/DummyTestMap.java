package myTest;

import myAdapter.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import myExceptions.UnsupportedOperationException;

/**
 * Dummy implementation of HMap that can contain null keys and values using {@code HashMap}.
 * This is used for testing purposes only.
 * Not all HMap operations are supported, and will throw UnsupportedOperationException.
 */
public class DummyTestMap implements HMap {

    private HashMap<Object, Object> map;

    /**
     * DummyEntry is a simple implementation of HMap.HEntry that allows for testing
     * without the need for a full implementation of HMap.Entry.
     * Not all HMap.HEntry operations are supported, and will throw UnsupportedOperationException.
     */
    protected class DummyEntry implements HMap.HEntry {
        private Object key;
        private Object value;

        public DummyEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Object getKey() {
            return this.key;
        }

        @Override
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


    protected class DummyKeySet implements HSet {
        private Set<Object> keySet;

        protected DummyKeySet() {
            this.keySet = map.keySet();
        }

        public HIterator iterator() {
            return new DummyKeySetIterator();
        }

        public boolean contains(Object o) {
            return this.keySet.contains(o);
        }

        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyKeySet");
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyKeySet");
        }

        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyKeySet");
        }

        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyKeySet");
        }

        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyKeySet");
        }

        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyKeySet");
        }

        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyKeySet");
        }

        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyKeySet");
        }

        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyKeySet");
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyKeySet");
        }

        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyKeySet");
        }

        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyKeySet");
        }
    }

    protected class DummyValueCollection implements HCollection {
        private Collection<Object> valueCollection;

        protected DummyValueCollection() {
            this.valueCollection = map.values();
        }

        public HIterator iterator() {
            return new DummyValueCollectionIterator();
        }

        public int size() {
            throw new UnsupportedOperationException("size not supported for DummyValueCollection");
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("isEmpty not supported for DummyValueCollection");
        }

        public boolean contains(Object o) {
            return this.valueCollection.contains(o);
        }

        public boolean add(Object o) {
            throw new UnsupportedOperationException("add not supported for DummyValueCollection");
        }

        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException("addAll not supported for DummyValueCollection");
        }

        public void clear() {
            throw new UnsupportedOperationException("clear not supported for DummyValueCollection");
        }

        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove not supported for DummyValueCollection");
        }

        public boolean removeAll(HCollection c) {
            throw new UnsupportedOperationException("removeAll not supported for DummyValueCollection");
        }

        public boolean retainAll(HCollection c) {
            throw new UnsupportedOperationException("retainAll not supported for DummyValueCollection");
        }

        public boolean containsAll(HCollection c) {
            throw new UnsupportedOperationException("containsAll not supported for DummyValueCollection");
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException("toArray not supported for DummyValueCollection");
        }

        public Object[] toArray(Object[] a) {
            throw new UnsupportedOperationException("toArray(Object[]) not supported for DummyValueCollection");
        }

        public boolean equals(Object o) {
            throw new UnsupportedOperationException("equals not supported for DummyValueCollection");
        }
    }

    protected class DummyKeySetIterator implements HIterator {
        private Iterator<Object> iterator;

        public DummyKeySetIterator() {
            this.iterator = map.keySet().iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Object next() {
            return this.iterator.next();
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported for DummyKeySetIterator");
        }
    }

    protected class DummyValueCollectionIterator implements HIterator {
        private Iterator<Object> iterator;

        public DummyValueCollectionIterator() {
            this.iterator = map.values().iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Object next() {
            return this.iterator.next();
        }

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
