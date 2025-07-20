//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import myExceptions.UnsupportedOperationException;
import org.junit.After;


/**
 * This test suite verifies the correct behavior of the collection views ({@link myAdapter.MapAdapter.EntrySet}, {@link myAdapter.MapAdapter.KeySet}, {@link myAdapter.MapAdapter.ValueCollection}) of a populated {@link myAdapter.MapAdapter}.
 * It covers all methods and scenarios relevant to populated collections, including query, mutation, equality, hash code, array conversion, and exception handling.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Populated collection behavior ({@link myAdapter.MapAdapter.EntrySet}, {@link myAdapter.MapAdapter.KeySet}, {@link myAdapter.MapAdapter.ValueCollection})</li>
 *   <li>Mutation and query operations</li>
 *   <li>Exception handling ({@link UnsupportedOperationException}, {@code NullPointerException}, {@link ClassCastException}, {@code ArrayStoreException})</li>
 *   <li>Equality and hash code</li>
 *   <li>Array conversion and string representation</li>
 *   <li>Backing behavior (collections reflect map changes)</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that all {@link myAdapter.HCollection} and {@link myAdapter.HSet} views of a populated {@link myAdapter.MapAdapter} behave as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that all query methods ({@code isEmpty}, {@code size}, {@code contains}, etc.) return results consistent with a populated {@link myAdapter.HCollection} or {@link myAdapter.HSet}.</li>
 *   <li>Check that mutator methods ({@code add}, {@code addAll}, {@code clear}, {@code remove}, {@code removeAll}, {@code retainAll}) behave correctly and throw exceptions for invalid input or unsupported operations.</li>
 *   <li>Test the behavior of equality, hash code, array conversion, and string representation for populated {@link myAdapter.HCollection} and {@link myAdapter.HSet}.</li>
 *   <li>Ensure that the collections are backed by the {@link myAdapter.MapAdapter} and reflect changes in the map.</li>
 *   <li>Test exception handling for invalid input (e.g., nulls, wrong types).</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of the populated state for all collection views, ensuring correctness, robustness and compliance with the {@link myAdapter.HCollection} and {@link myAdapter.HSet} contracts.
 */
public class TestPopulatedAbstractCollections {
    /**
     * Initial capacity for the MapAdapter used in tests.
     * This is set to 100 to ensure a sufficiently populated map for testing.
     * <strong>Note:</strong> changing this value may invalidate existing tests.
     */
    private static final int MAP_INITIAL_CAPACITY = 100;
    /**
     * The MapAdapter instance used for testing.
     * It is populated with MAP_INITIAL_CAPACITY entries before each test.
     */
    private HMap map;

    /**
     * Default constructor for the test class.
     */
    public TestPopulatedAbstractCollections() {
        //intentionally left empty
    }

    /**
     * Sets up the test environment by initializing the map and populating it with test entries.
     * MAP_INITIAL_CAPACITY entries are added to the empty map and they are formed this way: "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * This method is called before each test to ensure a consistent starting state.
     */
    @Before
    public void setUp() {
        this.map = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    /**
     * Cleans up the test environment by setting the map to null.
     * This method is called after each test to ensure no side effects between tests.
     */
    @After
    public void tearDown() {
        this.map = null;
    }

    /*--------------------------------------------------------------*/
    /*----------------------- EntrySet TESTS -----------------------*/
    /*--------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the add method on a populated EntrySet throws an UnsupportedOperationException.
     *<p>
     * Test Case Design:
     *   This test ensures that the add method is not supported on a populated EntrySet.
     *   The rationale is to confirm that the EntrySet does not allow addition of new elements.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the add method on the populated EntrySet with anything.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>Calling add on the EntrySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.add("anything");
    }

    /**
     * Summary:
     * Verifies that the addAll method on a populated EntrySet throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the addAll method is not supported on a populated EntrySet.
     *   The rationale is to confirm that the EntrySet does not allow addition of multiple new elements.
     * <p>
     * Test Description:
     * <ul> 
     *    <li>Get the populated EntrySet from the MapAdapter.</li>
     *    <li>Call the addAll method on the populated EntrySet with this EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling addAll on the EntrySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.addAll(this.map.entrySet());
    }

    /**
     * Summary:
     * Verifies that the clear method on a populated EntrySet clears the EntrySet and the underlying MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the clear method works as expected, removing all entries from the EntrySet and the MapAdapter.
     *   The rationale is to confirm that clearing the EntrySet reflects in the underlying map.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the clear method on the populated EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter should be empty after clearing the EntrySet.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should be empty after calling clear.</li>
     *   <li>The MapAdapter should also be empty after clearing the EntrySet.</li>
     * </ul>
     */
    @Test
    public void testClearOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.clear();
        assertTrue("EntrySet should be empty after clear", entrySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing EntrySet", this.map.isEmpty());
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated EntrySet returns true for existing entries and false for non-existing entries.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method works correctly, checking for the presence of entries in the EntrySet.
     *   The rationale is to confirm that the EntrySet reflects the contents of the underlying MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with the same entries as the original map.</li>
     *   <li>Check that both the EntrySets of the two MapAdapters contain the same entries.</li>
     *   <li>Remove half of the entries from the second MapAdapter and check that the EntrySet still contains the remaining entries.</li>
     *   <li>Create an empty MapAdapter and check that the EntrySet contains all entries from it.</li>
     *   <li>Create another MapAdapter with a different entry and check that the EntrySet does not contain it.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should contain all entries from the first MapAdapter.</li>
     *   <li>The EntrySet should still contain the remaining entries after partial removal from the second MapAdapter.</li>
     *   <li>The EntrySet should contain all entries from an empty MapAdapter.</li>
     *   <li>The EntrySet should not contain entries from a completely different MapAdapter.</li>
     * </ul>
     */
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
        anotherPopulatedMap.put("testKeyMAP_INITIAL_CAPACITY", "testValueMAP_INITIAL_CAPACITY");
        assertFalse("EntrySet should not contain all entries from a completely different map", this.map.entrySet().containsAll(anotherPopulatedMap.entrySet()));
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated EntrySet throws a ClassCastException when passed a non-EntrySet collection.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly identifies when it is passed a non-EntrySet collection.
     *   The rationale is to confirm that the EntrySet does not allow operations with incompatible types.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the populated EntrySet with a non-EntrySet collection (e.g., keySet).</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with a non-EntrySet should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testContainsAllOnPopulatedEntrySetWithANonEntrySet() {
        this.map.entrySet().containsAll(this.map.keySet());
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated EntrySet throws a NullPointerException when passed null.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles null input.
     *   The rationale is to confirm that the EntrySet does not allow null arguments.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the populated EntrySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetNull() {
        this.map.entrySet().containsAll(null);
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated EntrySet throws a NullPointerException when passed a collection with null keys.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles collections with null keys.
     *   The rationale is to confirm that the EntrySet does not allow null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the containsAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with a collection containing null keys should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetWithNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated EntrySet throws a NullPointerException when passed a collection with null values.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles collections with null values.
     *   The rationale is to confirm that the EntrySet does not allow null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the containsAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with a collection containing null values should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated EntrySet throws a NullPointerException when passed a collection with null keys and values.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles collections with both null keys and values.
     *   The rationale is to confirm that the EntrySet does not allow null keys or values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has both a null key and a null value.</li>
     *   <li>Call the containsAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with a collection containing null keys and values should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedEntrySetWithNullKeysAndValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    /**
     * Summary:
     * Verifies the behavior of the hashCode method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method correctly computes the hash code
     *   for the EntrySet based on its entries.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Iterate over the entries in the populated EntrySet and compute the expected hash code.</li>
     *   <li>Compare the computed hash code with the hash code returned by the EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hash code computed from the entries should match the hash code returned by the EntrySet.</li>
     * </ul>
     */
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

    /**
     * Summary: Verifies the behavior of the isEmpty method on a populated EntrySet.
     * <p>
     * Test Case Design:
     * This test ensures that the isEmpty method correctly identifies that the EntrySet is not empty when it contains entries.
     * The rationale is to confirm that if the MapAdapter is not empty, the EntrySet is not empty.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the isEmpty method on the populated EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The isEmpty method should return false.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnPopulatedEntrySet() {
        assertFalse("EntrySet should not be empty", this.map.entrySet().isEmpty());
    }

    /**
     * Summary: Verifies the behavior of the removeAll method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly removes specified entries from the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with half of the entries from the original map.</li>
     *   <li>Call the removeAll method on the populated EntrySet with the EntrySet of the second MapAdapter.</li>
     *   <li>Check that the EntrySet has reduced in size and does not contain the removed entries.</li>
     *   <li>Call removeAll again with the same EntrySet and check that EntrySet does not change.</li>
     *   <li>Add the remaining entries back to the second MapAdapter and call removeAll again.</li>
     *   <li>Check that the EntrySet is empty after removing all entries.</li>
     *   <li>Check that the MapAdapter is empty after removing all entries.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The removeAll method should return true if any entries were removed.</li>
     *   <li>The size of the EntrySet should be reduced by the number of removed entries.</li>
     *   <li>The EntrySet should not contain the removed entries after the first removeAll call.</li>
     *   <li>Calling removeAll with the same EntrySet should not change the EntrySet.</li>
     *   <li>After adding the remaining entries back to the second MapAdapter and calling removeAll again, the EntrySet should be empty.</li>
     *   <li>The MapAdapter should also be empty after removing all entries.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the removeAll method on a populated EntrySet with an empty EntrySet does not change the EntrySet or the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly when called with an empty EntrySet.
     *   The rationale is to confirm that removing from an empty EntrySet does not affect the original EntrySet or MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the removeAll method on the populated EntrySet with the EntrySet of the empty MapAdapter.</li>
     *   <li>Check that the EntrySet and MapAdapter's size is unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The removeAll method should return false, indicating no entries were removed.</li>
     *   <li>The size of the EntrySet should remain the same as before the removeAll call.</li>
     *   <li>The MapAdapter size should be unchanged after attempting to remove from an empty EntrySet.</li>
     * </ul>    
     */
    @Test
    public void testRemoveAllOnPopulatedEntrySetWithAnEmptyEntrySet() {
        HMap anotherEmptyMap = new MapAdapter();
        assertFalse("EntrySet should not change when removing from an empty EntrySet", this.map.entrySet().removeAll(anotherEmptyMap.entrySet()));
        assertEquals("EntrySet size should remain unchanged after attempting to remove from an empty EntrySet", MAP_INITIAL_CAPACITY, this.map.entrySet().size());
        assertEquals("MapAdapter size should remain unchanged after attempting to remove from an empty EntrySet", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated EntrySet throws a NullPointerException when passed null.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles null input.
     *   The rationale is to confirm that the EntrySet does not allow null arguments.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the populated EntrySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling removeAll with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnPopulatedEntrySetWithNull() {
        this.map.entrySet().removeAll(null);
    }   

    /**
     * Summary:
     * Verifies that the removeAll method on a populated EntrySet with another EntrySet containing null keys does not change the EntrySet or the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly when called with an EntrySet containing null keys.
     *   The rationale is to confirm that removing from an EntrySet with null keys does not affect the original EntrySet or MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the removeAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the EntrySet and MapAdapter's size is unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The removeAll method should return false.</li>
     *   <li>The size of the EntrySet should remain the same as before the removeAll call.</li>
     *   <li>The MapAdapter size should be unchanged after attempting to remove from another EntrySet with null keys.</li>
     * </ul>    
     */
    @Test
    public void testRemoveAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("EntrySet should not change when removing from another EntrySet with null keys", 
                    this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another EntrySet with null keys", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated EntrySet with another EntrySet containing null values does not change the EntrySet or the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly when called with an EntrySet containing null values.
     *   The rationale is to confirm that removing from an EntrySet with null values does not affect the original EntrySet or MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the removeAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the EntrySet and MapAdapter's size is unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The removeAll method should return false.</li>
     *   <li>The size of the EntrySet should remain the same as before the removeAll call.</li>
     *   <li>The MapAdapter size should be unchanged after attempting to remove from another EntrySet with null values.</li>
     * </ul>    
     */
    @Test
    public void testRemoveAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertFalse("EntrySet should not change when removing from another EntrySet with null values", 
                    this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another EntrySet with null values", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     * Verifies the behavior of the retainAll method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly retains specified entries in the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with half of the entries from the original map.</li>
     *   <li>Call the retainAll method on the populated EntrySet with the EntrySet of the second MapAdapter.</li>
     *   <li>Check that the EntrySet has reduced in size and contains only the retained entries.</li>
     *   <li>Call retainAll again with the same EntrySet and check that EntrySet does not change.</li>
     *   <li>Add the remaining entries back to the second MapAdapter and call retainAll again.</li>
     *   <li>Check that the EntrySet is empty after retaining all entries from a non-EntrySet collection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should return true if any entries were retained.</li>
     *   <li>The size of the EntrySet should be reduced to match the retained entries.</li>
     *   <li>The EntrySet should contain only retained entries after the first retainAll call.</li>
     *   <li>Calling retainAll with same EntrySet should not change the EntrySet.</li>
     *   <li>After adding remaining entries back to another MapAdapter and calling retainAll again, the EntrySet should be empty.</li>
     * </ul>    
     */
    @Test
    public void testRetainAllOnPopulatedEntrySet() {
        HMap anotherMap = new MapAdapter();
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
        assertFalse("Calling retainAll with same EntrySet should not change the EntrySet",
                    this.map.entrySet().retainAll(anotherMap.entrySet()));
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated EntrySet with an empty EntrySet modifies the EntrySet and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly when called with an empty EntrySet.
     *   The rationale is to confirm that retaining from an empty EntrySet results in an empty EntrySet and MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the retainAll method on the populated EntrySet with the EntrySet of the empty MapAdapter.</li>
     *   <li>Check that the EntrySet and MapAdapter are both empty after the retainAll call.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should return true, indicating that the EntrySet was modified.</li>
     *   <li>The size of the EntrySet should be zero after retaining from an empty EntrySet.</li>
     *   <li>The MapAdapter should also be empty after retaining from an empty EntrySet.</li>
     * </ul>    
     */
    @Test
    public void testRetainAllOnPopulatedEntrySetWithAnEmptyEntrySet() {
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("EntrySet should be modified when retaining from an empty EntrySet",
                   this.map.entrySet().retainAll(anotherEmptyMap.entrySet()));
        assertTrue("EntrySet should be empty after retaining from an empty EntrySet", this.map.entrySet().isEmpty());
        assertTrue("MapAdapter should be empty after retaining from an empty EntrySet", this.map.isEmpty());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated EntrySet with a non-EntrySet collection modifies the EntrySet and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly when called with a non-EntrySet collection.
     *   The rationale is to confirm that retaining from a non-EntrySet results in an empty EntrySet and MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a single entry.</li>
     *   <li>Call the retainAll method on the populated EntrySet with the keySet of the second MapAdapter.</li>
     *   <li>Check that the EntrySet and MapAdapter are both empty after the retainAll call.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should return true, indicating that the EntrySet was modified.</li>
     *   <li>The size of the EntrySet should be zero after retaining from a non-EntrySet collection.</li>
     *   <li>The MapAdapter should also be empty after retaining from a non-EntrySet collection.</li>
     * </ul>    
     */
    @Test
    public void testRetainAllOnPopulatedEntrySetWithANonEntrySet() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertTrue("EntrySet should change when retaining from a non-EntrySet", 
                    this.map.entrySet().retainAll(anotherMap.keySet()));
        assertTrue("MapAdapter should be empty after retaining from a non-EntrySet", this.map.isEmpty());
        assertTrue("EntrySet should be empty after retaining from a non-EntrySet", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated EntrySet throws a NullPointerException when passed null.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles null input.
     *   The rationale is to confirm that the EntrySet does not allow null arguments.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the populated EntrySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling retainAll with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllOnPopulatedEntrySetWithNull() {
        this.map.entrySet().retainAll(null);
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated EntrySet with another EntrySet containing null keys modifies the EntrySet and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly when called with an EntrySet containing null keys.
     *   The rationale is to confirm that retaining from an EntrySet with null keys results in an empty EntrySet and MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the retainAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the EntrySet and MapAdapter are both empty after the retainAll call.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should return true, indicating that the EntrySet was modified.</li>
     *   <li>The size of the EntrySet should be zero after retaining from another EntrySet with null keys.</li>
     *   <li>The MapAdapter should also be empty after retaining from another EntrySet with null keys.</li>
     * </ul>    
     */
    @Test
    public void testRetainAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertTrue("EntrySet should change when retaining from another EntrySet with null keys",
                   this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("MapAdapter should be empty after retaining from another EntrySet with null keys", this.map.isEmpty());
        assertTrue("EntrySet should be empty after retaining from another EntrySet with null keys", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated EntrySet with another EntrySet containing null values modifies the EntrySet and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly when called with an EntrySet containing null values.
     *   The rationale is to confirm that retaining from an EntrySet with null values results in an empty EntrySet and MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the retainAll method on the populated EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the EntrySet and MapAdapter are both empty after the retainAll call.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should return true, indicating that the EntrySet was modified.</li>
     *   <li>The size of the EntrySet should be zero after retaining from another EntrySet with null values.</li>
     *   <li>The MapAdapter should also be empty after retaining from another EntrySet with null values.</li>
     * </ul>    
     */
    @Test
    public void testRetainAllOnPopulatedEntrySetWithAnotherEntrySetContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertTrue("EntrySet should change when retaining from another EntrySet with null values",
                   this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("MapAdapter should be empty after retaining from another EntrySet with null values", this.map.isEmpty());
        assertTrue("EntrySet should be empty after retaining from another EntrySet with null values", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     * Verifies the behavior of the size method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method correctly returns the number of entries in the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the size method on the populated EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size method should return the number of entries in the EntrySet.</li>
     * </ul>
     */
    @Test
    public void testSizeOnPopulatedEntrySet() {
        assertEquals("Size of EntrySet should match the size of the MapAdapter", MAP_INITIAL_CAPACITY, this.map.entrySet().size());
    }

    /**
     * Summary:
     * Verifies the behavior of the toArray method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly converts the EntrySet into an array.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet.</li>
     *   <li>Check that the returned array has the correct length and contains the expected entries.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The returned array should have a length equal to the size of the EntrySet.</li>
     *   <li>The elements in the array should match the entries in the EntrySet.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated EntrySet is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the array is created and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet.</li>
     *   <li>Insert a new entry into the MapAdapter.</li>
     *   <li>Check that the array remains unchanged and does not contain the new entry.</li>
     *   <li>Create another MapAdapter and add an entry to it.</li>
     *   <li>Replace an element in the array with an entry from the new MapAdapter.</li>
     *   <li>Check that the MapAdapter does not contain the modified entry from the array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes made to the MapAdapter after the call.</li>
     *   <li>The array should not contain any new entries added to the MapAdapter after the toArray call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedEntrySetIsFreeFromMap() {
        Object[] array = this.map.entrySet().toArray();
        this.map.put("differentKey", "differentValue");
        assertEquals("array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("Array should not contain the new key", "differentKey", ((HMap.HEntry) array[i]).getKey());
            assertNotEquals("Array should not contain the new value", "differentValue", ((HMap.HEntry) array[i]).getValue());
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.entrySet().iterator().next();

        assertFalse("MapAdapter should not contain the new entry", this.map.entrySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated EntrySet with an array parameter of the right dimension is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an array parameter does not reflect changes made to the MapAdapter after the call and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with an array parameter of the right dimension.</li>
     *   <li>Insert a new entry into the MapAdapter.</li>
     *   <li>Check that the array remains unchanged and does not contain the new entry.</li>
     *   <li>Create another MapAdapter and add an entry to it.</li>
     *   <li>Replace an element in the array with an entry from the new MapAdapter.</li>
     *   <li>Check that the MapAdapter does not contain the modified entry from the array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes made to the MapAdapter after the call.</li>
     *   <li>The array should not contain any new entries added to the MapAdapter after the toArray call.</li>
     *   <li>The MapAdapter should not contain the modified entry from the array.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedEntrySetWithArrayParameterOfRightDimensionIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY];
        array = this.map.entrySet().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("Array should not contain the new key", "differentKey", ((HMap.HEntry) array[i]).getKey());
            assertNotEquals("Array should not contain the new value", "differentValue", ((HMap.HEntry) array[i]).getValue());
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.entrySet().iterator().next();

        assertFalse("MapAdapter should not contain the new entry", this.map.entrySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated EntrySet with an underdimensioned array is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an underdimensioned array does not reflect changes made to the MapAdapter after the call and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with an underdimensioned array.</li>
     *   <li>Insert a new entry into the MapAdapter.</li>
     *   <li>Check that the array remains unchanged and does not contain the new entry.</li>
     *   <li>Create another MapAdapter and add an entry to it.</li>
     *   <li>Replace an element in the array with an entry from the new MapAdapter.</li>
     *   <li>Check that the MapAdapter does not contain the modified entry from the array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes made to the MapAdapter after the call.</li>
     *   <li>The array should not contain any new entries added to the MapAdapter after the toArray call.</li>
     *   <li>The MapAdapter should not contain the modified entry from the array.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedEntrySetWithUnderdimensionedArrayIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY/2];
        array = this.map.entrySet().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("underdimensioned array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("underdimensioned array should not contain the new key", "differentKey", ((HMap.HEntry) array[i]).getKey());
            assertNotEquals("underdimensioned array should not contain the new value", "differentValue", ((HMap.HEntry) array[i]).getValue());
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.entrySet().iterator().next();

        assertFalse("MapAdapter should not contain the new entry", this.map.entrySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated EntrySet with an overdimensioned array is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an overdimensioned array does not reflect changes made to the MapAdapter after the call and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with an overdimensioned array.</li>
     *   <li>Insert a new entry into the MapAdapter.</li>
     *   <li>Check that the array remains unchanged and does not contain the new entry.</li>
     *   <li>Create another MapAdapter and add an entry to it.</li>
     *   <li>Replace an element in the array with an entry from the new MapAdapter.</li>
     *   <li>Check that the MapAdapter does not contain the modified entry from the array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes made to the MapAdapter after the call.</li>
     *   <li>The array should not contain any new entries added to the MapAdapter after the toArray call.</li>
     *   <li>The MapAdapter should not contain the modified entry from the array.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedEntrySetWithOverdimensionedArrayIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY + 50];
        array = this.map.entrySet().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("overdimensioned array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY + 50, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("overdimensioned array should not contain the new key", "differentKey", ((HMap.HEntry) array[i]).getKey());
            assertNotEquals("overdimensioned array should not contain the new value", "differentValue", ((HMap.HEntry) array[i]).getValue());
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.entrySet().iterator().next();

        assertFalse("MapAdapter should not contain the new entry", this.map.entrySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies the behavior of the toArray method on a populated EntrySet with an array parameter of the right dimension.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an array parameter correctly populates the array with entries from the EntrySet.
     *   The rationale is to confirm that the array returned by toArray matches the entries in the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with an array parameter of the right dimension.</li>
     *   <li>Check that the returned array has the correct length and contains the expected entries.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The returned array should have a length equal to the size of the EntrySet.</li>
     *   <li>The elements in the array should match the entries in the EntrySet.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedEntrySetWithArrayParameterOfRightDimension() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY];
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

    /**
     * Summary:
     * Verifies the behavior of the toArray method on a populated EntrySet with an underdimensioned array parameter.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an array parameter correctly handles underdimensioned arrays.
     *   The rationale is to confirm that the method returns a populated array of the same size as the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with an underdimensioned array parameter.</li>
     *   <li>Check that the returned array has the correct length and contains the expected entries.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The returned array should have a length equal to the size of the EntrySet.</li>
     *   <li>The elements in the array should match the entries in the EntrySet.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies the behavior of the toArray method on a populated EntrySet with an overdimensioned array parameter.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an array parameter correctly handles overdimensioned arrays.
     *   The rationale is to confirm that the method returns a populated array of the same size as the EntrySet, with additional null element.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with an overdimensioned array parameter.</li>
     *   <li>Check that the returned array has the correct length and contains the expected entries, including the null value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The returned array should have a length greater than the size of the EntrySet.</li>
     *   <li>The elements in the array should match the entries in the EntrySet.</li>
     *   <li>The last element in the array after the EntrySet should be null.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated EntrySet with a null array parameter throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles null input.
     *   The rationale is to confirm that the EntrySet does not allow null arguments.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated EntrySet with a null array parameter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling toArray with a null array parameter should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayOnPopulatedEntrySetWithNull() {
        this.map.entrySet().toArray(null);
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated EntrySet with an incompatible array type throws an ArrayStoreException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles incompatible array types.
     *   The rationale is to confirm that the EntrySet does not allow incompatible array types.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Instantiate an array of a different type (e.g., String[]).</li>
     *   <li>Call the toArray method on the populated EntrySet with an incompatible array type.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling toArray with an incompatible array type should throw an ArrayStoreException.</li>
     * </ul>
     */
    @Test(expected = ArrayStoreException.class)
    public void testToArrayOnPopulatedEntrySetWithIncompatibleArray() {
        Object[] array = new String[MAP_INITIAL_CAPACITY];
        this.map.entrySet().toArray(array);
    }
    
    /**
     * Summary:
     * Verifies the behavior of the toString method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method correctly formats the entries in the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toString method on the populated EntrySet.</li>
     *   <li>Check that the returned string matches the expected format.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string representation of the EntrySet in the format "[key1=value1, key2=value2, ...]".</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies the behavior of the EntrySet when the backing MapAdapter is modified.
     * <p>
     * Test Case Design:
     *   This test ensures that the EntrySet reflects changes made to the backing MapAdapter and vice-versa.
     *   The rationale is to confirm that the EntrySet is backed by the MapAdapter and changes in one reflect in the other.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the EntrySet from the MapAdapter.</li>
     *   <li>Check that the size of the EntrySet matches the MapAdapter.</li>
     *   <li>Add a new entry to the MapAdapter.</li>
     *   <li>Check that the size of the EntrySet has increased.</li>
     *   <li>Remove an entry from the EntrySet.</li>
     *   <li>Check that the MapAdapter has decreased in size and does not contain the removed entry.</li>
     *   <li>Clear the EntrySet.</li>
     *   <li>Check that both the EntrySet and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should have the same size as the MapAdapter.</li>
     *   <li>Adding a new entry to the MapAdapter should increase the size of the EntrySet.</li>
     *   <li>Removing an entry from the EntrySet should decrease the size of the MapAdapter and remove the entry.</li>
     *   <li>Clearing the EntrySet should empty both the EntrySet and the MapAdapter.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies the behavior of the equals method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly compares two EntrySets with the same entries.
     *   The rationale is to confirm that the equals method behaves as expected for populated EntrySets.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the EntrySet from the MapAdapter.</li>
     *   <li>Create another MapAdapter with the same entries and retrieve its EntrySet.</li>
     *   <li>Check that both EntrySets are equal.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true for two EntrySets with the same entries.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntrySet() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter(this.map).entrySet();
        assertTrue("EntrySet should be equal to another EntrySet with the same entries", entrySet.equals(anotherEntrySet));
    }

    /**
     * Summary:
     * Verifies the behavior of the equals method on a populated EntrySet with different entries.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when two EntrySets are not equal due to different entries.
     *   The rationale is to confirm that the equals method behaves as expected for populated EntrySets with different entries.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the EntrySet from the MapAdapter.</li>
     *   <li>Create another MapAdapter with different entries and retrieve its EntrySet.</li>
     *   <li>Check that both EntrySets are not equal.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false for two EntrySets with different entries.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntrySetWithDifferentEntrySet() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.remove("testKey0");
        assertFalse("EntrySet should not be equal to a different EntrySet", this.map.entrySet().equals(anotherMap.entrySet()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated EntrySet does not equal a non-HSet object.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when an EntrySet is compared to a non-HSet object.
     *   The rationale is to confirm that the equals method behaves as expected when comparing to non-HSet objects.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the EntrySet is not equal to a non-HSet object (e.g., the values collection of the MapAdapter).</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false when comparing the EntrySet to a non-HSet object.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntrySetWithNonHSet() {
        assertFalse("EntrySet should not be equal to a non-HSet object", this.map.entrySet().equals(this.map.values()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated EntrySet does not equal null.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when an EntrySet is compared to null.
     *   The rationale is to confirm that the equals method behaves as expected when comparing to null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the EntrySet is not equal to null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false when comparing the EntrySet to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntrySetWithNull() {
        assertFalse("EntrySet should not be equal to null", this.map.entrySet().equals(null));
    }   

    /**
     * Summary:
     * Verifies that a populated EntrySet is equal to itself.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when an EntrySet is compared to itself.
     *   The rationale is to confirm that the equals method behaves as expected for a populated EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the EntrySet is equal to itself.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true when comparing the EntrySet to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntryWithItself() {
        assertTrue("A populated EntrySet should be equal to itself", this.map.entrySet().equals(this.map.entrySet()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated EntrySet is symmetric and transitive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves symmetrically and transitively for populated EntrySets.
     *   The rationale is to confirm that the equals method behaves as expected for populated EntrySets in terms of symmetry and transitivity.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the EntrySet from the MapAdapter.</li>
     *   <li>Create another MapAdapter with the same entries and retrieve its EntrySet.</li>
     *   <li>Check that both EntrySets are equal and vice-versa (symmetry).</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true for two EntrySets with the same entries and vice-versa (symmetry).</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntrySetIsSymmetric() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter(this.map).entrySet();
        assertTrue("EntrySet should be equal to another EntrySet with the same entries and vice-versa", 
            entrySet.equals(anotherEntrySet) && anotherEntrySet.equals(entrySet));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated EntrySet is transitive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves transitively for populated EntrySets.
     *   The rationale is to confirm that if EntrySet A equals EntrySet B and EntrySet B equals EntrySet C, then EntrySet A should equal EntrySet C.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the EntrySet from the MapAdapter.</li>
     *   <li>Create another MapAdapter with the same entries and retrieve its EntrySet.</li>
     *   <li>Create a third MapAdapter with the same entries and retrieve its EntrySet.</li>
     *   <li>Check that all three EntrySets are equal (transitivity).</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true for three EntrySets with the same entries, confirming transitivity.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedEntrySetIsTransitive() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter(this.map).entrySet();
        HSet anotherAnotherEntrySet = new MapAdapter(this.map).entrySet();
        assertTrue("If EntrySet A equals EntrySet B and MapAdapter B equals EntrySet C, then EntrySet A should equal MapAdapter C",
            entrySet.equals(anotherEntrySet) && anotherEntrySet.equals(anotherAnotherEntrySet) && entrySet.equals(anotherAnotherEntrySet));
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated EntrySet correctly identifies entries.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly identifies whether an entry is present in the EntrySet.
     *   The rationale is to confirm that the contains method behaves as expected for populated EntrySets.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get an entry from the EntrySet.</li>
     *   <li>Check that the EntrySet contains the entry.</li>
     *   <li>Create another MapAdapter with a different entry and check that the EntrySet does not contain it.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The contains method should return true for an entry that is present in the EntrySet.</li>
     *   <li>The contains method should return false for an entry that is not present in the EntrySet.</li>
     * </ul>
     */
    @Test
    public void testContainsOnPopulatedEntrySet() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        assertTrue("EntrySet should contain the entry", this.map.entrySet().contains(entry));
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "differentValue");
        assertFalse("EntrySet should not contain an entry with a different key and value",
                    this.map.entrySet().contains(anotherMap.entrySet().iterator().next()));
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated EntrySet throws a ClassCastException for non-HEntry objects.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles non-HEntry objects.
     *   The rationale is to confirm that the EntrySet does not allow non-HEntry objects in the contains method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the populated EntrySet with a non-HEntry object.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling contains with a non-HEntry object should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testContainsOnPopulatedEntrySetWithNonHEntryObject() {
        this.map.entrySet().contains("nonHEntryObject");
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated EntrySet throws a NullPointerException for null.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles null input.
     *   The rationale is to confirm that the EntrySet does not allow null arguments in the contains method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the populated EntrySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling contains with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedEntrySetWithNull() {
        this.map.entrySet().contains(null);
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated EntrySet throws a NullPointerException for entries with null keys.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles entries with null keys.
     *   The rationale is to confirm that the EntrySet does not allow entries with null keys in the contains method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the contains method on the populated EntrySet with this entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling contains with an entry that has a null key should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedEntrySetWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().contains(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated EntrySet throws a NullPointerException for entries with null values.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles entries with null values.
     *   The rationale is to confirm that the EntrySet does not allow entries with null values in the contains method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the contains method on the populated EntrySet with this entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling contains with an entry that has a null value should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedEntrySetWithNullValue() {
        HMap anotherMap = new DummyTestMap();       
        anotherMap.put("testKey", null);
        this.map.entrySet().contains(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     * Verifies the behavior of the remove method on a populated EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly removes entries from the EntrySet and updates the MapAdapter accordingly.
     *   The rationale is to confirm that the remove method behaves as expected for populated EntrySets.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the EntrySet from the MapAdapter.</li>
     *   <li>Remove an entry from the EntrySet and check that it is no longer present.</li>
     *   <li>Check that the MapAdapter has also removed the corresponding key and value.</li>
     *   <li>Iterate through the EntrySet and remove all entries, checking that both the EntrySet and MapAdapter are empty at the end.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty after removing all entries from the EntrySet.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should decrease in size by 1 after removing an entry.</li>
     *   <li>The removed entry should no longer be present in the EntrySet.</li>
     *   <li>The MapAdapter should not contain the removed key and value.</li>
     *   <li>After removing all entries, the EntrySet and MapAdapter should be empty.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the remove method on a populated EntrySet throws a NullPointerException with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles null input.
     *   The rationale is to confirm that the EntrySet does not allow null arguments in the remove method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the populated EntrySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling remove with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedEntrySetWithNull() {
        this.map.entrySet().remove(null);
    }

    /**
     * Summary:
     * Verifies that the remove method on a populated EntrySet throws a ClassCastException with non-HEntry objects.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles non-HEntry objects.
     *   The rationale is to confirm that the EntrySet does not allow non-HEntry objects in the remove method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the populated EntrySet with a non-HEntry object.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling remove with a non-HEntry object should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveOnPopulatedEntrySetWithNonHEntryObject() {
        this.map.entrySet().remove("nonHEntryObject");
    }

    /**
     * Summary:
     * Verifies that the remove method on a populated EntrySet throws a NullPointerException for entries with null keys.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles entries with null keys.
     *   The rationale is to confirm that the EntrySet does not allow entries with null keys in the remove method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the remove method on the populated EntrySet with this entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling remove with an entry that has a null key should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedEntrySetWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().remove(anotherMap.entrySet().iterator().next());
    }   

    /**
     * Summary:
     * Verifies that the remove method on a populated EntrySet throws a NullPointerException for entries with null values.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles entries with null values.
     *   The rationale is to confirm that the EntrySet does not allow entries with null values in the remove method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the remove method on the populated EntrySet with this entry.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling remove with an entry that has a null value should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedEntrySetWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().remove(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     * Verifies that the iterator on a populated EntrySet is not null.
     * <p>
     * Test Case Design:
     *   This test ensures that the iterator on a populated EntrySet is correctly initialized and not null.
     *   The rationale is to confirm that the EntrySet provides a valid iterator for traversing its entries.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the iterator from the populated EntrySet.</li>
     *   <li>Check that the iterator is not null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The iterator should not be null.</li>
     * </ul>
     */
    @Test
    public void testIteratorOnPopulatedEntrySet() {
        assertNotNull("Iterator should not be null", this.map.entrySet().iterator());
    }
    
    /*--------------------------------------------------------------*/
    /*------------------------ KeySet TESTS ------------------------*/
    /*--------------------------------------------------------------*/

    /**
     * Summary:
     * Verifies that the add method on a populated KeySet throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the add method is not supported on the KeySet.
     *   The rationale is to confirm that the KeySet does not allow addition of new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Call the add method on the KeySet with anything.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling add on the KeySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        keySet.add("anything");
    }

    /**
     * Summary:
     * Verifies that the addAll method on a populated KeySet throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the addAll method is not supported on the KeySet.
     *   The rationale is to confirm that the KeySet does not allow addition of multiple new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Call the addAll method on the KeySet with this KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling addAll on the KeySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        keySet.addAll(this.map.keySet());
    }

    /**
     * Summary:
     * Verifies that the clear method on a populated KeySet clears both the KeySet and the underlying MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the clear method removes all elements from the KeySet.
     *   The rationale is to confirm that the KeySet can be empty.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Call the clear method on the KeySet.</li>
     *   <li>Check that the KeySet is empty after clearing.</li>
     *   <li>Check that the MapAdapter is also empty after clearing the KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>KeySet should be empty after clear.</li>
     *   <li>MapAdapter should also be empty after clearing the KeySet.</li>
     * </ul>
     */
    @Test
    public void testClearOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        keySet.clear();
        assertTrue("KeySet should be empty after clear", keySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing KeySet", this.map.isEmpty());
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated KeySet works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method checks for the presence of all specified keys.
     *   The rationale is to confirm that the KeySet can accurately report its contents.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with the same entries and retrieve its KeySet.</li>
     *   <li>Check that the KeySet contains all keys from the other KeySet.</li>
     *   <li>Remove some keys from the other KeySet and check that the KeySet still contains all remaining keys.</li>
     *   <li>Create an empty MapAdapter and check that the KeySet contains all keys from this empty KeySet.</li>
     *   <li>Create another MapAdapter with a different key and check that the KeySet does not contain all keys from this different KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>KeySet should contain all keys from the other KeySet.</li>
     *   <li>KeySet should still contain all remaining keys after partial removal from the other KeySet.</li>
     *   <li>KeySet should contain all keys from an empty KeySet.</li>
     *   <li>KeySet should not contain all keys from a completely different KeySet.</li>
     * </ul>
     */
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
        anotherPopulatedMap.put("testKeyMAP_INITIAL_CAPACITY", "testValueMAP_INITIAL_CAPACITY");
        assertFalse("KeySet should not contain all keys from a completely different map", this.map.keySet().containsAll(anotherPopulatedMap.keySet()));
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles null input.
     *   The rationale is to confirm that the KeySet does not allow null arguments in the containsAll method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the populated KeySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedKeySetNull() {
        this.map.keySet().containsAll(null);
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated KeySet with another KeySet containing null keys throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles another KeySet with null keys.
     *   The rationale is to confirm that the KeySet does not allow null keys in the containsAll method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the containsAll method on the populated KeySet with this KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling containsAll with a KeySet that has null keys should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedKeySetWithNullKeys() {
        HMap anotherMap = new DummyTestMap();   
        anotherMap.put(null, "testValue");
        this.map.keySet().containsAll(anotherMap.keySet());
    }

    /**
     * Summary:
     * Verifies that the hashCode method on a populated KeySet returns the expected value.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method correctly computes the hash code for the KeySet.
     *   The rationale is to confirm that the KeySet's hash code is consistent with the sum of the hash codes of its keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Compute the expected hash code by summing the hash codes of all keys in the KeySet.</li>
     *   <li>Check that the KeySet's hashCode matches the expected value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hashCode method should return the expected value based on the keys in the KeySet.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the isEmpty method on a populated KeySet returns false.
     * <p>
     * Test Case Design:
     *   This test ensures that the isEmpty method correctly identifies that the KeySet is not empty.
     *   The rationale is to confirm that the KeySet reports its non-empty state accurately.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the isEmpty method on the populated KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The isEmpty method should return false for a populated KeySet.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnPopulatedKeySet() {
        assertFalse("KeySet should not be empty", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated KeySet works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly removes specified keys from the KeySet.
     *   The rationale is to confirm that the KeySet can accurately remove multiple keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with partial entries of the original MapAdapter.</li>
     *   <li>Call the removeAll method on the KeySet with this other KeySet.</li>
     *   <li>Check that the KeySet and MapAdapter have been updated accordingly.</li>
     *   <li>Check that calling removeAll with the same KeySet does not change the KeySet.</li>
     *   <li>Check that calling removeAll with an empty KeySet does not change the KeySet.</li>
     *   <li>Populated the other MapAdapter with the remaining keys and call removeAll again.</li>
     *   <li>Check that the KeySet is empty after removing all keys.</li>
     *   <li>Check that the MapAdapter is empty after removing all keys.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>KeySet should remove all specified keys and reduce its size accordingly.</li>
     *   <li>MapAdapter should have reduced size after removing keys.</li>
     *   <li>KeySet should not contain removed keys after removeAll.</li>
     *   <li>Calling removeAll with the same KeySet should not change the KeySet.</li>
     *   <li>Calling removeAll with a KeySet containing the remaining keys should empty the KeySet and the MapAdapter.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the removeAll method on a populated KeySet with an empty KeySet does not change the KeySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles an empty KeySet.
     *   The rationale is to confirm that removing from an empty KeySet does not affect the original KeySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the removeAll method on the populated KeySet with this empty KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling removeAll with an empty KeySet should not change the KeySet.</li>
     *   <li>The MapAdapter should remain unchanged after attempting to remove from an empty KeySet.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnPopulatedKeySetWithAnEmptyKeySet() {
        HMap anotherEmptyMap = new MapAdapter();
        assertFalse("KeySet should not change when removing from an empty KeySet", this.map.keySet().removeAll(anotherEmptyMap.keySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from an empty KeySet", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated KeySet with an EntrySet throws a ClassCastException.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method does not accept an EntrySet as an argument.
     *   The rationale is to confirm that the KeySet does not allow removal of entries using an EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the populated KeySet with the EntrySet of the MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling removeAll with an EntrySet should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveAllOnPopulatedKeySetWithAnEntrySet() {
        this.map.keySet().removeAll(this.map.entrySet());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles null input.
     *   The rationale is to confirm that the KeySet does not allow null arguments in the removeAll method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the populated KeySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling removeAll with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnPopulatedKeySetWithNull() {
        this.map.keySet().removeAll(null);
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated KeySet with another KeySet containing null keys works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles another KeySet with null keys.
     *   The rationale is to confirm that the KeySet can accurately remove keys, including null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the removeAll method on the populated KeySet with this KeySet.</li>
     *   <li>Check that the KeySet and MapAdapter have not changed.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling removeAll with another KeySet containing null keys should not change the KeySet.</li>
     *   <li>The MapAdapter should remain unchanged after removing all keys from another KeySet with null keys.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnPopulatedKeySetWithAnotherKeySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("KeySet should not change when removing from another KeySet with null keys",
                    this.map.keySet().removeAll(anotherMap.keySet()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another KeySet with null keys", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated KeySet works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly retains specified keys in the KeySet.
     *   The rationale is to confirm that the KeySet can accurately retain multiple keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with partial entries of the original MapAdapter.</li>
     *   <li>Call the retainAll method on the KeySet with this other KeySet.</li>
     *   <li>Check that the KeySet and MapAdapter have been updated accordingly.</li>
     *   <li>Check that calling retainAll with the same KeySet does not change the KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is updated to retain only specified keys.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>KeySet should retain only specified keys and reduce its size accordingly.</li>
     *   <li>MapAdapter should have reduced size after retaining keys.</li>
     *   <li>KeySet should still contain retained keys after retainAll.</li>
     *   <li>Calling retainAll with the same KeySet should not change the KeySet.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedKeySet() {
        HMap anotherMap = new MapAdapter();
        for (int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        assertTrue("KeySet should be modified when calling retainAll", this.map.keySet().retainAll(anotherMap.keySet()));
        assertEquals("MapAdapter should have the same size as anotherMap after retainAll",
                     anotherMap.size(), this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY / 2; i++) {
            assertFalse("MapAdapter should not contain removed keys", this.map.containsKey("testKey" + i));
        }
        for(int i = MAP_INITIAL_CAPACITY / 2; i < MAP_INITIAL_CAPACITY; i++) {
            assertTrue("MapAdapter should still contain retained keys", this.map.containsKey("testKey" + i));
        }
        assertFalse("Calling retainAll with same KeySet should not change the KeySet",
                    this.map.keySet().retainAll(anotherMap.keySet()));
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated KeySet with an empty KeySet empties the KeySet and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles an empty KeySet.
     *   The rationale is to confirm that retaining from an empty KeySet results in an empty KeySet and MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the retainAll method on the populated KeySet with the other MapAdapter's KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>KeySet should be modified when retaining from an empty KeySet.</li>
     *   <li>KeySet should be empty after retaining from an empty KeySet.</li>
     *   <li>MapAdapter should be empty after retaining from an empty KeySet.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedKeySetWithAnEmptyKeySet() {
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("KeySet should be modified when retaining from an empty KeySet",
                   this.map.keySet().retainAll(anotherEmptyMap.keySet()));
        assertTrue("KeySet should be empty after retaining from an empty KeySet", this.map.keySet().isEmpty());
        assertTrue("MapAdapter should be empty after retaining from an empty KeySet", this.map.isEmpty());
    }   

    /**
     * Summary:
     * Verifies that the retainAll method on a populated KeySet with an EntrySet throws a ClassCastException.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method does not accept an EntrySet as an argument.
     *   The rationale is to confirm that the KeySet does not allow retention of entries using an EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the populated KeySet with the EntrySet of the MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling retainAll with an EntrySet should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testRetainAllOnPopulatedKeySetWithAnEntrySet() {
        this.map.keySet().retainAll(this.map.entrySet());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles null input.
     *   The rationale is to confirm that the KeySet does not allow null arguments in the retainAll method.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the populated KeySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling retainAll with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllOnPopulatedKeySetWithNull() {
        this.map.keySet().retainAll(null);
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated KeySet with another KeySet containing null keys works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles another KeySet with null keys.
     *   The rationale is to confirm that the KeySet can accurately retain keys, including null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null key.</li>
     *   <li>Call the retainAll method on the populated KeySet with this KeySet.</li>
     *   <li>Check that the KeySet and MapAdapter have been updated accordingly.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling retainAll with another KeySet containing null keys should return true.</li>
     *   <li>The KeySet should be empty after retaining from another KeySet with null keys.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedKeySetWithAnotherKeySetContainingNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertTrue("KeySet should change when retaining from another KeySet with null keys",
                   this.map.keySet().retainAll(anotherMap.keySet()));
        assertTrue("MapAdapter should be empty after retaining from another KeySet with null keys", this.map.isEmpty());
        assertTrue("KeySet should be empty after retaining from another KeySet with null keys", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     * Verifies that the size method on a populated KeySet returns the expected size.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method correctly reports the number of keys in the KeySet.
     *   The rationale is to confirm that the KeySet accurately reflects the size of the underlying MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Check that the size of the KeySet matches the size of the MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size method should return the expected value based on the number of keys in the KeySet.</li>
     * </ul>
     */
    @Test
    public void testSizeOnPopulatedKeySet() {
        assertEquals("Size of KeySet should match the size of the MapAdapter", MAP_INITIAL_CAPACITY, this.map.keySet().size());
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet returns an array containing all keys.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly converts the KeySet to an array.
     *   The rationale is to confirm that the KeySet can accurately represent its contents as an array.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Convert the KeySet to an array using the toArray method.</li>
     *   <li>Check that the array length matches the size of the KeySet.</li>
     *   <li>Verify that each element in the array matches a key in the KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toArray method should return an array containing all keys from the KeySet.</li>
     *   <li>The array length should match the size of the KeySet.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the call and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated KeySet.</li>
     *   <li>Modify the MapAdapter after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new keys added after toArray.</li>
     *   <li>Modify the array returned by toArray and check that the MapAdapter does not contain the new key.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after the call.</li>
     *   <li>The MapAdapter should not contain any keys that were added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedKeySetIsFreeFromMap() {
        Object[] array = this.map.keySet().toArray();
        this.map.put("differentKey", "differentValue");
        assertEquals("array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("array should not contain the new key", "differentKey", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.keySet().iterator().next();

        assertFalse("MapAdapter should not contain the new key", this.map.keySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an array parameter of the right dimension is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the call when using an array parameter and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated KeySet with an array parameter of the right dimension.</li>
     *   <li>Modify the MapAdapter after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new keys added after toArray.</li>
     *   <li>Modify the array returned by toArray and check that the MapAdapter does not contain the new key.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after the call.</li>
     *   <li>The MapAdapter should not contain any keys that were added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedKeySetWithArrayParameterOfRightDimensionIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY];
        array = this.map.keySet().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("Array should not contain the new key", "differentKey", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.keySet().iterator().next();

        assertFalse("MapAdapter should not contain the new key", this.map.keySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an underdimensioned array is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the call when using an underdimensioned array and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated KeySet with an underdimensioned array.</li>
     *   <li>Modify the MapAdapter after calling toArray.</li>
     *   <li>Check that the array length is the same as MapAdapter's before being modified.</li>
     *   <li>Modify the array returned by toArray and check that the MapAdapter does not contain the new key.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after the call.</li>
     *   <li>The MapAdapter should not contain any keys that were added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedKeySetWithUnderdimensionedArrayIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY/2];
        array = this.map.keySet().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("underdimensioned array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("underdimensioned array should not contain the new key", "differentKey", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.keySet().iterator().next();

        assertFalse("MapAdapter should not contain the new key", this.map.keySet().contains(array[50]));

    }
    
    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an overdimensioned array is free from changes in the MapAdapter after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the call when using an overdimensioned array and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated KeySet with an overdimensioned array.</li>
     *   <li>Modify the MapAdapter after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new keys added after toArray.</li>
     *   <li>Modify the array returned by toArray and check that the MapAdapter does not contain the new key.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after the call.</li>
     *   <li>The MapAdapter should not contain any keys that were added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedKeySetWithOverdimensionedArrayIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY + 50];
        array = this.map.keySet().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("overdimensioned array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY + 50, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("overdimensioned array should not contain the new key", "differentKey", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.keySet().iterator().next();

        assertFalse("MapAdapter should not contain the new key", this.map.keySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an array parameter of the right dimension works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly converts the KeySet to an array with a specified size.
     *   The rationale is to confirm that the KeySet can accurately represent its contents as an array of the right size.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Convert the KeySet to an array using the toArray method with an array parameter of the right dimension.</li>
     *   <li>Check that the array length matches the size of the KeySet.</li>
     *   <li>Verify that each element in the array matches a key in the KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toArray method should return an array containing all keys from the KeySet.</li>
     *   <li>The array length should match the size of the KeySet.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedKeySetWithArrayParameterOfRightDimension() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY];
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an underdimensioned array works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly converts the KeySet to an array when called with an underdimensioned array.
     *   The rationale is to confirm that the KeySet can accurately represent its contents as an array, even if the provided array is smaller than the KeySet size.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Convert the KeySet to an array using the toArray method with an underdimensioned array.</li>
     *   <li>Check that the array length matches the size of the KeySet.</li>
     *   <li>Verify that each element in the array matches a key in the KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array length should match the size of the KeySet.</li>
     *   <li>The toArray method should return an array containing all keys from the KeySet.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an overdimensioned array works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly converts the KeySet to an array when called with an overdimensioned array.
     *   The rationale is to confirm that the KeySet can accurately represent its contents as an array, even if the provided array is larger than the KeySet size.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Convert the KeySet to an array using the toArray method with an overdimensioned array.</li>
     *   <li>Check that the array length is the same as before the call.</li>
     *   <li>Verify that each element in the array matches a key in the KeySet.</li>
     *   <li>Check that the first element after the last key in the array is null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array length should be the same as before the call.</li>
     *   <li>The toArray method should return an array containing all keys from the KeySet.</li>
     *   <li>The first element after the last key in the array should be null.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with a null array throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles a null array input.
     *   The rationale is to confirm that the KeySet does not accept null as a valid array.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Call the toArray method with a null array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayOnPopulatedKeySetWithNull() {
        this.map.keySet().toArray(null);
    }   

    /**
     * Summary:
     * Verifies that the toArray method on a populated KeySet with an incompatible array throws an ArrayStoreException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles an incompatible array type (i.e., an Integer array and String keys).
     *   The rationale is to confirm that storing keys in an incompatible array throws an ArrayStoreException (i.e., an attempt to store a String in an Integer array).
     * <p>
     * Test Description:
     * <ul>
     *   <li>Instantiate an Integer array of the same size as the MapAdapter.</li>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Call the toArray method on the KeySet with the Integer array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>An ArrayStoreException should be thrown when attempting to store a String key in an Integer array.</li>
     * </ul>
     */
    @Test(expected = ArrayStoreException.class)
    public void testToArrayOnPopulatedKeySetWithIncompatibleArray() {
        Object[] array = new Integer[MAP_INITIAL_CAPACITY];
        this.map.keySet().toArray(array);
    }

    /**
     * Summary:
     * Verifies that the toString method on a populated KeySet returns a string representation of the keys.
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method correctly formats the keys in the KeySet.
     *   The rationale is to confirm that the KeySet provides a readable string representation of its contents.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Generate the expected string representation of the keys.</li>
     *   <li>Call the toString method on the KeySet.</li>
     *   <li>Check that the returned string matches the expected format.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string representation of the keys in the KeySet.</li>
     *   <li>The format should be "[key1, key2, key3, ...]" where each key is separated by a comma and space.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the KeySet on a populated MapAdapter is backed by the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the KeySet reflects changes made to the MapAdapter and vice-versa.
     *   The rationale is to confirm that the KeySet is a view of the keys in the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Check that the size of the KeySet matches the size of the MapAdapter.</li>
     *   <li>Modify the MapAdapter and check that the KeySet reflects these changes.</li>
     *   <li>Remove a key from the KeySet and check that it is also removed from the MapAdapter.</li>
     *   <li>Clear the KeySet and check that both the KeySet and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The KeySet should have the same size as the MapAdapter.</li>
     *   <li>Changes in the MapAdapter should be reflected in the KeySet.</li>
     *   <li>Removing a key from the KeySet should also remove it from the MapAdapter.</li>
     *   <li>Clearing the KeySet should empty both the KeySet and MapAdapter.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet return true when compared to another KeySet with the same keys.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly compares two KeySets for equality.
     *   The rationale is to confirm that the equals method returns true when both KeySets contain the same keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Create another KeySet from a new MapAdapter with the same keys.</li>
     *   <li>Check that the equals method returns true when comparing the two KeySets.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>equals should return true when comparing two KeySets with the same keys.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySet() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter(this.map).keySet();
        assertTrue("KeySet should be equal to another KeySet with the same entries", keySet.equals(anotherKeySet));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet returns false when compared to a different KeySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when two KeySets are not equal.
     *   The rationale is to confirm that the equals method returns false when the KeySets contain different keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Create another MapAdapter with a different key removed.</li>
     *   <li>Check that the equals method returns false when comparing the two KeySets.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>equals should return false when comparing two KeySets with different keys.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySetWithDifferentKeySet() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.remove("testKey0");
        assertFalse("KeySet should not be equal to a different KeySet", this.map.keySet().equals(anotherMap.keySet()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet returns false when compared to a non-HSet object.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when a KeySet is compared to an object that is not an HSet.
     *   The rationale is to confirm that the equals method returns false when the compared object is not an instance of HSet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Check that the equals method returns false when comparing the KeySet to a non-HSet object (e.g., values collection).</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>equals should return false when comparing a KeySet to a non-HSet object.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySetWithNonHSet() {
        assertFalse("KeySet should not be equal to a non-HSet object", this.map.keySet().equals(this.map.values()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet returns false when compared to null.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies when a KeySet is compared to null.
     *   The rationale is to confirm that the equals method returns false when the compared object is null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Check that the equals method returns false when comparing the KeySet to null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>equals should return false when comparing a KeySet to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySetWithNull() {
        assertFalse("KeySet should not be equal to null", this.map.keySet().equals(null));
    }   

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet is reflexive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method returns true when a KeySet is compared to itself.
     *   The rationale is to confirm that the equals method is reflexive.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Check that the equals method returns true when comparing the KeySet to itself.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>equals should return true when comparing a KeySet to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySetWithItself() {
        assertTrue("A populated KeySet should be equal to itself", this.map.keySet().equals(this.map.keySet()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet is symmetric.
     * <p>
     * Test Case Design:
     *   This test ensures that if KeySet A equals KeySet B, then KeySet B should also equal KeySet A.
     *   The rationale is to confirm that the equals method is symmetric.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Create another KeySet from a new MapAdapter with the same keys.</li>
     *   <li>Check that the equals method returns true when comparing KeySet A to KeySet B and vice-versa.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>If KeySet A equals KeySet B, then KeySet B should also equal KeySet A.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySetIsSymmetric() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter(this.map).keySet();
        assertTrue("KeySet should be equal to another KeySet with the same entries and vice-versa",
            keySet.equals(anotherKeySet) && anotherKeySet.equals(keySet));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated KeySet is transitive.
     * <p>
     * Test Case Design:
     *   This test ensures that if KeySet A equals KeySet B and KeySet B equals KeySet C, then KeySet A should equal KeySet C.
     *   The rationale is to confirm that the equals method is transitive.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Create another KeySet from a new MapAdapter with the same keys.</li>
     *   <li>Create a third KeySet from another new MapAdapter with the same keys.</li>
     *   <li>Check that if KeySet A equals KeySet B and KeySet B equals KeySet C, then KeySet A should equal KeySet C.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>If KeySet A equals KeySet B and KeySet B equals KeySet C, then KeySet A should equal KeySet C.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedKeySetIsTransitive() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter(this.map).keySet();
        HSet anotherAnotherKeySet = new MapAdapter(this.map).keySet();
        assertTrue("If KeySet A equals KeySet B and MapAdapter B equals KeySet C, then KeySet A should equal MapAdapter C",
            keySet.equals(anotherKeySet) && anotherKeySet.equals(anotherAnotherKeySet) && keySet.equals(anotherAnotherKeySet));
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated KeySet returns true for keys that are present in the KeySet, false otherwise.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly identifies whether a key is present in the KeySet or not.
     *   The rationale is to confirm that the KeySet can accurately determine the presence of keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get a key from the populated MapAdapter.</li>
     *   <li>Check that the KeySet contains this key.</li>
     *   <li>Create another MapAdapter with a different key and check that the KeySet does not contain this key.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The contains method should return true for keys that are present in the KeySet.</li>
     *   <li>The contains method should return false for keys that are not present in the KeySet.</li>
     * </ul>
     */
    @Test
    public void testContainsOnPopulatedKeySet() {
        Object key = this.map.keySet().iterator().next();
        assertTrue("KeySet should contain the key", this.map.keySet().contains(key));
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "differentValue");
        assertFalse("KeySet should not contain a key that is not present",
                    this.map.keySet().contains(anotherMap.keySet().iterator().next()));
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated KeySet throws a NullPointerException when called with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles a null input.
     *   The rationale is to confirm that the KeySet does not accept null as a valid key.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the MapAdapter's KeySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling contains on a KeySet with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedKeySetWithNull() {
        this.map.keySet().contains(null);
    }

    /**
     * Summary:
     * Verifies that the remove method on a populated KeySet works as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly removes a key from the KeySet and the MapAdapter.
     *   The rationale is to confirm that the KeySet can accurately remove keys and that this change is reflected in the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Remove a key from the KeySet and check that it is no longer present in both the KeySet and MapAdapter.</li>
     *   <li>Continue removing keys until the KeySet is empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The remove method should decrease the size of the KeySet by 1 after each removal.</li>
     *   <li>The removed key should not be present in either the KeySet or MapAdapter after removal.</li>
     *   <li>Both the KeySet and MapAdapter should be empty after all keys are removed.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the remove method on a populated KeySet throws a NullPointerException when called with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles a null input.
     *   The rationale is to confirm that the KeySet does not accept null as a valid key for removal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the MapAdapter's KeySet with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling remove on a KeySet with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedKeySetWithNull() {
        this.map.keySet().remove(null);
    }

    /**
     * Summary:
     * Verifies that the iterator on a populated KeySet is not null.
     * <p>
     * Test Case Design:
     *   This test ensures that the iterator can be obtained from a populated KeySet.
     *   The rationale is to confirm that the KeySet provides an iterator for traversing its keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the KeySet from the populated MapAdapter.</li>
     *   <li>Check that the iterator on the KeySet is not null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The iterator on a populated KeySet should not be null.</li>
     * </ul>
     */
    @Test
    public void testIteratorOnPopulatedKeySet() {
        assertNotNull("Iterator on KeySet should not be null", this.map.keySet().iterator());
    }

    /*--------------------------------------------------------------*/
    /*--------------------- ValueCollection TESTS ------------------*/
    /*--------------------------------------------------------------*/

    /**
     * Summary:
     * Verifies that the add method on a populated ValueCollection throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the add method is not supported on a populated ValueCollection.
     *   The rationale is to confirm that the ValueCollection does not allow addition of new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the add method on the populated ValueCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling add on the ValueCollection should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        values.add("anything");
    }

    /**
     * Summary:
     * Verifies that the addAll method on a populated ValueCollection throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the addAll method is not supported on a populated ValueCollection.
     *   The rationale is to confirm that the ValueCollection does not allow addition of multiple new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the addAll method on the populated ValueCollection with another ValueCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Calling addAll on the ValueCollection should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        values.addAll(this.map.values());
    }

    /**
     * Summary:
     * Verifies that the clear method on a populated ValueCollection empties the collection and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the clear method correctly empties the ValueCollection and the MapAdapter.
     *   The rationale is to confirm that clearing the ValueCollection also clears the underlying MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Call the clear method on the ValueCollection.</li>
     *   <li>Check that both the ValueCollection and MapAdapter are empty after clearing.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should be empty after calling clear.</li>
     *   <li>The MapAdapter should also be empty after clearing the ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testClearOnPopulatedValueCollection() {
        HCollection values = this.map.values();
        values.clear();
        assertTrue("ValueCollection should be empty after clear", values.isEmpty());
        assertTrue("MapAdapter should be empty after clearing ValueCollection", this.map.isEmpty());
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly identifies whether all values from another collection are present in the ValueCollection or not.
     *   The rationale is to confirm that the ValueCollection can accurately determine if it contains all specified values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with the same values as the populated MapAdapter.</li>
     *   <li>Check that the containsAll method returns true when comparing the ValueCollection to the values of the other MapAdapter.</li>
     *   <li>Remove some values from the other MapAdapter and check that containsAll still returns true for the remaining values.</li>
     *   <li>Create another empty MapAdapter and check that containsAll on this ValueCollection returns true when comparing to its values.</li>
     *   <li>Create another MapAdapter with different values and check that containsAll returns false.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The containsAll method should return true when comparing to another collection with the same values.</li>
     *   <li>The containsAll method should still return true after removing some values from the other collection.</li>
     *   <li>The containsAll method should return true when comparing to an empty collection.</li>
     *   <li>The containsAll method should return false when comparing to a collection with different values.</li>
     * </ul>
     */
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
        anotherPopulatedMap.put("testKeyMAP_INITIAL_CAPACITY", "testValueMAP_INITIAL_CAPACITY");
        assertFalse("ValueCollection should not contain all values from a completely different map", this.map.values().containsAll(anotherPopulatedMap.values()));
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated ValueCollection behaves correctly when there are repeated values.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly identifies whether all values from another collection are present in the ValueCollection, even when there are repeated values.
     *   The rationale is to confirm that the ValueCollection can accurately determine if it contains all specified values, including those that may occur multiple times.
     * <p>
     * Test Description:
     * <ul>
     *  <li>Create another MapAdapter with the same values as the populated MapAdapter.</li>
     *  <li>Add a new entry to the other MapAdapter with a repeated value.</li>
     *  <li>Check that the containsAll method returns true when comparing the ValueCollection to the other MapAdapter's ValueCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new entry with a repeated value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The containsAll method should return true when comparing to another collection that has the same values but with a different frequency of occurrence.</li>
     * </ul>
     */
    @Test
    public void testContainsAllOnPopulatedValueCollectionWithRepeatedValues() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.put("testKeyMAP_INITIAL_CAPACITY", "testValue50");
        assertTrue("ValueCollection should contain all values of the other ValueCollection even if they are repeated",
                   this.map.values().containsAll(anotherMap.values()));
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated ValueCollection throws a NullPointerException when called with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles a null input.
     *   The rationale is to confirm that the ValueCollection does not accept null as a valid collection for checking containment.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the MapAdapter's ValueCollection with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling containsAll on a ValueCollection with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedValueCollectionWithNull() {
        this.map.values().containsAll(null);
    }

    /**
     * Summary:
     * Verifies that the containsAll method on a populated ValueCollection throws a NullPointerException when called with another ValueCollection containing null values.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles a collection with null values.
     *   The rationale is to confirm that the ValueCollection does not accept null values in the collection for checking containment.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the containsAll method on the MapAdapter's ValueCollection with this collection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling containsAll on a ValueCollection with another ValueCollection containing null values.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnPopulatedValueCollectionWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.values().containsAll(anotherMap.values());
    }

    /**
     * Summary:
     * Verifies that the hashCode method on a populated ValueCollection returns the expected value.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method correctly computes the hash code based on the values in the ValueCollection.
     *   The rationale is to confirm that the hashCode is consistent with the sum of the hash codes of individual values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Iterate through the values in the populated MapAdapter.</li>
     *   <li>Compute the expected hash code by summing the hash codes of each value.</li>
     *   <li>Check that the hashCode method on the ValueCollection returns this expected value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hashCode should match the expected value based on the sum of individual value hash codes.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the isEmpty method on a populated ValueCollection returns false.
     * <p>
     * Test Case Design:
     *   This test ensures that the isEmpty method correctly identifies that the ValueCollection is not empty.
     *   The rationale is to confirm that the ValueCollection can accurately determine its non-empty state.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the isEmpty method on the populated ValueCollection returns false.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The isEmpty method should return false for a populated ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnPopulatedValueCollection() {
        assertFalse("ValueCollection should not be empty", this.map.values().isEmpty());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated ValueCollection behaves as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly removes specified values from the ValueCollection.
     *   The rationale is to confirm that the ValueCollection can accurately modify its contents.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with some of the values from the populated MapAdapter.</li>
     *   <li>Call the removeAll method on the ValueCollection with this collection.</li>
     *   <li>Check that the ValueCollection and MapAdapter have been modified accordingly.</li>
     *   <li>Call removeAll again with the same collection and check that it does not change the ValueCollection.</li>
     *   <li>Add the remaining values to the other MapAdapter and call removeAll again, checking that the ValueCollection is empty afterwards.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The removeAll method should modify the ValueCollection and MapAdapter by removing specified values.</li>
     *   <li>The size of the MapAdapter should decrease accordingly after removing values.</li>
     *   <li>Calling removeAll again with the same collection should not change the ValueCollection.</li>
     *   <li>After removing all values, the ValueCollection and the MapAdapter should be empty.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the removeAll method on a populated ValueCollection removes all repeated values.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly removes all instances of a repeated value from the ValueCollection.
     *   The rationale is to confirm that the ValueCollection can accurately handle repeated values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Add a repeated value to the MapAdapter.</li>
     *   <li>Create another MapAdapter with the same repeated value.</li>
     *   <li>Call the removeAll method on the ValueCollection with the other MapAdapter's ValueCollection.</li>
     *   <li>Check that the ValueCollection no longer contains the repeated value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter does not contain the entries with value "testValue50".
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The removeAll method should remove all instances of the repeated value from the ValueCollection.</li>
     *   <li>The ValueCollection should not contain the repeated value after removal.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnPopulatedValueCollectionWithRepeatedValues() {
        this.map.put("testKeyMAP_INITIAL_CAPACITY", "testValue50");
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey0", "testValue50");
        this.map.values().removeAll(anotherMap.values());
        assertFalse("remove all should remove all the repeated values in the ValueCollection",
                    this.map.values().contains("testValue50"));
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated ValueCollection with an empty ValueCollection does not change the ValueCollection.
     * <p>
     * Test Case Design:
     *   This test ensures that calling removeAll with an empty ValueCollection does not modify the original ValueCollection.
     *   The rationale is to confirm that the ValueCollection remains unchanged when attempting to remove from an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the removeAll method on the populated ValueCollection with the empty MapAdapter's ValueCollection.</li>
     *   <li>Check that the original ValueCollection and MapAdapter remain unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should not change when removing from an empty ValueCollection.</li>
     *   <li>The MapAdapter should remain unchanged after attempting to removeAll from an empty ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnPopulatedValueCollectionWithAnEmptyValueCollection() {
        HMap anotherEmptyMap = new MapAdapter();
        assertFalse("ValueCollection should not change when removing from an empty ValueCollection", this.map.values().removeAll(anotherEmptyMap.values()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from an empty ValueCollection", MAP_INITIAL_CAPACITY, this.map.size());
    }
    
    /**
     * Summary:
     * Verifies that the removeAll method on a populated ValueCollection throws a ClassCastException when called with an EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method does not accept an EntrySet as a valid collection for removal.
     *   The rationale is to confirm that the ValueCollection does not allow removal of entries from an incompatible collection type.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the MapAdapter's ValueCollection with its EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A ClassCastException should be thrown when calling removeAll on a ValueCollection with an EntrySet.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveAllOnPopulatedValueCollectionWithAnEntrySet() {
        this.map.values().removeAll(this.map.entrySet());
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated ValueCollection throws a NullPointerException when called with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles a null input.
     *   The rationale is to confirm that the ValueCollection does not accept null as a valid collection for removal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the MapAdapter's ValueCollection with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling removeAll on a ValueCollection with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnPopulatedValueCollectionWithNull() {
        this.map.values().removeAll(null);
    }

    /**
     * Summary:
     * Verifies that the removeAll method on a populated ValueCollection with another ValueCollection containing null values behaves as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles a collection with null values.
     *   The rationale is to confirm that the ValueCollection can accurately modify its contents when removing null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry that has a null value.</li>
     *   <li>Call the removeAll method on the MapAdapter's ValueCollection with the DummyTestMap's ValueCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should not change when removing from another ValueCollection with null values.</li>
     *   <li>The MapAdapter should remain unchanged after attempting to removeAll from another ValueCollection with null values.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnPopulatedValueCollectionWithAnotherValueCollectionContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("ValueCollection should not change when removing from another ValueCollection with null values",
                    this.map.values().removeAll(anotherMap.values()));
        assertEquals("MapAdapter should remain unchanged after attempting to remove from another ValueCollection with null values", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated ValueCollection behaves as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly retains specified values in the ValueCollection.
     *   The rationale is to confirm that the ValueCollection can accurately modify its contents by retaining only specified values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with some of the values from the populated MapAdapter.</li>
     *   <li>Call the retainAll method on the ValueCollection with the other MapAdapter's ValueCollection.</li>
     *   <li>Check that the ValueCollection and MapAdapter have been modified accordingly.</li>
     *   <li>Call retainAll again with the same collection and check that it does not change the ValueCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter retains only specified values.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should modify the ValueCollection and MapAdapter by retaining specified values.</li>
     *   <li>After retaining all specified values, only those values should remain in the ValueCollection and MapAdapter.</li>
     *   <li>The size of the MapAdapter should decrease accordingly after retaining values.</li>
     *   <li>Calling retainAll again with the same collection should not change the ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedValueCollection() {
        HMap anotherMap = new MapAdapter();
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
        assertFalse("Calling retainAll with same ValueCollection should not change the ValueCollection",
                    this.map.values().retainAll(anotherMap.values()));
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated ValueCollection with an empty ValueCollection empties the ValueCollection.
     * <p>
     * Test Case Design:
     *   This test ensures that calling retainAll with an empty ValueCollection empties the original ValueCollection, no values are retained.
     *   The rationale is to confirm that the ValueCollection changes as expected when retaining from an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the retainAll method on the populated ValueCollection with the empty MapAdapter's ValueCollection.</li>
     *   <li>Check that retainAll returns true, indicating the ValueCollection was modified.</li>
     *   <li>Check that the original ValueCollection and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should be modified when retaining from an empty ValueCollection.</li>
     *   <li>The ValueCollection should be empty after calling retainAll with an empty ValueCollection.</li>
     *   <li>The MapAdapter should also be empty after retaining from an empty ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedValueCollectionWithAnEmptyValueCollection() {
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("ValueCollection should be modified when retaining from an empty ValueCollection",
                   this.map.values().retainAll(anotherEmptyMap.values()));
        assertTrue("ValueCollection should be empty after retaining from an empty ValueCollection", this.map.values().isEmpty());
        assertTrue("MapAdapter should be empty after retaining from an empty ValueCollection", this.map.isEmpty());
    }   

    /**
     * Summary:
     * Verifies that the retainAll method on a populated ValueCollection throws a ClassCastException when called with an EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method does not accept an EntrySet as a valid collection for retaining.
     *   The rationale is to confirm that the ValueCollection does not allow retaining entries from an incompatible collection type.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the MapAdapter's ValueCollection with its EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A ClassCastException should be thrown when calling retainAll on a ValueCollection with an EntrySet.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testRetainAllOnPopulatedValueCollectionWithAnEntrySet() {
        this.map.values().retainAll(this.map.entrySet());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated ValueCollection throws a NullPointerException when called with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method does not accept null as a valid collection for retaining.
     *   The rationale is to confirm that the ValueCollection does not allow retaining from a null collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the MapAdapter's ValueCollection with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling retainAll on a ValueCollection with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllOnPopulatedValueCollectionWithNull() {
        this.map.values().retainAll(null);
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated ValueCollection with another ValueCollection containing null values behaves as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method can handle another ValueCollection with null values.
     *   The rationale is to confirm that the ValueCollection can retain null values from another collection.
     * <p>
     * Test Description:
     * <ul>
     * <li>Create a DummyTestMap with an entry that has a null value.</li>
     * <li>Call the retainAll method on the MapAdapter's ValueCollection with the DummyTestMap's ValueCollection.</li>
     * <li>Check that both the ValueCollection and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should be modified when retaining from another ValueCollection with null values.</li>
     *   <li>The ValueCollection should be empty after retaining from another ValueCollection with null values.</li>
     *   <li>The MapAdapter should also be empty after retaining from another ValueCollection with null values.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedValueCollectionWithAnotherValueCollectionContainingNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertTrue("ValueCollection should change when retaining from another ValueCollection with null values",
                   this.map.values().retainAll(anotherMap.values()));
        assertTrue("MapAdapter should be empty after retaining from another ValueCollection with null values", this.map.isEmpty());
        assertTrue("KeySet should be empty after retaining from another KeySet with null keys", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     * Verifies that the retainAll method on a populated ValueCollection retains all repeated values.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly retains all instances of a repeated value in the ValueCollection.
     *   The rationale is to confirm that the ValueCollection can accurately handle repeated values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Add a repeated value to the MapAdapter.</li>
     *   <li>Create another MapAdapter with an entry that has the repeated value.</li>
     *   <li>Call the retainAll method on the ValueCollection with the other MapAdapter's ValueCollection.</li>
     *   <li>Check that the ValueCollection still contains only all the instances of the repeated value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains only the two entries with the repeated value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The retainAll method should retain all instances of the repeated value in the ValueCollection.</li>
     *   <li>The ValueCollection should contain only all the instances of the repeated value after retaining.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnPopulatedValueCollectionWithRepeatedValues() {
        this.map.put("testKeyMAP_INITIAL_CAPACITY", "testValue50");
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey0", "testValue50");
        assertTrue("ValueCollection should retain all repeated values", this.map.values().retainAll(anotherMap.values()));
        assertEquals("ValueCollection should contain all the instances of the repeated value", 2, this.map.values().size());
    }

    /**
     * Summary:
     * Verifies that the size method on a populated ValueCollection returns the expected size.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method correctly returns the number of values in the ValueCollection.
     *   The rationale is to confirm that the ValueCollection accurately reports its size.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the size method on the populated ValueCollection returns the expected size.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size method should return the number of values in the ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testSizeOnPopulatedValueCollection() {
        assertEquals("Size of ValueCollection should match the size of the MapAdapter", MAP_INITIAL_CAPACITY, this.map.values().size());
    }

    /**
     * Summary:
     * Verifies that the size method on a populated ValueCollection with repeated values counts all instances.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method correctly counts repeated values in the ValueCollection.
     *   The rationale is to confirm that the ValueCollection accurately reports its size, including repeated values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Add a repeated value to the MapAdapter.</li>
     *   <li>Check that the size method on the ValueCollection returns the expected size, including the repeated value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains an additional entry with a repeated value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size method should count all instances of repeated values in the ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testSizeOnPopulatedValueCollectionWithRepeatedValues() {
        this.map.put("testKeyMAP_INITIAL_CAPACITY", "testValue50");
        assertEquals("Size of ValueCollection should count repeated values", MAP_INITIAL_CAPACITY + 1, this.map.values().size());
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated ValueCollection returns an array containing all values.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly converts the ValueCollection to an array.
     *   The rationale is to confirm that the array contains all values in the ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection.</li>
     *   <li>Check that the returned array has the expected length and contains all values.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toArray method should return an array containing all values in the ValueCollection.</li>
     *   <li>The array length should match the size of the ValueCollection.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the array return by toArray method on a populated ValueCollection is free from changes in the Map after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the array returned by toArray is not affected by subsequent changes to the Map and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection.</li>
     *   <li>Modify the Map after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new values added to the Map.</li>
     *   <li>Modify the array returned by toArray and check that the Map does not contain the new value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the Map after the call.</li>
     *   <li>The MapAdapter should not contain any new values added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedValueCollectionIsFreeFromMap() {
        Object[] array = this.map.values().toArray();
        this.map.put("differentKey", "differentValue");
        assertEquals("array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("array should not contain the new value", "differentValue", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.values().iterator().next();

        assertFalse("MapAdapter should not contain the new value", this.map.values().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the array returned by toArray method on a populated ValueCollection with an array parameter of the right dimension is free from changes in the Map after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the array returned by toArray with an array parameter is not affected by subsequent changes to the Map and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with an array parameter of the right dimension.</li>
     *   <li>Modify the Map after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new values added to the Map.</li>
     *   <li>Modify the array returned by toArray and check that the Map does not contain the new value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray with an array parameter should not be affected by changes in the Map after the call.</li>
     *   <li>The MapAdapter should not contain any new values added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedValueCollectionWithArrayParameterOfRightDimensionIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY];
        array = this.map.values().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("Array should not contain the new value", "differentValue", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.values().iterator().next();

        assertFalse("MapAdapter should not contain the new value", this.map.values().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the array returned by toArray method on a populated ValueCollection with an underdimensioned rray is free from changes in the Map after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the array returned by toArray with an underdimensioned array is not affected by subsequent changes to the Map and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with an underdimensioned array.</li>
     *   <li>Modify the Map after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new values added to the Map.</li>
     *   <li>Modify the array returned by toArray and check that the Map does not contain the new value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the Map after the call.</li>
     *   <li>The MapAdapter should not contain any new values added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedValueCollectionWithUnderdimensionedArrayIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY/2];
        array = this.map.values().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("underdimensioned array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("underdimensioned array should not contain the new value", "differentValue", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.values().iterator().next();

        assertFalse("MapAdapter should not contain the new value", this.map.values().contains(array[50]));

    }
    
    /**
     * Summary:
     * Verifies that the array returned by toArray method on a populated ValueCollection with an overdimensioned array is free from changes in the Map after the call and vice-versa.
     * <p>
     * Test Case Design:
     *   This test ensures that the array returned by toArray with an overdimensioned array is not affected by subsequent changes to the Map and vice-versa.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter and vice-versa.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with an overdimensioned array.</li>
     *   <li>Modify the Map after calling toArray.</li>
     *   <li>Check that the array length remains unchanged and does not contain new values added to the Map.</li>
     *   <li>Modify the array returned by toArray and check that the Map does not contain the new value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the Map after the call.</li>
     *   <li>The MapAdapter should not contain any new values added to the array after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedValueCollectionWithOverdimensionedArrayIsFreeFromMap() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY + 50];
        array = this.map.values().toArray(array);
        this.map.put("differentKey", "differentValue");
        assertEquals("overdimensioned array should not be affected by changes in the map after toArray", MAP_INITIAL_CAPACITY + 50, array.length);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertNotEquals("overdimensioned array should not contain the new value", "differentValue", array[i]);
        }

        HMap anotherMap = new MapAdapter();
        anotherMap.put("anotherDifferentKey", "anotherDifferentValue");
        array[50] = anotherMap.keySet().iterator().next();

        assertFalse("MapAdapter should not contain the new key", this.map.keySet().contains(array[50]));
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated ValueCollection with an array parameter of the right dimension returns an array containing all values.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an array parameter correctly converts the ValueCollection to an array.
     *   The rationale is to confirm that the array contains all values in the ValueCollection and has the correct length.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with an array parameter of the right dimension.</li>
     *   <li>Check that the returned array has the expected length and contains all values.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toArray method should return an array containing all values in the ValueCollection.</li>
     *   <li>The array length should match the size of the ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnPopulatedValueCollectionWithArrayParameterOfRightDimension() {
        Object[] array = new Object[MAP_INITIAL_CAPACITY];
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated ValueCollection with an underdimensioned array returns an array containing all values.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an underdimensioned array correctly converts the ValueCollection to an array.
     *   The rationale is to confirm that the array contains all values in the ValueCollection and has the correct length.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with an underdimensioned array.</li>
     *   <li>Check that the returned array has the expected length and contains all values.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toArray method should return an array containing all values in the ValueCollection.</li>
     *   <li>The array length should match the size of the ValueCollection.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated ValueCollection with an overdimensioned array returns an array containing all values.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method with an overdimensioned array correctly converts the ValueCollection to an array.
     *   The rationale is to confirm that the array contains all values in the ValueCollection and has the correct length.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with an overdimensioned array.</li>
     *   <li>Check that the returned array has the original length and contains all values.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toArray method should return an array containing all values in the ValueCollection.</li>
     *   <li>The array length should be the same as the original array length.</li>
     *   <li>The first elements after the ValueCollection in the array should be null.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toArray method on a populated ValueCollection with a null array throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not accept null as a parameter.
     *   The rationale is to confirm that the method correctly handles null input.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the populated ValueCollection with a null array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayOnPopulatedValueCollectionWithNull() {
        this.map.values().toArray(null);
    }

    /**
     * Summary:
     * Verifies that the toArray method on a populated ValueCollection with an incompatible array throws an ArrayStoreException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles an incompatible array type (i.e., an Integer array and String values).
     *   The rationale is to confirm that storing values in an incompatible array throws an ArrayStoreException (i.e., an attempt to store a String in an Integer array).
     * <p>
     * Test Description:
     * <ul>
     *   <li>Instantiate an Integer array of the same size as the MapAdapter.</li>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Call the toArray method on the ValueCollection with the Integer array.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>An ArrayStoreException should be thrown when attempting to store a String value in an Integer array.</li>
     * </ul>
     */
    @Test(expected = ArrayStoreException.class)
    public void testToArrayOnPopulatedValueCollectionWithIncompatibleArray() {
        Object[] array = new Integer[MAP_INITIAL_CAPACITY];
        this.map.values().toArray(array);
    }

    /**
     * Summary:
     * Verifies that the toString method on a populated ValueCollection returns a string representation of all values.
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method correctly formats the string representation of the ValueCollection.
     *   The rationale is to confirm that the string representation matches the expected format.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toString method on the populated ValueCollection.</li>
     *   <li>Check that the returned string matches the expected format.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string representation of all values in the ValueCollection.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the toString method on a populated ValueCollection with repeated values returns a string representation of all values, including repeated ones.
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method correctly formats the string representation of the ValueCollection, including repeated values.
     *   The rationale is to confirm that the string representation matches the expected format, including repeated values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Add a repeated value to the MapAdapter.</li>
     *   <li>Call the toString method on the populated ValueCollection.</li>
     *   <li>Check that the returned string matches the expected format, including the repeated value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains an additional entry with a repeated value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string representation of all values in the ValueCollection, including repeated values.</li>
     *   <li>The string representation should match the expected format, including repeated values.</li>
     * </ul>
     */
    @Test
    public void testToStringOnPopulatedValueCollectionWithRepeatedValues() {
        this.map.put("testKeyMAP_INITIAL_CAPACITY", "testValue50");
        String expected = "[";
        HIterator iterator = this.map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            expected += value.toString() + (iterator.hasNext() ? ", " : "");
        }
        expected += "]";
        assertEquals("ToString on a populated ValueCollection with repeated values should match the expected format", expected, this.map.values().toString());
    }

    /**
     * Summary:
     * Verifies that the ValueCollection on a populated MapAdapter is backed by the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the ValueCollection reflects changes made to the MapAdapter and vice-versa.
     *   The rationale is to confirm that the ValueCollection is backed by the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the ValueCollection has the same size as the MapAdapter.</li>
     *   <li>Modify the MapAdapter and check that the ValueCollection reflects the changes.</li>
     *   <li>Remove a value from the ValueCollection and check that the MapAdapter reflects this change.</li>
     *   <li>Clear the ValueCollection and check that both the ValueCollection and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should have the same size as the MapAdapter.</li>
     *   <li>The ValueCollection should reflect changes made to the MapAdapter.</li>
     *   <li>The MapAdapter should reflect changes made to the ValueCollection.</li>
     *   <li>Both the ValueCollection and MapAdapter should be empty after clearing.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection returns true when comparing with another ValueCollection containing the same values.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly compares ValueCollections.
     *   The rationale is to confirm that the equals method returns true when comparing with another ValueCollection containing the same values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Instantiate another MapAdapter with the same entries as the original MapAdapter.</li>
     *   <li>Get the ValueCollection from both MapAdapters.</li>
     *   <li>Check that the equals method returns true when comparing the two ValueCollections.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true when comparing with another ValueCollection containing the same entries.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollection() {
        HMap anotherMap = new MapAdapter(this.map);
        HCollection values = this.map.values();
        HCollection anotherValueCollection = anotherMap.values();
        assertTrue("ValueCollection should be equal to another ValueCollection with the same entries", values.equals(anotherValueCollection));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection returns false when comparing with a different ValueCollection.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies differences between ValueCollections.
     *   The rationale is to confirm that the equals method returns false when comparing with a different ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Instantiate another MapAdapter with the same entries as the original MapAdapter.</li>
     *   <li>Remove an entry from the second MapAdapter.</li>
     *   <li>Get the ValueCollection from both MapAdapters.</li>
     *   <li>Check that the equals method returns false when comparing the two ValueCollections.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false when comparing with a different ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollectionWithDifferentValueCollection() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.remove("testKey0");
        assertFalse("ValueCollection should not be equal to a different ValueCollection", this.map.values().equals(anotherMap.values()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection returns false when comparing with a ValueCollection the same values but with a different frequency.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies differences in value frequency between ValueCollections.
     *   The rationale is to confirm that the equals method returns false when comparing with a ValueCollection that has the same values but with a different frequency.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Instantiate another MapAdapter with the same entries as the original MapAdapter.</li>
     *   <li>Add a repeated value to the second MapAdapter.</li>
     *   <li>Get the ValueCollection from both MapAdapters.</li>
     *   <li>Check that the equals method returns false when comparing the two ValueCollections.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains an additional entry with a repeated value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false when comparing with a ValueCollection that has the same values but with a different frequency.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollectionWithAValueCollectionWithRepeatedValues() {
        HMap anotherMap = new MapAdapter(this.map);
        anotherMap.put("testKeyMAP_INITIAL_CAPACITY", "testValue50");
        assertFalse("ValueCollection should not be equal to a ValueCollection with repeated values", this.map.values().equals(anotherMap.values()));
    }
    
    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection returns false when comparing with an HSet object.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies differences between a ValueCollection and an HSet object.
     *   The rationale is to confirm that the equals method returns false when comparing a ValueCollection with an HSet object.     
     *<p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Compare the ValueCollection with an HSet object.</li>
     *   <li>Check that the equals method returns false when comparing the ValueCollection with the HSet object.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false when comparing with an HSet object.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollectionWithAnHSet() {
        assertFalse("ValueCollection should not be equal to an HSet object", this.map.values().equals(this.map.keySet()));
    }
    
    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection returns false when comparing with null.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies that a ValueCollection is not equal to null.
     *   The rationale is to confirm that the equals method returns false when comparing a ValueCollection with null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Compare the ValueCollection with null.</li>
     *   <li>Check that the equals method returns false when comparing the ValueCollection with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return false when comparing with null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollectionWithNull() {
        assertFalse("ValueCollection should not be equal to null", this.map.values().equals(null));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection returns is reflexive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies that a ValueCollection is equal to itself.
     *   The rationale is to confirm that the equals method is reflexive.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Compare the ValueCollection with itself.</li>
     *   <li>Check that the equals method returns true when comparing the ValueCollection with itself.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true when comparing with itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollectionWithItself() {
        assertTrue("A populated ValueCollection should be equal to itself", this.map.values().equals(this.map.values()));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection is symmetric.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies that two ValueCollections with the same entries are equal in both directions.
     *   The rationale is to confirm that the equals method is symmetric.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Instantiate another MapAdapter with the same entries as the original MapAdapter.</li>
     *   <li>Get the ValueCollection from the second MapAdapter.</li>
     *   <li>Check that the equals method returns true when comparing the two ValueCollections in both directions.</li>
     * </ul>
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * </p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true when comparing the two ValueCollections in both directions.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedValueCollectionIsSymmetric() {
        HCollection values = this.map.values();
        HCollection anotherValues = new MapAdapter(this.map).values();
        assertTrue("ValueCollection should be equal to another ValueCollection with the same entries and vice-versa",
            values.equals(anotherValues) && anotherValues.equals(values));
    }

    /**
     * Summary:
     * Verifies that the equals method on a populated ValueCollection is transitive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies that if ValueCollection A equals ValueCollection B and MapAdapter B equals ValueCollection C, then ValueCollection A should equal MapAdapter C.
     *   The rationale is to confirm that the equals method is transitive.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Instantiate another MapAdapter with the same entries as the original MapAdapter.</li>
     *   <li>Get the ValueCollection from the second MapAdapter.</li>
     *   <li>Instantiate another MapAdapter with the same entries as the second MapAdapter.</li>
     *   <li>Get the ValueCollection from the third MapAdapter.</li>
     *   <li>Check that the equals method returns true when comparing the three ValueCollections.</li>
     * </ul>
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * </p>
     * Expected Results:
     * <ul>
     *   <li>The equals method should return true when comparing the three ValueCollections.</li>
     * </ul>
     */ 
    @Test
    public void testEqualsOnPopulatedValueCollectionIsTransitive() {
        HCollection values = this.map.values();
        HCollection anotherValues = new MapAdapter(this.map).values();
        HCollection anotherAnotherValues = new MapAdapter(this.map).values();
        assertTrue("If ValueCollection A equals ValueCollection B and MapAdapter B equals ValueCollection C, then ValueCollection A should equal MapAdapter C",
            values.equals(anotherValues) && anotherValues.equals(anotherAnotherValues) && values.equals(anotherAnotherValues));
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated ValueCollection returns true when the value is present in the ValueCollection, false otherwise.
     * <p>
     * Test Case Design:
     *  This test ensures that the contains method correctly identifies whether a value is present in the ValueCollection or not.
     *  The rationale is to confirm that the contains method returns true for values present in the ValueCollection and false for values not present.
     * </p>
     * Test Description:
     * <ul>
     *   <li>Get a value from the populated MapAdapter.</li>
     *   <li>Check that the ValueCollection contains the value.</li>
     *   <li>Instantiate another MapAdapter with an entry that has a value not present in the MapAdapter.</li>
     *   <li>Check that the ValueCollection does not contain a value from the second MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The contains method should return true for a value present in the ValueCollection.</li>
     *   <li>The contains method should return false for a value not present in the ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testContainsOnPopulatedValueCollection() {
        Object value = this.map.values().iterator().next();
        assertTrue("ValueCollection should contain the value", this.map.values().contains(value));
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "differentValue");
        assertFalse("ValueCollection should not contain a value that is not present",
                    this.map.values().contains(anotherMap.values().iterator().next()));
    }

    /**
     * Summary:
     * Verifies that the contains method on a populated ValueCollection returns false when checking for a null value.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method does not accept null as a parameter.
     *   The rationale is to confirm that the method correctly handles null input.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the populated ValueCollection with a null value.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown when calling contains with a null value.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnPopulatedValueCollectionWithNull() {
        this.map.values().contains(null);
    }

    /**
     * Summary:
     * Verifies that the remove method on a populated ValueCollection removes a value from the ValueCollection and the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly removes a value from the ValueCollection and the MapAdapter.
     *   The rationale is to confirm that the size of the ValueCollection decreases by 1 after removing a value, and that the MapAdapter no longer contains the removed value.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get a value from the populated ValueCollection.</li>
     *   <li>Remove the value from the ValueCollection.</li>
     *   <li>Check that the size of the ValueCollection has decreased by 1.</li>
     *   <li>Check that the removed value is no longer present in both the ValueCollection and MapAdapter.</li>
     *   <li>Remove all values from the ValueCollection and check that both the ValueCollection and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The remove method should decrease the size of the ValueCollection by 1.</li>
     *   <li>The removed value should not be present in both the ValueCollection and MapAdapter.</li>
     *   <li>The ValueCollection and the MapAdapter should both be empty after removing all values.</li>
     * </ul>
     */
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

    /**
     * Summary:
     * Verifies that the remove method on a populated ValueCollection with repeated values removes only one instance of the value.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly removes only one instance of a value from the ValueCollection and the MapAdapter.
     *   The rationale is to confirm that the size of the ValueCollection remains unchanged after removing a value that has multiple instances, and that the MapAdapter still contains an instance of the removed value.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Add a repeated value to the MapAdapter.</li>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Check that the ValueCollection contains the value to be removed.</li>
     *   <li>Remove one instance of the value from the ValueCollection.</li>
     *   <li>Check that the size of the ValueCollection remains unchanged and that it still contains an instance of the removed value.</li>
     *   <li>Remove another instance of the value from the ValueCollection and check that its size decreases by 1.</li>
     *   <li>Check that both the ValueCollection and MapAdapter no longer contain any instances of the removed value after removing both instances.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains an additional entry with a repeated value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should contain the value to be removed.</li>
     *   <li>The ValueCollection should have the original size after removing a value that was duplicated.</li>
     *   <li>The ValueCollection should still contain an instance of the removed value after the first removal.</li>
     *   <li>The ValueCollection should decrease in size by 1 after removing the second instance of the value.</li>
     *   <li>The ValueCollection should not contain the removed value after removing both instances.</li>
     *   <li>The MapAdapter should not contain the removed value after removing both instances.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedValueCollectionWithRepeatedValues() {
        this.map.put("tesKeyMAP_INITIAL_CAPACITY", "testValue50");
        HCollection values = this.map.values();
        Object valueToBeRemoved = "testValue50";
        assertTrue("ValueCollection should contain the value to be removed", values.contains(valueToBeRemoved));
        values.remove(valueToBeRemoved);
        assertEquals("ValueCollection should have the original size after removing a value from ValueCollection",
                     MAP_INITIAL_CAPACITY, values.size());
        assertTrue("ValueCollection should still contain an instance of the removed value", values.contains(valueToBeRemoved));
        assertTrue("MapAdapter should still contain an instance of the removed value", this.map.containsValue(valueToBeRemoved));
        values.remove(valueToBeRemoved);
        assertEquals("ValueCollection should have decreased size by 1 after removing the second instance of the value",
                     MAP_INITIAL_CAPACITY - 1, values.size());
        assertFalse("ValueCollection should not contain the removed value after removing both instances", values.contains(valueToBeRemoved));
        assertFalse("MapAdapter should not contain the removed value after removing both instances", this.map.containsValue(valueToBeRemoved));
    }

    /**
     * Summary:
     * Verifies that the remove method on a populated ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method does not accept null as a parameter.
     *   The rationale is to confirm that the method correctly handles null input.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the populated ValueCollection with null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException should be thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedValueCollectionWithNull() {
        this.map.values().remove(null);
    }

    /**
     * Summary:
     * Verifies that the iterator on a populated ValueCollection is not null.
     * <p>
     * Test Case Design:
     *   This test ensures that the iterator on a populated ValueCollection is not null.
     *   The rationale is to confirm that the ValueCollection provides an iterator for traversing its values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the ValueCollection from the populated MapAdapter.</li>
     *   <li>Check that the iterator on the ValueCollection is not null.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The iterator on the ValueCollection should not be null.</li>
     * </ul>
     */
    @Test
    public void testIteratorOnPopulatedValueCollection() {
        assertNotNull("Iterator on ValueCollection should not be null", this.map.values().iterator());
    }
}