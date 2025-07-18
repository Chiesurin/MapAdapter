//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import myExceptions.UnsupportedOperationException;
import org.junit.After;

public class TestPopulatedAbstractCollections {
    private static final int MAP_INITIAL_CAPACITY = 100;
    private HMap map;

    public TestPopulatedAbstractCollections() {
        // intentionally left empty
    }

    @Before
    public void setUp() {
        this.map = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    /*--------------------------------------------------------------*/
    /*----------------------- EntrySet TESTS -----------------------*/
    /*--------------------------------------------------------------*/

    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.add("anything");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.addAll(this.map.entrySet());
    }

    @Test
    public void testClearOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.clear();
        assertTrue("EntrySet should be empty after clear", entrySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing EntrySet", this.map.isEmpty());
    }

    @Test
    public void testContainsAllOnPopulatedEntrySet() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("EntrySet should contain all entries", this.map.entrySet().containsAll(anotherMap.entrySet()));
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            anotherMap.remove("testKey" + i);
        }
        assertTrue("EntrySet should still contain all remaining entries after partial removal",
                   this.map.entrySet().containsAll(anotherMap.entrySet()));
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("EntrySet should contain all entries from an empty EntrySet", this.map.entrySet().containsAll(anotherEmptyMap.entrySet()));
        HMap anotherPopulatedMap = new MapAdapter();
        anotherPopulatedMap.put("testKey100", "testValue100");
        assertFalse("EntrySet should not contain all entries from a completely different map", this.map.entrySet().containsAll(anotherPopulatedMap.entrySet()));
    }

    @Test(expected = ClassCastException.class)
    public void testContainsAllOnPopulatedEntrySetWithANonEntrySet() {
        this.map.entrySet().containsAll(this.map.keySet());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetNull() {
        this.map.entrySet().containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetWithNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetWithNullKeysAndValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    @Test
    public void testHashCodeOnPopulatedEntrySet() {
        int expectedHashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expectedHashCode += entry.hashCode();
        }
        assertEquals("HashCode should match expected value", expectedHashCode, this.map.entrySet().hashCode());
    }

    @Test
    public void testIsEmptyOnPopulatedEntrySet() {
        assertFalse("EntrySet should not be empty", this.map.entrySet().isEmpty());
    }

    @Test
    public void testRemoveAllOnPopulatedEntrySet() {
        HMap anotherMap = new MapAdapter();
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("EntrySet should remove all specified entries", this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertEquals("MapAdapter should have reduced size after removing entries",
                     MAP_INITIAL_CAPACITY - anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed keys", this.map.containsKey("testKey" + i));
            assertFalse("MapAdapter should not contain removed values", this.map.containsValue("testValue" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain remaining keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain remaining values", this.map.containsValue("testValue" + i));
        }
        assertFalse("Calling removeAll with same EntrySet should not change the EntrySet",
                    this.map.entrySet().removeAll(anotherMap.entrySet()));
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        this.map.entrySet().removeAll(anotherMap.entrySet());
        assertTrue("EntrySet should be empty after removing all entries", this.map.entrySet().isEmpty());
        assertTrue("MapAdapter should be empty after removing all entries", this.map.isEmpty());
    }

    @Test
    public void testRemoveAllOnPopulatedEntrySetWithAnEmptyEntrySet() {
        HMap anotherEmptyMap = new MapAdapter();
        assertFalse("EntrySet should not change when removing from an empty EntrySet", this.map.entrySet().removeAll(anotherEmptyMap.entrySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from an empty EntrySet", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnPopulatedEntrySetWithNull() {
        this.map.entrySet().removeAll(null);
    }   

    @Test
    public void testRemoveAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("EntrySet should not change when removing from another EntrySet with null keys", 
                    this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another EntrySet with null keys", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test
    public void testRemoveAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertFalse("EntrySet should not change when removing from another EntrySet with null values", 
                    this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another EntrySet with null values", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test
    public void testRetainAllOnPopulatedEntrySet() {
        HMap anotherMap = new MapAdapter();
        //partial intersection
        for (int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("EntrySet should be modified when calling retainAll", this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertEquals("MapAdapter should have the same size as anotherMap after retainAll",
                     anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed keys", this.map.containsKey("testKey" + i));
            assertFalse("MapAdapter should not contain removed values", this.map.containsValue("testValue" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain retained keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain retained values", this.map.containsValue("testValue" + i));
        }
        //full intersection
        assertFalse("Calling retainAll with same EntrySet should not change the EntrySet",
                    this.map.entrySet().retainAll(anotherMap.entrySet()));
    }

    @Test
    public void testRetainAllOnPopulatedEntrySetWithAnEmptyEntrySet() {
        HMap anotherEmptyMap = new MapAdapter();
        //no intersection
        assertTrue("EntrySet should be modified when retaining from an empty EntrySet",
                   this.map.entrySet().retainAll(anotherEmptyMap.entrySet()));
        assertTrue("EntrySet should be empty after retaining from an empty EntrySet", this.map.entrySet().isEmpty());
        assertTrue("MapAdapter should be empty after retaining from an empty EntrySet", this.map.isEmpty());
    }

    @Test
    public void testRetainAllOnPopulatedEntrySetWithANonEntrySet() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertTrue("EntrySet should change when retaining from a non-EntrySet", 
                    this.map.entrySet().retainAll(anotherMap.keySet()));
        assertTrue("MapAdapter should be empty after retaining from a non-EntrySet", this.map.isEmpty());
        assertTrue("EntrySet should be empty after retaining from a non-EntrySet", this.map.entrySet().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllOnPopulatedEntrySetWithNull() {
        this.map.entrySet().retainAll(null);
    }

    @Test
    public void testRetainAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertTrue("EntrySet should change when retaining from another EntrySet with null keys",
                   this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("MapAdapter should be empty after retaining from another EntrySet with null keys", this.map.isEmpty());
        assertTrue("EntrySet should be empty after retaining from another EntrySet with null keys", this.map.entrySet().isEmpty());
    }

    @Test
    public void testRetainAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertTrue("EntrySet should change when retaining from another EntrySet with null values",
                   this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("MapAdapter should be empty after retaining from another EntrySet with null values", this.map.isEmpty());
        assertTrue("EntrySet should be empty after retaining from another EntrySet with null values", this.map.entrySet().isEmpty());
    }

    @Test
    public void testSizeOnPopulatedEntrySet() {
        assertEquals("Size of EntrySet should match the size of the MapAdapter", MAP_INITIAL_CAPACITY, this.map.entrySet().size());
    }

    @Test
    public void testToArrayOnPopulatedEntrySet() {
        Object[] array = this.map.entrySet().toArray();
        assertEquals("Array length should match the size of the EntrySet", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            assertEquals("Array element at index " + index + " should match the entry", entry, (HMap.HEntry) array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedEntrySetWithArrayParameterOfRightDimension() {
        Object[] array = new Object[100];
        array = this.map.entrySet().toArray(array);
        assertEquals("Array length should match the size of the EntrySet", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            assertEquals("Array element at index " + index + " should match the entry", entry, (HMap.HEntry) array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedEntrySetWithUnderdimensionedArray() {
        Object[] array = new Object[50];
        array = this.map.entrySet().toArray(array);
        assertEquals("Array length should match the size of the EntrySet", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            assertEquals("Array element at index " + index + " should match the entry", entry, (HMap.HEntry) array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedEntrySetWithOverdimensionedArray() {
        Object[] array = new Object[150];
        array = this.map.entrySet().toArray(array);
        assertEquals("Array length should be the same",150, array.length);
        HIterator iterator = this.map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            assertEquals("Array element at index " + index + " should match the entry", entry, (HMap.HEntry) array[index]);
            index++;
        }
        assertNull("Array element at index " + index + " should be null", array[index]);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayOnPopulatedEntrySetWithNull() {
        this.map.entrySet().toArray(null);
    }
    
    @Test
    public void testToStringOnPopulatedEntrySet() {
        String expected = "[";
        HIterator iterator = this.map.entrySet().iterator();
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expected += entry.toString() + (iterator.hasNext() ? ", " : "");
        }
        expected += "]";
        assertEquals("ToString on a populated EntrySet should match the expected format", expected, this.map.entrySet().toString());
    }

    @Test
    public void testPopulatedEntrySetIsBackedByMapAdapter() {
        HSet entrySet = this.map.entrySet();
        assertEquals("EntrySet should have the same size as MapAdapter", this.map.size(), entrySet.size());
        this.map.put("newKey", "newValue");
        assertEquals("EntrySet should reflect the changes in the MapAdapter", this.map.size(), entrySet.size());
        HMap.HEntry entryToBeRemoved = (HMap.HEntry) entrySet.iterator().next();
        entrySet.remove(entryToBeRemoved);
        assertEquals("MapAdapter should have decreased size by 1 after removing an entry from EntrySet",
                     MAP_INITIAL_CAPACITY, this.map.size());
        assertFalse("MapAdapter should not contain the removed key", this.map.containsKey(entryToBeRemoved.getKey()));
        assertFalse("MapAdapter should not contain the removed value", this.map.containsValue(entryToBeRemoved.getValue()));
        entrySet.clear();
        assertTrue("EntrySet should be empty after clearing", entrySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing EntrySet", this.map.isEmpty());
    }

    @Test
    public void testEqualsOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter(this.map).entrySet();
        assertTrue("EntrySet should be equal to another EntrySet with the same entries", entrySet.equals(anotherEntrySet));
    }

    @Test
    public void testEqualsOnPopulatedEntrySetWithDifferentEntrySet() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.remove("testKey0");
        assertFalse("EntrySet should not be equal to a different EntrySet", this.map.entrySet().equals(anotherMap.entrySet()));
    }

    @Test
    public void testEqualsOnPopulatedEntrySetWithNonHSet() {
        assertFalse("EntrySet should not be equal to a non-HSet object", this.map.entrySet().equals(this.map.values()));
    }

    @Test
    public void testEqualsOnPopulatedEntrySetWithNull() {
        assertFalse("EntrySet should not be equal to null", this.map.entrySet().equals(null));
    }   

    @Test
    public void testEqualsOnPopulatedEntryWithItself() {
        assertTrue("A populated EntrySet should be equal to itself", this.map.entrySet().equals(this.map.entrySet()));
    }

    @Test
    public void testEqualsOnPopulatedEntrySetIsSymmetric() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter(this.map).entrySet();
        assertTrue("EntrySet should be equal to another EntrySet with the same entries and vice-versa", 
            entrySet.equals(anotherEntrySet) && anotherEntrySet.equals(entrySet));
    }

    @Test
    public void testEqualsOnPopulatedEntrySetIsTransitive() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter(this.map).entrySet();
        HSet anotherAnotherEntrySet = new MapAdapter(this.map).entrySet();
        assertTrue("If EntrySet A equals EntrySet B and MapAdapter B equals EntrySet C, then EntrySet A should equal MapAdapter C",
            entrySet.equals(anotherEntrySet) && anotherEntrySet.equals(anotherAnotherEntrySet) && entrySet.equals(anotherAnotherEntrySet));
    }

    @Test
    public void testContainsOnPopulatedEntrySet() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        assertTrue("EntrySet should contain the entry", this.map.entrySet().contains(entry));
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "differentValue");
        assertFalse("EntrySet should not contain an entry with a different key and value",
                    this.map.entrySet().contains(anotherMap.entrySet().iterator().next()));
    }

    @Test(expected = ClassCastException.class)
    public void testContainsOnPopulatedEntrySetWithNonHEntryObject() {
        this.map.entrySet().contains("nonHEntryObject");
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedEntrySetWithNull() {
        this.map.entrySet().contains(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedEntrySetWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().contains(anotherMap.entrySet().iterator().next());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedEntrySetWithNullValue() {
        HMap anotherMap = new DummyTestMap();       
        anotherMap.put("testKey", null);
        this.map.entrySet().contains(anotherMap.entrySet().iterator().next());
    }

    @Test
    public void testRemoveOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        HIterator iterator = entrySet.iterator();
        HMap.HEntry entryToBeRemoved = (HMap.HEntry) iterator.next();
        entrySet.remove(entryToBeRemoved);
        assertEquals("EntrySet should have decreased size by 1 after removing an entry from EntrySet",
                     MAP_INITIAL_CAPACITY - 1, entrySet.size());
        assertFalse("EntrySet should not contain the removed entry", entrySet.contains(entryToBeRemoved));
        assertFalse("MapAdapter should not contain the removed key", this.map.containsKey(entryToBeRemoved.getKey()));
        assertFalse("MapAdapter should not contain the removed value", this.map.containsValue(entryToBeRemoved.getValue()));
        while(iterator.hasNext()) {
            entryToBeRemoved = (HMap.HEntry) iterator.next();
            entrySet.remove(entryToBeRemoved);
            assertFalse("EntrySet should not contain the removed entry", entrySet.contains(entryToBeRemoved));
        }
        assertTrue("EntrySet should be empty after removing all entries", entrySet.isEmpty());
        assertTrue("MapAdapter should be empty after removing all entries", this.map.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedEntrySetWithNull() {
        this.map.entrySet().remove(null);
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveOnPopulatedEntrySetWithNonHEntryObject() {
        this.map.entrySet().remove("nonHEntryObject");
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedEntrySetWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().remove(anotherMap.entrySet().iterator().next());
    }   

    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedEntrySetWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().remove(anotherMap.entrySet().iterator().next());
    }

    @Test
    public void testIteratorOnPopulatedEntrySet() {
        assertNotNull("Iterator should not be null", this.map.entrySet().iterator());
    }
    
    /*--------------------------------------------------------------*/
    /*------------------------ KeySet TESTS ------------------------*/
    /*--------------------------------------------------------------*/

    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        keySet.add("anything");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        keySet.addAll(this.map.keySet());
    }

    @Test
    public void testClearOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        keySet.clear();
        assertTrue("KeySet should be empty after clear", keySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing KeySet", this.map.isEmpty());
    }

    @Test
    public void testContainsAllOnPopulatedKeySet() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("KeySet should contain all keys", this.map.keySet().containsAll(anotherMap.keySet()));
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            anotherMap.remove("testKey" + i);
        }
        assertTrue("KeySet should still contain all remaining keys after partial removal",
                   this.map.keySet().containsAll(anotherMap.keySet()));
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("KeySet should contain all keys from an empty KeySet", this.map.keySet().containsAll(anotherEmptyMap.keySet()));
        HMap anotherPopulatedMap = new MapAdapter();
        anotherPopulatedMap.put("testKey100", "testValue100");
        assertFalse("KeySet should not contain all keys from a completely different map", this.map.keySet().containsAll(anotherPopulatedMap.keySet()));
    }

    @Test
    public void testContainsAllOnPopulatedKeySetWithANonKeySet() {
        assertFalse("KeySet should not contain all keys from a non-KeySet object", this.map.keySet().containsAll(this.map.entrySet()));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedKeySetNull() {
        this.map.keySet().containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedKeySetWithNullKeys() {
        HMap anotherMap = new DummyTestMap();   
        anotherMap.put(null, "testValue");
        this.map.keySet().containsAll(anotherMap.keySet());
    }

    @Test
    public void testHashCodeOnPopulatedKeySet() {
        int expectedHashCode = 0;
        HIterator iterator = this.map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            expectedHashCode += key.hashCode();
        }
        assertEquals("HashCode should match expected value", expectedHashCode, this.map.keySet().hashCode());
    }

    @Test
    public void testIsEmptyOnPopulatedKeySet() {
        assertFalse("KeySet should not be empty", this.map.keySet().isEmpty());
    }

    @Test
    public void testRemoveAllOnPopulatedKeySet() {
        HMap anotherMap = new MapAdapter();
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("KeySet should remove all specified keys", this.map.keySet().removeAll(anotherMap.keySet()));
        assertEquals("MapAdapter should have reduced size after removing keys",
                     MAP_INITIAL_CAPACITY - anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed keys", this.map.containsKey("testKey" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain remaining keys", this.map.containsKey("testKey" + i));
        }
        assertFalse("Calling removeAll with same KeySet should not change the KeySet",
                    this.map.keySet().removeAll(anotherMap.keySet()));
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        this.map.keySet().removeAll(anotherMap.keySet());
        assertTrue("KeySet should be empty after removing all keys", this.map.keySet().isEmpty());
        assertTrue("MapAdapter should be empty after removing all keys", this.map.isEmpty());
    }

    @Test
    public void testRemoveAllOnPopulatedKeySetWithAnEmptyKeySet() {
        HMap anotherEmptyMap = new MapAdapter();
        assertFalse("KeySet should not change when removing from an empty KeySet", this.map.keySet().removeAll(anotherEmptyMap.keySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from an empty KeySet", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveAllOnPopulatedKeySetWithAnEntrySet() {
        this.map.keySet().removeAll(this.map.entrySet());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnPopulatedKeySetWithNull() {
        this.map.keySet().removeAll(null);
    }

    @Test
    public void testRemoveAllOnPopulatedKeySetWithAnotherKeySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("KeySet should not change when removing from another KeySet with null keys",
                    this.map.keySet().removeAll(anotherMap.keySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another KeySet with null keys", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test
    public void testRetainAllOnPopulatedKeySet() {
        HMap anotherMap = new MapAdapter();
        //partial intersection
        for (int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("KeySet should be modified when calling retainAll", this.map.keySet().retainAll(anotherMap.keySet()));
        assertEquals("MapAdapter should have the same size as   anotherMap after retainAll",
                     anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed keys", this.map.containsKey("testKey" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain retained keys", this.map.containsKey("testKey" + i));
        }
        //full intersection
        assertFalse("Calling retainAll with same KeySet should not change the KeySet",
                    this.map.keySet().retainAll(anotherMap.keySet()));
    }

    @Test
    public void testRetainAllOnPopulatedKeySetWithAnEmptyKeySet() {
        HMap anotherEmptyMap = new MapAdapter();
        //no intersection
        assertTrue("KeySet should be modified when retaining from an empty KeySet",
                   this.map.keySet().retainAll(anotherEmptyMap.keySet()));
        assertTrue("KeySet should be empty after retaining from an empty KeySet", this.map.keySet().isEmpty());
        assertTrue("MapAdapter should be empty after retaining from an empty KeySet", this.map.isEmpty());
    }   

    @Test(expected = ClassCastException.class)
    public void testRetainAllOnPopulatedKeySetWithAnEntrySet() {
        this.map.keySet().retainAll(this.map.entrySet());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllOnPopulatedKeySetWithNull() {
        this.map.keySet().retainAll(null);
    }

    @Test
    public void testRetainAllOnPopulatedKeySetWithAnotherKeySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertTrue("KeySet should change when retaining from another KeySet with null keys",
                   this.map.keySet().retainAll(anotherMap.keySet()));
        assertTrue("MapAdapter should be empty after retaining from another KeySet with null keys", this.map.isEmpty());
        assertTrue("KeySet should be empty after retaining from another KeySet with null keys", this.map.keySet().isEmpty());
    }

    @Test
    public void testSizeOnPopulatedKeySet() {
        assertEquals("Size of KeySet should match the size of the MapAdapter", MAP_INITIAL_CAPACITY, this.map.keySet().size());
    }

    @Test
    public void testToArrayOnPopulatedKeySet() {
        Object[] array = this.map.keySet().toArray();
        assertEquals("Array length should match the size of the KeySet", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.keySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object key = iterator.next();
            assertEquals("Array element at index " + index + " should match the key", key, array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedKeySetWithArrayParameterOfRightDimension() {
        Object[] array = new Object[100];
        array = this.map.keySet().toArray(array);
        assertEquals("Array length should match the size of the KeySet", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.keySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object key = iterator.next();
            assertEquals("Array element at index " + index + " should match the key", key, array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedKeySetWithUnderdimensionedArray() {
        Object[] array = new Object[50];
        array = this.map.keySet().toArray(array);
        assertEquals("Array length should match the size of the KeySet", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.keySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {    
            Object key = iterator.next();
            assertEquals("Array element at index " + index + " should match the key", key, array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedKeySetWithOverdimensionedArray() {
        Object[] array = new Object[150];
        array = this.map.keySet().toArray(array);
        assertEquals("Array length should be the same", 150, array.length);
        HIterator iterator = this.map.keySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object key = iterator.next();
            assertEquals("Array element at index " + index + " should match the key", key, array[index]);
            index++;
        }
        assertNull("Array element at index " + index + " should be null", array[index]);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayOnPopulatedKeySetWithNull() {
        this.map.keySet().toArray(null);
    }   

    @Test
    public void testToStringOnPopulatedKeySet() {
        String expected = "[";
        HIterator iterator = this.map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            expected += key.toString() + (iterator.hasNext() ? ", " : "");
        }
        expected += "]";
        assertEquals("ToString on a populated KeySet should match the expected format", expected, this.map.keySet().toString());
    }

    @Test
    public void testPopulatedKeySetIsBackedByMapAdapter() {
        HSet keySet = this.map.keySet();
        assertEquals("KeySet should have the same size as MapAdapter", this.map.size(), keySet.size());
        this.map.put("newKey", "newValue");
        assertEquals("KeySet should reflect the changes in the MapAdapter", this.map.size(), keySet.size());
        Object keyToBeRemoved = keySet.iterator().next();
        keySet.remove(keyToBeRemoved);
        assertEquals("MapAdapter should have decreased size by 1 after removing a key from KeySet",
                     MAP_INITIAL_CAPACITY, this.map.size());
        assertFalse("MapAdapter should not contain the removed key", this.map.containsKey(keyToBeRemoved));
        keySet.clear();
        assertTrue("KeySet should be empty after clearing", keySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing KeySet", this.map.isEmpty());
    }

    @Test
    public void testEqualsOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter(this.map).keySet();
        assertTrue("KeySet should be equal to another KeySet with the same entries", keySet.equals(anotherKeySet));
    }

    @Test
    public void testEqualsOnPopulatedKeySetWithDifferentKeySet() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.remove("testKey0");
        assertFalse("KeySet should not be equal to a different KeySet", this.map.keySet().equals(anotherMap.keySet()));
    }

    @Test
    public void testEqualsOnPopulatedKeySetWithNonHSet() {
        assertFalse("KeySet should not be equal to a non-HSet object", this.map.keySet().equals(this.map.values()));
    }

    @Test
    public void testEqualsOnPopulatedKeySetWithNull() {
        assertFalse("KeySet should not be equal to null", this.map.keySet().equals(null));
    }   

    @Test
    public void testEqualsOnPopulatedKeySetWithItself() {
        assertTrue("A populated KeySet should be equal to itself", this.map.keySet().equals(this.map.keySet()));
    }

    @Test
    public void testEqualsOnPopulatedKeySetIsSymmetric() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter(this.map).keySet();
        assertTrue("KeySet should be equal to another KeySet with the same entries and vice-versa",
            keySet.equals(anotherKeySet) && anotherKeySet.equals(keySet));
    }

    @Test
    public void testEqualsOnPopulatedKeySetIsTransitive() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter(this.map).keySet();
        HSet anotherAnotherKeySet = new MapAdapter(this.map).keySet();
        assertTrue("If KeySet A equals KeySet B and MapAdapter B equals KeySet C, then KeySet A should equal MapAdapter C",
            keySet.equals(anotherKeySet) && anotherKeySet.equals(anotherAnotherKeySet) && keySet.equals(anotherAnotherKeySet));
    }

    @Test
    public void testContainsOnPopulatedKeySet() {
        Object key = this.map.keySet().iterator().next();
        assertTrue("KeySet should contain the key", this.map.keySet().contains(key));
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "differentValue");
        assertFalse("KeySet should not contain a key that is not present",
                    this.map.keySet().contains(anotherMap.keySet().iterator().next()));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedKeySetWithNull() {
        this.map.keySet().contains(null);
    }

    @Test
    public void testRemoveOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        Object keyToBeRemoved = keySet.iterator().next();
        keySet.remove(keyToBeRemoved);
        assertEquals("KeySet should have decreased size by 1 after removing a key from KeySet",
                     MAP_INITIAL_CAPACITY - 1, keySet.size());
        assertFalse("KeySet should not contain the removed key", keySet.contains(keyToBeRemoved));
        assertFalse("MapAdapter should not contain the removed key", this.map.containsKey(keyToBeRemoved));
        while(keySet.iterator().hasNext()) {
            keyToBeRemoved = keySet.iterator().next();
            keySet.remove(keyToBeRemoved);
            assertFalse("KeySet should not contain the removed key", keySet.contains(keyToBeRemoved));
        }
        assertTrue("KeySet should be empty after removing all keys", keySet.isEmpty());
        assertTrue("MapAdapter should be empty after removing all keys", this.map.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedKeySetWithNull() {
        this.map.keySet().remove(null);
    }

    /*--------------------------------------------------------------*/
    /*--------------------- ValuesCollection TESTS -----------------*/
    /*--------------------------------------------------------------*/

    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        values.add("anything");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        values.addAll(this.map.values());
    }

    @Test
    public void testClearOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        values.clear();
        assertTrue("ValueCollection should be empty after clear", values.isEmpty());
        assertTrue("MapAdapter should be empty after clearing ValueCollection", this.map.isEmpty());
    }

    @Test
    public void testContainsAllOnPopulatedValueCollection() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("ValueCollection should contain all values", this.map.values().containsAll(anotherMap.values()));
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            anotherMap.values().remove("testValue" + i);
        }
        assertTrue("ValueCollection should still contain all remaining values after partial removal",
                   this.map.values().containsAll(anotherMap.values()));
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("ValueCollection should contain all values from an empty ValueCollection", this.map.values().containsAll(anotherEmptyMap.values()));
        HMap anotherPopulatedMap = new MapAdapter();
        anotherPopulatedMap.put("testKey100", "testValue100");
        assertFalse("ValueCollection should not contain all values from a completely different map", this.map.values().containsAll(anotherPopulatedMap.values()));
    }

    @Test
    public void testContainsAllOnPopulatedValueCollectionWithANonValueCollection() {
        assertFalse("ValueCollection should not contain all values from a non-ValueCollection object", this.map.values().containsAll(this.map.entrySet()));
    }

    
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedValueCollectionWithNull() {
        this.map.values().containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedValueCollectionWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.values().containsAll(anotherMap.values());
    }

    @Test
    public void testHashCodeOnPopulatedValueCollection() {
        int expectedHashCode = 0;
        HIterator iterator = this.map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            expectedHashCode += value.hashCode();
        }
        assertEquals("HashCode should match expected value", expectedHashCode, this.map.values().hashCode());
    }

    @Test
    public void testIsEmptyOnPopulatedValueCollection() {
        assertFalse("ValueCollection should not be empty", this.map.values().isEmpty());
    }

    @Test
    public void testRemoveAllOnPopulatedValueCollection() {
        HMap anotherMap = new MapAdapter();
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("ValueCollection should remove all specified values", this.map.values().removeAll(anotherMap.values()));
        assertEquals("MapAdapter should have reduced size after removing values",
                     MAP_INITIAL_CAPACITY - anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed values", this.map.containsValue("testValue" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain remaining values", this.map.containsValue("testValue" + i));
        }
        assertFalse("Calling removeAll with same ValueCollection should not change the ValueCollection",
                    this.map.values().removeAll(anotherMap.values()));
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        this.map.values().removeAll(anotherMap.values());
        assertTrue("ValueCollection should be empty after removing all values", this.map.values().isEmpty());
        assertTrue("MapAdapter should be empty after removing all values", this.map.isEmpty());
    }

    
    @Test
    public void testRemoveAllOnPopulatedValueCollectionWithAnEmptyValueCollection() {
        HMap anotherEmptyMap = new MapAdapter();
        assertFalse("ValueCollection should not change when removing from an empty ValueCollection", this.map.values().removeAll(anotherEmptyMap.values()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from an empty ValueCollection", MAP_INITIAL_CAPACITY, this.map.size());
    }
    
    @Test(expected = ClassCastException.class)
    public void testRemoveAllOnPopulatedValueCollectionWithAnEntrySet() {
        this.map.values().removeAll(this.map.entrySet());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnPopulatedValueCollectionWithNull() {
        this.map.values().removeAll(null);
    }

    @Test
    public void testRemoveAllOnPopulatedValueCollectionWithAnotherValueCollectionContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("ValueCollection should not change when removing from another ValueCollection with null values",
                    this.map.values().removeAll(anotherMap.values()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another ValueCollection with null values", MAP_INITIAL_CAPACITY, this.map.size());
    }

    @Test
    public void testRetainAllOnPopulatedValueCollection() {
        HMap anotherMap = new MapAdapter();
        //partial intersection
        for (int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("ValueCollection should be modified when calling retainAll", this.map.values().retainAll(anotherMap.values()));
        assertEquals("MapAdapter should have the same size as anotherMap after retainAll",
                     anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed values", this.map.containsValue("testValue" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain retained values", this.map.containsValue("testValue" + i));
        }
        //full intersection
        assertFalse("Calling retainAll with same ValueCollection should not change the ValueCollection",
                    this.map.values().retainAll(anotherMap.values()));
    }

    @Test
    public void testRetainAllOnPopulatedValueCollectionWithAnEmptyValueCollection() {
        HMap anotherEmptyMap = new MapAdapter();
        //no intersection
        assertTrue("ValueCollection should be modified when retaining from an empty ValueCollection",
                   this.map.values().retainAll(anotherEmptyMap.values()));
        assertTrue("ValueCollection should be empty after retaining from an empty ValueCollection", this.map.values().isEmpty());
        assertTrue("MapAdapter should be empty after retaining from an empty ValueCollection", this.map.isEmpty());
    }   

    @Test(expected = ClassCastException.class)
    public void testRetainAllOnPopulatedValueCollectionWithAnEntrySet() {
        this.map.values().retainAll(this.map.entrySet());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllOnPopulatedValueCollectionWithNull() {
        this.map.values().retainAll(null);
    }

    @Test
    public void testRetainAllOnPopulatedValueCollectionWithAnotherValueCollectionContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertTrue("ValueCollection should change when retaining from another ValueCollection with null values",
                   this.map.values().retainAll(anotherMap.values()));
        assertTrue("MapAdapter should be empty after retaining from another ValueCollection with null values", this.map.isEmpty());
        assertTrue("KeySet should be empty after retaining from another KeySet with null keys", this.map.keySet().isEmpty());
    }

    @Test
    public void testSizeOnPopulatedValueCollection() {
        assertEquals("Size of ValueCollection should match the size of the MapAdapter", MAP_INITIAL_CAPACITY, this.map.values().size());
    }

    @Test
    public void testToArrayOnPopulatedValueCollection() {
        Object[] array = this.map.values().toArray();
        assertEquals("Array length should match the size of the ValueCollection", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.values().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertEquals("Array element at index " + index + " should match the value", value, array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedValueCollectionWithArrayParameterOfRightDimension() {
        Object[] array = new Object[100];
        array = this.map.values().toArray(array);
        assertEquals("Array length should match the size of the ValueCollection", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.values().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertEquals("Array element at index " + index + " should match the value", value, array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedValueCollectionWithUnderdimensionedArray() {
        Object[] array = new Object[50];
        array = this.map.values().toArray(array);
        assertEquals("Array length should match the size of the ValueCollection", MAP_INITIAL_CAPACITY, array.length);
        HIterator iterator = this.map.values().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertEquals("Array element at index " + index + " should match the value", value, array[index]);
            index++;
        }
    }

    @Test
    public void testToArrayOnPopulatedValueCollectionWithOverdimensionedArray() {
        Object[] array = new Object[150];
        array = this.map.values().toArray(array);
        assertEquals("Array length should be the same", 150, array.length);
        HIterator iterator = this.map.values().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertEquals("Array element at index " + index + " should match the value", value, array[index]);
            index++;
        }
        assertNull("Array element at index " + index + " should be null", array[index]);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayOnPopulatedValueCollectionWithNull() {
        this.map.values().toArray(null);
    }

    @Test
    public void testToStringOnPopulatedValueCollection() {
        String expected = "[";
        HIterator iterator = this.map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            expected += value.toString() + (iterator.hasNext() ? ", " : "");
        }
        expected += "]";
        assertEquals("ToString on a populated ValueCollection should match the expected format", expected, this.map.values().toString());
    }

    @Test
    public void testPopulatedValueCollectionIsBackedByMapAdapter() {
        HCollection values = this.map.values();
        assertEquals("ValueCollection should have the same size as MapAdapter", this.map.size(), values.size());
        this.map.put("newKey", "newValue");
        assertEquals("ValueCollection should reflect the changes in the MapAdapter", this.map.size(), values.size());
        Object valueToBeRemoved = values.iterator().next();
        values.remove(valueToBeRemoved);
        assertEquals("MapAdapter should have decreased size by 1 after removing a key from KeySet",
                     MAP_INITIAL_CAPACITY, this.map.size());
        assertFalse("MapAdapter should not contain the removed key", this.map.containsKey(valueToBeRemoved));
        values.clear();
        assertTrue("ValueCollection should be empty after clearing", values.isEmpty());
        assertTrue("MapAdapter should be empty after clearing ValueCollection", this.map.isEmpty());
    }

    @Test
    public void testEqualsOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        HCollection anotherValues = new MapAdapter(this.map).values();
        assertTrue("ValueCollection should be equal to another ValueCollection with the same entries", values.equals(anotherValues));
    }

    @Test
    public void testEqualsOnPopulatedValueCollectionWithDifferentValueCollection() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.remove("testKey0");
        assertFalse("ValueCollection should not be equal to a different ValueCollection", this.map.values().equals(anotherMap.values()));
    }

    @Test
    public void testEqualsOnPopulatedValueCollectionWithNonHCollection() {
        assertFalse("ValueCollection should not be equal to a non-HCollection object", this.map.values().equals(this.map.keySet()));
    }
    
    @Test
    public void testEqualsOnPopulatedValueCollectionWithNull() {
        assertFalse("ValueCollection should not be equal to null", this.map.values().equals(null));
    }

    @Test
    public void testEqualsOnPopulatedValueCollectionWithItself() {
        assertTrue("A populated ValueCollection should be equal to itself", this.map.values().equals(this.map.values()));
    }

    @Test
    public void testEqualsOnPopulatedValueCollectionIsSymmetric() {
        HCollection values = this.map.values();
        HCollection anotherValues = new MapAdapter(this.map).values();
        assertTrue("ValueCollection should be equal to another ValueCollection with the same entries and vice-versa",
            values.equals(anotherValues) && anotherValues.equals(values));
    }

    @Test
    public void testEqualsOnPopulatedValueCollectionIsTransitive() {
        HCollection values = this.map.values();
        HCollection anotherValues = new MapAdapter(this.map).values();
        HCollection anotherAnotherValues = new MapAdapter(this.map).values();
        assertTrue("If ValueCollection A equals ValueCollection B and MapAdapter B equals ValueCollection C, then ValueCollection A should equal MapAdapter C",
            values.equals(anotherValues) && anotherValues.equals(anotherAnotherValues) && values.equals(anotherAnotherValues));
    }

    @Test
    public void testContainsOnPopulatedValueCollection() {
        Object value = this.map.values().iterator().next();
        assertTrue("ValueCollection should contain the value", this.map.values().contains(value));
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "differentValue");
        assertFalse("ValueCollection should not contain a value that is not present",
                    this.map.values().contains(anotherMap.values().iterator().next()));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedValueCollectionWithNull() {
        this.map.values().contains(null);
    }

    @Test
    public void testRemoveOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        Object valueToBeRemoved = values.iterator().next();
        values.remove(valueToBeRemoved);
        assertEquals("ValueCollection should have decreased size by 1 after removing a value from ValueCollection",
                     MAP_INITIAL_CAPACITY - 1, values.size());
        assertFalse("ValueCollection should not contain the removed value", values.contains(valueToBeRemoved));
        assertFalse("MapAdapter should not contain the removed value", this.map.containsValue(valueToBeRemoved));
        while(values.iterator().hasNext()) {
            valueToBeRemoved = values.iterator().next();
            values.remove(valueToBeRemoved);
            assertFalse("ValueCollection should not contain the removed value", values.contains(valueToBeRemoved));
        }
        assertTrue("ValueCollection should be empty after removing all values", values.isEmpty());
        assertTrue("MapAdapter should be empty after removing all values", this.map.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedValueCollectionWithNull() {
        this.map.values().remove(null);
    }
}
