//Diego Chiesurin - 2111553

package myTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import myAdapter.*;
import myExceptions.IllegalStateException;
import java.util.NoSuchElementException;



/**
 * This test suite verifies the correct behavior of the iterators returned by the collection views of a populated {@code MapAdapter}.
 * It covers all methods and scenarios relevant to iterators of entry sets, key sets, and value collections, including iteration, removal, and exception handling.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Iterator behavior (EntrySetIterator, KeySetIterator, ValueCollectionIterator)</li>
 *   <li>Populated state iteration</li>
 *   <li>Iterator is backed by MapAdapter</li>
 *   <li>Mutation and removal operations</li>
 *   <li>Exception handling (NoSuchElementException, IllegalStateException)</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that all iterators returned by the collection views of a populated {@code MapAdapter} behave as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that iterators correctly traverse all elements in entry sets, key sets, and value collections.</li>
 *   <li>Check that the {@code hasNext} and {@code next} methods behave as expected, including at the end of iteration.</li>
 *   <li>Ensure that the {@code remove} method removes the correct element and throws exceptions when called in invalid states.</li>
 *   <li>Test exception handling for calling {@code next} when no elements remain and {@code remove} before {@code next} or after a removal.</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of iterator behavior in a populated map, ensuring correctness, robustness, and compliance with the iterator contract.
 */
public class TestPopulatedAbstractCollectionIterators {

    
    private HMap map;
    
    public TestPopulatedAbstractCollectionIterators() {
        // intentionally left empty
    }

    @Before
    public void setUp() {
        this.map = new MapAdapter();
        for (int i = 0; i < 100; i++) {
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- EntrySetIterator -------------------------*/
    /*--------------------------------------------------------------------*/

    @Test
    public void testHasNextOnPopulatedEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        assertTrue("Iterator on a populated collection should have elements", iterator.hasNext());
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());    
    }

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

    @Test(expected = NoSuchElementException.class)
    public void testNextOnPopulatedEntrySetIteratorWhenNoMoreElements() {
        HIterator iterator = map.entrySet().iterator();
        while (true) {
            iterator.next();
        }
    }

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

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedEntrySetIteratorBeforeNext() {
        HIterator iterator = map.entrySet().iterator();
        iterator.remove();
    }  

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

    @Test
    public void testHasNextOnPopulatedKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        assertTrue("Iterator on a populated collection should have elements", iterator.hasNext());
        while (iterator.hasNext()) {
            iterator.next();
            assertTrue("KeySet should contain the key returned by next", map.keySet().contains(iterator.next()));
        }
        assertFalse("Iterator on a populated collection should not have more elements after iterating through all", iterator.hasNext());
    }

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

    @Test(expected = NoSuchElementException.class)
    public void testNextOnPopulatedKeySetIteratorWhenNoMoreElements() {
        HIterator iterator = map.keySet().iterator();
        while (true) {
            iterator.next();
        }
    }

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

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedKeySetIteratorBeforeNext() {
        HIterator iterator = map.keySet().iterator();
        iterator.remove();
    }

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

    @Test(expected = NoSuchElementException.class)
    public void testNextOnPopulatedValueCollectionIteratornWhenNoMoreElements() {
        HIterator iterator = map.values().iterator();
        while (true) {
            iterator.next();
        }
    }

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

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedValueCollectionIteratornBeforeNext() {
        HIterator iterator = map.values().iterator();
        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnPopulatedValueCollectionIteratorAfterRemove() {
        HIterator iterator = map.values().iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }
}