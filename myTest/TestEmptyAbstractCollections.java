// Diego Chiesurin

package myTest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import myAdapter.*;
import myExceptions.UnsupportedOperationException;


/**
 * This test suite verifies the correct behavior of the collection views ({@link myAdapter.MapAdapter.EntrySet}, {@link myAdapter.MapAdapter.KeySet}, {@link myAdapter.MapAdapter.ValueCollection}) of an empty {@link MapAdapter}.
 * It covers all methods and scenarios relevant to empty collections, including query, mutation, equality, hash code, and exception handling.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Empty collection behavior ({@link myAdapter.MapAdapter.EntrySet}, {@link myAdapter.MapAdapter.KeySet}, {@link myAdapter.MapAdapter.ValueCollection})</li>
 *   <li>Mutation and query operations</li>
 *   <li>Exception handling ({@link myExceptions.UnsupportedOperationException}, {@code NullPointerException}, {@code ClassCastException})</li>
 *   <li>Equality and hash code</li>
 *   <li>Array conversion and string representation</li>
 *   <li>Backing behavior (collections reflect map changes)</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that all {@link myAdapter.HCollection} and {@link myAdapter.HSet} views of an empty {@link MapAdapter} behave as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that all query methods ({@code isEmpty}, {@code size}, {@code contains}, etc.) return results consistent with an empty {@link myAdapter.HCollection} or {@link myAdapter.HSet}.</li>
 *   <li>Check that mutator methods ({@code add}, {@code addAll}, {@code clear}, {@code remove}, {@code removeAll}, {@code retainAll}) behave correctly and throw exceptions for invalid input or unsupported operations.</li>
 *   <li>Test the behavior of equality, hash code, array conversion, and string representation for empty {@link myAdapter.HCollection} and {@link myAdapter.HSet}.</li>
 *   <li>Ensure that the {@link myAdapter.HCollection} and {@link myAdapter.HSet} are backed by the {@link myAdapter.MapAdapter} and reflect changes in the map.</li>
 *   <li>Test exception handling for invalid input (e.g., nulls, wrong types).</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of the empty state for all collection views, ensuring correctness, robustness and compliance with the {@link myAdapter.HCollection} and {@link myAdapter.HSet} contracts.
 */
public class TestEmptyAbstractCollections {

    /**
     * The map instance used in tests.
     * It is initialized in the setUp method and is empty.
     */
    private HMap map;

    /**
     * Default constructor for the test class.
     */
    public TestEmptyAbstractCollections() {
        //intentionally left blank
    }

    /**
     * Sets up the test environment by initializing an empty MapAdapter instance.
     * This method is called before each test to ensure a fresh state.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Cleans up the test environment by setting the map instance to null.
     * This method is called after each test to ensure no state is carried over.
     */
    @After
    public void tearDown() {
        map = null;
    }

    /*--------------------------------------------------------------*/
    /*----------------------- EntrySet TESTS -----------------------*/
    /*--------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the add method on an empty EntrySet throws an UnsupportedOperationException.
     *<p>
     * Test Case Design:
     *   This test ensures that the add method is not supported on an empty EntrySet.
     *   The rationale is to confirm that the EntrySet does not allow addition of new elements..
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the add method on the empty EntrySet.</li>
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
    public void testAddOnEmptyEntrySet() {
        this.map.entrySet().add("anything");
    }

    /**
     * Summary:
     *   Verifies that the addAll method on an empty EntrySet throws an UnsupportedOperationException.
     *<p>
     * Test Case Design:
     *   This test ensures that the addAll method is not supported on an empty EntrySet.
     *   The rationale is to confirm that the EntrySet does not allow addition of multiple new elements.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the addAll method on the empty EntrySet.</li>
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
     *   <li>Calling addAll on the EntrySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnEmptyEntrySet() {
        this.map.entrySet().addAll(this.map.entrySet());
    }

    /**
     * Summary:
     *   Verifies that the clear method on an empty EntrySet does not throw an exception and leaves the EntrySet empty.
     *<p>
     * Test Case Design:
     *   This test ensures that the clear method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that clear does not affect an already empty collection.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the clear method on the empty EntrySet.</li>
     *   <li>Check that the EntrySet is still empty.</li>
     *   <li>Check that the size of the EntrySet is still 0.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   The MapAdapter is empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should remain empty after calling clear.</li>
     *   <li>The size of the EntrySet should be 0.</li>
     * </ul>
     */
    @Test
    public void testClearOnEmptyEntrySet() {
        HSet entrySet = this.map.entrySet();
        entrySet.clear();
        assertTrue("EntrySet should be empty after clear", entrySet.isEmpty());
        assertEquals("Size of EntrySet should be 0 after clear", 0, entrySet.size());
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method behaves as expected on an empty EntrySet.
     *   The rationale is to confirm that the method correctly identifies when all elements are contained.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the containsAll method on the empty EntrySet with another empty EntrySet.</li>
     *   <li>Check that the containsAll method returns true.</li>
     *   <li>Check that the containsAll method returns true when called with itself.</li>
     *   <li>Check that the containsAll method returns false when called with a non-empty EntrySet.</li>
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
     *   <li>Calling containsAll on the EntrySet should return true when passed another empty EntrySet.</li>
     *   <li>Calling containsAll on the EntrySet with itself should return true.</li>
     *   <li>Calling containsAll on the EntrySet with a non-empty EntrySet should return false.</li>
     * </ul>
     */
    @Test
    public void testContainsAllOnEmptyEntrySet() {
        HMap anotherMap = new MapAdapter();
        assertTrue("An empty EntrySet should contain all elements of another empty EntrySet", this.map.entrySet().containsAll(anotherMap.entrySet()));
        assertTrue("An empty EntrySet should contain all elements of itself", this.map.entrySet().containsAll(this.map.entrySet()));
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty EntrySet should not contain elements of a non-empty EntrySet", this.map.entrySet().containsAll(anotherMap.entrySet()));
    }

    /**
     * Summary:
     *  Verifies that the containsAll method on an empty EntrySet with a non-EntrySet throws a ClassCastException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly identifies when a non-EntrySet is passed.
     *   The rationale is to confirm that the method only accepts compatible types.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the empty EntrySet with a non-EntrySet.</li>
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
     *   <li>Calling containsAll on the EntrySet with a non-EntrySet should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testContainsAllOnEmptyEntrySetWithANonEntrySet() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        this.map.entrySet().containsAll(anotherMap.keySet());
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty EntrySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the empty EntrySet with null.</li>
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
     *   <li>Calling containsAll on the EntrySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyEntrySetWithNull() {
        this.map.entrySet().containsAll(null);
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty EntrySet with another EntrySet with null keys throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles EntrySets with null keys.
     *   The rationale is to confirm that MapAdapter does not accept null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a null key.</li>
     *   <li>Call the containsAll method on the empty EntrySet with this EntrySet.</li>
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
     *   <li>Calling containsAll on the EntrySet with an EntrySet containing a null key should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyEntrySetWithAnotherEntrySetWithNullKeys() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty EntrySet with another EntrySet with null values throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles EntrySets with null values.
     *   The rationale is to confirm that MapAdapter does not accept null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a null value.</li>
     *   <li>Call the containsAll method on the empty EntrySet with this EntrySet.</li>
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
     *   <li>Calling containsAll on the EntrySet with an EntrySet containing a null value should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyEntrySetWithAnotherEntrySetWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().containsAll(anotherMap.entrySet());
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on an empty EntrySet returns 0.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the hash code of an empty collection is defined as 0.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the hashCode method on the empty EntrySet.</li>
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
     *   <li>The hashCode of the empty EntrySet should be 0.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnEmptyEntrySet() {
        assertEquals("Hash code of an empty EntrySet should be 0", 0, this.map.entrySet().hashCode());
    }

    /**
     * Summary:
     *   Verifies that the isEmpty method on an empty EntrySet returns true.
     * <p>
     * Test Case Design:
     *   This test ensures that the isEmpty method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the method correctly identifies an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the isEmpty method on the empty EntrySet.</li>
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
     *   <li>The isEmpty method should return true for an empty EntrySet.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnEmptyEntrySet() {
        assertTrue("EntrySet should be empty", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures the correct behavior of the removeAll method on an empty EntrySet.
     *   The rationale is to confirm that the method behaves as expected when called on an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the removeAll method on the empty EntrySet with another empty EntrySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after removeAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after removeAll.</li>
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
     *   <li>Calling removeAll on the EntrySet with another empty EntrySet should return false.</li>
     *   <li>EntrySet should still be empty after removeAll.</li>
     *   <li>Size of EntrySet should still be 0 after removeAll.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyEntrySet() {
        HSet anotherEntrySet = new MapAdapter().entrySet();
        assertFalse("RemoveAll on an empty EntrySet with another empty EntrySet should return false", this.map.entrySet().removeAll(anotherEntrySet));
        assertTrue("EntrySet should still be empty after removeAll", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty EntrySet with an empty non-EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures the correct behavior of the removeAll method on an empty EntrySet with a non-EntrySet.
     *   The rationale is to confirm that the method behaves as expected when called with a non-compatible type.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the removeAll method on the empty EntrySet with this non-EntrySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after removeAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after removeAll.</li>
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
     *   <li>Calling removeAll on the EntrySet with a non-EntrySet should return false.</li>
     *   <li>EntrySet should still be empty after removeAll.</li>
     *   <li>Size of EntrySet should still be 0 after removeAll.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyEntrySetWithAnEmptyNonEntrySet() {
        this.map.entrySet().removeAll(this.map.keySet());
        assertTrue("EntrySet should still be empty after removeAll with an empty non-EntrySet", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll with an empty non-EntrySet", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty EntrySet with a non-EntrySet returns false.
     * <p>
     * Test Case Design:
     *   This test ensures the correct behavior of the removeAll method on an empty EntrySet with a non-EntrySet.
     *   The rationale is to confirm that the method behaves as expected when called with a non-compatible type.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the removeAll method on the empty EntrySet with this non-EntrySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after removeAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after removeAll.</li>
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
     *   <li>Calling removeAll on the EntrySet with a non-EntrySet should return false.</li>
     *   <li>EntrySet should still be empty after removeAll.</li>
     *   <li>Size of EntrySet should still be 0 after removeAll.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyEntrySetWithANonEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RemoveAll on an empty EntrySet with a non-EntrySet should return false", this.map.entrySet().removeAll(anotherMap.keySet()));
        assertTrue("EntrySet should still be empty after removeAll with a non-EntrySet", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll with a non-EntrySet", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty EntrySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty EntrySet with null.</li>
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
     *   <li>Calling removeAll on the EntrySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyEntrySetWithNull() {
        this.map.entrySet().removeAll(null);
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty EntrySet with an EntrySet containing null keys does not modify the EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles an EntrySet with null keys.
     *   The rationale is to confirm that the method does not remove any elements from the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with a null key.</li>
     *   <li>Call the removeAll method on the empty EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after removeAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after removeAll.</li>
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
     *   <li>Calling removeAll on the EntrySet with an EntrySet containing null keys should return false.</li>
     *   <li>EntrySet should still be empty after removeAll.</li>
     *   <li>Size of EntrySet should still be 0 after removeAll.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyEntrySetWithEntrySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("RemoveAll on an empty EntrySet with an EntrySet containing null keys should return false", this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertTrue("EntrySet should still be empty after removeAll with an EntrySet with null keys", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll with an EntrySet with null keys", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty EntrySet with another EntrySet with null values does not modify the EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles an EntrySet with null values.
     *   The rationale is to confirm that the method does not remove any elements from the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with a null value.</li>
     *   <li>Call the removeAll method on the empty EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after removeAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after removeAll.</li>
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
     *   <li>Calling removeAll on the EntrySet with an EntrySet containing null values should return false.</li>
     *   <li>EntrySet should still be empty after removeAll.</li>
     *   <li>Size of EntrySet should still be 0 after removeAll.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyEntrySetWithAnotherEntrySetWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertFalse("RemoveAll on an empty EntrySet with an EntrySet containing null values should return false", this.map.entrySet().removeAll(anotherMap.entrySet()));
        assertTrue("EntrySet should still be empty after removeAll with an EntrySet with null values", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after removeAll with an EntrySet with null values", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures the correct behavior of the retainAll method on an empty EntrySet.
     *   The rationale is to confirm that the method behaves as expected when called on an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another non-empty MapAdapter.</li>
     *   <li>Call the retainAll method on the empty EntrySet with another non-empty EntrySet.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after retainAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after retainAll.</li>
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
     *   <li>Calling retainAll on the EntrySet with another non-empty EntrySet should return false.</li>
     *   <li>EntrySet should still be empty after retainAll.</li>
     *   <li>Size of EntrySet should still be 0 after retainAll.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty EntrySet should return false", this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("EntrySet should still be empty after retainAll", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after retainAll", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty EntrySet with a non-EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures the correct behavior of the retainAll method on an empty EntrySet with a non-EntrySet.
     *   The rationale is to confirm that the method behaves as expected when called with a non-compatible type.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the retainAll method on the empty EntrySet with the other MapAdapter's non-EntrySet.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after retainAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after retainAll.</li>
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
     *   <li>Calling retainAll on the EntrySet with a non-EntrySet should return false.</li>
     *   <li>EntrySet should still be empty after retainAll.</li>
     *   <li>Size of EntrySet should still be 0 after retainAll.</li>
     * </ul>    
     */
    @Test
    public void testRetainAllOnEmptyEntrySetWithANonEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty EntrySet with a non-EntrySet should return false", this.map.entrySet().retainAll(anotherMap.keySet()));
        assertTrue("EntrySet should still be empty after retainAll with a non-EntrySet", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty EntrySet with another empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures the correct behavior of the retainAll method on an empty EntrySet with another empty EntrySet.
     *   The rationale is to confirm that the method behaves as expected when called on an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the retainAll method on the empty EntrySet with another empty EntrySet.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after retainAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after retainAll.</li>
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
     *   <li>Calling retainAll on the EntrySet with another empty EntrySet should return false.</li>
     *   <li>EntrySet should still be empty after retainAll.</li>
     *   <li>Size of EntrySet should still be 0 after retainAll.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyEntrySetWithAnEmptyEntrySet() {
        HMap anotherMap = new MapAdapter();
        assertFalse("RetainAll on an empty EntrySet with an empty EntrySet should return false", this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("EntrySet should still be empty after retainAll with an empty EntrySet", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty EntrySet with an EntrySet containing null keys does not modify the EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles an EntrySet with null keys.
     *   The rationale is to confirm that the method does not remove any elements from the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with a null key.</li>
     *   <li>Call the retainAll method on the empty EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after retainAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after retainAll.</li>
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
     *   <li>Calling retainAll on the EntrySet with an EntrySet containing null keys should return false.</li>
     *   <li>EntrySet should still be empty after retainAll.</li>
     *   <li>Size of EntrySet should still be 0 after retainAll.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyEntrySetWithEntrySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("RetainAll on an empty EntrySet with an EntrySet with null keys should return false", this.map.entrySet().retainAll(anotherMap.entrySet()));
        assertTrue("EntrySet should still be empty after retainAll with an EntrySet with null keys", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after retainAll with an EntrySet with null keys", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty EntrySet with another EntrySet with null values does not modify the EntrySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles an EntrySet with null values.
     *   The rationale is to confirm that the method does not remove any elements from the EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with a null value.</li>
     *   <li>Call the retainAll method on the empty EntrySet with the DummyTestMap's EntrySet.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the EntrySet is still empty after retainAll.</li>
     *   <li>Check that the size of the EntrySet is still 0 after retainAll.</li>
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
     *   <li>Calling retainAll on the EntrySet with an EntrySet containing null values should return false.</li>
     *   <li>EntrySet should still be empty after retainAll.</li>
     *   <li>Size of EntrySet should still be 0 after retainAll.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyEntrySetWithAnotherEntrySetWithNullValues() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().retainAll(anotherMap.entrySet());
        assertTrue("EntrySet should still be empty after retainAll with an EntrySet with null values", this.map.entrySet().isEmpty());
        assertEquals("Size of EntrySet should still be 0 after retainAll with an EntrySet with null values", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty EntrySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the empty EntrySet with null.</li>
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
     *   <li>Calling retainAll on the EntrySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllOnEmptyEntrySetWithNull() {
        this.map.entrySet().retainAll(null);
    }

    /**
     * Summary:
     *   Verifies that the size method on an empty EntrySet returns 0.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the size of an empty collection is defined as 0.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the size method on the empty EntrySet.</li>
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
     *   <li>The size of the empty EntrySet should be 0.</li>
     * </ul>
     */
    @Test
    public void testSizeOnEmptyEntrySet() {
        assertEquals("Size of an empty EntrySet should be 0", 0, this.map.entrySet().size());
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty EntrySet returns an empty array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the toArray method returns an empty array when called on an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty EntrySet.</li>
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
     *   <li>The toArray method on the empty EntrySet should return an empty array.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyEntrySet() {
        Object[] array = this.map.entrySet().toArray();
        assertNotNull("ToArray on an empty EntrySet should not return null", array);
        assertEquals("ToArray on an empty EntrySet should return an empty array", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty EntrySet is free from the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after it has been called.
     *   The rationale is to confirm that the array returned by toArray is independent of subsequent modifications to the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty EntrySet.</li>
     *   <li>Modify the MapAdapter by adding an entry.</li>
     *   <li>Check that the array returned by toArray remains unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains one key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after it has been called.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyEntrySetIsFreeFromMap() {
        Object[] array = this.map.entrySet().toArray();
        this.map.put("testKey", "testValue");
        assertEquals("array should not be affected by changes in the map after toArray", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty EntrySet with a same size array returns an empty array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly when provided with an array of the same size as the EntrySet.
     *   The rationale is to confirm that the method does not throw an exception and returns an empty array.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty EntrySet with an array of the same size.</li>
     *   <li>Check that the returned array is not null.</li>
     *   <li>Check that the returned array has length 0.</li>
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
     *   <li>The toArray method on the empty EntrySet with a same size array should return a non-null array.</li>
     *   <li>The return array should have length 0.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyEntrySetWithSameSize() {
        Object[] array = new Object[0];
        array = this.map.entrySet().toArray(array);
        assertNotNull("ToArray on an empty EntrySet with a same size array should not return null", array);
        assertEquals("ToArray on an empty EntrySet with a same size array should return an empty array", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty EntrySet with a greater size array returns an array of the original size.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly when provided with an array larger than the EntrySet.
     *   The rationale is to confirm that the method does not throw an exception and returns an array of the original size.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty EntrySet with a greater size array.</li>
     *   <li>Check that the returned array is not null.</li>
     *   <li>Check that the returned array has length 1.</li>
     *   <li>Check that the first element of the returned array is null.</li>
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
     *   <li>The toArray method on the empty EntrySet with a greater size array should return a non-null array.</li>
     *   <li>The return array should have length 1.</li>
     *   <li>The first element of the return array should be null.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyEntrySetWithGreaterArray() {
        Object[] array = this.map.entrySet().toArray(new Object[1]);
        assertNotNull("ToArray on an empty EntrySet with a greater array should not return null", array);
        assertEquals("ToArray on an empty EntrySet with a greater array should return an array with its original size", 1, array.length);
        assertNull("First element of the array after the copied EntrySet should be null", array[0]);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty EntrySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty EntrySet with null.</li>
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
     *   <li>Calling toArray on the EntrySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayOnEmptyEntrySetWithNull() {
        this.map.entrySet().toArray(null);
    }

    /**
     * Summary:
     *   Verifies that the toString method on an empty EntrySet returns "[]".
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the string representation of an empty collection is defined as "[]".
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toString method on the empty EntrySet.</li>
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
     *   <li>The toString method on the empty EntrySet should return "[]".</li>
     * </ul>
     */
    @Test
    public void testToStringOnEmptyEntrySet() {
        assertEquals("ToString on an empty EntrySet should return \"[]\"", "[]",this.map.entrySet().toString());
    }

    /**
     * Summary:
     *   Verifies that the entrySet on an empty MapAdapter is backed by the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the entrySet method on an empty MapAdapter returns an empty EntrySet.
     *   The rationale is to confirm that the entrySet is correctly backed by the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Save the current state of the empty EntrySet of the MapAdapter.</li>
     *   <li>Check that the EntrySet is empty.</li>
     *   <li>Add an entry to the MapAdapter.</li>
     *   <li>Check that the EntrySet is not empty after adding an entry to the MapAdapter.</li>
     *   <li>Check that the size of the EntrySet matches the size of the MapAdapter after adding an entry.</li>
     *   <li>Check that the toString method of the EntrySet returns the expected string representation.</li>
     *   <li>Clear the EntrySet.</li>
     *   <li>Check that the EntrySet is empty after clearing.</li>
     *   <li>Check that the MapAdapter is empty after clearing the EntrySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should be empty if the MapAdapter is empty.</li>
     *   <li>The EntrySet should not be empty after adding an entry to the MapAdapter.</li>
     *   <li>The size of the EntrySet should match the size of the MapAdapter after adding an entry.</li>
     *   <li>The toString method of the EntrySet should return the expected string representation.</li>
     *   <li>The EntrySet should be empty after clearing.</li>
     *   <li>The MapAdapter should be empty after clearing the EntrySet.</li>
     * </ul>
     */
    @Test
    public void testEmptyEntrySetIsBackedByMapAdapter() {
        HSet entrySet = this.map.entrySet();
        assertTrue("EntrySet should be empty if MapAdapter is empty", entrySet.isEmpty());
        map.put("testKey", "testValue");
        assertFalse("EntrySet should not be empty after adding an entry to MapAdapter", entrySet.isEmpty());
        assertEquals("EntrySet size should match MapAdapter size after adding an entry", 1, entrySet.size());
        assertEquals("EntrySet toString should be \"[testKey=testValue]\"", "[testKey=testValue]", entrySet.toString());
        entrySet.clear();
        assertTrue("EntrySet should be empty after clearing", entrySet.isEmpty());
        assertTrue("MapAdapter should be empty after clearing EntrySet", map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the method returns true when compared with another empty EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty EntrySet is equal to another empty EntrySet.</li>
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
     *   <li>Two empty EntrySets should be equal.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyEntrySet () {
        HSet entrySet = this.map.entrySet();
        HSet anotherEntrySet = new MapAdapter().entrySet();
        assertTrue("Two empty EntrySets should be equal", entrySet.equals(anotherEntrySet));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet with a non-empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when comparing an empty EntrySet with a non-empty EntrySet.
     *   The rationale is to confirm that the method returns false when comparing an empty EntrySet with a non-empty EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Check that the empty EntrySet is not equal to the non-empty EntrySet.</li>
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
     *   <li>An empty EntrySet should not be equal to a non-empty EntrySet.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyEntrySetWithNonEmptyEntrySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty EntrySet should not be equal to a non-empty EntrySet", this.map.entrySet().equals(anotherMap.entrySet()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet with a non-HSet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when comparing an empty EntrySet with a non-HSet.
     *   The rationale is to confirm that the method returns false when comparing an empty EntrySet with a non-HSet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Check that the empty EntrySet is not equal to the non-HSet.</li>
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
     *   <li>An empty EntrySet should not be equal to a non-HSet.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyEntrySetWithNonHSet() {
        MapAdapter anotherMap = new MapAdapter();
        assertFalse("An empty EntrySet should not be equal to a non-HSet", this.map.entrySet().equals(anotherMap.values()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet with null behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when comparing an empty EntrySet with null.
     *   The rationale is to confirm that the method returns false when comparing an empty EntrySet with null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty EntrySet is not equal to null.</li>
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
     *   <li>Empty EntrySet should not be equal to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEntrySetWithNull() {
        assertFalse("Empty EntrySet should not be equal to null", this.map.entrySet().equals(null));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet with itself behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly when comparing an empty EntrySet with itself.
     *   The rationale is to confirm that the method returns true when comparing an empty EntrySet with itself.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty EntrySet is equal to itself.</li>
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
     *   <li>Empty EntrySet should be equal to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyEntrySetWithItself() {
        assertTrue("Empty EntrySet should be equal to itself", this.map.entrySet().equals(this.map.entrySet()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet is symmetric.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves symmetrically when comparing two empty EntrySets.
     *   The rationale is to confirm that if one EntrySet is equal to another, then the reverse should also hold true.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Check that the empty EntrySet is equal to the other empty EntrySet and vice versa.</li>
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
     *   <li>Equals should be symmetric for two empty EntrySets.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyEntrySetIsSymmetric() {
        HSet anotherEmptyEntrySet = new MapAdapter().entrySet();
        assertTrue("Equals should be symmetric", anotherEmptyEntrySet.equals(this.map.entrySet()) && this.map.entrySet().equals(anotherEmptyEntrySet));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty EntrySet is transitive.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves transitively when comparing three empty EntrySets.
     *   The rationale is to confirm that if one EntrySet is equal to a second, and the second is equal to a third, then the first should also be equal to the third.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create two additional empty MapAdapters.</li>
     *   <li>Check that the first empty EntrySet is equal to the second, and the second is equal to the third, and thus the first is equal to the third.</li>
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
     *   <li>Equals should be transitive for three empty EntrySets.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyEntrySetIsTransitive() {
        HSet entrySet = this.map.entrySet();
        HSet anotherEmptyEntrySet = new MapAdapter().entrySet();
        HSet anotherAnotherEmptyEntrySet = new MapAdapter().entrySet();
        assertTrue("Equals should be transitive", entrySet.equals(anotherEmptyEntrySet) && anotherEmptyEntrySet.equals(anotherAnotherEmptyEntrySet) && entrySet.equals(anotherAnotherEmptyEntrySet));
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the method returns false when checking for any entry in an empty EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty EntrySet does not contain any entry.</li>
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
     *   <li>Contains on an empty EntrySet should return false.</li>
     * </ul>
     */
    @Test
    public void testContainsOnEmptyEntrySet() {
        HMap anotherMapAdapter = new MapAdapter();
        anotherMapAdapter.put("testKey", "testValue");
        HSet entrySet = anotherMapAdapter.entrySet();
        HIterator iterator = entrySet.iterator();
        assertFalse("Empty EntrySet should not contain any keys", this.map.entrySet().contains(iterator.next()));
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty EntrySet with an Entry containing null key throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly when checking for an entry with a null key.
     *   The rationale is to confirm that MapAdapter does not allow null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty EntrySet does not contain an entry with a null key.</li>
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
     *   <li>Contains on an empty EntrySet with an entry with a null key should throw a NullPointerException.</li>
     * </ul>    
     */
    @Test(expected = NullPointerException.class) 
    public void testContainsOnEmptyEntrySetWithEntryWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().contains(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty EntrySet with an Entry containing null value throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly when checking for an entry with a null value.
     *   The rationale is to confirm that MapAdapter does not allow null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty EntrySet does not contain an entry with a null value.</li>
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
     *   <li>Contains on an empty EntrySet with an entry with a null value should throw a NullPointerException.</li>
     * </ul>    
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyEntrySetWithEntryWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().contains(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty EntrySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the empty EntrySet with null.</li>
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
     *   <li>Calling contains on the EntrySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyEntrySetWithNull() {
        this.map.entrySet().contains(null);
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty EntrySet with a non-HEntry object throws a ClassCastException.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles non-HEntry objects.
     *   The rationale is to confirm that the method does not accept non-HEntry objects as valid arguments.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the empty EntrySet with a non-HEntry object.</li>
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
     *   <li>Calling contains on the EntrySet with a non-HEntry object should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testContainsOnEmptyEntrySetWithNonEntryObject() {
        this.map.entrySet().contains("not an entry");
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty EntrySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method behaves correctly on an empty EntrySet.
     *   The rationale is to confirm that the method returns false when trying to remove any entry from an empty EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Get the EntrySet of the other MapAdapter.</li>
     *   <li>Get an iterator from the EntrySet.</li>
     *   <li>Check that removing an entry from the empty EntrySet returns false.</li>
     *   <li>Check that the EntrySet is still empty after the remove operation.</li>
     *   <li>Check that the size of the EntrySet is still 0 after the remove operation.</li>
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
     *   <li>Remove on an empty EntrySet should return false.</li>
     *   <li>The EntrySet should still be empty after the remove operation.</li>
     *   <li>The size of the EntrySet should still be 0 after the remove operation.</li>
     * </ul>
     */
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

    /**
     * Summary:
     *   Verifies that the remove method on an empty EntrySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the empty EntrySet with null.</li>
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
     *   <li>Calling remove on the EntrySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyEntrySetWithNull() {
        this.map.entrySet().remove(null);
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty EntrySet with an Entry containing null key throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles an entry with a null key.
     *   The rationale is to confirm that MapAdapter does not allow null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry with a null key.</li>
     *   <li>Check that removing an entry with a null key from the empty EntrySet throws a NullPointerException.</li>
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
     *   <li>Remove on an empty EntrySet with an entry with a null key should throw a NullPointerException.</li>
     * </ul>    
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyEntrySetWithEntryWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.entrySet().remove(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty EntrySet with an Entry containing null value throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles an entry with a null value.
     *   The rationale is to confirm that MapAdapter does not allow null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry with a null value.</li>
     *   <li>Check that removing an entry with a null value from the empty EntrySet throws a NullPointerException.</li>
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
     *   <li>Remove on an empty EntrySet with an entry with a null value should throw a NullPointerException.</li>
     * </ul>    
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyEntrySetWithEntryWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.entrySet().remove(anotherMap.entrySet().iterator().next());
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty EntrySet with a non-HEntry object throws a ClassCastException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles non-HEntry objects.
     *   The rationale is to confirm that the method does not accept non-HEntry objects as valid arguments.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the empty EntrySet with a non-HEntry object.</li>
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
     *   <li>Calling remove on the EntrySet with a non-HEntry object should throw a ClassCastException.</li>
     * </ul>
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveOnEmptyEntrySetWithNonEntryObject() {
        this.map.entrySet().remove("not an entry");
    }

    /**
     * Summary:
     *   Verifies that the iterator on an empty EntrySet is not null.
     * <p>
     * Test Case Design:
     *   This test ensures that the iterator method on an empty EntrySet returns a valid iterator.
     *   The rationale is to confirm that the method does not return null for an empty EntrySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the iterator method on the empty EntrySet.</li>
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
     *   <li>The iterator on an empty EntrySet should not be null.</li>
     * </ul>
     */
    @Test
    public void testIteratorOnEmptyEntrySet() {
        HIterator iterator = this.map.entrySet().iterator();
        assertNotNull("Iterator on an empty EntrySet should not be null", iterator);
    }

    /*--------------------------------------------------------------*/
    /*------------------------ KeySet TESTS ------------------------*/
    /*--------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the add method on an empty KeySet throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the add method on an empty KeySet does not allow adding elements.
     *   The rationale is to confirm that the KeySet does not allow addition of new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the add method on the empty KeySet.</li>
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
     *   <li>Calling add on an empty KeySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnEmptyKeySet() {
        this.map.keySet().add("anything");
    }

    /**
     * Summary:
     *   Verifies that the addAll method on an empty KeySet throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the addAll method on an empty KeySet does not allow adding multiple elements.
     *   The rationale is to confirm that the KeySet does not allow addition of multiple new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the addAll method on the empty KeySet.</li>
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
     *   <li>Calling addAll on an empty KeySet should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnEmptyKeySet() {
        this.map.keySet().addAll(this.map.keySet());
    }

    /**
     * Summary:
     *   Verifies that the clear method on an empty KeySet does not throw an exception and leaves the KeySet empty.
     * <p>
     * Test Case Design:
     *   This test ensures that the clear method on an empty KeySet behaves correctly.
     *   The rationale is to confirm that clearing an empty KeySet does not change its state.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the clear method on the empty KeySet.</li>
     *   <li>Check that the KeySet is still empty after clearing.</li>
     *   <li>Check that the size of the KeySet is still 0 after clearing.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>Clear on an empty KeySet should not throw an exception and should leave it empty.</li>
     *   <li>The size of the KeySet should be 0 after clearing.</li>
     * </ul>
     */
    @Test
    public void testClearOnEmptyKeySet() {
        HSet keySet = this.map.keySet();
        keySet.clear();
        assertTrue("KeySet should be empty after clear", keySet.isEmpty());
        assertEquals("Size of KeySet should be 0 after clear", 0, keySet.size());
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method return the correct result.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Get the KeySet of the other MapAdapter.</li>
     *   <li>Check that the empty KeySet contains all elements of another empty KeySet.</li>
     *   <li>Check that the empty KeySet contains all elements of itself.</li>
     *   <li>Insert a key-value pair into the other MapAdapter.</li>
     *   <li>Check that the empty KeySet does not contain elements of a non-empty KeySet.</li>
     *   <li>Check that the empty KeySet does not contain any key.</li>
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
     *   <li>An empty KeySet should contain all elements of another empty KeySet.</li>
     *   <li>An empty KeySet should contain all elements of itself.</li>
     *   <li>An empty KeySet should not contain elements of a non-empty KeySet.</li>
     * </ul>
     */
    @Test
    public void testContainsAllOnEmptyKeySet() {
        HMap anotherMap = new MapAdapter();
        HSet anotherKeySet = anotherMap.keySet();
        assertTrue("An empty KeySet should contain all elements of another empty KeySet", this.map.keySet().containsAll(anotherKeySet));
        assertTrue("An empty KeySet should contain all elements of itself", this.map.keySet().containsAll(this.map.keySet()));
        anotherMap.put("testKey", "testValue"); 
        assertFalse("An empty KeySet should not contain elements of a non-empty KeySet", this.map.keySet().containsAll(anotherMap.keySet()));
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles null input.
     *   The rationale is to confirm that MapAdapter does not accept null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the empty KeySet with null.</li>
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
     *   <li>Calling containsAll on the KeySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyKeySetWithNull() {
        this.map.keySet().containsAll(null);
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty KeySet with another KeySet containing null keys throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles another KeySet with null keys.
     *   The rationale is to confirm that MapAdapter does not accept null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry with a null key.</li>
     *   <li>Get the KeySet of the DummyTestMap.</li>
     *   <li>Call the containsAll method on the empty KeySet with the KeySet containing null keys.</li>
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
     *   <li>Calling containsAll on the empty KeySet with a KeySet containing null keys should throw a NullPointerException.</li>
     * </ul>    
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyKeySetWithAnotherKeySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        HSet anotherKeySet = anotherMap.keySet();
        this.map.keySet().containsAll(anotherKeySet);
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on an empty KeySet returns 0.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns 0 when the KeySet is empty.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the hashCode method on the empty KeySet.</li>
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
     *   <li>Hash code of an empty KeySet should be 0.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnEmptyKeySet() {
        assertEquals("Hash code of an empty KeySet should be 0", 0, this.map.keySet().hashCode());
    }

    /**
     * Summary:
     *   Verifies that the isEmpty method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the isEmpty method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns true when the KeySet is empty.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty KeySet is indeed empty.</li>
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
     *   <li>isEmpty on an empty KeySet should return true.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnEmptyKeySet() {
        assertTrue("KeySet should be empty", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns false when there are no elements to remove.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty KeySet.</li>
     *   <li>Call the removeAll method on the empty KeySet with another empty KeySet returns false.</li>
     *   <li>Check that the KeySet is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the removeAll operation.</li>
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
     *   <li>RemoveAll on an empty KeySet should return false.</li>
     *   <li>The KeySet should still be empty after the removeAll operation.</li>
     *   <li>The size of the KeySet should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyKeySet() {
        HSet anotherKeySet = new MapAdapter().keySet();
        assertFalse("RemoveAll on an empty KeySet should return false", this.map.keySet().removeAll(anotherKeySet));
        assertTrue("KeySet should still be empty after removeAll", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after removeAll", 0, this.map.keySet().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty KeySet with an empty non-KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty KeySet with an empty non-KeySet.
     *   The rationale is to confirm that the method returns false when there are no elements to remove.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty KeySet with another empty non-KeySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the KeySet is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the removeAll operation.</li>
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
     *   <li>RemoveAll on an empty KeySet with an empty non-KeySet should return false.</li>
     *   <li>The KeySet should still be empty after the removeAll operation.</li>
     *   <li>The size of the KeySet should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyKeySetWithAnEmptyNonKeySet() {
        assertFalse("RemoveAll on an empty KeySet with an empty non-KeySet should return false", this.map.keySet().removeAll(this.map.entrySet()));
        assertTrue("KeySet should still be empty after removeAll with an empty non-KeySet", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after removeAll with an empty non-KeySet", 0, this.map.keySet().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty KeySet with a non-empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty KeySet with a non-empty KeySet.
     *   The rationale is to confirm that the method returns false when there are no elements to remove.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the removeAll method on the empty KeySet with the non-empty KeySet.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the KeySet is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the removeAll operation.</li>
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
     *   <li>RemoveAll on an empty KeySet with a non-empty KeySet should return false.</li>
     *   <li>The KeySet should still be empty after the removeAll operation.</li>
     *   <li>The size of the KeySet should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyKeySetWithANonEmptyKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RemoveAll on an empty KeySet with a non-empty KeySet should return false", this.map.keySet().removeAll(anotherMap.keySet()));
        assertTrue("KeySet should still be empty after removeAll with a non-empty KeySet", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles null input.
     *   The rationale is to confirm that the method does not accept null as a valid argument.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty KeySet with null.</li>
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
     *   <li>Calling removeAll on the KeySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyKeySetWithNull() {
        this.map.keySet().removeAll(null);
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty KeySet with a KeySet containing null keys behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty KeySet with a KeySet containing null keys.
     *   The rationale is to confirm that the method does not allow removing null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap with an entry with a null key.</li>
     *   <li>Get the KeySet of the DummyTestMap.</li>
     *   <li>Call the removeAll method on the empty KeySet with the KeySet containing null keys.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the KeySet is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the removeAll operation.</li>
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
     *   <li>RemoveAll on an empty KeySet with a KeySet containing null keys should return false.</li>
     *   <li>The KeySet should still be empty after removeAll with a KeySet containing null keys.</li>
     *   <li>The size of the KeySet should still be 0 after removeAll with a KeySet containing null keys.</li>
     * </ul>    
     */
    @Test
    public void testRemoveAllOnEmptyKeySetWithKeySetWithNullKeys() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("RemoveAll on an empty KeySet with a KeySet containing null keys should return false", this.map.keySet().removeAll(anotherMap.keySet()));
        assertTrue("KeySet should still be empty after removeAll with a KeySet with null keys", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after removeAll with a KeySet with null keys", 0, this.map.keySet().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns false when there are no elements to retain.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter containing a key-value pair.</li>
     *   <li>Call the retainAll method on the empty KeySet with the other MapAdapter's KeySet returns false.</li>
     *   <li>Check that the KeySet is still empty after the retainAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the retainAll operation.</li>
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
     *   <li>RetainAll on an empty KeySet should return false.</li>
     *   <li>The KeySet should still be empty after the retainAll operation.</li>
     *   <li>The size of the KeySet should still be 0 after the retainAll operation.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty KeySet should return false", this.map.keySet().retainAll(anotherMap.keySet()));
        assertTrue("KeySet should still be empty after retainAll", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after retainAll", 0, this.map.keySet().size());
    }
    
    /**
     * Summary:
     *   Verifies that the retainAll method on an empty KeySet with an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly on an empty KeySet with another empty KeySet.
     *   The rationale is to confirm that the method returns false when there are no elements to retain.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the retainAll method on the empty KeySet with the other MapAdapter's KeySet returns false.</li>
     *   <li>Check that the KeySet is still empty after the retainAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the retainAll operation.</li>
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
     *   <li>RetainAll on an empty KeySet with an empty KeySet should return false.</li>
     *   <li>The KeySet should still be empty after the retainAll operation.</li>
     *   <li>The size of the KeySet should still be 0 after the retainAll operation.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyKeySetWithEmptyKeySet() {
        HMap anotherMap = new MapAdapter();
        assertFalse("RetainAll on an empty KeySet with an empty KeySet should return false", this.map.keySet().retainAll(anotherMap.keySet()));
        assertTrue("KeySet should still be empty after retainAll with an empty KeySet", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after retainAll with an empty KeySet", 0, this.map.keySet().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty KeySet with a non-KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly on an empty KeySet with a non-KeySet.
     *   The rationale is to confirm that the method returns false when there are no elements to retain.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the retainAll method on the empty KeySet with the other MapAdapter's EntrySet returns false.</li>
     *   <li>Check that the KeySet is still empty after the retainAll operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the retainAll operation.</li>
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
     *   <li>RetainAll on an empty KeySet with a non-KeySet should return false.</li>
     *   <li>The KeySet should still be empty after the retainAll operation.</li>
     *   <li>The size of the KeySet should still be 0 after the retainAll operation.</li>
     * </ul>
     */
    @Test 
    public void testRetainAllOnEmptyKeySetWithANonKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty KeySet with a non-KeySet should return false", this.map.keySet().retainAll(anotherMap.entrySet()));
        assertTrue("KeySet should still be empty after retainAll with a non-KeySet", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles null input.
     *   The rationale is to confirm that MapAdapter does not accept null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the empty KeySet with null.</li>
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
     *   <li>Calling retainAll on the KeySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllOnEmptyKeySetWithNull() {
        this.map.keySet().retainAll(null);
    }

    /**
     * Summary:
     *   Verifies the size of an empty KeySet.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns 0 when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the size method on the empty KeySet.</li>
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
     *   <li>Calling size on the KeySet should return 0.</li>
     * </ul>
     */
    @Test
    public void testSizeOnEmptyKeySet() {
        assertEquals("Size of an empty KeySet should be 0", 0, this.map.keySet().size());
    }   

    /**
     * Summary:
     *   Verifies that the toArray method on an empty KeySet returns an empty array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns an empty array when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty KeySet.</li>
     *   <li>Check that the returned array is not null and has a length of 0.</li>
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
     *   <li>The return array should not be null.</li>
     *   <li>The return array should have a length of 0.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyKeySet() {
        Object[] array = this.map.keySet().toArray();
        assertNotNull("ToArray on an empty KeySet should not return null", array);
        assertEquals("ToArray on an empty KeySet should return an empty array", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty KeySet is free from the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the call.
     *   The rationale is to confirm that the KeySet is independent of the MapAdapter after the toArray call.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty KeySet.</li>
     *   <li>Modify the MapAdapter by adding an entry.</li>
     *   <li>Check that the array returned by toArray remains unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyKeySetIsFreeFromMap() {
        Object[] array = this.map.keySet().toArray();
        this.map.put("testKey", "testValue");
        assertEquals("array should not be affected by changes in the map after toArray", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty KeySet with a same size array does not modify the array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not modify the provided array when it has the same size as the KeySet.
     *   The rationale is to confirm that the method behaves correctly and does not alter the input array.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty KeySet with a same size array.</li>
     *   <li>Check that the returned array is not null and has a length of 0.</li>
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
     *   <li>The return array should not be null.</li>
     *   <li>The return array should have a length of 0.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyKeySetWithSameSize() {
        Object[] array = new Object[0];
        array = this.map.keySet().toArray(array);
        assertNotNull("ToArray on an empty KeySet with a same size array should not return null", array);
        assertEquals("ToArray on an empty KeySet with a same size array should return an empty array", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty KeySet with a greater array does not modify the array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not modify the provided array when it has a greater size than the KeySet.
     *   The rationale is to confirm that the method behaves correctly and does not alter the input array.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty KeySet with a greater array.</li>
     *   <li>Check that the returned array is not null and has a length of 1.</li>
     *   <li>Check that the first element of the array is null.</li>
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
     *   <li>The return array should not be null.</li>
     *   <li>The return array should have a length of 1.</li>
     *   <li>The first element of the array should be null.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyKeySetWithGreaterArray() {
        Object[] array = new Object[1];
        array = this.map.keySet().toArray(array);
        assertNotNull("ToArray on an empty KeySet with a greater array should not return null", array);
        assertEquals("ToArray on an empty KeySet with a greater array should return an array with its original size", 1, array.length);
        assertNull("First element of the array after the copied KeySet should be null", array[0]);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty KeySet with a null array throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method throws a NullPointerException when the provided array is null.
     *   The rationale is to confirm that the method behaves correctly and does not accept null arrays.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty KeySet with a null array.</li>
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
     *   <li>A NullPointerException should be thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayOnEmptyKeySetWithNull() {
        this.map.keySet().toArray(null);
    }

    /**
     * Summary:
     *   Verifies that the toString method on an empty KeySet returns "[]".
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns "[]" when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toString method on the empty KeySet.</li>
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
     *   <li>Calling toString on the KeySet should return "[]".</li>
     * </ul>
     */
    @Test
    public void testToStringOnEmptyKeySet() {
        assertEquals("ToString on an empty KeySet should return \"[]\"", "[]",this.map.keySet().toString());
    }

    /**
     * Summary:
     *   Verifies that the empty KeySet is backed by the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the empty KeySet is backed by the MapAdapter.
     *   The rationale is to confirm that the KeySet reflects the current state of the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the KeySet is empty.</li>
     *   <li>Add an entry to the MapAdapter.</li>
     *   <li>Check that the KeySet is not empty after adding an entry to the MapAdapter.</li>
     *   <li>Check that the KeySet size matches the MapAdapter size after adding an entry.</li>
     *   <li>Check that the KeySet toString matches the expected string after adding an entry.</li>
     *   <li> Clear the KeySet.</li>
     *   <li>Check that the KeySet is empty after clearing.</li>
     *   <li>Check that the MapAdapter is empty after clearing the KeySet.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>KeySet should be empty if MapAdapter is empty.</li>
     *   <li>KeySet should not be empty after adding an entry to MapAdapter.</li>
     *   <li>KeySet size should match MapAdapter size after adding an entry.</li>
     *   <li>KeySet toString should match MapAdapter keySet toString after adding an entry.</li>
     *   <li>KeySet should be empty after clearing.</li>
     *   <li>MapAdapter should be empty after clearing KeySet.</li>
     * </ul>
     */
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

    /**
     * Summary:
     *   Verifies that the equals method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns true when comparing two empty KeySets.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the equals method on the empty KeySet with another empty KeySet.</li>
     *   <li>Check that the equals method returns true.</li>
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
     *   <li>Calling equals on the KeySet with another empty KeySet should return true.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyKeySet() {
        HSet keySet = this.map.keySet();
        HSet anotherKeySet = new MapAdapter().keySet();
        assertTrue("Two empty KeySets should be equal", keySet.equals(anotherKeySet));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty KeySet with a non-empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty KeySet with a non-empty KeySet.
     *   The rationale is to confirm that the method returns false when comparing an empty KeySet with a non-empty KeySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the equals method on the empty KeySet with the non-empty KeySet.</li>
     *   <li>Check that the equals method returns false.</li>
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
     *   <li>An empty KeySet should not be equal to a non-empty KeySet.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyKeySetWithNonEmptyKeySet() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty KeySet should not be equal to a non-empty KeySet", this.map.keySet().equals(anotherMap.keySet()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty KeySet with a non-HSet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty KeySet with a non-HSet.
     *   The rationale is to confirm that the method returns false when comparing an empty KeySet with a non-HSet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter.</li>
     *   <li>Call the equals method on the empty KeySet with the values of the other MapAdapter.</li>
     *   <li>Check that the equals method returns false.</li>
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
     *   <li>An empty KeySet should not be equal to a non-HSet.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyKeySetWithNonHSet() {
        MapAdapter anotherMap = new MapAdapter();
        assertFalse("An empty KeySet should not be equal to a non-HSet", this.map.keySet().equals(anotherMap.values()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty KeySet with null behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty KeySet with null.
     *   The rationale is to confirm that the method returns false when comparing an empty KeySet with null.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the equals method on the empty KeySet with null.</li>
     *   <li>Check that the equals method returns false.</li>
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
     *   <li>An empty KeySet should not be equal to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnKeySetWithNull() {
        assertFalse("Empty KeySet should not be equal to null", this.map.keySet().equals(null));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty KeySet with itself behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty KeySet with itself.
     *   The rationale is to confirm that the method returns true when comparing an empty KeySet with itself.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the equals method on the empty KeySet with itself.</li>
     *   <li>Check that the equals method returns true.</li>
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
     *   <li>An empty KeySet should be equal to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyKeySetWithItself() {
        assertTrue("Empty KeySet should be equal to itself", this.map.keySet().equals(this.map.keySet()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty KeySet is symmetric.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method is symmetric when comparing two empty KeySets.
     *   The rationale is to confirm that both KeySets consider each other equal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty KeySet.</li>
     *   <li>Check that both KeySets are equal to each other.</li>
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
     *   <li>Both empty KeySets should be equal to each other.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyKeySetIsSymmetric() {
        HSet anotherEmptyKeySet = new MapAdapter().keySet();
        assertTrue("Equals should be symmetric", anotherEmptyKeySet.equals(this.map.keySet()) && this.map.keySet().equals(anotherEmptyKeySet));
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns false when checking for any key in an empty KeySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the empty KeySet with a test key.</li>
     *   <li>Check that the contains method returns false.</li>
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
     *   <li>Calling contains on the KeySet with a test key should return false.</li>
     * </ul>
     */
    @Test
    public void testContainsOnEmptyKeySet() {
        assertFalse("Empty KeySet should not contain any keys", this.map.keySet().contains("testKey"));
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles null input.
     *   The rationale is to confirm that MapAdapter does not accept null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the empty KeySet with null.</li>
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
     *   <li>Calling contains on the KeySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyKeySetWithNull() {
        this.map.keySet().contains(null);
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty KeySet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns false when trying to remove a key from an empty KeySet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the empty KeySet with a test key.</li>
     *   <li>Check that the remove method returns false.</li>
     *   <li>Check that the KeySet is still empty after the remove operation.</li>
     *   <li>Check that the size of the KeySet is still 0 after the remove operation.</li>
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
     *   <li>Calling remove on the KeySet with a test key should return false.</li>
     *   <li>The KeySet should still be empty after the remove operation.</li>
     *   <li>The size of the KeySet should still be 0 after the remove operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnEmptyKeySet() {
        assertFalse("Remove on an empty KeySet should return false", this.map.keySet().remove("testKey"));
        assertTrue("KeySet should still be empty after remove", this.map.keySet().isEmpty());
        assertEquals("Size of KeySet should still be 0 after remove", 0, this.map.keySet().size());
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty KeySet with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles null input.
     *   The rationale is to confirm that MapAdapter does not accept null keys.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the empty KeySet with null.</li>
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
     *   <li>Calling remove on the KeySet with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyKeySetWithNull() {
        this.map.keySet().remove(null);
    }

    /**
     * Summary:
     *   Verifies that the iterator on an empty KeySet is not null.
     * <p>
     * Test Case Design:
     *   This test ensures that the iterator method behaves correctly on an empty KeySet.
     *   The rationale is to confirm that the method returns a valid iterator even when the KeySet is empty.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the iterator method on the empty KeySet.</li>
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
     *   <li>Calling iterator on the KeySet should return a non-null iterator.</li>
     * </ul>
     */
    @Test
    public void testIteratorOnEmptyKeySet() {
        HIterator iterator = this.map.keySet().iterator();
        assertNotNull("Iterator on an empty KeySet should not be null", iterator);
    }

    /*--------------------------------------------------------------*/
    /*--------------------- ValueCollection TESTS ------------------*/
    /*--------------------------------------------------------------*/

    /**
     * Summary:
     *   Verifies that the add method on an empty ValueCollection throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the add method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the ValueCollection does not allow addition of new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the add method on the empty ValueCollection with a test value.</li>
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
     *   <li>Calling add on the ValueCollection should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddOnEmptyValueCollection() {
        this.map.values().add("anything");
    }

    /**
     * Summary:
     *   Verifies that the addAll method on an empty ValueCollection throws an UnsupportedOperationException.
     * <p>
     * Test Case Design:
     *   This test ensures that the addAll method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the ValueCollection does not allow addition of multiple new elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the addAll method on the empty ValueCollection with itself.</li>
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
     *   <li>Calling addAll on the ValueCollection should throw an UnsupportedOperationException.</li>
     * </ul>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllOnEmptyValueCollection() {
        this.map.values().addAll(this.map.values());
    }

    /**
     * Summary:
     *   Verifies that the clear method on an empty ValueCollection does not throw an exception.
     * <p>
     * Test Case Design:
     *   This test ensures that the clear method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the ValueCollection can be cleared without issues.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the clear method on the empty ValueCollection.</li>
     *   <li>Check that the ValueCollection is still empty after the clear operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the clear operation.</li>
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
     *   <li>Calling clear on the ValueCollection should not throw an exception.</li>
     *   <li>The ValueCollection should still be empty after the clear operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the clear operation.</li>
     * </ul>
     */
    @Test
    public void testClearOnEmptyValueCollection() {
        HCollection values = this.map.values();
        values.clear();
        assertTrue("ValueCollection should be empty after clear", values.isEmpty());
        assertEquals("Size of ValueCollection should be 0 after clear", 0, values.size());
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the ValueCollection does not contain any elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call the contains method on the other empty MapAdapter's ValueCollection.</li>
     *   <li>Check that the contains method returns true when checking against another empty ValueCollection.</li>
     *   <li>Check that the contains method returns true when checking against itself.</li>
     *   <li>Insert a key-value pair into the other MapAdapter.</li>
     *   <li>Check that the contains method returns false when checking against a non-empty ValueCollection.</li>
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
     *   <li>An empty ValueCollection should contain all elements of another empty ValueCollection.</li>
     *   <li>An empty ValueCollection should contain all elements of itself.</li>
     *   <li>An empty ValueCollection should not contain elements of a non-empty ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testContainsAllOnEmptyValueCollection() {
        HMap anotherMap = new MapAdapter();
        HCollection anotherValueCollection = anotherMap.values();
        assertTrue("An empty ValueCollection should contain all elements of another empty ValueCollection", this.map.values().containsAll(anotherValueCollection));
        assertTrue("An empty ValueCollection should contain all elements of itself", this.map.values().containsAll(this.map.values()));
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty ValueCollection should not contain elements of a non-empty ValueCollection", this.map.values().containsAll(anotherMap.values()));
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null collections.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the containsAll method on the empty ValueCollection with null.</li>
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
     *   <li>Calling containsAll on the ValueCollection with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyValueCollectionWithNull() {
        this.map.values().containsAll(null);
    }

    /**
     * Summary:
     *   Verifies that the containsAll method on an empty ValueCollection with another ValueCollection containing null values throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the containsAll method correctly handles another ValueCollection with null values.
     *   The rationale is to confirm that the ValueCollection does not accept collections with null values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another DummyTestMap with a null value.</li>
     *   <li>Call the containsAll method on the empty ValueCollection with the other DummyTestMap's ValueCollection.</li>
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
     *   <li>Calling containsAll on the ValueCollection with another ValueCollection containing null values should throw a NullPointerException.</li>
     * </ul>    
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllOnEmptyValueCollectionWithAnotherValueCollectionWithNullValues() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        HCollection anotherValueCollection = anotherMap.values();
        this.map.values().containsAll(anotherValueCollection);
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on an empty ValueCollection returns 0.
     * <p>
     * Test Case Design:
     *   This test ensures that the hashCode method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns 0 when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the hashCode method on the empty ValueCollection.</li>
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
     *   <li>Calling hashCode on the ValueCollection should return 0.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnEmptyValueCollection() {
        assertEquals("Hash code of an empty ValueCollection should be 0", 0, this.map.values().hashCode());
    }

    /**
     * Summary:
     *   Verifies that the isEmpty method on an empty ValueCollection returns true.
     * <p>
     * Test Case Design:
     *   This test ensures that the isEmpty method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns true when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the isEmpty method on the empty ValueCollection.</li>
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
     *   <li>Calling isEmpty on the ValueCollection should return true.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnEmptyValueCollection() {
        assertTrue("ValueCollection should be empty", this.map.values().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns false when there are no elements to remove.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty ValueCollection with another empty Collection.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the removeAll operation.</li>
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
     *   <li>Calling removeAll on the ValueCollection should return false.</li>
     *   <li>The ValueCollection should still be empty after the removeAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyValueCollection() {
        HCollection anotherValueCollection = new MapAdapter().values();
        assertFalse("RemoveAll on an empty ValueCollection should return false", this.map.values().removeAll(anotherValueCollection));
        assertTrue("ValueCollection should still be empty after removeAll", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after removeAll", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty ValueCollection with an empty non-ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty ValueCollection with an empty non-ValueCollection.
     *   The rationale is to confirm that the method does not modify the ValueCollection when called with another non-ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty ValueCollection with an empty non-ValueCollection.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the removeAll operation.</li>
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
     *   <li>Calling removeAll on the ValueCollection with an empty non-ValueCollection should return false.</li>
     *   <li>The ValueCollection should still be empty after the removeAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyValueCollectionWithAnEmptyNonValueCollection() {
        assertFalse("RemoveAll on an empty ValueCollection with an empty non-ValueCollection should return false", this.map.values().removeAll(this.map.entrySet()));
        assertTrue("ValueCollection should still be empty after removeAll with an empty non-ValueCollection", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after removeAll with an empty non-ValueCollection", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty ValueCollection with a non-empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method behaves correctly on an empty ValueCollection with a non-empty ValueCollection.
     *   The rationale is to confirm that the method does not modify the ValueCollection when called with another non-empty ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the removeAll method on the empty ValueCollection with the non-empty ValueCollection.</li>
     *   <li>Check that the removeAll method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the removeAll operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the removeAll operation.</li>
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
     *   <li>Calling removeAll on the ValueCollection with a non-empty ValueCollection should return false.</li>
     *   <li>The ValueCollection should still be empty after the removeAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyValueCollectionWithANonEmptyValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RemoveAll on an empty ValueCollection with a non-empty ValueCollection should return false", this.map.values().removeAll(anotherMap.values()));
        assertTrue("ValueCollection should still be empty after removeAll with a non-empty ValueCollection", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after removeAll with a non-empty ValueCollection", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null collections.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty ValueCollection with null.</li>
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
     *   <li>Calling removeAll on the ValueCollection with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllOnEmptyValueCollectionWithNull() {
        this.map.values().removeAll(null);
    }

    /**
     * Summary:
     *   Verifies that the removeAll method on an empty ValueCollection with a ValueCollection containing null values behaves as expected.
     * <p>
     * Test Case Design:
     *   This test ensures that the removeAll method correctly handles a ValueCollection with null values.
     *   The rationale is to confirm that the ValueCollection can handle null values without throwing exceptions.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the removeAll method on the empty ValueCollection with a ValueCollection containing null values.</li>
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
     *   <li>Calling removeAll on the ValueCollection with a ValueCollection containing null values should return false.</li>
     *   <li>The ValueCollection should still be empty after the removeAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the removeAll operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveAllOnEmptyValueCollectionWithValueCollectionWithNullValues() {
        DummyTestMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        HCollection anotherValueCollection = anotherMap.values();
        assertFalse("RemoveAll should return false when removing a ValueCollection with null values", this.map.values().removeAll(anotherValueCollection));
        assertTrue("ValueCollection should still be empty after removeAll with a ValueCollection with null values", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after removeAll with a ValueCollection with null values", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns false when there are no elements to retain.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the empty ValueCollection with another empty Collection.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the retainAll operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the retainAll operation.</li>
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
     *   <li>Calling retainAll on the ValueCollection should return false.</li>
     *   <li>The ValueCollection should still be empty after the retainAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the retainAll operation.</li>
     * </ul>
     */
    @Test
    public void testRetainAllOnEmptyValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty ValueCollection should return false", this.map.values().retainAll(anotherMap.values()));
        assertTrue("ValueCollection should still be empty after retainAll", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after retainAll", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty ValueCollection with an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly on an empty ValueCollection with another empty ValueCollection.
     *   The rationale is to confirm that the method does not modify the ValueCollection when called with another empty ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the empty ValueCollection with another empty ValueCollection.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the retainAll operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the retainAll operation.</li>
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
     *   <li>Calling retainAll on the ValueCollection with another empty ValueCollection should return false.</li>
     *   <li>The ValueCollection should still be empty after the retainAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the retainAll operation.</li>
     * </ul>
     */
    @Test 
    public void testRetainAllOnEmptyValueCollectionWithEmptyValueCollection() {
        HMap anotherMap = new MapAdapter();
        assertFalse("RetainAll on an empty ValueCollection with an empty ValueCollection should return false", this.map.values().retainAll(anotherMap.values()));
        assertTrue("ValueCollection should still be empty after retainAll with an empty ValueCollection", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after retainAll with an empty ValueCollection", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty ValueCollection with a non-empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method behaves correctly on an empty ValueCollection with a non-empty ValueCollection.
     *   The rationale is to confirm that the method does not modify the ValueCollection when called with another non-empty ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Call the retainAll method on the empty ValueCollection with the non-empty ValueCollection.</li>
     *   <li>Check that the retainAll method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the retainAll operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the retainAll operation.</li>
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
     *   <li>Calling retainAll on the ValueCollection with a non-empty ValueCollection should return false.</li>
     *   <li>The ValueCollection should still be empty after the retainAll operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the retainAll operation.</li>
     * </ul>
     */
    @Test 
    public void testRetainAllOnEmptyValueCollectionWithANonValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("RetainAll on an empty ValueCollection with a non-ValueCollection should return false", this.map.values().retainAll(anotherMap.entrySet()));
        assertTrue("ValueCollection should still be empty after retainAll with a non-ValueCollection", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after retainAll with a non-ValueCollection", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the retainAll method on an empty ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the retainAll method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null collections.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the retainAll method on the empty ValueCollection with null.</li>
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
     *   <li>Calling retainAll on the ValueCollection with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllOnEmptyValueCollectionWithNull() {
        this.map.values().retainAll(null);
    }

    /**
     * Summary:
     *   Verifies that the size method on an empty ValueCollection returns 0.
     * <p>
     * Test Case Design:
     *   This test ensures that the size method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns 0 when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the size method on the empty ValueCollection.</li>
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
     *   <li>Calling size on the ValueCollection should return 0.</li>
     * </ul>
     */
    @Test
    public void testSizeOnEmptyValueCollection() {
        assertEquals("Size of an empty ValueCollection should be 0", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty ValueCollection returns an empty array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns an empty array when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty ValueCollection.</li>
     *   <li>Check that the returned array is not null and has a length of 0.</li>
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
     *   <li>Calling toArray on the ValueCollection should return a non-null empty array.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyValueCollection() {
        Object[] array = this.map.values().toArray();
        assertNotNull("ToArray on an empty ValueCollection should not return null", array);
        assertEquals("ToArray on an empty ValueCollection should return an empty array", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty ValueCollection is free from changes in the MapAdapter after the call.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method does not reflect changes made to the MapAdapter after the call.
     *   The rationale is to confirm that the returned array remains unaffected by subsequent modifications to the MapAdapter.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty ValueCollection.</li>
     *   <li>Modify the MapAdapter by adding a key-value pair.</li>
     *   <li>Check that the array returned by toArray remains unchanged.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter contains a key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The array returned by toArray should not be affected by changes in the MapAdapter after the call.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyValueCollectionIsFreeFromMap() {
        Object[] array = this.map.values().toArray();
        this.map.put("testKey", "testValue");
        assertEquals("array should not be affected by changes in the map after toArray", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty ValueCollection with a same size array returns an empty array.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly when provided with an array of the same size as the ValueCollection.
     *   The rationale is to confirm that the method returns an empty array when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create an empty array of size 0.</li>
     *   <li>Call the toArray method on the empty ValueCollection with the same size array.</li>
     *   <li>Check that the returned array is not null and has a length of 0.</li>
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
     *   <li>Calling toArray on an empty ValueCollection with a same size array should return a non-null empty array.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyValueCollectionWithSameSize() {
        Object[] array = new Object[0];
        array = this.map.values().toArray(array);
        assertNotNull("ToArray on an empty ValueCollection with a same size array should not return null", array);
        assertEquals("ToArray on an empty ValueCollection with a same size array should return an empty array", 0, array.length);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty ValueCollection with a greater size array returns an array of the original size.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method behaves correctly when provided with an array larger than the ValueCollection.
     *   The rationale is to confirm that the method returns an array of the original size, with null elements for any missing values.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create an empty array of size 1.</li>
     *   <li>Call the toArray method on the empty ValueCollection with the greater size array.</li>
     *   <li>Check that the returned array is not null and has a length of 1.</li>
     *   <li>Check that the first element of the array is null.</li>
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
     *   <li>Calling toArray on an empty ValueCollection with a greater size array should return a non-null array of size 1.</li>
     *   <li>The first element of the returned array should be null.</li>
     * </ul>
     */
    @Test
    public void testToArrayOnEmptyValueCollectionWithGreaterArray() {
        Object[] array = new Object[1];
        array = this.map.values().toArray(array);
        assertNotNull("ToArray on an empty ValueCollection with a greater array should not return null", array);
        assertEquals("ToArray on an empty ValueCollection with a greater array should return an array with its original size", 1, array.length);
        assertNull("First element of the array after the copied ValueCollection should be null", array[0]);
    }

    /**
     * Summary:
     *   Verifies that the toArray method on an empty ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the toArray method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null arrays.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toArray method on the empty ValueCollection with null.</li>
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
     *   <li>Calling toArray on the ValueCollection with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayOnEmptyValueCollectionWithNull() {
        this.map.values().toArray(null);
    }

    /**
     * Summary:
     *   Verifies that the toString method on an empty ValueCollection returns "[]".
     * <p>
     * Test Case Design:
     *   This test ensures that the toString method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns an empty string representation when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the toString method on the empty ValueCollection.</li>
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
     *   <li>Calling toString on the ValueCollection should return "[]".</li>
     * </ul>
     */
    @Test
    public void testToStringOnEmptyValueCollection() {
        assertEquals("ToString on an empty ValueCollection should return \"[]\"", "[]",this.map.values().toString());
    }

    /**
     * Summary:
     *   Verifies that the ValueCollection on an empty MapAdapter is backed by the MapAdapter.
     * <p>
     * Test Case Design:
     *   This test ensures that the ValueCollection is correctly backed by the MapAdapter.
     *   The rationale is to confirm that changes in the MapAdapter reflect in the ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the ValueCollection is empty when the MapAdapter is empty.</li>
     *   <li>Add an entry to the MapAdapter and check that the ValueCollection is not empty.</li>
     *   <li>Check that the size of the ValueCollection matches the size of the MapAdapter after adding an entry.</li>
     *   <li>Check that the ValueCollection toString reflects the added entry.</li>
     *   <li>Clear the ValueCollection and check that both the ValueCollection and MapAdapter are empty.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     * <p>
     * Post-Condition:
     *   The MapAdapter is cleared.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should be empty when the MapAdapter is empty.</li>
     *   <li>After adding an entry to the MapAdapter, the ValueCollection should not be empty.</li>
     *   <li>The size of the ValueCollection should match the size of the MapAdapter after adding an entry.</li>
     *   <li>The ValueCollection toString should reflect the added entry.</li>
     *   <li>After clearing the ValueCollection, both the ValueCollection and MapAdapter should be empty.</li>
     * </ul>
     */
    @Test
    public void testEmptyValueCollectionIsBackedByMapAdapter() {
        HCollection valueCollection = this.map.values();
        assertTrue("ValueCollection should be empty if MapAdapter is empty", valueCollection.isEmpty());
        this.map.put("testKey", "testValue");
        assertFalse("MapAdapter should not be empty after adding an entry", this.map.isEmpty());
        assertFalse("ValueCollection should not be empty after adding an entry to MapAdapter", valueCollection.isEmpty());
        assertEquals("ValueCollection size should match MapAdapter size after adding an entry", 1, valueCollection.size());
        assertEquals("ValueCollection toString should be \"[testValue]\"", "[testValue]", valueCollection.toString());
        valueCollection.clear();
        assertTrue("ValueCollection should be empty after clearing", valueCollection.isEmpty());
        assertTrue("MapAdapter should be empty after clearing ValueCollection", this.map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns true when compared with another empty ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty ValueCollection is equal to another empty ValueCollection.</li>
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
     *   <li>The empty ValueCollection should be equal to another empty ValueCollection.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyValueCollection() {
        HCollection anotherValueCollection = new MapAdapter().values();
        assertTrue("Two empty ValueCollections should be equal", this.map.values().equals(anotherValueCollection));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty ValueCollection with a non-empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty ValueCollection with a non-empty ValueCollection.
     *   The rationale is to confirm that the method returns false when compared with a non-empty ValueCollection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with a key-value pair.</li>
     *   <li>Check that the empty ValueCollection is not equal to the non-empty ValueCollection.</li>
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
     *   <li>The empty ValueCollection should not be equal to a non-empty ValueCollection.</li>
     * </ul>    
     */
    @Test
    public void testEqualsOnEmptyValueCollectionWithNonEmptyValueCollection() {
        MapAdapter anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertFalse("An empty ValueCollection should not be equal to a non-empty ValueCollection", this.map.values().equals(anotherMap.values()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty ValueCollection with an HSet behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty ValueCollection with an HSet.
     *   The rationale is to confirm that the method returns false when compared with an HSet.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter and get its keySet (HSet).</li>
     *   <li>Check that the empty ValueCollection is not equal to the HSet.</li>
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
     *   <li>The empty ValueCollection should not be equal to an HSet.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyValueCollectionWithHSet() {
        MapAdapter anotherMap = new MapAdapter();
        assertFalse("An empty ValueCollection should not be equal to an HSet", this.map.values().equals(anotherMap.keySet()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty ValueCollection with null behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null for equality checks.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty ValueCollection is not equal to null.</li>
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
     *   <li>The empty ValueCollection should not be equal to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnValueCollectionWithNull() {
        assertFalse("Empty ValueCollection should not be equal to null", this.map.values().equals(null));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty ValueCollection with itself behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves correctly on an empty ValueCollection with itself.
     *   The rationale is to confirm that the method returns true when compared with itself.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty ValueCollection is equal to itself.</li>
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
     *   <li>The empty ValueCollection should be equal to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyValueCollectionWithItself() {
        assertTrue("Empty ValueCollection should be equal to itself", this.map.values().equals(this.map.values()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty ValueCollection is symmetric.
     * <p>
     * Test Case Design:
     *   This test ensures that the equals method behaves symmetrically on an empty ValueCollection.
     *   The rationale is to confirm that equality checks are symmetric.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another empty ValueCollection.</li>
     *   <li>Check that the two empty ValueCollections are equal in both directions.</li>
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
     *   <li>Equals should be symmetric for two empty ValueCollections.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyValueCollectionIsSymmetric() {
        HCollection anotherEmptyValueCollection = new MapAdapter().values();
        assertTrue("Equals should be symmetric", anotherEmptyValueCollection.equals(this.map.values()) && this.map.values().equals(anotherEmptyValueCollection));
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns false when there are no elements.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the empty ValueCollection with a value.</li>
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
     *   <li>Calling contains on the ValueCollection with any value should return false.</li>
     * </ul>
     */
    @Test
    public void testContainsOnEmptyValueCollection() {
        assertFalse("Empty ValueCollection should not contain any values", this.map.values().contains("testvalue"));
    }

    /**
     * Summary:
     *   Verifies that the contains method on an empty ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the contains method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null for containment checks.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the contains method on the empty ValueCollection with null.</li>
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
     *   <li>Calling contains on the ValueCollection with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsOnEmptyValueCollectionWithNull() {
        this.map.values().contains(null);
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the method returns false when there are no elements to remove.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the empty ValueCollection with a value.</li>
     *   <li>Check that the remove method returns false.</li>
     *   <li>Check that the ValueCollection is still empty after the remove operation.</li>
     *   <li>Check that the size of the ValueCollection is still 0 after the remove operation.</li>
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
     *   <li>Calling remove on the ValueCollection with any value should return false.</li>
     *   <li>The ValueCollection should still be empty after the remove operation.</li>
     *   <li>The size of the ValueCollection should still be 0 after the remove operation.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnEmptyValueCollection() {
        assertFalse("Remove on an empty ValueCollection should return false", this.map.values().remove("testValue"));
        assertTrue("ValueCollection should still be empty after remove", this.map.values().isEmpty());
        assertEquals("Size of ValueCollection should still be 0 after remove", 0, this.map.values().size());
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty ValueCollection with null throws a NullPointerException.
     * <p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles null input.
     *   The rationale is to confirm that the ValueCollection does not accept null for removal.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Call the remove method on the empty ValueCollection with null.</li>
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
     *   <li>Calling remove on the ValueCollection with null should throw a NullPointerException.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnEmptyValueCollectionWithNull() {
        this.map.values().remove(null);
    }

    /**
     * Summary:
     *   Verifies that the iterator on an empty ValueCollection behaves correctly.
     * <p>
     * Test Case Design:
     *   This test ensures that the iterator behaves correctly on an empty ValueCollection.
     *   The rationale is to confirm that the iterator is not null and does not throw exceptions when iterating over an empty collection.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Get the iterator from the empty ValueCollection.</li>
     *   <li>Check that the iterator is not null.</li>
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
     *   <li>The iterator on an empty ValueCollection should not be null.</li>
     * </ul>
     */
    @Test
    public void testIteratorOnEmptyValueCollection() {
        HIterator iterator = this.map.values().iterator();
        assertNotNull("Iterator on an empty ValueCollection should not be null", iterator);
    }

    /*--------------------------------------------------------------*/ 
    /*--------------------------------------------------------------*/
    /*--------------------------------------------------------------*/

}