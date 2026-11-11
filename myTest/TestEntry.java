// Diego Chiesurin

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;


/**
 * This test suite verifies the correct behavior of the {@link myAdapter.HMap.HEntry} implementation in the {@link MapAdapter}.
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
 * The design of this test suite is motivated by the need to ensure that {@link myAdapter.HMap.HEntry} objects behave as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that entries are equal if and only if their keys and values are equal, and that the equals contract (reflexive, symmetric, transitive) is satisfied.</li>
 *   <li>Check that {@link myAdapter.MapAdapter.Entry#getKey()}, {@link myAdapter.MapAdapter.Entry#getValue()}, and {@link myAdapter.MapAdapter.Entry#toString()} return the correct results for entries.</li>
 *   <li>Ensure that {@link myAdapter.MapAdapter.Entry#setValue(Object)} updates the value and reflects the change in the map, and throws exceptions for invalid input.</li>
 *   <li>Test edge cases such as null keys/values, different types, and behavior after removal from the map.</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of the {@link myAdapter.MapAdapter.Entry}, ensuring correctness, robustness and compliance with the {@link myAdapter.HMap.HEntry} specification.
 */
public class TestEntry {
    /**
     * The entry to be tested, initialized in the setUp method.
     * It represents a single entry in the map, allowing for testing of entry-specific methods and behaviors.
     */
    private HMap.HEntry entry;

    /**
     * Default constructor for the test class.
     */
    public TestEntry() {
        //intentionally left empty
    }

    /**
     * Sets up the test environment by initializing the entry with a key-value pair in a new MapAdapter.
     * This method is called before each test to ensure a fresh state for testing entry behaviors.
     */
    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put("testKey", "testValue");
        this.entry = (HMap.HEntry) map.entrySet().iterator().next();
    }

    /**
     * Cleans up the test environment by setting the entry to null.
     * This method is called after each test to ensure no side effects between tests.
     */
    @After
    public void tearDown() {
        this.entry = null;
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an Entry.
     *   The rationale is to confirm that the method returns true when compared with another Entry with the same key and value.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a MapAdapter with an entry that has the same key and value of the entry being tested.</li>
     *   <li>Check that the equals method returns true when comparing the entry with the entry from the MapAdapter.</li>
     *   <li>Create another MapAdapter with an entry that has the same key but a different value, and check that equals returns false.</li>
     *   <li>Create a third MapAdapter with an entry that has a different key but the same value, and check that equals returns false.</li>
     *   <li>Check that equals returns false when comparing with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should be equal to another Entry with the same key and value.</li>
     *   <li>The Entry should not be equal to another Entry with the same key but a different value.</li>
     *   <li>The Entry should not be equal to another Entry with a different key but the same value.</li>
     * </ul>
     */
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

    /**
     * Summary:
     *   Verifies that the equals method on an Entry behaves correctly with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with null.
     *   The rationale is to confirm that the method returns false when compared with null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the equals method returns false when comparing the entry with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should not be equal to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryWithNull() {
        assertFalse("An Entry should not be equal to null", entry.equals(null));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry behaves correctly with another Entry with a null key.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with another Entry that has a null key.
     *   The rationale is to confirm that the method returns false when compared with an entry that has a null key.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Check that the equals method returns false when comparing the entry with the entry from the DummyTestMap.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should not be equal to another Entry with a null key.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("An Entry should not be equal to another Entry with a null key",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry behaves correctly with another Entry with a null value.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with another Entry that has a null value.
     *   The rationale is to confirm that the method returns false when compared with an entry that has a null value.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Check that the equals method returns false when comparing the entry with the entry from the DummyTestMap.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should not be equal to another Entry with a null value.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertFalse("An Entry should not be equal to another Entry with a null value",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry behaves correctly with another Entry with both key and value as null.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with another Entry that has both key and value as null.
     *   The rationale is to confirm that the method returns false when compared with an entry that has both key and value as null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has both key and value as null.</li>
     *   <li>Check that the equals method returns false when comparing the entry with the entry from the DummyTestMap.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should not be equal to another Entry with both key and value as null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullKeyAndValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        assertFalse("An Entry should not be equal to another Entry with both key and value as null",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     *   Verifies that equals on an Entry safely returns false when compared to a foreign HEntry
     *   whose key or value is null, even if the entry's own key/value equals(Object) throws NPE on null.
     * <p>
     * Test Case Design:
     *   Uses a custom key whose {@code equals(Object)} throws {@code NullPointerException} on {@code null}
     *   to verify that {@code Entry.equals} checks for null fields on the other entry before delegating.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Insert a strict-non-null key into a MapAdapter and retrieve its HEntry.</li>
     *   <li>Compare it with a DummyTestMap entry having a null key and with one having a null value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   A MapAdapter entry holds a key whose {@code equals(null)} throws {@code NullPointerException}.
     * <p>
     * Post-Condition:
     *   Both comparisons return false without throwing NullPointerException.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>{@code strictEntry.equals(nullKeyEntry)} returns false.</li>
     *   <li>{@code strictEntry.equals(nullValueEntry)} returns false.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryWithNullKeyOrValueDoesNotCallEqualsWithNull() {
        Object strictObj = new Object() {
            public int hashCode() {
                return System.identityHashCode(this);
            }

            public boolean equals(Object o) {
                if (o == null) {
                    throw new NullPointerException("equals(null) not allowed");
                }
                return this == o;
            }
        };
        HMap strictMap = new MapAdapter();
        strictMap.put(strictObj, strictObj);
        HMap.HEntry strictEntry = (HMap.HEntry) strictMap.entrySet().iterator().next();

        HMap nullKeyMap = new DummyTestMap();
        nullKeyMap.put(null, strictObj);
        assertFalse("Entry should return false for foreign entry with null key",
                    strictEntry.equals(nullKeyMap.entrySet().iterator().next()));

        HMap nullValMap = new DummyTestMap();
        nullValMap.put(strictObj, null);
        assertFalse("Entry should return false for foreign entry with null value",
                    strictEntry.equals(nullValMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry behaves correctly with itself.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with itself.
     *   The rationale is to confirm that the method returns true when compared with itself.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the equals method returns true when comparing the entry with itself.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should be equal to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryWithItself() {
        assertTrue("An Entry should be equal to itself", entry.equals(entry));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry is symmetric.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves symmetrically when comparing two entries.
     *   The rationale is to confirm that if entry A equals entry B, then entry B should equal entry A.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with an entry that has the same key and value as the entry being tested.</li>
     *   <li>Check that the equals method is symmetric by comparing both entries.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should be symmetric.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntryIsSymmetric() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        HMap.HEntry anotherEntry = (HMap.HEntry) anotherMap.entrySet().iterator().next();
        assertTrue("Equals should be symmetric", entry.equals(anotherEntry) && anotherEntry.equals(entry));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry is transitive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves transitively when comparing three entries.
     *   The rationale is to confirm that if entry A equals entry B and entry B equals entry C, then entry A should equal entry C.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create two additional MapAdapters with entries that have the same key and value as the entry being tested.</li>
     *   <li>Check that the equals method is transitive by comparing all three entries.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should be transitive.</li>
     * </ul>
     */
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

    /**
     * Summary:
     *   Verifies that the equals method on an Entry with another Entry with a different type of key behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with another Entry that has a different type of key.
     *   The rationale is to confirm that the method returns false when compared with an entry that has a different type of key.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a MapAdapter with an entry that has a different type of key.</li>
     *   <li>Check that the equals method returns false when comparing the entry with the entry from the MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should not be equal to another Entry with a different type of key.</li>
     * </ul>    
     */
    @Test
    public void testEqualsOnEntryWithAnotherEntryWithDifferentTypeOfKey() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put(1, "testValue");
        assertFalse("An Entry should not be equal to another Entry with a different type of key",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an Entry with another Entry with a different type of value behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when compared with another Entry that has a different type of value.
     *   The rationale is to confirm that the method returns false when compared with an entry that has a different type of value.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a MapAdapter with an entry that has a different type of value.</li>
     *   <li>Check that the equals method returns false when comparing the entry with the entry from the MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The Entry should not be equal to another Entry with a different type of value.</li>
     * </ul>    
     */
    @Test
    public void testEqualsOnEntryWithAnotherEntryWithDifferentTypeOfValue() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", 1);
        assertFalse("An Entry should not be equal to another Entry with a different type of value",
                    entry.equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     *   Verifies that the getKey method on an Entry behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the getKey method behaves correctly on an Entry.
     *   The rationale is to confirm that the method returns the correct key when called on the entry.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the getKey method returns the correct key when called on the entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The getKey method should return the correct key.</li>
     * </ul>
     */
    @Test
    public void testGetKeyOnEntry() {
        assertEquals("Get key should return the correct key", "testKey", entry.getKey());
    }

    /**
     * Summary:
     *   Verifies that the getValue method on an Entry behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the getValue method behaves correctly on an Entry.
     *   The rationale is to confirm that the method returns the correct value when called on the entry.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the getValue method returns the correct value when called on the entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The getValue method should return the correct value.</li>
     * </ul>
     */
    @Test
    public void testGetValueOnEntry() {
        assertEquals("Get value should return the correct value", "testValue", entry.getValue());
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on an Entry behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method behaves correctly on an Entry.
     *   The rationale is to confirm that the method returns the correct hash code based on the key and value.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Calculate the expected hash code based on the key and value.</li>
     *   <li>Check that the hashCode method returns the correct hash code when called on the entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hashCode method should return the correct hash code based on the key and value.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnEntry() {
        int expectedHashCode = (entry.getKey().hashCode() ^ entry.getValue().hashCode());
        assertEquals("Hash code should be computed as key's hash code XOR value's hash code", expectedHashCode, entry.hashCode());
    }

    /**
     * Summary:
     *   Verifies that the setValue method on an Entry behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the setValue method behaves correctly on an Entry.
     *   The rationale is to confirm that the method updates the value in the entry and reflects the change in the map.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Set a new value using the setValue method.</li>
     *   <li>Check that the value in the entry is updated.</li>
     *   <li>Check that the map reflects the updated value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry's value is updated to "newValue".
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The setValue method should update the value in the entry.</li>
     *   <li>The map should reflect the updated value.</li>
     *   <li>The MapAdapter should contain the updated value.</li>
     * </ul>
     */
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

    /**
     * Summary:
     *   Verifies the behavior of setValue on an entry, retrieved interating on the EntrySet of the MapAdapter, that was removed during iteration.
     * <p>
     * Test Case Design:
     *   This test ensures that the setValue method reinserts an entry that was removed from the map while iterating.
     *   The rationale is to check an undefined behavior.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a MapAdapter and put an entry with a key and value.</li>
     *   <li>Retrieve the entry from the MapAdapter through the iterator of its EntrySet.</li>
     *   <li>Remove the entry from the MapAdapter using its key.</li>
     *   <li>Check that the MapAdapter no longer contains the removed entry.</li>
     *   <li>Call setValue on the retrieved entry to change its value.</li>
     *   <li>Check that the MapAdapter will contain the removed entry again.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value (not used).
     * <p>
     * Post-Condition:
     *   The Entry remain unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *  <li>The MapAdapter should not contain the entry after it has been removed.</li>
     *  <li>After calling setValue on the entry, the MapAdapter should contain the entry again with the new value.</li>
     * </ul>
     */
    @Test
    public void testSetValueOnEntryCalledAfterRemovingThisEntry() {
        HMap map = new MapAdapter();
        map.put("testKey", "testValue");
        HMap.HEntry anotherEntry = (HMap.HEntry) map.entrySet().iterator().next();
        map.remove(anotherEntry.getKey());
        assertFalse("MapAdapter should not contain the entry after it has been removed", map.containsKey(anotherEntry.getKey()));
        anotherEntry.setValue("newValue");
        assertTrue("MapAdapter will contain this entry after setValue is called, even if the entry was removed", 
                   map.containsKey(anotherEntry.getKey()));
        assertEquals("MapAdapter should still contain the key after setValue is called", "testKey", anotherEntry.getKey());
    }

    /**
     * Summary:
     *   Verifies that the setValue method on an Entry throws a NullPointerException when called with a null value.
     * <p>
     * Test Case Design:
     *   This test ensures that the setValue method throws a NullPointerException when called with a null value.
     *   The rationale is to confirm that the method does not allow null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the setValue method with a null value.</li>
     *   <li>Check that a NullPointerException is thrown.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when setValue is called with a null value.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testSetValueOnEntryWithNullValue() {
        entry.setValue(null);
    }

    /**
     * Summary:
     *   Verifies that the toString method on an Entry behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method behaves correctly on an Entry.
     *   The rationale is to confirm that the method returns a string representation of the entry in the format "key=value".
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the toString method returns the correct string representation when called on the entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   The Entry has "testKey" as key and "testValue" as value.
     * <p>
     * Post-Condition:
     *   The Entry remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string in the format "key=value".</li>
     * </ul>
     */
    @Test
    public void testToStringOnEntry() {
        String expectedString = "testKey=testValue";
        assertEquals("ToString should return the key-value pair in the format 'key=value'", expectedString, entry.toString());
    }   
}