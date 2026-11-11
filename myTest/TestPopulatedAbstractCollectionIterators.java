// Diego Chiesurin

package myTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import myAdapter.*;
import myExceptions.IllegalStateException;
import java.util.NoSuchElementException;

/**
 * This test suite verifies the correct behavior of the iterators {@link myAdapter.MapAdapter.EntrySetIterator}, {@link myAdapter.MapAdapter.KeySetIterator} and {@link myAdapter.MapAdapter.ValueCollectionIterator} returned by the collection views of a populated {@link myAdapter.MapAdapter}.
 * It covers all methods and scenarios relevant to iterators of a populated {@link myAdapter.MapAdapter.AbstractCollection} including iteration, removal, and exception handling.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Iterator behavior ({@link myAdapter.MapAdapter.EntrySetIterator}, {@link myAdapter.MapAdapter.KeySetIterator}, {@link myAdapter.MapAdapter.ValueCollectionIterator})</li>
 *   <li>Populated state iteration</li>
 *   <li>Iterator is backed by {@link myAdapter.MapAdapter}</li>
 *   <li>Mutation and removal operations</li>
 *   <li>Exception handling ({@code NoSuchElementException}, {@link myExceptions.IllegalStateException})</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that all iterators returned by the {@link myAdapter.MapAdapter.AbstractCollection} views of a populated {@link myAdapter.MapAdapter} behave as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that iterators correctly traverse all elements in entry sets, key sets, and value collections.</li>
 *   <li>Check that the {@link myAdapter.HIterator#hasNext()} and {@link myAdapter.HIterator#next()} methods behave as expected, including at the end of iteration.</li>
 *   <li>Ensure that the {@link myAdapter.HIterator#remove()} method removes the correct element and throws exceptions when called in invalid states.</li>
 *   <li>Test exception handling for calling {@link myAdapter.HIterator#next()} when no elements remain and {@link myAdapter.HIterator#remove()} before {@link myAdapter.HIterator#next()} or after a removal.</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of {@link myAdapter.HIterator} of the populated {@link myAdapter.MapAdapter} views, ensuring correctness, robustness and compliance with the {@link myAdapter.HIterator} contract.
 */
public class TestPopulatedAbstractCollectionIterators {

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
    public TestPopulatedAbstractCollectionIterators() {
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
        for (int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    /**
     * Cleans up the test environment after each test by setting the map to null.
     * This ensures that each test starts with a fresh instance of the map.
     */
    @After
    public void tearDown() {
        this.map = null;
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- EntrySetIterator -------------------------*/
    /*--------------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the hasNext method on an EntrySetIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the hasNext method behaves correctly on an EntrySetIterator of a populated EntrySet.
     *   The rationale is to confirm that the method returns true when there are more elements to iterate on, false otherwise.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the hasNext method returns true while there are more elements to iterate on.</li>
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
     *   <li>The hasNext method should return true while it has more elements to iterate on.</li>
     * </ul>
     */
    @Test
    public void testHasNextOnPopulatedEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        assertTrue("Iterator on a populated collection should have elements", iterator.hasNext());
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());    
    }

    /**
     * Summary:
     *   Verifies that the next method on an EntrySetIterator of a populated EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method returns a non-null entry and that the entry is contained in the EntrySet.
     *   The rationale is to confirm that the next method behaves correctly on an EntrySetIterator of a populated EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the next method returns a non-null entry while there are more elements to iterate on.</li>
     *   <li>Verify that all the entries returned by next are contained in the EntrySet.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
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
     *   <li>The next method should return a non-null entry while it has more elements to iterate on.</li>
     *   <li>The entry returned by next should be contained in the EntrySet.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
     * </ul>
     */
    @Test
    public void testNextOnPopulatedEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            assertNotNull("Next on EntrySetIterator should return a non-null entry", entry);
            assertTrue("EntrySet should contain the entry returned by next", map.entrySet().contains(entry));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on an EntrySetIterator throws NoSuchElementException when there are no more elements to iterate on.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method throws NoSuchElementException when called after all elements have been iterated.
     *   The rationale is to confirm that the next method behaves correctly when there are no more elements to iterate on.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the method on the EntrySetIterator repeatedly until NoSuchElementException is thrown.</li>
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
     *   <li>The next method should throw NoSuchElementException when called after all elements have been iterated.</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnPopulatedEntrySetIteratorWhenNoMoreElements() {
        HIterator iterator = map.entrySet().iterator();
        while (true) {
            iterator.next();
        }
    }

    /**
     * Summary:
     *   Verifies that the remove method on an EntrySetIterator of a populated EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method can be called after next and that it removes the current entry from the EntrySet.
     *   The rationale is to confirm that the remove method behaves correctly on an EntrySetIterator of a populated EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the remove method removes the current entry from the EntrySet after calling next.</li>
     *   <li>Verify that after removing all entries, the EntrySet is empty.</li>
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
     *   <li>The remove method should remove the current entry from the MapAdapter after calling next.</li>
     *   <li>The MapAdapter is empty after removing all entries.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            iterator.remove();
            assertFalse("Entry should be removed from the map after calling remove", map.entrySet().contains(entry));
        }
        assertTrue("Map should be empty after removing all entries", map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the remove method on an EntrySetIterator throws IllegalStateException when called before next.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called in invalid states.
     *   The rationale is to confirm that the remove method behaves correctly when called before next.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the EntrySetIterator before calling next.</li>
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
     *   <li>The remove method should throw IllegalStateException when called before next.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedEntrySetIteratorBeforeNext() {
        HIterator iterator = map.entrySet().iterator();
        iterator.remove();
    }  

    /**
     * Summary:
     *   Verifies that the remove method on an EntrySetIterator throws IllegalStateException when called after a removal.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called after a removal.
     *   The rationale is to confirm that the remove method behaves correctly when called after a removal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the EntrySetIterator after calling next and then remove.</li>
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
     *   <li>The remove method should throw IllegalStateException when called after a removal.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedEntrySetIteratorAfterRemove() {
        HIterator iterator = map.entrySet().iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    /*--------------------------------------------------------------------*/
    /*-------------------------- KeySetIterator --------------------------*/
    /*--------------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the hasNext method on a KeySetIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the hasNext method behaves correctly on a KeySetIterator of a populated KeySet.
     *   The rationale is to confirm that the method returns true when there are more elements to iterate on, false otherwise.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the hasNext method returns true while there are more elements to iterate on.</li>
     *   <li>Verify that after iterating through all elements, hasNext returns false.</li>
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
     *   <li>The hasNext method should return true while it has more elements to iterate on.</li>
     *   <li>Verify that after iterating through all elements, hasNext returns false.</li>
     * </ul>
     */
    @Test
    public void testHasNextOnPopulatedKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        assertTrue("Iterator on a populated collection should have elements", iterator.hasNext());
        while (iterator.hasNext()) {
            Object key = iterator.next();
            assertTrue("KeySet should contain the key returned by next", map.keySet().contains(key));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on a KeySetIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method returns a non-null key and that the key is contained in the KeySet.
     *   The rationale is to confirm that the next method behaves correctly on a KeySetIterator of a populated KeySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the next method returns a non-null key while there are more elements to iterate on.</li>
     *   <li>Verify that all keys returned by next are contained in the KeySet.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
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
     *   <li>The next method should return a non-null key while it has more elements to iterate on.</li>
     *   <li>The key returned by next should be contained in the KeySet.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
     * </ul>
     */
    @Test
    public void testNextOnPopulatedKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            assertNotNull("Next on KeySetIterator should return a non-null key", key);
            assertTrue("KeySet should contain the key returned by next", map.keySet().contains(key));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on a KeySetIterator throws NoSuchElementException when there are no more elements to iterate on.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method throws NoSuchElementException when called after all elements have been iterated.
     *   The rationale is to confirm that the next method behaves correctly when there are no more elements to iterate on.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the method on the KeySetIterator repeatedly until NoSuchElementException is thrown.</li>
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
     *   <li>The next method should throw NoSuchElementException when called after all elements have been iterated.</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnPopulatedKeySetIteratorWhenNoMoreElements() {
        HIterator iterator = map.keySet().iterator();
        while (true) {
            iterator.next();
        }
    }

    /**
     * Summary:
     *   Verifies that the remove method on a KeySetIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method can be called after next and that it removes the current key from the KeySet.
     *   The rationale is to confirm that the remove method behaves correctly on a KeySetIterator of a populated KeySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the KeySetIterator after calling next.</li>
     *   <li>Verify that after removing all keys, the KeySet is empty.</li>
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
     *   <li>The remove method should remove the key returned by next from the KeySet.</li>
     *   <li>The KeySet should be empty after removing all keys.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            iterator.remove();
            assertFalse("Key should be removed from the map after calling remove", map.keySet().contains(key));
        }
        assertTrue("Map should be empty after removing all keys", map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the remove method on a KeySetIterator throws IllegalStateException when called before next.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called before next.
     *   The rationale is to confirm that the remove method behaves correctly when called before next.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the KeySetIterator before calling next.</li>
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
     *   <li>The remove method should throw IllegalStateException when called before next.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedKeySetIteratorBeforeNext() {
        HIterator iterator = map.keySet().iterator();
        iterator.remove();
    }

    /**
     * Summary:
     *   Verifies that the remove method on a KeySetIterator throws IllegalStateException when called after a removal.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called after a removal.
     *   The rationale is to confirm that the remove method behaves correctly when called after a removal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the KeySetIterator after calling next and then remove.</li>
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
     *   <li>The remove method should throw IllegalStateException when called after a removal.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedKeySetIteratorAfterRemove() {
        HIterator iterator = map.keySet().iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- ValueCollectionIterator ------------------*/
    /*--------------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the hasNext method on a ValueCollectionIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the hasNext method behaves correctly on a ValueCollectionIterator of a populated ValueCollection.
     *   The rationale is to confirm that the method returns true when there are more elements to iterate on, false otherwise.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the hasNext method returns true while there are more elements to iterate on.</li>
     *   <li>Verify that after iterating through all elements, hasNext returns false.</li>
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
     *   <li>The hasNext method should return true while it has more elements to iterate on.</li>
     *   <li>Verify that after iterating through all elements, hasNext returns false.</li>
     * </ul>
     */
    @Test
    public void testHasNextOnPopulatedValueCollectionIterator() {
        HIterator iterator = map.values().iterator();
        assertTrue("Iterator on a populated collection should have elements", iterator.hasNext());
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertTrue("ValueCollection should contain the value returned by next", map.values().contains(value));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on a ValueCollectionIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method returns a non-null value and that the value is contained in the ValueCollection.
     *   The rationale is to confirm that the next method behaves correctly on a ValueCollectionIterator of a populated ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the next method returns a non-null value while there are more elements to iterate on.</li>
     *   <li>Verify that all values returned by next are contained in the ValueCollection.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
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
     *   <li>The next method should return a non-null value while it has more elements to iterate on.</li>
     *   <li>The value returned by next should be contained in the ValueCollection.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
     * </ul>
     */
    @Test
    public void testNextOnPopulatedValueCollectionIterator() {
        HIterator iterator = map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertNotNull("Next on ValueCollectionIterator should return a non-null value", value);
            assertTrue("ValueCollection should contain the value returned by next", map.values().contains(value));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on a ValueCollectionIterator of a populated ValueCollection behaves correctly when there are repeated values.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method iterates through all values in the ValueCollection, including repeated values.
     *   The rationale is to confirm that the next method behaves correctly when there are repeated values in the collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Populate the ValueCollection with repeated values.</li>
     *   <li>Check that the next method returns a non-null value while there are more elements to iterate on.</li>
     *   <li>Verify that all values returned by next are contained in the ValueCollection, including repeated values.</li>
     *   <li> Check that after iterating through all elements, hasNext returns false.</li>
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
     *   <li>The next method should return a non-null value while it has more elements to iterate on.</li>
     *   <li>The value returned by next should be contained in the ValueCollection, including repeated values.</li>
     *   <li>Check that after iterating through all elements, hasNext returns false.</li>
     * </ul>
     */
    @Test
    public void testNextOnPopulatedValueCollectionIteratorWithRepeatedValues() {
        this.map.put("testKey100", "testValue0");
        HIterator iterator = map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            assertNotNull("Next on ValueCollectionIterator should return a non-null value", value);
            assertTrue("ValueCollection should contain the value returned by next", map.values().contains(value));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on a ValueCollectionIterator throws NoSuchElementException when there are no more elements to iterate on.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method throws NoSuchElementException when called after all elements have been iterated.
     *   The rationale is to confirm that the next method behaves correctly when there are no more elements to iterate on.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the method on the ValueCollectionIterator repeatedly until NoSuchElementException is thrown.</li>
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
     *   <li>The next method should throw NoSuchElementException when called after all elements have been iterated.</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnPopulatedValueCollectionIteratorWhenNoMoreElements() {
        HIterator iterator = map.values().iterator();
        while (true) {
            iterator.next();
        }
    }

    /**
     * Summary:
     *   Verifies that the remove method on a ValueCollectionIterator behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method can be called after next and that it removes the current value from the ValueCollection.
     *   The rationale is to confirm that the remove method behaves correctly on a ValueCollectionIterator of a populated ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the ValueCollectionIterator after calling next.</li>
     *   <li>Verify that after removing all values, the ValueCollection is empty.</li>
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
     *   <li>The remove method should remove the value returned by next from the ValueCollection.</li>
     *   <li>The ValueCollection should be empty after removing all values.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedValueCollectionIterator() {
        HIterator iterator = map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            iterator.remove();
            assertFalse("Value should be removed from the map after calling remove", map.values().contains(value));
        }
        assertTrue("Map should be empty after removing all values", map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the remove method on a ValueCollectionIterator of a populated ValueCollection behaves correctly when there are repeated values.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method removes only one instance of a repeated value.
     *   The rationale is to confirm that the remove method behaves correctly on a ValueCollectionIterator of a populated ValueCollection with repeated values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Populate the ValueCollection with repeated values.</li>
     *   <li>Call remove when the iterator points to a repeated value.</li>
     *   <li>Verify that the ValueCollection still contains the repeated value after removing one instance.</li>
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
     *   <li>The remove method should remove only one instance of the repeated value.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedValueCollectionIteratorWithRepeatedValues() {
        this.map.put("testKey100", "testValue0");
        HIterator iterator = map.values().iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            if ("testValue0".equals(value)) {
                iterator.remove();
                break;
            }
        }
        assertTrue("MapAdapter should still contain an entry with the repeated value", map.containsValue("testValue0"));
    }

    /**
     * Summary:
     *   Verifies that the remove method on a ValueCollectionIterator throws IllegalStateException when called before next.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called before next.
     *   The rationale is to confirm that the remove method behaves correctly when called before next.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the ValueCollectionIterator before calling next.</li>
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
     *   <li>The remove method should throw IllegalStateException when called before next.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedValueCollectionIteratorBeforeNext() {
        HIterator iterator = map.values().iterator();
        iterator.remove();
    }

    /**
     * Summary:
     *   Verifies that the remove method on a ValueCollectionIterator throws IllegalStateException when called after a removal.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called after a removal.
     *   The rationale is to confirm that the remove method behaves correctly when called after a removal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the ValueCollectionIterator after calling next and then remove.</li>
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
     *   <li>The remove method should throw IllegalStateException when called after a removal.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedValueCollectionIteratorAfterRemove() {
        HIterator iterator = map.values().iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }
}