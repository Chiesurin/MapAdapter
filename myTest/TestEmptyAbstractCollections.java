//Diego Chiesurin - 2111553

package myTest;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import myAdapter.*;
import myExceptions.UnsupportedOperationException;

public class TestEmptyAbstractCollections {

    private HMap map;

    public TestEmptyAbstractCollections() {
        // intentionally left blank
    }

    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    @After
    public void tearDown() {
        map = null;
    }

    /*--------------------------------------------------------------*/
    /*----------------------- EntrySet TESTS -----------------------*/
    /*--------------------------------------------------------------*/

    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnEmptyEntrySet() {
        this.map.entrySet().add("anything");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnEmptyEntrySet() {
        this.map.entrySet().addAll(this.map.entrySet());
    }

    @Test
    public void testClearOnEmptyEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.clear();
        assertTrue("EntrySet should be empty after clear", entrySet.isEmpty());
        assertEquals("Size of EntrySet should be 0 after clear", 0, entrySet.size());
    }

    @Test
    public void testContainsAllOnEmptyEntrySet() {
        HSet anotherEntrySet = new MapAdapter().entrySet();
        assertTrue("An empty EntrySet should contain all elements of another empty EntrySet", this.map.entrySet().containsAll(anotherEntrySet));
        assertTrue("An empty EntrySet should contain all elements of itself", this.map.entrySet().containsAll(this.map.entrySet()));
    }

    @Test(expected = ClassCastException.class)
    public void testContainsAllOnEmptyEntrySetWithANonEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        this.map.entrySet().containsAll(anotherMap.keySet());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyEntrySetWithNull() {
        this.map.entrySet().containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyEntrySetWithAnotherEntrySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    @Test
    public void hashCodeOnEmptyEntrySet() {
        assertEquals("Hash code of an empty EntrySet should be 0", 0, this.map.entrySet().hashCode());
    }

    @Test
    public void testIsEmptyOnEmptyEntrySet() {
        assertTrue("EntrySet should be empty", this.map.entrySet().isEmpty());
    }

    @Test
    public void testRemoveAllOnEmptyEntrySet() {
        HSet anotherEntrySet = new MapAdapter().entrySet();
        assertFalse("RemoveAll on an empty EntrySet should return false", this.map.entrySet().removeAll(anotherEntrySet));
        assertTrue("EntrySet should still be empty after removeAll", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll", 0, this.map.entrySet().size());
    }

    @Test
    public void testRemoveAllOnEmptyEntrySetWithAnEmptyNonEntrySet() {
        this.map.entrySet().removeAll(this.map.keySet());
        assertTrue("EntrySet should still be empty after removeAll with an empty non-EntrySet", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll with an empty non-EntrySet", 0, this.map.entrySet().size());
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveAllOnEmptyEntrySetWithANonEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        this.map.entrySet().removeAll(anotherMap.keySet());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyEntrySetWithNull() {
        this.map.entrySet().removeAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyEntrySetWithEntrySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().removeAll(anotherMap.entrySet());
    }

    @Test
    public void testRetainAllOnEmptyEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty EntrySet should return false", this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("EntrySet should still be empty after retainAll", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after retainAll", 0, this.map.entrySet().size());
    }

    @Test
    public void testRetainAllOnEmptyEntrySetWithANonEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty EntrySet with a non-EntrySet should return false", this.map.entrySet().retainAll(anotherMap.keySet()));
        assertTrue("EntrySet should still be empty after retainAll with a non-EntrySet", this.map.entrySet().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllOnEmptyEntrySetWithNull() {
        this.map.entrySet().retainAll(null);
    }

    @Test
    public void testSizeOnEmptyEntrySet() {
        assertEquals("Size of an empty EntrySet should be 0", 0, this.map.entrySet().size());
    }

    @Test
    public void testToArrayOnEmptyEntrySet() {
        Object[] array = this.map.entrySet().toArray();
        assertNotNull("ToArray on an empty EntrySet should not return null", array);
        assertEquals("ToArray on an empty EntrySet should return an empty array", 0, array.length);
    }

    @Test
    public void testToArrayOnEmptyEntrySetWithGreaterArray() {
        Object[] array = this.map.entrySet().toArray(new Object[1]);
        assertNotNull("ToArray on an empty EntrySet with a greater array should not return null", array);
        assertEquals("ToArray on an empty EntrySet with a greater array should return an array with its original size", 1, array.length);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayOnEmptyEntrySetWithNull() {
        this.map.entrySet().toArray(null);
    }

    @Test
    public void testToStringOnEmptyEntrySet() {
        assertEquals("ToString on an empty EntrySet should return \"[]\"", "[]",this.map.entrySet().toString());
    }

    @Test
    public void testEmptyEntrySetIsBackedByMapAdapter() {
        HSet entrySet = this.map.entrySet();
        assertTrue("EntrySet should be empty if MapAdapter is empty", entrySet.isEmpty());
        map.put("testKey", "testValue");
        assertFalse("MapAdapter should not be empty after adding an entry", map.isEmpty());
        assertFalse("EntrySet should not be empty after adding an entry to MapAdapter", entrySet.isEmpty());
        assertEquals("EntrySet size should match MapAdapter size after adding an entry", 1, entrySet.size());
        assertEquals("EntrySet toString should be \"[testKey=testValue]\"", "[testKey=testValue]", entrySet.toString());
        entrySet.clear();
        assertTrue("EntrySet should be empty after clearing", entrySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing EntrySet", map.isEmpty());
    }

    @Test
    public void testEqualsOnEmptyEntrySet () {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter().entrySet();
        assertTrue("Two empty EntrySets should be equal", entrySet.equals(anotherEntrySet));
    }

    @Test
    public void testEqualsOnEmptyEntrySetWithNonEmptyEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty EntrySet should not be equal to a non-empty EntrySet", this.map.entrySet().equals(anotherMap.entrySet()));
    }

    @Test
    public void testEqualsOnEmptyEntrySetWithNonHSet() {
        MapAdapter anotherMap = new MapAdapter();
        assertFalse("An empty EntrySet should not be equal to a non-HSet", this.map.entrySet().equals(anotherMap.values()));
    }

     @Test
    public void testEqualsOnEntrySetWithNull() {
        assertFalse("Empty EntrySet should not be equal to null", this.map.entrySet().equals(null));
    }

    @Test
    public void testEqualsOnEmptyEntrySetWithItself() {
        assertTrue("Empty EntrySet should be equal to itself", this.map.entrySet().equals(this.map.entrySet()));
    }

    @Test
    public void testEqualsOnEmptyEntrySetIsSymmetric() {
        HSet anotherEmptyEntrySet = new MapAdapter().entrySet();
        assertTrue("Equals should be symmetric", anotherEmptyEntrySet.equals(this.map.entrySet()) && this.map.entrySet().equals(anotherEmptyEntrySet));
    }

    @Test
    public void testContainsOnEmptyEntrySet() {
        HMap anotherMapAdapter = new MapAdapter();
        anotherMapAdapter.put("testKey", "testValue");
        HSet entrySet = anotherMapAdapter.entrySet();
        HIterator iterator = entrySet.iterator();
        assertFalse("Empty EntrySet should not contain any keys", this.map.entrySet().contains(iterator.next()));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyEntrySetWithNull() {
        this.map.entrySet().contains(null);
    }

    @Test(expected = ClassCastException.class)
    public void testContainsOnEmptyEntrySetWithNonEntryObject() {
        this.map.entrySet().contains("not an entry");
    }

    @Test
    public void testRemoveOnEmptyEntrySet() {
        HMap anotherMapAdapter = new MapAdapter();
        anotherMapAdapter.put("testKey", "testValue");
        HSet entrySet = anotherMapAdapter.entrySet();
        HIterator iterator = entrySet.iterator();
        assertFalse("Remove on an empty EntrySet should return false", this.map.entrySet().remove(iterator.next()));
        assertTrue("EntrySet should still be empty after remove", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after remove", 0, this.map.entrySet().size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyEntrySetWithNull() {
        this.map.entrySet().remove(null);
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveOnEmptyEntrySetWithNonEntryObject() {
        this.map.entrySet().remove("not an entry");
    }

    @Test
    public void testIteratorOnEmptyEntrySet() {
        HIterator iterator = this.map.entrySet().iterator();
        assertNotNull("Iterator on an empty EntrySet should not be null", iterator);
    }

    /*--------------------------------------------------------------*/
    /*------------------------ KeySet TESTS ------------------------*/
    /*--------------------------------------------------------------*/

    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnEmptyKeySet() {
        this.map.keySet().add("anything");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnEmptyKeySet() {
        this.map.keySet().addAll(this.map.keySet());
    }

    @Test
    public void testClearOnEmptyKeySet() {
        HSet keySet = this.map.keySet();
        keySet.clear();
        assertTrue("KeySet should be empty after clear", keySet.isEmpty());
        assertEquals("Size of KeySet should be 0 after clear", 0, keySet.size());
    }

    @Test
    public void testContainsAllOnEmptyKeySet() {
        HSet anotherKeySet = new MapAdapter().keySet();
        assertTrue("An empty KeySet should contain all elements of another empty KeySet", this.map.keySet().containsAll(anotherKeySet));
        assertTrue("An empty KeySet should contain all elements of itself", this.map.keySet().containsAll(this.map.keySet()));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyKeySetWithNull() {
        this.map.keySet().containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyKeySetWithAnotherKeySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        HSet anotherKeySet = anotherMap.keySet();
        this.map.keySet().containsAll(anotherKeySet);
    }

    @Test
    public void testHashCodeOnEmptyKeySet() {
        assertEquals("Hash code of an empty KeySet should be 0", 0, this.map.keySet().hashCode());
    }

    @Test
    public void testIsEmptyOnEmptyKeySet() {
        assertTrue("KeySet should be empty", this.map.keySet().isEmpty());
    }

    @Test
    public void testRemoveAllOnEmptyKeySet() {
        HSet anotherKeySet = new MapAdapter().keySet();
        assertFalse("RemoveAll on an empty KeySet should return false", this.map.keySet().removeAll(anotherKeySet));
        assertTrue("KeySet should still be empty after removeAll", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after removeAll", 0, this.map.keySet().size());
    }

    @Test
    public void testRemoveAllOnEmptyKeySetWithAnEmptyNonKeySet() {
        this.map.keySet().removeAll(this.map.entrySet());
        assertTrue("KeySet should still be empty after removeAll with an empty non-KeySet", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after removeAll with an empty non-KeySet", 0, this.map.keySet().size());
    }

    @Test
    public void testRemoveAllOnEmptyKeySetWithANonEmptyKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RemoveAll on an empty KeySet with a non-empty KeySet should return false", this.map.keySet().removeAll(anotherMap.keySet()));
        assertTrue("KeySet should still be empty after removeAll with a non-empty KeySet", this.map.keySet().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyKeySetWithNull() {
        this.map.keySet().removeAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyKeySetWithKeySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.keySet().removeAll(anotherMap.keySet());
    }

    @Test
    public void testRetainAllOnEmptyKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty KeySet should return false", this.map.keySet().retainAll(anotherMap.keySet()));
        assertTrue("KeySet should still be empty after retainAll", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after retainAll", 0, this.map.keySet().size());
    }

    @Test 
    public void testRetainAllOnEmptyKeySetWithANonKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty KeySet with a non-KeySet should return false", this.map.keySet().retainAll(anotherMap.entrySet()));
        assertTrue("KeySet should still be empty after retainAll with a non-KeySet", this.map.keySet().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllOnEmptyKeySetWithNull() {
        this.map.keySet().retainAll(null);
    }

    @Test
    public void testSizeOnEmptyKeySet() {
        assertEquals("Size of an empty KeySet should be 0", 0, this.map.keySet().size());
    }   

    @Test
    public void testToArrayOnEmptyKeySet() {
        Object[] array = this.map.keySet().toArray();
        assertNotNull("ToArray on an empty KeySet should not return null", array);
        assertEquals("ToArray on an empty KeySet should return an empty array", 0, array.length);
    }

    @Test
    public void testToArrayOnEmptyKeySetWithGreaterArray() {
        Object[] array = this.map.keySet().toArray(new Object[1]);
        assertNotNull("ToArray on an empty KeySet with a greater array should not return null", array);
        assertEquals("ToArray on an empty KeySet with a greater array should return an array with its original size", 1, array.length);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayOnEmptyKeySetWithNull() {
        this.map.keySet().toArray(null);
    }

    @Test
    public void testToStringOnEmptyKeySet() {
        assertEquals("ToString on an empty KeySet should return \"[]\"", "[]",this.map.keySet().toString());
    }

    @Test
    public void testEmptyKeySetIsBackedByMapAdapter() {
        HSet keySet = this.map.keySet();
        assertTrue("KeySet should be empty if MapAdapter is empty", keySet.isEmpty());
        map.put("testKey", "testValue");
        assertFalse("MapAdapter should not be empty after adding an entry", map.isEmpty());
        assertFalse("KeySet should not be empty after adding an entry to MapAdapter", keySet.isEmpty());
        assertEquals("KeySet size should match MapAdapter size after adding an entry", 1, keySet.size());
        assertEquals("KeySet toString should be \"[testKey]\"", "[testKey]", keySet.toString());
        keySet.clear();
        assertTrue("KeySet should be empty after clearing", keySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing KeySet", map.isEmpty());
    }

    @Test
    public void testEqualsOnEmptyKeySet() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter().keySet();
        assertTrue("Two empty KeySets should be equal", keySet.equals(anotherKeySet));
    }

    @Test
    public void testEqualsOnEmptyKeySetWithNonEmptyKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty KeySet should not be equal to a non-empty KeySet", this.map.keySet().equals(anotherMap.keySet()));
    }

    @Test
    public void testEqualsOnEmptyKeySetWithNonHSet() {
        MapAdapter anotherMap = new MapAdapter();
        assertFalse("An empty KeySet should not be equal to a non-HSet", this.map.keySet().equals(anotherMap.values()));
    }

     @Test
    public void testEqualsOnKeySetWithNull() {
        assertFalse("Empty KeySet should not be equal to null", this.map.keySet().equals(null));
    }

    @Test
    public void testEqualsOnEmptyKeySetWithItself() {
        assertTrue("Empty KeySet should be equal to itself", this.map.keySet().equals(this.map.keySet()));
    }

    @Test
    public void testEqualsOnEmptyKeySetIsSymmetric() {
        HSet anotherEmptyKeySet = new MapAdapter().keySet();
        assertTrue("Equals should be symmetric", anotherEmptyKeySet.equals(this.map.keySet()) && this.map.keySet().equals(anotherEmptyKeySet));
    }

    @Test
    public void testContainsOnEmptyKeySet() {
        assertFalse("Empty KeySet should not contain any keys", this.map.keySet().contains("testKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyKeySetWithNull() {
        this.map.keySet().contains(null);
    }

    @Test
    public void testRemoveOnEmptyKeySet() {
        assertFalse("Remove on an empty KeySet should return false", this.map.keySet().remove("testKey"));
        assertTrue("KeySet should still be empty after remove", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after remove", 0, this.map.keySet().size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyKeySetWithNull() {
        this.map.keySet().remove(null);
    }

    @Test
    public void testIteratorOnEmptyKeySet() {
        HIterator iterator = this.map.keySet().iterator();
        assertNotNull("Iterator on an empty KeySet should not be null", iterator);
    }

    /*--------------------------------------------------------------*/
    /*--------------------- ValuesCollection TESTS -----------------*/
    /*--------------------------------------------------------------*/

    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnEmptyValuesCollection() {
        this.map.values().add("anything");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnEmptyValuesCollection() {
        this.map.values().addAll(this.map.values());
    }

    @Test
    public void testClearOnEmptyValuesCollection() {
        HCollection values = this.map.values();
        values.clear();
        assertTrue("ValuesCollection should be empty after clear", values.isEmpty());
        assertEquals("Size of ValuesCollection should be 0 after clear", 0, values.size());
    }

    @Test
    public void testContainsAllOnEmptyValueCollection() {
        HCollection anotherValueCollection = new MapAdapter().values();
        assertTrue("An empty ValueCollection should contain all elements of another empty ValueCollection", this.map.values().containsAll(anotherValueCollection));
        assertTrue("An empty ValueCollection should contain all elements of itself", this.map.values().containsAll(this.map.values()));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyValueCollectionWithNull() {
        this.map.values().containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyValueCollectionWithAnotherValueCollectionWithNullValues() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        HCollection anotherValueCollection = anotherMap.values();
        this.map.values().containsAll(anotherValueCollection);
    }

    @Test
    public void testHashCodeOnEmptyValueCollection() {
        assertEquals("Hash code of an empty ValueCollection should be 0", 0, this.map.values().hashCode());
    }

    @Test
    public void testIsEmptyOnEmptyValueCollection() {
        assertTrue("ValueCollection should be empty", this.map.values().isEmpty());
    }

    @Test
    public void testRemoveAllOnEmptyValueCollection() {
        HCollection anotherValueCollection = new MapAdapter().values();
        assertFalse("RemoveAll on an empty ValueCollection should return false", this.map.values().removeAll(anotherValueCollection));
        assertTrue("ValueCollection should still be empty after removeAll", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after removeAll", 0, this.map.values().size());
    }

    @Test
    public void testRemoveAllOnEmptyValueCollectionWithAnEmptyNonValueCollection() {
        this.map.values().removeAll(this.map.entrySet());
        assertTrue("ValueCollection should still be empty after removeAll with an empty non-ValueCollection", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after removeAll with an empty non-ValueCollection", 0, this.map.values().size());
    }

    @Test
    public void testRemoveAllOnEmptyValueCollectionWithANonEmptyValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RemoveAll on an empty ValueCollection with a non-empty ValueCollection should return false", this.map.values().removeAll(anotherMap.values()));
        assertTrue("ValueCollection should still be empty after removeAll with a non-empty ValueCollection", this.map.values().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyValueCollectionWithNull() {
        this.map.values().removeAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyValueCollectionWithValueCollectionWithNullValues() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        HCollection anotherValueCollection = anotherMap.values();
        this.map.values().removeAll(anotherValueCollection);
    }

    @Test
    public void testRetainAllOnEmptyValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty ValueCollection should return false", this.map.values().retainAll(anotherMap.values()));
        assertTrue("ValueCollection should still be empty after retainAll", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after retainAll", 0, this.map.values().size());
    }

    @Test 
    public void testRetainAllOnEmptyValueCollectionWithANonValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty ValueCollection with a non-ValueCollection should return false", this.map.values().retainAll(anotherMap.entrySet()));
        assertTrue("ValueCollection should still be empty after retainAll with a non-ValueCollection", this.map.values().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllOnEmptyValueCollectionWithNull() {
        this.map.values().retainAll(null);
    }

    @Test
    public void testSizeOnEmptyValueCollection() {
        assertEquals("Size of an empty ValueCollection should be 0", 0, this.map.values().size());
    }

    @Test
    public void testToArrayOnEmptyValueCollection() {
        Object[] array = this.map.values().toArray();
        assertNotNull("ToArray on an empty ValueCollection should not return null", array);
        assertEquals("ToArray on an empty ValueCollection should return an empty array", 0, array.length);
    }

    @Test
    public void testToArrayOnEmptyValueCollectionWithGreaterArray() {
        Object[] array = this.map.values().toArray(new Object[1]);
        assertNotNull("ToArray on an empty ValueCollection with a greater array should not return null", array);
        assertEquals("ToArray on an empty ValueCollection with a greater array should return an array with its original size", 1, array.length);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayOnEmptyValueCollectionWithNull() {
        this.map.values().toArray(null);
    }

    @Test
    public void testToStringOnEmptyValueCollection() {
        assertEquals("ToString on an empty ValueCollection should return \"[]\"", "[]",this.map.values().toString());
    }

    @Test
    public void testEmptyValueCollectionIsBackedByMapAdapter() {
        HCollection valueCollection = this.map.values();
        assertTrue("ValueCollection should be empty if MapAdapter is empty", valueCollection.isEmpty());
        map.put("testKey", "testValue");
        assertFalse("MapAdapter should not be empty after adding an entry", map.isEmpty());
        assertFalse("ValueCollection should not be empty after adding an entry to MapAdapter", valueCollection.isEmpty());
        assertEquals("ValueCollection size should match MapAdapter size after adding an entry", 1, valueCollection.size());
        assertEquals("ValueCollection toString should be \"[testValue]\"", "[testValue]", valueCollection.toString());
        valueCollection.clear();
        assertTrue("ValueCollection should be empty after clearing", valueCollection.isEmpty());
        assertTrue("MapAdapter should be empty after clearing ValueCollection", map.isEmpty());
    }

    @Test
    public void testEqualsOnEmptyValueCollection() {
        HCollection anotherValueCollection = new MapAdapter().values();
        assertTrue("Two empty ValueCollections should be equal", this.map.values().equals(anotherValueCollection));
    }

    @Test
    public void testEqualsOnEmptyValueCollectionWithNonEmptyValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty ValueCollection should not be equal to a non-empty ValueCollection", this.map.values().equals(anotherMap.values()));
    }

    @Test
    public void testEqualsOnEmptyValueCollectionWithHSet() {
        MapAdapter anotherMap = new MapAdapter();
        assertFalse("An empty ValueCollection should not be equal to an HSet", this.map.values().equals(anotherMap.keySet()));
    }

     @Test
    public void testEqualsOnValueCollectionWithNull() {
        assertFalse("Empty ValueCollection should not be equal to null", this.map.values().equals(null));
    }

    @Test
    public void testEqualsOnEmptyValueCollectionWithItself() {
        assertTrue("Empty ValueCollection should be equal to itself", this.map.values().equals(this.map.values()));
    }

    @Test
    public void testEqualsOnEmptyValueCollectiontIsSymmetric() {
        HCollection anotherEmptyValueCollection = new MapAdapter().values();
        assertTrue("Equals should be symmetric", anotherEmptyValueCollection.equals(this.map.values()) && this.map.values().equals(anotherEmptyValueCollection));
    }

    @Test
    public void testContainsOnEmptyValueCollection() {
        assertFalse("Empty ValueCollection should not contain any values", this.map.values().contains("testvalue"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyValueCollectionWithNull() {
        this.map.values().contains(null);
    }

    @Test
    public void testRemoveOnEmptyValueCollection() {
        assertFalse("Remove on an empty ValueCollection should return false", this.map.values().remove("testValue"));
        assertTrue("ValueCollection should still be empty after remove", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after remove", 0, this.map.values().size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyValueCollectionWithNull() {
        this.map.values().remove(null);
    }

    @Test
    public void testIteratorOnEmptyValueCollection() {
        HIterator iterator = this.map.values().iterator();
        assertNotNull("Iterator on an empty ValueCollection should not be null", iterator);
    }

    /*--------------------------------------------------------------*/ 
    /*--------------------------------------------------------------*/
    /*--------------------------------------------------------------*/

}