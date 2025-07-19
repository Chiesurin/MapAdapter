//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;


/**
 * This test suite verifies the correct behavior of the {@link HMap.HEntry} implementation as returned by the {@link MapAdapter}.
 * It covers all methods and scenarios relevant to map entries, including equality, hash code, value updates, and string representation.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Entry equality and contract (equals, hashCode, symmetry, transitivity)</li>
 *   <li>Entry mutation (setValue)</li>
 *   <li>Entry accessors (getKey, getValue, toString)</li>
 *   <li>Exception handling (NullPointerException for setValue)</li>
 *   <li>Edge cases (null keys/values, type differences, removed entries)</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that {@code HMap.HEntry} objects behave as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that entries are equal if and only if their keys and values are equal, and that the equals contract (reflexive, symmetric, transitive) is satisfied.</li>
 *   <li>Check that {@code getKey}, {@code getValue}, and {@code toString} return the correct results for entries.</li>
 *   <li>Ensure that {@code setValue} updates the value and reflects the change in the map, and throws exceptions for invalid input.</li>
 *   <li>Test edge cases such as null keys/values, different types, and behavior after removal from the map.</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of the entry contract, ensuring correctness, robustness, and compliance with the map entry specification.
 */
public class TestEntry {
    private HMap.HEntry entry;

    public TestEntry() {
        //intentionally left empty
    }

    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put("testKey", "testValue");
        this.entry = (HMap.HEntry) map.entrySet().iterator().next();
    }

    @After
    public void tearDown() {
        this.entry = null;
    }

    @Test
    public void testEqualsOnEntry() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertTrue("An Entry should be equal to another Entry with the same key and value", 
                   entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));

        anotherMap = new MapAdapter();
        anotherMap.put("testKey", "differentValue");
        assertFalse("An Entry should not be equal to another Entry with the same key but different value", 
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));

        anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "testValue");
        assertFalse("An Entry should not be equal to another Entry with a different key",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithNull() {
        assertFalse("An Entry should not be equal to null", entry.equals(null));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("An Entry should not be equal to another Entry with a null key",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertFalse("An Entry should not be equal to another Entry with a null value",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullKeyAndValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        assertFalse("An Entry should not be equal to another Entry with both key and value as null",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithItself() {
        assertTrue("An Entry should be equal to itself", entry.equals(entry));
    }

    @Test
    public void testEqualsOnEntryIsSymmetric() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        HMap.HEntry anotherEntry = (HMap.HEntry) anotherMap.entrySet().iterator().next();
        assertTrue("Equals should be symmetric", entry.equals(anotherEntry) && anotherEntry.equals(entry));
    }

    @Test
    public void testEqualsOnEntryIsTransitive() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        HMap yetAnotherMap = new MapAdapter();
        yetAnotherMap.put("testKey", "testValue");
        
        HMap.HEntry anotherEntry = (HMap.HEntry) anotherMap.entrySet().iterator().next();
        HMap.HEntry anotherAnotherEntry = (HMap.HEntry) yetAnotherMap.entrySet().iterator().next();

        assertTrue("If Entry A equals Entry B and Entry B equals Entry C, then Entry A should equal Entry C",
                   entry.equals(anotherEntry) && anotherEntry.equals(anotherAnotherEntry) && entry.equals(anotherAnotherEntry));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithDifferentTypeOfKey() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put(1, "testValue");
        assertFalse("An Entry should not be equal to another Entry with a different type of key",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithDifferentTypeOfValue() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", 1);
        assertFalse("An Entry should not be equal to another Entry with a different type of value",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testGetKeyOnEntry() {
        assertEquals("Get key should return the correct key", "testKey", entry.getKey());
    }

    @Test
    public void testGetValueOnEntry() {
        assertEquals("Get value should return the correct value", "testValue", entry.getValue());
    }

    @Test
    public void testHashCodeOnEntry() {
        int expectedHashCode = (entry.getKey().hashCode() ^ entry.getValue().hashCode());
        assertEquals("Hash code should be computed as key's hash code XOR value's hash code", expectedHashCode, entry.hashCode());
    }

    @Test
    public void testSetValueOnEntry() {
        HMap map = new MapAdapter();
        map.put("testKey", "testValue");
        HMap.HEntry anotherEntry = (HMap.HEntry) map.entrySet().iterator().next();
        anotherEntry.setValue("newValue");
        assertEquals("Set value should update the value in the entry", "newValue", anotherEntry.getValue());
        assertEquals("Map should reflect the updated value", "newValue", map.get("testKey"));
        assertTrue("MapAdapter should contain the updated value", map.containsValue("newValue"));
    }

    @Test
    public void testSetValueOnEntryCalledAfterRemovingThisEntry() {
        HMap map = new MapAdapter();
        map.put("testKey", "testValue");
        HMap.HEntry anotherEntry = (HMap.HEntry) map.entrySet().iterator().next();
        map.remove(anotherEntry.getKey());
        anotherEntry.setValue("newValue");
        assertTrue("MapAdapter will contain this entry after setValue is called, even if the entry was removed", 
                   map.containsKey(anotherEntry.getKey()));
        assertEquals("MapAdapter should still contain the key after setValue is called", "testKey", anotherEntry.getKey());
    }

    @Test(expected = NullPointerException.class)
    public void testSetValueOnEntryWithNullValue() {
        entry.setValue(null);
    }

    @Test
    public void testToStringOnEntry() {
        String expectedString = "testKey=testValue";
        assertEquals("ToString should return the key-value pair in the format 'key=value'", expectedString, entry.toString());
    }   
}