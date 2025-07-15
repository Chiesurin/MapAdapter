//Diego Chiesurin - 2111553

package myTest;

import myAdapter.MapAdapter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

import myExceptions.UnsupportedOperationException;

import org.junit.After;

public class TestEmptyMapAdapter {

    private MapAdapter map;

    public TestEmptyMapAdapter() {
        // intentionally left blank
    }

    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    @Test
    public void testMapAdapterDefaultConstructor() {
        assertNotNull("MapAdapter should not be null", map);
        assertTrue("MapAdapter should be empty", map.isEmpty());
        assertEquals("Size of MapAdapter should be 0", 0, map.size());
    }

    @Test
    public void testMapAdapterConstructorWithZeroInitialCapacity() {
        MapAdapter mapWithInitialCapacity = new MapAdapter(0);
        assertNotNull("MapAdapter with initial capacity should not be null", mapWithInitialCapacity);
        assertTrue("MapAdapter with initial capacity should be empty", mapWithInitialCapacity.isEmpty());
        assertEquals("Size of MapAdapter with initial capacity should be 0", 0, mapWithInitialCapacity.size());
        assertTrue("MapAdapter with zero initial capacity should be equal to an empty map", mapWithInitialCapacity.equals(map));
    }  

    public void testMapAdapterConstructorWithPositiveInitialCapacity() {
        MapAdapter mapWithInitialCapacity = new MapAdapter(1);
        assertNotNull("MapAdapter with initial capacity should not be null", mapWithInitialCapacity);
        assertTrue("MapAdapter with initial capacity should be empty", mapWithInitialCapacity.isEmpty());
        assertEquals("Size of MapAdapter with initial capacity should be 0", 0, mapWithInitialCapacity.size());
        assertTrue("MapAdapter with positive initial capacity should be equal to an empty map", mapWithInitialCapacity.equals(map));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapAdapterConstructorWithNegativeInitialCapacity() {
        MapAdapter mapWithNegativeInitialCapacity = new MapAdapter(-1);
    }

    @Test
    public void testMapAdapterCopyConstructor() {
        MapAdapter copy = new MapAdapter(map);
        assertNotNull("Copy of MapAdapter should not be null", copy);
        assertTrue("Copy of empty MapAdapter should be empty", copy.isEmpty());
        assertEquals("Size of copied MapAdapter should be 0", 0, copy.size());
    }

    @Test(expected = NullPointerException.class)
    public void testMapAdapterCopyConstructorWithNullMap() {
        MapAdapter copy = new MapAdapter(null);
    }

    @Test
    public void testMapAdapterClearOnEmptyMap() {
        map.clear();
        assertTrue("Clear on empty map should not change its state", map.isEmpty());
        assertEquals("Size after clear on empty map should still be 0", 0, map.size());
    }

    @Test
    public void testContainsKeyOnEmptyMap() {
        assertFalse("Empty map should not contain any key", map.containsKey("testKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNullKeyOnEmptyMap() {
        map.containsKey(null);
    }

    @Test
    public void testContainsValueOnEmptyMap() {
        assertFalse("Empty map should not contain any value", map.containsValue("testValue"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNullValueOnEmptyMap() {
        map.containsValue(null);
    }

    @Test
    public void testEntrySetOnEmptyMap() {
        assertNotNull("Entry set of an empty map should not be null", map.entrySet());
        assertTrue("EntrySet of an empty map should be empty", map.entrySet().isEmpty());
    }

    @Test
    public void testEqualsOnEmptyMap() {
        MapAdapter anotherEmptyMap = new MapAdapter();
        assertTrue("Two empty maps should be equal", map.equals(anotherEmptyMap));
    }

    @Test
    public void testEqualsOnEmptyMapWithNull() {
        assertFalse("Empty map should not be equal to null", map.equals(null));
    }

    @Test
    public void testEqualsOnEmptyMapWithItself() {
        assertTrue("Empty map should be equal to itself", map.equals(map));
    }

    @Test
    public void testEqualsOnEmptyMapWithEntrySet() {
        assertFalse("Empty map should not be equal to its entry set", map.equals(map.entrySet()));
    }

    @Test
    public void testEqualsOnEmptyMapIsSymmetric() {
        MapAdapter anotherEmptyMap = new MapAdapter();
        assertTrue("Equals should be symmetric", anotherEmptyMap.equals(map) && map.equals(anotherEmptyMap));
    }

    @Test
    public void testEqualsWithNonHMapObject() {
        assertFalse("Empty map should not be equal to a non-HMap object", map.equals("Not a map"));
    }

    @Test
    public void testGetOnEmptyMap() {
        assertNull("Get should return null for any key", map.get("testKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetWithNullKeyOnEmptyMap() {
        map.get(null);
    }

    @Test
    public void testHashCodeOnEmptyMap() {
        assertEquals("Hash code of an empty map should be 0", 0, map.hashCode());
    }

    @Test
    public void testIsEmptyOnEmptyMap() {
        assertTrue("Map should be empty", map.isEmpty());
    }

    @Test
    public void testKeySetOnEmptyMap() {
        assertNotNull("KeySet of an empty map should not be null", map.keySet());
        assertTrue("KeySet of an empty map should be empty", map.keySet().isEmpty());
    }

    @Test
    public void testPutOnEmptyMap() {
        map.put("key", "value");
        assertEquals("Size should be 1 after put", 1, map.size());
        assertFalse("Map should not be empty after put", map.isEmpty());
        assertTrue("Map should contain the key after put", map.containsKey("key"));
        assertTrue("Map should contain the value after put", map.containsValue("value"));
        assertEquals("Get should return the value after put", "value", map.get("key"));
    }    

    @Test
    public void testPutWithThisMapAsAKeyInsertsANewEntry () {
        this.map.put(this.map, "value");
        assertFalse("Map should not be empty after put with this map as a key", map.isEmpty());
        assertEquals("Map after put with this map as a key should have size 1", 1, map.size());
    }

    @Test
    public void testPutWithThisMapAsAValueInsertsANewEntry () {
        this.map.put("key", this.map);
        assertFalse("Map should not be empty after put with this map as a value", map.isEmpty());
        assertEquals("Map after put with this map as a value should have size 1", 1, map.size());
    }

    @Test
    public void testPutWithThisMapAsAKeyAndThisMapAsAValueInsertsANewEntry () {
        this.map.put(this.map, this.map);
        assertFalse("Map should not be empty after put with this map as a key and value", map.isEmpty());
        assertEquals("Map after put with this map as a key and value should have size 1", 1, map.size());
    }

    @Test
    public void testPutWithThisMapAsAValueWorks () {
        this.map.put("key", this.map);
        assertEquals("Get should return the value after put", this.map, map.get("key"));
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyOnEmptyMap() {
        map.put(null, "value");
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullValueOnEmptyMap() {
        map.put("key", null);
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyAndNullValueOnEmptyMap() {
        map.put(null, null);
    }

    @Test
    public void testPutAllOnEmptyMap() {
        MapAdapter anotherMap = new MapAdapter();
        this.map.putAll(anotherMap);
        assertTrue("PutAll on empty map from empty map should not change its state", map.isEmpty());
        assertEquals("Size after PutAll on empty map from empty map should still be 0", 0, map.size());
    }

    @Test
    public void testPutAllWithThisMap() {
        this.map.putAll(this.map);
        assertTrue("Map should be empty after putAll with empty map", map.isEmpty());
        assertEquals("Map after putAll with this map should have size 0", 0, map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullMapOnEmptyMap() {
        map.putAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullKeyOnEmptyMap() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put(null, "value");
        map.putAll(anotherMap);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullValueOnEmptyMap() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("key", null);
        map.putAll(anotherMap);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullKeyAndNullValueOnEmptyMap() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put(null, null);
        map.putAll(anotherMap);
    }

    @Test 
    public void testRemoveOnEmptyMap() {
        assertNull("Remove should return null for any key", map.remove("testKey"));
        assertTrue("Map should still be empty after remove on empty map", map.isEmpty());
        assertEquals("Size should still be 0 after remove on empty map", 0, map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullKeyOnEmptyMap() {
        map.remove(null);
    }

    @Test
    public void testSizeOnEmptyMap() {
        assertEquals("Size should be 0", 0, map.size());
    }

    @Test
    public void testValuesOnEmptyMap() {
        assertNotNull("Values collection of an empty map should not be null", map.values());
        assertTrue("Values collection should be empty", map.values().isEmpty());
    }

    @Test
    public void testToStringOnEmptyMap() {
        assertEquals("String representation of an empty map should be \"{}\"", "{}", map.toString());
    }

    @After
    public void tearDown() {
        map = null;
    }
}