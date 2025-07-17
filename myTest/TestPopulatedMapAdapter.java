//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.Stack;
import myExceptions.UnsupportedOperationException;
import org.junit.After;

public class TestPopulatedMapAdapter {

    private static final int MAP_INITIAL_CAPACITY = 100;
    private HMap map;

    public TestPopulatedMapAdapter() {
        //intentionally left blank
    }

    @Before
    public void setUp() {
        this.map = new MapAdapter(MAP_INITIAL_CAPACITY);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    @Test
    public void testCopyConstuctorOnPopulatedMap() {
        HMap copy = new MapAdapter(this.map);
        assertEquals("Copy constructor should create an identical map", this.map, copy);
        assertEquals("Size of copied map should match original", this.map.size(), copy.size());
    }

    @Test
    public void clear() {
        this.map.clear();
        assertTrue("MapAdapter should be empty after clear", this.map.isEmpty());
        assertEquals("Size of map should be 0 after clear", 0, this.map.size());
    }

    @Test
    public void testContainsKeyOnPopulatedMapAdapter(){
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain every key inserted", this.map.containsKey("testKey" + i));
        }

        assertFalse("MapAdapter should not contain a key that is not contained", this.map.containsKey("nonExistentKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsKeyOnMapAdapterWithNullKey() {
        this.map.containsKey(null);
    }

    @Test(expected = StackOverflowError.class)
    public void testContainsKeyOnPopulatedMapAdapterWithThisKeyAsAMapCausesStackOverflow() {
        this.map.put(this.map, "value"); //does not cause stack overflow
        this.map.containsKey(this.map); //causes stack overflow
    }

    @Test
    public void testContainsValueOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain every value inserted", this.map.containsValue("testValue" + i));
        }

        assertFalse("MapAdapter should not contain a value that is not contained", this.map.containsValue("nonExistentValue"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsValueOnPopulatedMapAdapterWithNullValue() {
        this.map.containsValue(null);
    }

    @Test
    public void testEntrySetOnPopulatedMapAdapter() {
        assertNotNull("Entry set of a populated MapAdapter should not be null", this.map.entrySet());
    }

    @Test
    public void testEntrySetOnPopulatedMapAdapterWithDifferentTypeOfKeys() {
        this.map.put(1, "testValue");
        assertNotNull("Entry set of a populated MapAdapter with different type keys should not be null", this.map.entrySet());
    }

    @Test
    public void testEntrySetOnPopulatedMapAdapterWithDifferentTypeOfValues() {
        this.map.put("testKey", 1);
        assertNotNull("Entry set of a populated MapAdapter with different type values should not be null", this.map.entrySet());
    }

    @Test
    public void testEqualsOnPopulatedMapAdapter() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("Two maps with the same content should be equal", this.map.equals(anotherMap));
        anotherMap.clear();
        assertFalse("A map should not be equal to an empty map", this.map.equals(anotherMap));
        anotherMap.put("extraKey", "extraValue");
        assertFalse("Two maps with different content should not be equal", this.map.equals(anotherMap));
        anotherMap.clear();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("testKey" + i, "differentValue" + i);
        }
        assertFalse("Two maps with the same size and keys but different values should not be equal", this.map.equals(anotherMap));
        anotherMap.clear();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("differentKey" + i, "testValue" + i);
        }   
        assertFalse("Two maps with the same size and values values but different keys should not be equal", this.map.equals(anotherMap));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterWithNonHMapObject() {
        assertFalse("A map should not be equal to an object of a different type", this.map.equals("not a map"));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterWithNull() {
        assertFalse("A map should not be equal to null", this.map.equals(null));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterWithItself() {
        assertTrue("A map should be equal to itself", this.map.equals(this.map));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterIsSymmetric() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("If map A equals map B, then map B should equal map A", anotherMap.equals(this.map) && this.map.equals(anotherMap));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterWithEntrySet() {
        HSet entrySet = this.map.entrySet();
        assertFalse("MapAdapter should not be equal to its entry set", this.map.equals(this.map.entrySet()));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterIsTransitive() {
        HMap anotherMap = new MapAdapter(this.map);
        HMap anotherAnotherMap = new MapAdapter(this.map);
        assertTrue("If MapAdapter A equals MapAdapter B and MapAdapter B equals MapAdapter C, then MapAdapter A should equal MapAdapter C", 
                    this.map.equals(anotherMap) && anotherMap.equals(anotherAnotherMap) && this.map.equals(anotherAnotherMap));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeKeys() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put(i, "testValue" + i);
        }
        assertFalse("A map with different type keys should not be equal to the original map", this.map.equals(anotherMap));
    }

    @Test
    public void testEqualsOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("testKey" + i, i);
        }
        assertFalse("A map with different type values should not be equal to the original map", this.map.equals(anotherMap));
    }

    @Test(expected = StackOverflowError.class)
    public void testEqualsBetweenTwoMapAdaptersContainingThisMapAsKey() {
        this.map.clear();
        this.map.put(this.map, "testValue"); //does not cause stack overflow
        HMap anotherMap = new MapAdapter(this.map);
        this.map.equals(anotherMap); //causes stack overflow
    }

    @Test
    public void testGetOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertEquals("MapAdapter should return the correct value for each key", "testValue" + i, this.map.get("testKey" + i));
        }
        assertNull("MapAdapter should return null for a non-existent key", this.map.get("nonExistentKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetOnPopulatedMapAdapterWithNullKey() {
        this.map.get(null);
    }

    @Test(expected = StackOverflowError.class)
    public void testGetOnPopulatedMapAdapterWithThisKeyAsAMapCausesStackOverflow() {
        this.map.put(this.map, "testValue"); //does not cause stack overflow
        this.map.get(this.map); //causes stack overflow
    }

    @Test
    public void testHashCodeOnPopulatedMapAdapter() {
        HMap anotherMap = new MapAdapter(this.map);
        int hashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            hashCode += entry.getKey().hashCode() ^ entry.getValue().hashCode();
        }
        assertEquals("Hash code of the map should match the calculated hash code", hashCode, this.map.hashCode());
        assertEquals("Hash code of two maps with the same content should be equal", this.map.hashCode(), anotherMap.hashCode());
        this.map.clear();
        this.map.put(1, 1);
        this.map.put(2, 2);
        this.map.put(3, 3);
        assertEquals("Hash code should be 0 when the map has entries with key = value", 0, this.map.hashCode());
    }

    @Test
    public void testHashCodeOnPopulatedMapAdapterWithDifferentTypeOfKeys() {
        this.map.put(1, "testValue");
        int hashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            hashCode += entry.getKey().hashCode() ^ entry.getValue().hashCode();
        }
        assertEquals("Hash code should be calculated correctly with different type keys", hashCode, this.map.hashCode());
    }

    @Test
    public void testHashCodeOnPopulatedMapAdapterWithDifferentTypeOfValues() {
        this.map.put("testKey", 1);
        int hashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            hashCode += entry.getKey().hashCode() ^ entry.getValue().hashCode();
        }
        assertEquals("Hash code should be calculated correctly with different type values", hashCode, this.map.hashCode());
    }

    @Test(expected = StackOverflowError.class)
    public void testHashCodeOnPopulatedMapAdapterWithThisKeyAsAMapCausesStackOverflow() {
        this.map.put(this.map, "value"); //does not cause stack overflow
        this.map.hashCode(); //causes stack overflow
    }

    @Test(expected = StackOverflowError.class)
    public void testHashCodeOnPopulatedMapAdapterWithThisValueAsAMapCausesStackOverflow() {
        this.map.put("key", this.map); //does not cause stack overflow
        this.map.hashCode(); //causes stack overflow
    }

    @Test(expected = StackOverflowError.class)
    public void testHashCodeOnPopulatedMapAdapterWithThisKeyAndValueAsAMapCausesStackOverflow() {
        this.map.put(this.map, this.map); //does not cause stack overflow
        this.map.hashCode(); //causes stack overflow
    }

    @Test
    public void testIsEmptyOnPopulatedMapAdapter() {
        assertFalse("MapAdapter should not be empty after adding entries", this.map.isEmpty());
        this.map.clear();
        assertTrue("MapAdapter should be empty after clearing", this.map.isEmpty());
    }

    @Test
    public void testKeySetOnPopulatedMapAdapter() {
        HSet keySet = this.map.keySet();
        assertNotNull("KeySet of a populated MapAdapter should not be null", keySet);
        assertFalse("KeySet of a populated MapAdapter should not be empty", keySet.isEmpty());
    }

    @Test
    public void testkeySetOnPopulatedMapAdapterWithDifferentTypeOfKeys() {
        this.map.put(1, "testValue");
        assertNotNull("KeySet of a populated MapAdapter with different type keys should not be null", this.map.keySet());
    }

    @Test
    public void testPutOnPopulatedMapAdapter() {
        assertEquals("Put should return the previous value for an existing key", "testValue0", this.map.put("testKey0", "newValue0"));
        assertEquals("New value should have replaced the old value", "newValue0", this.map.get("testKey0"));
        assertEquals("Size of the map should remain the same after updating an existing key", MAP_INITIAL_CAPACITY, this.map.size());
        assertNull("Put should return null for a new key", this.map.put("newKey", "newValue"));
        assertEquals("Size of the map should increase by 1 after adding a new key", MAP_INITIAL_CAPACITY + 1, this.map.size());
        assertEquals("New key should be retrievable after put", "newValue", this.map.get("newKey"));
    }

    @Test
    public void testPutOnPopulatedMapAdapterWithDifferentTypeOfkey() {
        this.map.put(1, "testValue");
        assertTrue("MapAdapter should allow different type keys", this.map.containsKey(1));
        assertEquals("Value for the different type key should be retrievable", "testValue", this.map.get(1));
        assertEquals("Size of the map should increase by 1 after adding a different type key", MAP_INITIAL_CAPACITY + 1, this.map.size());
    }

    @Test
    public void testPutOnPopulatedMapAdapterWithDifferentTypeOfValue() {
        this.map.put("testKey", 1);
        assertTrue("MapAdapter should allow different type values", this.map.containsValue(1));
        assertEquals("Value for the different type value should be retrievable", 1, this.map.get("testKey"));
        assertEquals("Size of the map should increase by 1 after adding a different type value", MAP_INITIAL_CAPACITY + 1, this.map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testPutOnPopulatedMapAdapterWithNullKey() {
        this.map.put(null, "value");
    }

    @Test(expected = NullPointerException.class)
    public void testPutOnPopulatedMapAdapterWithNullValue() {
        this.map.put("key", null);
    }

    @Test(expected = NullPointerException.class)
    public void testPutOnPopulatedMapAdapterWithNullKeyAndValue() {
        this.map.put(null, null);
    }

    @Test
    public void testPutOnPopulatedMapAdapterWithThisMapAdapterAsAKeyInsertsANewEntry() {
        this.map.put(this.map, "testValue");
        assertEquals("Using the MapAdapter itself as a key should increase the size of the map", MAP_INITIAL_CAPACITY + 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
    }

    @Test
    public void testPutOnPopulatedMapAdapterWithThisMapAdapterAsAValueInsertsANewEntry() {
        this.map.put("testKey", this.map);
        assertEquals("Using the MapAdapter itself as a value should increase the size of the map", MAP_INITIAL_CAPACITY + 1, this.map.size());
        assertEquals("Using the MapAdapter itself as a value should be retrievable", this.map, this.map.get("testKey"));
    }

    @Test
    public void testPutOnPopulatedMapAdapterWithThisMapAsAKeyAndThisMapAsAValueInsertsANewEntry () {
        this.map.put(this.map, this.map);
        assertFalse("MapAdapter should not be empty after put with this map as a key and value", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a key and value should have size 1", MAP_INITIAL_CAPACITY + 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
        //and cannot check if this.map.equals(this.map.get(this.map)) for the same reason
    }

    @Test(expected = StackOverflowError.class)
    public void testPutOnPopulatedMapAdapterWithThisKeyAsAValueWhenItIsAlreadyAKeyCausesStackOverflow() {
        this.map.put(this.map, "testValue"); //does not cause stack overflow
        this.map.put(this.map, "testValue"); //causes stack overflow
    }

    @Test
    public void testPutAllOnPopulaterMapAdapter() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("anotherKey" + i, "anotherValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain all keys from the other map", this.map.containsKey("anotherKey" + i));
            assertTrue("MapAdapter should contain all values from the other map", this.map.containsValue("anotherValue" + i));
        }
    }

    @Test
    public void testPutAllOnPopulatedMapAdapterWithEmptyMap() {
        HMap emptyMap = new MapAdapter();
        this.map.putAll(emptyMap);
        assertEquals("Size of the map should remain the same after putting an empty map", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    @Test
    public void testPutAllOnPopulatedMapAdapterWithAMapWithTheSameKeysAndValues() {
        HMap anotherMap = new MapAdapter(this.map);
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should remain the same after putting a map with the same keys and values", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    @Test
    public void testPutAllOnPopulatedMapAdapterWithAMapWithSameKeysButDifferentValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("testKey" + i, "differentValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should remain the same after putting a map with the same keys but different values", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should contain the different values", this.map.containsValue("differentValue" + i));
        }
    }

    @Test
    public void testPutAllOnPopulatedMapAdapterWithAMapWithDifferentKeysButSameValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("differentKey" + i, "testValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should remain the same after putting a map with different keys but same values", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain the different keys", this.map.containsKey("differentKey" + i));
            assertTrue("MapAdapter should still contain all the original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    @Test
    public void testPutAllOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeKeys() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put(i, "differentValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map with different type keys", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain all keys from the other map with different type keys", this.map.containsKey(i));
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
            assertTrue("MapAdapter should contain all values from the other map with different type keys", this.map.containsValue("differentValue" + i));
        }
    }

    @Test
    public void testPutAllOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("differentKey" + i, i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map with different type values", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain all keys from the other map with different type values", this.map.containsKey("differentKey" + i));
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should contain all the original values", this.map.containsValue("testValue" + i));
            assertTrue("MapAdapter should contain all values from the other map with different type values", this.map.containsValue(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapAdapterWithNullMap() {
        this.map.putAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapWithMapContainingNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "value");
        this.map.putAll(anotherMap);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapWithMapContainingNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("key", null);
        this.map.putAll(anotherMap);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapWithMapContainingNullKeyAndValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        this.map.putAll(anotherMap);
    }

    @Test
    public void testRemoveOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertEquals("Remove should return the value for the key being removed", "testValue" + i, this.map.remove("testKey" + i));
            assertFalse("MapAdapter should not contain the key after removal", this.map.containsKey("testKey" + i));
            assertFalse("MapAdapter should not contain the value after removal", this.map.containsValue("testValue" + i));
            assertEquals("Size of the map should decrease by 1 after each removal", MAP_INITIAL_CAPACITY - i - 1, this.map.size());
        }
        assertTrue("MapAdapter should be empty after removing all entries", this.map.isEmpty());
        assertEquals("Size of the map should be 0 after removing all entries", 0, this.map.size());
        assertNull("Remove should return null for a non-existent key", this.map.remove("nonExistentKey"));
    }

    @Test
    public void testRemoveOnPopulatedMapAdapterWithDifferentTypeOfKey() {
        this.map.put(1, "testValue");
        assertEquals("Remove should return the value for the different type key", "testValue", this.map.remove(1));
        assertFalse("MapAdapter should not contain the different type key after removal", this.map.containsKey(1));
        assertEquals("Size of MapAdapter should be the same after removing the different type key", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    @Test
    public void testRemoveOnPopulatedMapAdapterWithDifferentTypeOfValue() {
        this.map.put("testKey", 1);
        assertEquals("Remove should return the value for the different type value", 1, this.map.remove("testKey"));
        assertFalse("MapAdapter should not contain the different type value after removal", this.map.containsValue(1));
        assertEquals("Size of MapAdapter should be the same after removing the different type value", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedMapAdapterWithNullKey() {
        this.map.remove(null);
    }

    @Test(expected = StackOverflowError.class)
    public void testRemoveOnPopulatedMapAdapterWithThisKeyAsAMapCausesStackOverflow() {
        this.map.put(this.map, "testValue"); //does not cause stack overflow
        this.map.remove(this.map); //causes stack overflow
    }

    @Test
    public void testSizeOnPopulatedMapAdapter() {
        assertEquals("Size of the map should match the initial capacity", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test
    public void testValuesOnPopulatedMapAdapter() {
        HCollection values = this.map.values();
        assertNotNull("Values collection of a populated MapAdapter should not be null", values);
    }   

    @Test
    public void testValuesOnPopulatedMapAdapterWithDifferentTypeOfValues() {
        this.map.put("testKey", 1);
        assertNotNull("Values collection of a populated MapAdapter with different type values should not be null", this.map.values());
    }

    @Test
    public void testToStringOnPopulatedMapAdapter() {
        HIterator iterator = this.map.entrySet().iterator();
        String expected = "{";
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expected += entry.toString() + (iterator.hasNext() ? ", " : "}");
        }

        assertEquals("toString() should return a string representation of the map created this way", expected, this.map.toString());
    }

    @Test
    public void testToStringOnPopulatedMapAdapterWithDifferentTypeOfKeysAndValues() {
        this.map.put(1, 1);
        HIterator iterator = this.map.entrySet().iterator();
        String expected = "{";
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expected += entry.toString() + (iterator.hasNext() ? ", " : "}");
        }

        assertEquals("toString() should return a string representation of the map created this way", expected, this.map.toString());
    }
}