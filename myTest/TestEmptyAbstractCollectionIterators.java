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
 * Test suite for verifying the correct behavior of iterators {@link myAdapter.MapAdapter.EntrySetIterator},
 * {@link myAdapter.MapAdapter.KeySetIterator}, {@link myAdapter.MapAdapter.ValueCollectionIterator}
 * returned by the collection views of an empty {@link myAdapter.MapAdapter}. 
 * It covers all methods and scenarios relevant to iterators of an empty {@link myAdapter.MapAdapter.AbstractCollection} including iteration, removal, and exception handling.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Iterator behavior on empty collections</li>
 *   <li>Exception handling for {@link myAdapter.HIterator#next()} and {@link myAdapter.HIterator#remove()} on empty iterators</li>
 *   <li>Compliance with {@link myAdapter.HIterator} contract for {@link myAdapter.HIterator#hasNext()}, {@link myAdapter.HIterator#next()}, and {@link myAdapter.HIterator#remove()}</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that all iterator implementations
 * for empty collection views of {@link myAdapter.MapAdapter} behave as specified. The tests are organized to:
 * <ul>
 *   <li>Verify that {@link myAdapter.HIterator#hasNext()} returns false for empty iterators.</li>
 *   <li>Check that {@link myAdapter.HIterator#next()} throws {@link NoSuchElementException} when called on an empty iterator.</li>
 *   <li>Check that {@link myAdapter.HIterator#remove()} throws {@link myExceptions.IllegalStateException} when called before any call to {@link myAdapter.HIterator#next()}.</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of {@link myAdapter.HIterator} of the empty {@link myAdapter.MapAdapter} views, ensuring correctness, robustness and compliance with the {@link myAdapter.HIterator} contract.
 */
public class TestEmptyAbstractCollectionIterators {
    /**
     * The {@code MapAdapter} instance used for testing.
     */
    private HMap map;

    /**
     * Default constructor for the test class.
     */
    public TestEmptyAbstractCollectionIterators() {
        // intentionally left empty
    }

    /**
     * Sets up the test environment by initializing an empty {@code MapAdapter} instance.
     */
    @Before
    public void setUp() {
        this.map = new MapAdapter();
    }

    /**
     * Cleans up the test environment by nullifying the {@code MapAdapter} instance.
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
     *   This test ensures that the hasNext method behaves correctly on an EntrySetIterator of an empty EntrySet.
     *   The rationale is to confirm that the method returns false when there are no more elements to iterate on, true otherwise.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the hasNext method returns false when called on an EntrySetIterator of an empty EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hasNext method should return false.</li>
     * </ul>
     */
    @Test
    public void testHasNextOnEmptyEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        assertFalse("Iterator on an empty collection should not have elements", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on an EntrySetIterator of an empty EntrySet throws NoSuchElementException.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method throws NoSuchElementException when called on an EntrySetIterator of an empty EntrySet.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to return.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the next method throws NoSuchElementException when called on an EntrySetIterator of an empty EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The next method should throw NoSuchElementException.</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        iterator.next();
    }

    /**
     * Summary:
     *   Verifies that the remove method on an EntrySetIterator of an empty EntrySet throws IllegalStateException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called on an EntrySetIterator of an empty EntrySet.
     *   The rationale is to confirm that the method behaves correctly when the next method was not called before.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the remove method throws IllegalStateException when called on an EntrySetIterator of an empty EntrySet, in particular because next() was not called before.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The remove method should throw IllegalStateException.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        iterator.remove();
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- KeySetIterator ----------------------------*/
    /*--------------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the hasNext method on a KeySetIterator of an empty KeySet returns false.
     * <p>
     * Test Case Design:
     *   This test ensures that the hasNext method returns false when called on a KeySetIterator of an empty KeySet.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to iterate over.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the hasNext method returns false when called on a KeySetIterator of an empty KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hasNext method should return false.</li>
     * </ul>
     */
    @Test
    public void testHasNextOnEmptyKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        assertFalse("Iterator on an empty collection should not have elements", iterator.hasNext());    
    }

    /**
     * Summary:
     *   Verifies that the next method on a KeySetIterator of an empty KeySet throws NoSuchElementException.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method throws NoSuchElementException when called on a KeySetIterator of an empty KeySet.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to iterate over.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the next method throws NoSuchElementException when called on a KeySetIterator of an empty KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The next method should throw NoSuchElementException.</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        iterator.next();
    }

    /**
     * Summary:
     *   Verifies that the remove method on a KeySetIterator of an empty KeySet throws IllegalStateException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called on a KeySetIterator of an empty KeySet.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to iterate over.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the remove method throws IllegalStateException when called on a KeySetIterator of an empty KeySet, in particular because next() was not called before.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The remove method should throw IllegalStateException.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        iterator.remove();
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- ValuesIterator ---------------------------*/
    /*--------------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the hasNext method on a ValuesIterator of an empty ValuesCollection returns false.
     * <p>
     * Test Case Design:
     *   This test ensures that the hasNext method returns false when called on a ValuesIterator of an empty ValuesCollection.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to iterate over.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the hasNext method returns false when called on a ValuesIterator of an empty ValuesCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hasNext method should return false.</li>
     * </ul>
     */
    @Test
    public void testHasNextOnEmptyValuesIterator() {
        HIterator iterator = map.values().iterator();
        assertFalse("Iterator on an empty collection should not have elements", iterator.hasNext());
    }

    /**
     * Summary:
     *   Verifies that the next method on a ValuesIterator of an empty ValuesCollection throws NoSuchElementException.
     * <p>
     * Test Case Design:
     *   This test ensures that the next method throws NoSuchElementException when called on a ValuesIterator of an empty ValuesCollection.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to iterate over.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the next method throws NoSuchElementException when called on a ValuesIterator of an empty ValuesCollection.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The next method should throw NoSuchElementException.</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyValuesIterator() {
        HIterator iterator = map.values().iterator();
        iterator.next();
    }

    /**
     * Summary:
     *   Verifies that the remove method on a ValuesIterator of an empty ValuesCollection throws IllegalStateException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method throws IllegalStateException when called on a ValuesIterator of an empty ValuesCollection.
     *   The rationale is to confirm that the method behaves correctly when there are no elements to iterate over.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the remove method throws IllegalStateException when called on a ValuesIterator of an empty ValuesCollection, in particular because next() was not called before.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The remove method should throw IllegalStateException.</li>
     * </ul>
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyValuesIterator() {
        HIterator iterator = map.values().iterator();
        iterator.remove();
    }
}
