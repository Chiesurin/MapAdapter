//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;


/**
 * This test suite verifies the correct behavior of the {@code MapAdapter} implementation when it is empty.
 * It covers all methods and constructors of {@code MapAdapter} that are relevant to the empty state, including default and parameterized constructors, copy constructor, and all public methods such as {@code clear}, {@code containsKey}, {@code containsValue}, {@code entrySet}, {@code equals}, {@code get}, {@code hashCode}, {@code isEmpty}, {@code keySet}, {@code put}, {@code putAll}, {@code remove}, {@code size}, {@code values}, and {@code toString}.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Constructor tests</li>
 *   <li>Empty state behavior</li>
 *   <li>Exception handling (NullPointerException, ClassCastException, IllegalArgumentException)</li>
 *   <li>Equality and hash code</li>
 *   <li>Collection views (entrySet, keySet, values)</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that a newly created {@code MapAdapter} behaves as specified when empty, and that all operations either return the correct result or throw the correct exception. The tests are organized to:
 * <ul>
 *   <li>Verify that all constructors create an empty map and handle invalid arguments appropriately.</li>
 *   <li>Check that all query methods ({@code isEmpty}, {@code size}, {@code containsKey}, {@code containsValue}, {@code get}, etc.) return results consistent with an empty map.</li>
 *   <li>Ensure that mutator methods ({@code put}, {@code putAll}, {@code remove}, {@code clear}) behave correctly and throw exceptions for invalid input (e.g., null keys/values).</li>
 *   <li>Validate the contract of {@code equals} and {@code hashCode} for empty maps.</li>
 *   <li>Check that the string representation of an empty map is as expected.</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of the empty state and to ensure robustness against edge cases and invalid input.
 */
public class TestEmptyMapAdapter {

    /**
     * The empty MapAdapter instance to be tested.
     */
    private HMap map;

    /**
     * Default constructor for TestEmptyMapAdapter.
     */
    public TestEmptyMapAdapter() {
        //intentionally left blank
    }

    /**
     * Sets up the test environment by initializing the empty MapAdapter instance.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Cleans up the test environment by setting the MapAdapter instance to null.
     */
    @After
    public void tearDown() {
        this.map = null;
    }

    /**
     * Summary:
     *   Verifies that the default constructor of MapAdapter creates a non-null, empty map.
     *   Category: Constructor tests
     *<p>
     * Test Case Design:
     *   This test ensures that the default constructor initializes the map to a valid, empty state.
     *   The rationale is to confirm the basic contract of the constructor.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the instance is not null.</li>
     *   <li>Check that the map is empty and its size is zero.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter instance is not null.</li>
     *   <li>The map is empty.</li>
     *   <li>The size of the map is zero.</li>
     * </ul>
     */
    @Test
    public void testMapAdapterDefaultConstructor() {
        assertNotNull("MapAdapter should not be null", map);
        assertTrue("MapAdapter should be empty", this.map.isEmpty());
        assertEquals("Size of MapAdapter should be 0", 0, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the MapAdapter constructor with zero initial capacity creates a non-null, empty map.
     *   Category: Constructor tests
     *<p>
     * Test Case Design:
     *   This test ensures that the constructor initializes the map to a valid, empty state.
     *   The rationale is to confirm the basic contract of the constructor.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of MapAdapter called mapWithInitialCapacity with zero initial capacity.</li>
     *   <li>Check that the instance is not null.</li>
     *   <li>Check that the map is empty and its size is zero.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The mapWithInitialCapacity is not null.</li>
     *   <li>The mapWithInitialCapacity is empty.</li>
     *   <li>The size of the mapWithInitialCapacity is zero.</li>
     * </ul>
     */
    @Test
    public void testMapAdapterConstructorWithZeroInitialCapacity() {
        HMap mapWithInitialCapacity = new MapAdapter(0);
        assertNotNull("MapAdapter with initial capacity should not be null", mapWithInitialCapacity);
        assertTrue("MapAdapter with initial capacity should be empty", mapWithInitialCapacity.isEmpty());
        assertEquals("Size of MapAdapter with initial capacity should be 0", 0, mapWithInitialCapacity.size());
        assertTrue("MapAdapter with zero initial capacity should be equal to an empty MapAdapter", mapWithInitialCapacity.equals(map));
    }  

    /**
     * Summary:
     *   Verifies that the MapAdapter constructor with non zero initial capacity creates a non-null, empty map.
     *   Category: Constructor tests
     *<p>
     * Test Case Design:
     *   This test ensures that the constructor initializes the map to a valid, empty state.
     *   The rationale is to confirm the basic contract of the constructor.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of MapAdapter called mapWithInitialCapacity with non zero initial capacity.</li>
     *   <li>Check that the instance is not null.</li>
     *   <li>Check that the map is empty and its size is zero.</li>
     *   <li>Check that the map is equal to an empty MapAdapter.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The mapWithInitialCapacity is not null.</li>
     *   <li>The mapWithInitialCapacity is empty.</li>
     *   <li>The size of the mapWithInitialCapacity is zero.</li>
     *   <li>The mapWithInitialCapacity is equal to an empty MapAdapter.</li>
     * </ul>
     */
    @Test
    public void testMapAdapterConstructorWithPositiveInitialCapacity() {
        HMap mapWithInitialCapacity = new MapAdapter(1);
        assertNotNull("MapAdapter with initial capacity should not be null", mapWithInitialCapacity);
        assertTrue("MapAdapter with initial capacity should be empty", mapWithInitialCapacity.isEmpty());
        assertEquals("Size of MapAdapter with initial capacity should be 0", 0, mapWithInitialCapacity.size());
        assertTrue("MapAdapter with positive initial capacity should be equal to an empty MapAdapter", mapWithInitialCapacity.equals(map));
    }

    /**
     * Summary:
     *   Verifies that the MapAdapter constructor with negative initial capacity throws an IllegalArgumentException.
     *   Category: Constructor tests
     *<p>
     * Test Case Design:
     *   This test ensures that the constructor handles invalid arguments appropriately.
     *   The rationale is to confirm that the constructor does not allow negative initial capacity.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to create an instance of MapAdapter with negative initial capacity.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is not created.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>An IllegalArgumentException is thrown.</li>
     *   <li>The mapWithNegativeInitialCapacity is not created.</li>
     * </ul>
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMapAdapterConstructorWithNegativeInitialCapacity() {
        HMap mapWithNegativeInitialCapacity = new MapAdapter(-1);
    }

    /**
     * Summary:
     *   Verifies that the MapAdapter copy constructor creates a non-null, empty map.
     *   Category: Constructor tests
     *<p>
     * Test Case Design:
     *   This test ensures that the copy constructor initializes the map to a valid, empty state.
     *   The rationale is to confirm the basic contract of the constructor.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of MapAdapter called copy using the copy constructor with the empty MapAdapter.</li>
     *   <li>Check that the instance is not null.</li>
     *   <li>Check that the map is empty</li>
     *   <li>Check that the size is zero.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The copy MapAdapter is not null.</li>
     *   <li>The copy MapAdapter is empty.</li>
     *   <li>The size of the copy MapAdapter is zero.</li>
     * </ul>
     */
    @Test
    public void testMapAdapterCopyConstructor() {
        HMap copy = new MapAdapter(map);
        assertNotNull("Copy of MapAdapter should not be null", copy);
        assertTrue("Copy of empty MapAdapter should be empty", copy.isEmpty());
        assertEquals("Size of copied MapAdapter should be 0", 0, copy.size());
    }

    /**
     * Summary:
     *   Verifies that the MapAdapter copy constructor throws an IllegalArgumentException when passed a null HMap.
     *   Category: Constructor tests
     *<p>
     * Test Case Design:
     *   This test ensures that the copy constructor handles null arguments appropriately.
     *   The rationale is to confirm that the constructor does not allow null input.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to create an instance of MapAdapter using the copy constructor with a null HMap.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is not created.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>An NullPointerException is thrown.</li>
     *   <li>The copy MapAdapter is not created.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testMapAdapterCopyConstructorWithNullHMap() {
        HMap copy = new MapAdapter(null);
    }

    /**
     * Summary:
     *   Verifies that the clear method on an empty MapAdapter does not change its state.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that calling clear on an empty map does not alter its state.
     *   The rationale is to confirm that clear behaves correctly when the map is already empty.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the clear method on the empty MapAdapter.</li>
     *   <li>Check that the map is still empty.</li>
     *   <li>Check that the size is still zero.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter remains empty after calling clear.</li>
     *   <li>The size of the MapAdapter remains zero after calling clear.</li>
     * </ul>
     */
    @Test
    public void testClearOnEmptyMapAdapter() {
        this.map.clear();
        assertTrue("Clear on empty MapAdapter should not change its state", this.map.isEmpty());
        assertEquals("Size after clear on empty MapAdapter should still be 0", 0, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the containsKey method on an empty MapAdapter returns false for any key.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that containsKey behaves correctly when the map is empty.
     *   The rationale is to confirm that no keys are present in an empty map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that containsKey returns false for a non-existent key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>containsKey should return false for any key.</li>
     * </ul>
     */
    @Test
    public void testContainsKeyOnEmptyMapAdapter() {
        assertFalse("Empty MapAdapter should not contain any key", this.map.containsKey("testKey"));
    }

    /**
     * Summary:
     *   Verifies that the containsKey method on an empty MapAdapter throws a NullPointerException when passed a null key.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that containsKey handles null keys appropriately.
     *   The rationale is to confirm that null keys are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to check containsKey with a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNullKeyOnEmptyMapAdapter() {
        this.map.containsKey(null);
    }

    /**
     * Summary:
     *   Verifies that the containsValue method on an empty MapAdapter returns false for any value.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that containsValue behaves correctly when the map is empty.
     *   The rationale is to confirm that no values are present in an empty map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that containsValue returns false for a non-existent value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>containsValue should return false for any value.</li>
     * </ul>
     */
    @Test
    public void testContainsValueOnEmptyMapAdapter() {
        assertFalse("Empty MapAdapter should not contain any value", this.map.containsValue("testValue"));
    }

    /**
     * Summary:
     *   Verifies that the containsValue method on an empty MapAdapter throws a NullPointerException when passed a null value.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that containsValue handles null values appropriately.
     *   The rationale is to confirm that null values are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to check containsValue with a null value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsNullValueOnEmptyMapAdapter() {
        this.map.containsValue(null);
    }

    /**
     * Summary:
     *   Verifies that the entrySet method on an empty MapAdapter returns a non-null, empty EntrySet.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that entrySet behaves correctly when the map is empty.
     *   The rationale is to confirm that the entry set is empty and not null.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that entrySet returns a non-null EntrySet.</li>
     *   <li>Check that entrySet is empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>entrySet should return a non-null EntrySet.</li>
     *   <li>entrySet should return an empty EntrySet.</li>
     * </ul>
     */
    @Test
    public void testEntrySetOnEmptyMapAdapter() {
        assertNotNull("Entry set of an empty MapAdapter should not be null", this.map.entrySet());
        assertTrue("Entry set of an empty MapAdapter should be empty", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter returns true when compared to another empty MapAdapter.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals behaves correctly for empty maps.
     *   The rationale is to confirm that two empty maps are considered equal.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Check that the two empty MapAdapters are equal.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The two empty MapAdapters should be equal.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapter() {
        HMap anotherEmptyMap = new MapAdapter();
        assertTrue("Two empty MapAdapters should be equal", this.map.equals(anotherEmptyMap));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter returns false when compared to a non-empty MapAdapter.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals behaves correctly for empty and non-empty maps.
     *   The rationale is to confirm that an empty map is not equal to a non-empty map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a non-empty MapAdapter.</li>
     *   <li>Check that the empty MapAdapter is not equal to the non-empty MapAdapter.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The empty MapAdapter should not be equal to the non-empty MapAdapter.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterWithNonEmptyMap() {
        HMap nonEmptyMap = new MapAdapter();
        nonEmptyMap.put("testKey", "testValue");
        assertFalse("Empty MapAdapter should not be equal to a non-empty MapAdapter", this.map.equals(nonEmptyMap));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter returns false when compared to null.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals behaves correctly when compared to null.
     *   The rationale is to confirm that an empty map is not equal to null.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty MapAdapter is not equal to null.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The empty MapAdapter should not be equal to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterWithNull() {
        assertFalse("Empty MapAdapter should not be equal to null", this.map.equals(null));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter returns true when compared to itself.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals behaves correctly when an empty map is compared to itself.
     *   The rationale is to confirm that an empty map is equal to itself.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty MapAdapter is equal to itself.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The empty MapAdapter should be equal to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterWithItself() {
        assertTrue("Empty MapAdapter should be equal to itself", this.map.equals(map));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter returns false when compared to its entry set.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals behaves correctly when an empty map is compared to its entry set.
     *   The rationale is to confirm that an empty map is not equal to its entry set.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty MapAdapter is not equal to its entry set.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The empty MapAdapter should not be equal to its entry set.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterWithEntrySet() {
        assertFalse("Empty MapAdapter should not be equal to its entry set", this.map.equals(this.map.entrySet()));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter is symmetric.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals is symmetric for empty maps.
     *   The rationale is to confirm that if one empty map is equal to another, then the reverse should also hold true.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Check that both empty MapAdapters are equal to each other.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>Equals should be symmetric for empty MapAdapters.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterIsSymmetric() {
        HMap anotherEmptyMapAdapter = new MapAdapter();
        assertTrue("Equals should be symmetric", anotherEmptyMapAdapter.equals(map) && this.map.equals(anotherEmptyMapAdapter));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter is transitive.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals is transitive for empty maps.
     *   The rationale is to confirm that if one empty map is equal to another, and that map is equal to a third map, then the first map should be equal to the third.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create two other empty MapAdapters.</li>
     *   <li>Check that all three empty MapAdapters are equal to each other.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>Equals should be transitive for empty MapAdapters.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterIsTransitive() {
        HMap anotherEmptyMapAdapter = new MapAdapter();
        HMap anotherAnotherEmptyMapAdapter = new MapAdapter();
        assertTrue("If MapAdapter A equals MapAdapter B and MapAdapter B equals MapAdapter C, then MapAdapter A should equal MapAdapter C", 
                    this.map.equals(anotherEmptyMapAdapter) && anotherEmptyMapAdapter.equals(anotherAnotherEmptyMapAdapter) && this.map.equals(anotherAnotherEmptyMapAdapter));
    }

    /**
     * Summary:
     *   Verifies that the equals method on an empty MapAdapter returns false when compared to a non-HMap object.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that equals behaves correctly when an empty map is compared to a non-HMap object.
     *   The rationale is to confirm that an empty map is not equal to a non-HMap object.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty MapAdapter is not equal to a non-HMap object.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The empty MapAdapter should not be equal to a non-HMap object.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnEmptyMapAdapterWithNonHMapObject() {
        assertFalse("Empty MapAdapter should not be equal to a non-HMap object", this.map.equals("Not a map"));
    }

    /**
     * Summary:
     *   Verifies that the get method on an empty MapAdapter returns null for any key.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that get behaves correctly when called on an empty map.
     *   The rationale is to confirm that an empty map returns null for any key.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the empty MapAdapter returns null for any key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>Get should return null for any key.</li>
     * </ul>
     */
    @Test
    public void testGetOnEmptyMapAdapter() {
        assertNull("Get should return null for any key", this.map.get("testKey"));
    }

    /**
     * Summary:
     *   Verifies that the get method on an empty MapAdapter throws a NullPointerException when passed a null key.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that get handles null keys appropriately.
     *   The rationale is to confirm that null keys are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to get a value using a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testGetOnEmptyMapAdapterWithNullKey() {
        this.map.get(null);
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on an empty MapAdapter returns 0.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that hashCode behaves correctly for empty maps.
     *   The rationale is to confirm that the hash code of an empty map is defined as 0.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the hashCode of the empty MapAdapter is 0.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The hashCode of the empty MapAdapter should be 0.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnEmptyMapAdapter() {
        assertEquals("Hash code of an empty MapAdapter should be 0", 0, this.map.hashCode());
    }

    /**
     * Summary:
     *   Verifies that the isEmpty method on an empty MapAdapter returns true.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that isEmpty behaves correctly for empty maps.
     *   The rationale is to confirm that an empty map is indeed empty.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the isEmpty method returns true for an empty MapAdapter.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The isEmpty method should return true.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnEmptyMapAdapter() {
        assertTrue("MapAdapter should be empty", this.map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the keySet method on an empty MapAdapter returns an empty set.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that keySet behaves correctly for empty maps.
     *   The rationale is to confirm that the key set of an empty map is defined as empty.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the keySet method returns a non-null KeySet.</li>
     *   <li>Check that the keySet of the empty MapAdapter is empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The keySet method should return a non-null KeySet.</li>
     *   <li>The keySet of the empty MapAdapter should be empty.</li>
     * </ul>
     */
    @Test
    public void testKeySetOnEmptyMapAdapter() {
        assertNotNull("KeySet of an empty MapAdapter should not be null", this.map.keySet());
        assertTrue("KeySet of an emptyMapAdapter should be empty", this.map.keySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter inserts a new entry.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put behaves correctly when called on an empty map.
     *   The rationale is to confirm that a new entry can be added to an empty map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the put method with a key and value.</li>
     *   <li>Check that the size of the map is 1 after the put operation.</li>
     *   <li>Check that the map is not empty after the put operation.</li>
     *   <li>Check that the map contains the key and value after the put operation.</li>
     *   <li>Check that the get method returns the value associated with the key after the put operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance has one entry.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The size of the MapAdapter should be 1 after put.</li>
     *   <li>The MapAdapter should not be empty after put.</li>
     *   <li>The MapAdapter should contain the key and value after put.</li>
     *   <li>Check that the get method returns the value associated with the key after the put operation.</li>
     * </ul>
     */
    @Test
    public void testPutOnEmptyMapAdapter() {
        this.map.put("testKey", "testValue");
        assertEquals("Size should be 1 after put", 1, this.map.size());
        assertFalse("MapAdapter should not be empty after put", this.map.isEmpty());
        assertTrue("MapAdapter should contain the key after put", this.map.containsKey("testKey"));
        assertTrue("MapAdapter should contain the value after put", this.map.containsValue("testValue"));
        assertEquals("Get should return the value after put", "testValue", this.map.get("testKey"));
    }    

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter with this map as a key or value inserts a new entry.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put behaves correctly when the key or value is the map itself.
     *   The rationale is to confirm that the map can insert itself as a key or value.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the put method with this map as a key and a generic value.</li>
     *   <li>Check that the size of the map is 1 after the put operation.</li>
     *   <li>Check that the map is not empty after the put operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance has one entry with itself as a key or value.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The size of the MapAdapter should be 1 after put with this map as a key.</li>
     *   <li>The MapAdapter should not be empty after put with this map as a key.</li>
     * </ul>
     */
    @Test
    public void testPutOnEmptyMapAdapterWithThisMapAsAKeyInsertsANewEntry () {
        this.map.put(this.map, "testValue");
        assertFalse("MapAdapter should not be empty after put with this map as a key", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a key should have size 1", 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
    }

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter with this map as a value inserts a new entry.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put behaves correctly when the value is the map itself.
     *   The rationale is to confirm that the map can insert itself as a value.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the put method with a generic key and this map as a value.</li>
     *   <li>Check that the size of the map is 1 after the put operation.</li>
     *   <li>Check that the map is not empty after the put operation.</li>
     *   <li>Check that the get method returns the value associated with the key after the put operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance has one entry with itself as a value.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The size of the MapAdapter should be 1 after put with this map as a value.</li>
     *   <li>The MapAdapter should not be empty after put with this map as a value.</li>
     *   <li>The get method should return the map itself after put with this map as a value.</li>
     * </ul>
     */
    @Test
    public void testPutOnEmptyMapAdapterWithThisMapAsAValueInsertsANewEntry () {
        this.map.put("testKey", this.map);
        assertFalse("MapAdapter should not be empty after put with this map as a value", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a value should have size 1", 1, this.map.size());
        assertEquals("Get should return the value after put", this.map, this.map.get("testKey"));
    }

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter with this map as both a key and value inserts a new entry.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put behaves correctly when both the key and value are the map itself.
     *   The rationale is to confirm that the map can insert itself as both a key and value.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the put method with this map as both a key and value.</li>
     *   <li>Check that the size of the map is 1 after the put operation.</li>
     *   <li>Check that the map is not empty after the put operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance has one entry with itself as both a key and value.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The size of the MapAdapter should be 1 after put with this map as both a key and value.</li>
     *   <li>The MapAdapter should not be empty after put with this map as both a key and value.</li>
     * </ul>
     */
    @Test
    public void testPutOnEmptyMapAdapterWithThisMapAsAKeyAndThisMapAsAValueInsertsANewEntry () {
        this.map.put(this.map, this.map);
        assertFalse("MapAdapter should not be empty after put with this map as a key and value", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a key and value should have size 1", 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
        //and cannot check if this.map.equals(this.map.get(this.map)) for the same reason
    }

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter throws a NullPointerException when passed a null key and a value.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put handles null keys appropriately.
     *   The rationale is to confirm that null keys are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to put a null key with a value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyOnEmptyMapAdapter() {
        this.map.put(null, "value");
    }

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter throws a NullPointerException when passed a key and a null value.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put handles null values appropriately.
     *   The rationale is to confirm that null values are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to put a key with a null value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNullValueOnEmptyMapAdapter() {
        this.map.put("key", null);
    }

    /**
     * Summary:
     *   Verifies that the put method on an empty MapAdapter throws a NullPointerException when passed a null key and a null value.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that put handles null keys and values appropriately.
     *   The rationale is to confirm that null keys and values are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to put a null key with a null value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyAndNullValueOnEmptyMapAdapter() {
        this.map.put(null, null);
    }

    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with another empty MapAdapter does not change its state.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll behaves correctly when called with another empty map.
     *   The rationale is to confirm that an empty map remains unchanged after putAll with another empty map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create another empty MapAdapter.</li>
     *   <li>Call putAll with another empty MapAdapter.</li>
     *   <li>Check that the map is still empty after the putAll operation.</li>
     *   <li>Check that the size of the map is still 0 after the putAll operation.</li>
     *   <li>Check that the two maps are equal after the putAll operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is still empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should still be empty after putAll with another empty MapAdapter.</li>
     *   <li>The size of the MapAdapter should still be 0 after putAll with another empty MapAdapter.</li>
     *   <li>Both the maps should be equal after putAll with another empty MapAdapter.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnEmptyMapAdapterWithAnotherEmptyMapMapAdapter() {
        HMap anotherMap = new MapAdapter();
        this.map.putAll(anotherMap);
        assertTrue("PutAll on empty MapAdapter from empty map should not change its state", this.map.isEmpty());
        assertEquals("Size after PutAll on empty MapAdapter from empty map should still be 0", 0, this.map.size());
        assertTrue("Both the maps should be equal", this.map.equals(anotherMap));
    }

    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with itself does not change its state.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll behaves correctly when called with itself.
     *   The rationale is to confirm that an empty map remains unchanged after putAll with itself.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call putAll with this map.</li>
     *   <li>Check that the map is still empty after the putAll operation.</li>
     *   <li>Check that the size of the map is still 0 after the putAll operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is still empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should still be empty after putAll with itself.</li>
     *   <li>The size of the MapAdapter should still be 0 after putAll with itself.</li>
     * </ul>
     */
    @Test
    public void testPutAllWithThisMapAdapter() {
        this.map.putAll(this.map);
        assertTrue("MapAdapter should be empty after putAll with empty map", this.map.isEmpty());
        assertEquals("MapAdapter after putAll with this map should have size 0", 0, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with another non-empty MapAdapter updates the state correctly.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll behaves correctly when called with another non-empty MapAdapter.
     *   The rationale is to confirm that the empty map is updated with the contents of the non-empty map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call putAll with another non-empty MapAdapter.</li>
     *   <li>Check that the map is no longer empty after the putAll operation.</li>
     *   <li>Check that the size of the map is updated after the putAll operation.</li>
     *   <li>Check that the map contains all keys and values from the other MapAdapter after the putAll operation.</li>
     *   <li>Check that the map is equal to the other non-empty MapAdapter after the putAll operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is updated with the contents of the other map.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should not be empty after putAll with another non-empty MapAdapter.</li>
     *   <li>The size of the MapAdapter should be equal to the size of the other MapAdapter after putAll.</li>
     *   <li>The MapAdapter should contain all keys and values from the other MapAdapter after putAll.</li>
     *   <li>The MapAdapter should be equal to the other non-empty MapAdapter after putAll.</li>
     *   <li>The get method should return the correct values for the keys from the other MapAdapter after putAll.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnEmptyMapAdapterWithAnotherNonEmptyMapAdapter() {
        HMap anotherMap = new MapAdapter();
        for (int i = 0; i < 5; i++) {
            anotherMap.put("testKey" + i, "testValue" + i);
        }
        this.map.putAll(anotherMap);
        assertFalse("MapAdapter should not be empty after putAll with another non empty MapAdapter", this.map.isEmpty());
        assertEquals("MapAdapter size after putAll with another MapAdapter of size 5 should be 5", 5, this.map.size());
        assertTrue("MapAdapter should be equal to another non empty MapAdapter after putAll", this.map.equals(anotherMap));
        for (int i = 0; i < 5; i++) {
            assertTrue("MapAdapter should contain testKey" + i + " after putAll", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should contain testValue" + i + " after putAll", this.map.containsValue("testValue" + i));
            assertEquals("Get should return the correct value after putAll", "testValue" + i, this.map.get("testKey" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with a null map throws a NullPointerException.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll handles null maps appropriately.
     *   The rationale is to confirm that passing a null map to putAll is not allowed.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to putAll with a null map.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullMapOnEmptyMapAdapter() {
        this.map.putAll(null);
    }
    
    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with another map containing null keys throws a NullPointerException.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll handles null keys appropriately when called with another map containing null keys.
     *   The rationale is to confirm that null keys are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of DummyTestMap with null keys.</li>
     *   <li>Attempt to putAll with a map containing null keys.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is still empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown for null keys.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullKeyOnEmptyMapAdapter() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        this.map.putAll(anotherMap);
    }

    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with another map containing null values throws a NullPointerException.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll handles null values appropriately when called with another map containing null values.
     *   The rationale is to confirm that null values are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of DummyTestMap with null values.</li>
     *   <li>Attempt to putAll with a map containing null values.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is still empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown for null values.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullValueOnEmptyMapAdapter() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        this.map.putAll(anotherMap);
    }

    /**
     * Summary:
     *   Verifies that the putAll method on an empty MapAdapter with another map containing null keys and values throws a NullPointerException.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that putAll handles null keys and values appropriately when called with another map containing null keys and values.
     *   The rationale is to confirm that null keys and values are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of DummyTestMap with null keys and values.</li>
     *   <li>Attempt to putAll with a map containing null keys and values.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is still empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>NullPointerException is thrown for null keys and values.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNullKeyAndNullValueOnEmptyMapAdapter() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        this.map.putAll(anotherMap);
    }
    
    /**
     * Summary:
     *   Verifies that the remove method on an empty MapAdapter returns null for any key.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that remove behaves correctly when called on an empty map.
     *   The rationale is to confirm that removing a key from an empty map returns null.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the remove method returns null for any key.</li>
     *   <li>Check that the map is still empty after the remove operation.</li>
     *   <li>Check that the size of the map is still 0 after the remove operation.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is still empty.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The remove method should return null for any key.</li>
     *   <li>The MapAdapter should still be empty after remove on empty MapAdapter.</li>
     *   <li>The size of the MapAdapter should still be 0 after remove on empty MapAdapter.</li>
     * </ul>
     */
    @Test 
    public void testRemoveOnEmptyMapAdapter() {
        assertNull("Remove should return null for any key", this.map.remove("testKey"));
        assertTrue("MapAdapter should still be empty after remove on empty MapAdapter", this.map.isEmpty());
        assertEquals("Size should still be 0 after remove on empty MapAdapter", 0, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the remove method on an empty MapAdapter throws a NullPointerException when passed a null key.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that remove handles null keys appropriately.
     *   The rationale is to confirm that null keys are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Attempt to remove a value using a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>A NullPointerException is thrown.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullKeyOnEmptyMapAdapter() {
        this.map.remove(null);
    }

    /**
     * Summary:
     *   Verifies that the size method on an empty MapAdapter returns 0.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that size behaves correctly for empty maps.
     *   The rationale is to confirm that the size of an empty map is defined as 0.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the size of the empty MapAdapter is 0.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The size of the empty MapAdapter should be 0.</li>
     * </ul>
     */
    @Test
    public void testSizeOnEmptyMapAdapter() {
        assertEquals("Size should be 0", 0, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the values method on an empty MapAdapter returns a non-null, empty collection.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that values behaves correctly for empty maps.
     *   The rationale is to confirm that the ValueCollection of an empty map is not null and is empty.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the ValueCollection is not null.</li>
     *   <li>Check that the ValueCollection is empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection should not be null.</li>
     *   <li>The ValueCollection should be empty.</li>
     * </ul>
     */
    @Test
    public void testValuesOnEmptyMapAdapter() {
        assertNotNull("Values collection of an empty MapAdapter should not be null", this.map.values());
        assertTrue("Values collection should be empty", this.map.values().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the toString method on an empty MapAdapter returns an empty string representation.
     *   Category: Method tests
     *<p>
     * Test Case Design:
     *   This test ensures that toString behaves correctly for empty maps.
     *   The rationale is to confirm that the string representation of an empty map is defined as "{}".
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the string representation of the empty MapAdapter is "{}".</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is kept empty.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The string representation of the empty MapAdapter should be "{}".</li>
     * </ul>
     */
    @Test
    public void testToStringOnEmptyMapAdapter() {
        assertEquals("String representation of an empty MapAdapter should be \"{}\"", "{}", this.map.toString());
    }
}