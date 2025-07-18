//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;

public class TestEmptyMapAdapter {

    private HMap map;

    public TestEmptyMapAdapter() {
        // intentionally left blank
    }

    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    @Test
    public void testMapAdapterDefaultConstructor() {
        assertNotNull("MapAdapter should not be null", map);
        assertTrue("MapAdapter should be empty", this.map.isEmpty());
        assertEquals("Size of MapAdapter should be 0", 0, this.map.size());
    }

    @Test
    public void testMapAdapterConstructorWithZeroInitialCapacity() {
        HMap mapWithInitialCapacity = new MapAdapter(0);
        assertNotNull("MapAdapter with initial capacity should not be null", mapWithInitialCapacity);
        assertTrue("MapAdapter with initial capacity should be empty", mapWithInitialCapacity.isEmpty());
        assertEquals("Size of MapAdapter with initial capacity should be 0", 0, mapWithInitialCapacity.size());
        assertTrue("MapAdapter with zero initial capacity should be equal to an empty MapAdapter", mapWithInitialCapacity.equals(map));
    }  

    @Test
    public void testMapAdapterConstructorWithPositiveInitialCapacity() {
        HMap mapWithInitialCapacity = new MapAdapter(1);
        assertNotNull("MapAdapter with initial capacity should not be null", mapWithInitialCapacity);
        assertTrue("MapAdapter with initial capacity should be empty", mapWithInitialCapacity.isEmpty());
        assertEquals("Size of MapAdapter with initial capacity should be 0", 0, mapWithInitialCapacity.size());
        assertTrue("MapAdapter with positive initial capacity should be equal to an empty MapAdapter", mapWithInitialCapacity.equals(map));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapAdapterConstructorWithNegativeInitialCapacity() {
        HMap mapWithNegativeInitialCapacity = new MapAdapter(-1);
    }

    @Test
    public void testMapAdapterCopyConstructor() {
        HMap copy = new MapAdapter(map);
        assertNotNull("Copy of MapAdapter should not be null", copy);
        assertTrue("Copy of empty MapAdapter should be empty", copy.isEmpty());
        assertEquals("Size of copied MapAdapter should be 0", 0, copy.size());
    }

    @Test(expected = NullPointerException.class)
    public void testMapAdapterCopyConstructorWithNullHMap() {
        HMap copy = new MapAdapter(null);
    }

    @Test
    public void testClearOnEmptyMapAdapter() {
        this.map.clear();
        assertTrue("Clear on empty MapAdapter should not change its state", this.map.isEmpty());
        assertEquals("Size after clear on empty MapAdapter should still be 0", 0, this.map.size());
    }

    @Test
    public void testContainsKeyOnEmptyMapAdapter() {
        assertFalse("Empty MapAdapter should not contain any key", this.map.containsKey("testKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNullKeyOnEmptyMapAdapter() {
        this.map.containsKey(null);
    }

    @Test
    public void testContainsValueOnEmptyMapAdapter() {
        assertFalse("Empty MapAdapter should not contain any value", this.map.containsValue("testValue"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNullValueOnEmptyMapAdapter() {
        this.map.containsValue(null);
    }

    @Test
    public void testEntrySetOnEmptyMapAdapter() {
        assertNotNull("Entry set of an empty MapAdapter should not be null", this.map.entrySet());
        assertTrue("EntrySet of an empty MapAdapter should be empty", this.map.entrySet().isEmpty());
    }

    @Test
    public void testEqualsOnEmptyMapAdapter() {
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("Two empty MapAdapters should be equal", this.map.equals(anotherEmptyMap));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterWithNonEmptyMap() {
        HMap nonEmptyMap = new MapAdapter();
        nonEmptyMap.put("testKey", "testValue");
        assertFalse("Empty MapAdapter should not be equal to a non-empty MapAdapter", this.map.equals(nonEmptyMap));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterWithNull() {
        assertFalse("Empty MapAdapter should not be equal to null", this.map.equals(null));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterWithItself() {
        assertTrue("Empty MapAdapter should be equal to itself", this.map.equals(map));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterWithEntrySet() {
        assertFalse("Empty MapAdapter should not be equal to its entry set", this.map.equals(this.map.entrySet()));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterIsSymmetric() {
        HMap anotherEmptyMapAdapter = new MapAdapter();
        assertTrue("Equals should be symmetric", anotherEmptyMapAdapter.equals(map) && this.map.equals(anotherEmptyMapAdapter));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterIsTransitive() {
        HMap anotherEmptyMapAdapter = new MapAdapter();
        HMap anotherAnotherEmptyMapAdapter = new MapAdapter();
        assertTrue("If MapAdapter A equals MapAdapter B and MapAdapter B equals MapAdapter C, then MapAdapter A should equal MapAdapter C", 
                    this.map.equals(anotherEmptyMapAdapter) && anotherEmptyMapAdapter.equals(anotherAnotherEmptyMapAdapter) && this.map.equals(anotherAnotherEmptyMapAdapter));
    }

    @Test
    public void testEqualsOnEmptyMapAdapterWithNonHMapObject() {
        assertFalse("Empty MapAdapter should not be equal to a non-HMap object", this.map.equals("Not a map"));
    }

    @Test
    public void testGetOnEmptyMapAdapter() {
        assertNull("Get should return null for any key", this.map.get("testKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetOnEmptyMapAdapterWithNullKey() {
        this.map.get(null);
    }

    @Test
    public void testHashCodeOnEmptyMapAdapter() {
        assertEquals("Hash code of an empty MapAdapter should be 0", 0, this.map.hashCode());
    }

    @Test
    public void testIsEmptyOnEmptyMapAdapter() {
        assertTrue("MapAdapter should be empty", this.map.isEmpty());
    }

    @Test
    public void testKeySetOnEmptyMapAdapter() {
        assertNotNull("KeySet of an empty MapAdapter should not be null", this.map.keySet());
        assertTrue("KeySet of an emptyMapAdapter should be empty", this.map.keySet().isEmpty());
    }

    @Test
    public void testPutOnEmptyMapAdapter() {
        this.map.put("testKey", "testValue");
        assertEquals("Size should be 1 after put", 1, this.map.size());
        assertFalse("MapAdapter should not be empty after put", this.map.isEmpty());
        assertTrue("MapAdapter should contain the key after put", this.map.containsKey("testKey"));
        assertTrue("MapAdapter should contain the value after put", this.map.containsValue("testValue"));
        assertEquals("Get should return the value after put", "testValue", this.map.get("testKey"));
    }    

    @Test
    public void testPutOnEmptyMapAdapterWithThisMapAsAKeyInsertsANewEntry () {
        this.map.put(this.map, "testValue");
        assertFalse("MapAdapter should not be empty after put with this map as a key", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a key should have size 1", 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
    }

    @Test
    public void testPutOnEmptyMapAdapterWithThisMapAsAValueInsertsANewEntry () {
        this.map.put("testKey", this.map);
        assertFalse("MapAdapter should not be empty after put with this map as a value", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a value should have size 1", 1, this.map.size());
        assertEquals("Get should return the value after put", this.map, this.map.get("testKey"));
    }

    @Test
    public void testPutOnEmptyMapAdapterWithThisMapAsAKeyAndThisMapAsAValueInsertsANewEntry () {
        this.map.put(this.map, this.map);
        assertFalse("MapAdapter should not be empty after put with this map as a key and value", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a key and value should have size 1", 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
        //and cannot check if this.map.equals(this.map.get(this.map)) for the same reason
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyOnEmptyMapAdapter() {
        this.map.put(null, "value");
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullValueOnEmptyMapAdapter() {
        this.map.put("key", null);
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyAndNullValueOnEmptyMapAdapter() {
        this.map.put(null, null);
    }

    @Test
    public void testPutAllOnEmptyMapAdapterWithAnotherEmptyMapMapAdapter() {
        HMap anotherMap = new MapAdapter();
        this.map.putAll(anotherMap);
        assertTrue("PutAll on empty MapAdapter from empty map should not change its state", this.map.isEmpty());
        assertEquals("Size after PutAll on empty MapAdapter from empty map should still be 0", 0, this.map.size());
        assertTrue("Both the maps should be equal", this.map.equals(anotherMap));
    }

    @Test
    public void testPutAllWithThisMapAdapter() {
        this.map.putAll(this.map);
        assertTrue("MapAdapter should be empty after putAll with empty map", this.map.isEmpty());
        assertEquals("MapAdapter after putAll with this map should have size 0", 0, this.map.size());
    }

    @Test
    public void testPutAllOnEmptyMapAdapterWithAnotherNonEmptyMapAdapter() {
        HMap anotherMap = new MapAdapter();
        for (int i = 0; i < 5; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        this.map.putAll(anotherMap);
        assertFalse("MapAdapter should not be empty after putAll with another non empty MapAdapter", this.map.isEmpty());
        assertEquals("MapAdapter size after putAll with another MapAdapter of size 5 should be 5", 5, this.map.size());
        assertTrue("MapAdapter should be equal to another non empty MapAdapter after putAll", this.map.equals(anotherMap));
        for (int i = 0; i < 5; i++) {
            assertTrue("MapAdapter should contain testKey" + i + " after putAll", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should contain testValue" + i + " after putAll", this.map.containsValue("testValue" + i));
            assertEquals("Get should return the correct value after putAll", "testValue" + i, this.map.get("testKey" + i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullMapOnEmptyMapAdapter() {
        this.map.putAll(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullKeyOnEmptyMapAdapter() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.putAll(anotherMap);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullValueOnEmptyMapAdapter() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.putAll(anotherMap);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullKeyAndNullValueOnEmptyMapAdapter() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        this.map.putAll(anotherMap);
    }
    
    @Test 
    public void testRemoveOnEmptyMapAdapter() {
        assertNull("Remove should return null for any key", this.map.remove("testKey"));
        assertTrue("MapAdapter should still be empty after remove on empty MapAdapter", this.map.isEmpty());
        assertEquals("Size should still be 0 after remove on empty MapAdapter", 0, this.map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullKeyOnEmptyMapAdapter() {
        this.map.remove(null);
    }

    @Test
    public void testSizeOnEmptyMapAdapter() {
        assertEquals("Size should be 0", 0, this.map.size());
    }

    @Test
    public void testValuesOnEmptyMapAdapter() {
        assertNotNull("Values collection of an empty MapAdapter should not be null", this.map.values());
        assertTrue("Values collection should be empty", this.map.values().isEmpty());
    }

    @Test
    public void testToStringOnEmptyMapAdapter() {
        assertEquals("String representation of an empty MapAdapter should be \"{}\"", "{}", this.map.toString());
    }
}