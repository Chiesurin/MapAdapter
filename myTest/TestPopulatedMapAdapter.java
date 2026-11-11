// Diego Chiesurin

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;

/**
 * This test suite verifies the correct behavior of the {@link myAdapter.MapAdapter} implementation when it is populated with a large number of entries.
 * It covers all methods and scenarios relevant to a non-empty map,
 * including the copy constructor {@link myAdapter.MapAdapter#MapAdapter(HMap)}
 * and all public methods such as
 * {@link myAdapter.MapAdapter#clear()}, {@link myAdapter.MapAdapter#containsKey(Object)},
 * {@link myAdapter.MapAdapter#containsValue(Object)}, {@link myAdapter.MapAdapter#entrySet()},
 * {@link myAdapter.MapAdapter#equals(Object)}, {@link myAdapter.MapAdapter#get(Object)},
 * {@link myAdapter.MapAdapter#hashCode()}, {@link myAdapter.MapAdapter#isEmpty()},
 * {@link myAdapter.MapAdapter#keySet()}, {@link myAdapter.MapAdapter#put(Object, Object)}, 
 * {@link myAdapter.MapAdapter#putAll(HMap)}, {@link myAdapter.MapAdapter#remove(Object)}, 
 * {@link myAdapter.MapAdapter#size()}, {@link myAdapter.MapAdapter#values()}, and 
 * {@link myAdapter.MapAdapter#toString()}.
 * <p>
 * <b>Test Suite Categories:</b>
 * <ul>
 *   <li>Constructor and copy constructor tests</li>
 *   <li>Populated state behavior</li>
 *   <li>Mutation and update operations</li>
 *   <li>Exception handling (NullPointerException, ClassCastException)</li>
 *   <li>Equality and hash code</li>
 *   <li>Collection views (entrySet, keySet, values)</li>
 *   <li>Type flexibility (different key/value types)</li>
 * </ul>
 *
 * <b>Test Case Design:</b>
 * <p>
 * The design of this test suite is motivated by the need to ensure that a {@code MapAdapter} with many entries behaves as specified under all supported operations. The tests are organized to:
 * <ul>
 *   <li>Verify that constructors and copy constructors correctly initialize a populated map.</li>
 *   <li>Check that all query methods ({@link myAdapter.MapAdapter#containsKey(Object)}, {@link myAdapter.MapAdapter#containsValue(Object)}, {@link myAdapter.MapAdapter#get(Object)}, etc.) return correct results for present and absent keys/values.</li>
 *   <li>Ensure that mutator methods ({@link myAdapter.MapAdapter#put(Object, Object)}, {@link myAdapter.MapAdapter#putAll(HMap)}, {@link myAdapter.MapAdapter#remove(Object)}, {@link myAdapter.MapAdapter#clear()}) behave correctly, including updates and removals.</li>
 *   <li>Test the behavior of collection views ({@link myAdapter.MapAdapter#entrySet()}, {@link myAdapter.MapAdapter#keySet()}, {@link myAdapter.MapAdapter#values()}) in a populated state and with mixed key/value types.</li>
 *   <li>Validate the contract of {@link myAdapter.MapAdapter#equals(Object)} and {@link myAdapter.MapAdapter#hashCode()} for populated maps, including edge cases with different key/value types.</li>
 *   <li>Check that the string representation of a populated map is as expected.</li>
 *   <li>Ensure correct exception handling for invalid input (e.g., null keys/values).</li>
 * </ul>
 * The rationale is to provide comprehensive coverage of the populated state of the {@link myAdapter.MapAdapter}, ensuring correctness, robustness and compliance with the {@link myAdapter.HMap} contract.
 */
public class TestPopulatedMapAdapter {

    /**
     * The initial capacity of the map used in tests.
     * This value is used to populate the map with a significant number of entries to test its behavior under normal usage conditions.
     * <strong>Note:</strong> changing this value may invalidate existing tests.
     */
    private static final int MAP_INITIAL_CAPACITY = 100;
    /**
     * The map instance used in tests.
     * It is initialized in the setUp method and populated with test entries.
     */
    private HMap map;

    /**
     * Default constructor for the test class.
     */
    public TestPopulatedMapAdapter() {
        //intentionally left blank
    }

    /**
     * Sets up the test environment by initializing the map and populating it with test entries.
     * MAP_INITIAL_CAPACITY entries are added to the empty map and they are formed this way: "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * This method is called before each test to ensure a consistent starting state.
     */
    @Before
    public void setUp() {
        this.map = new MapAdapter(MAP_INITIAL_CAPACITY);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    /**
     * Cleans up the test environment after each test.
     * This method is called after each test to ensure that the map instance is reset and does not affect subsequent tests.
     */
    @After
    public void tearDown() {
        this.map = null;
    }

    /**
     * Summary:
     *   Verifies that the MapAdapter copy constructor creates a non-null, non empty map.
     *<p>
     * Test Case Design:
     *   This test ensures that the copy constructor initializes the map to a valid, non empty state.
     *   The rationale is to confirm the basic contract of the constructor.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an instance of MapAdapter called copy using the copy constructor with the populated MapAdapter.</li>
     *   <li>Check that the instance is not null.</li>
     *   <li>Check that the map is not empty</li>
     *   <li>Check that the size is equal to the original map's size.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   the MapAdapter instance is the same.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The copy MapAdapter is not null.</li>
     *   <li>The copy MapAdapter is not empty.</li>
     *   <li>The size of the copy MapAdapter is equal to the original map's size.</li>
     * </ul>
     */
    @Test
    public void testCopyConstuctorOnPopulatedMap() {
        HMap copy = new MapAdapter(this.map);
        assertEquals("Copy constructor should create an identical map", this.map, copy);
        assertEquals("Size of copied map should match original", this.map.size(), copy.size());
    }

    /**
     * Summary:
     *   Verifies that the clear method on a populated MapAdapter empties the map.
     *<p>
     * Test Case Design:
     *   This test ensures that the clear method correctly empties the map, resetting its size to zero.
     *   The rationale is to confirm that the clear operation behaves as expected on a populated map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call the clear method on the populated MapAdapter.</li>
     *   <li>Check that the MapAdapter is empty after clearing.</li>
     *   <li>Check that the size of the MapAdapter is zero after clearing.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance is empty after calling clear.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should be empty after calling clear.</li>
     *   <li>The size of the map should be zero after calling clear.</li>
     * </ul>
     */
    @Test
    public void testClearOnPopulatedMapAdapter() {
        this.map.clear();
        assertTrue("MapAdapter should be empty after clear", this.map.isEmpty());
        assertEquals("Size of map should be 0 after clear", 0, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the containsKey method on a populated MapAdapter returns true for existing keys and false for non-existing keys.
     *<p>
     * Test Case Design:
     *   This test ensures that the containsKey method correctly identifies keys that are present in the map and those that are not.
     *   The rationale is to confirm the basic contract of the containsKey method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Iterate over the keys that were inserted into the MapAdapter.</li>
     *   <li>Check that containsKey returns true for each of these keys.</li>
     *   <li>Check that containsKey returns false for a key that was not inserted.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>containsKey should return true for every key that was inserted.</li>
     *   <li>containsKey should return false for a key that was not inserted.</li>
     * </ul>
     */
    @Test
    public void testContainsKeyOnPopulatedMapAdapter(){
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain every key inserted", this.map.containsKey("testKey" + i));
        }

        assertFalse("MapAdapter should not contain a key that is not contained", this.map.containsKey("nonExistentKey"));
    }

    /**
     * Summary:
     *   Verifies that the containsKey method on a populated MapAdapter throws a NullPointerException when called with a null key.
     *<p>
     * Test Case Design:
     *   This test ensures that the containsKey method correctly handles null keys by throwing a NullPointerException.
     *   The rationale is to confirm that null keys are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call containsKey with a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>containsKey should throw a NullPointerException when called with a null key.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsKeyOnMapAdapterWithNullKey() {
        this.map.containsKey(null);
    }

    /**
     * Summary:
     *   Verifies that the containsValue method on a populated MapAdapter returns true for existing values and false for non-existing values.
     *<p>
     * Test Case Design:
     *   This test ensures that the containsValue method correctly identifies values that are present in the map.
     *   The rationale is to confirm the basic contract of the containsValue method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Iterate over the values that were inserted into the MapAdapter.</li>
     *   <li>Check that containsValue returns true for each of these values.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>containsValue should return true for every value that was inserted.</li>
     * </ul>
     */
    @Test
    public void testContainsValueOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain every value inserted", this.map.containsValue("testValue" + i));
        }

        assertFalse("MapAdapter should not contain a value that is not contained", this.map.containsValue("nonExistentValue"));
    }

    /**
     * Summary:
     *   Verifies that the containsValue method on a populated MapAdapter throws a NullPointerException when called with a null value.
     *<p>
     * Test Case Design:
     *   This test ensures that the containsValue method correctly handles null values by throwing a NullPointerException.
     *   The rationale is to confirm that null values are not allowed in the map.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call containsValue with a null value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>containsValue should throw a NullPointerException when called with a null value.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsValueOnPopulatedMapAdapterWithNullValue() {
        this.map.containsValue(null);
    }

    /**
     * Summary:
     *   Verifies that the entrySet method on a populated MapAdapter returns a non-null, non-empty EntrySet.
     *<p>
     * Test Case Design:
     *   This test ensures that the entrySet method correctly provides a view of the map's entries.
     *   The rationale is to confirm the basic contract of the entrySet method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call entrySet on the populated MapAdapter.</li>
     *   <li>Check that the returned EntrySet is not null.</li>
     *   <li>Check that the EntrySet is not empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The EntrySet should not be null.</li>
     *   <li>The EntrySet should not be empty.</li>
     * </ul>
     */
    @Test
    public void testEntrySetOnPopulatedMapAdapter() {
        assertNotNull("Entry set of a populated MapAdapter should not be null", this.map.entrySet());
        assertFalse("Entry set of a populated MapAdapter should not be empty", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the entrySet method on a populated MapAdapter with different type keys returns a non-null EntrySet.
     *<p>
        * Test Case Design:
        *   This test ensures that the entrySet method correctly handles different type keys in the map.
        *   The rationale is to confirm the basic contract of the entrySet method when using mixed types keys.
        *<p>
        * Test Description:
        * <ul>
        *   <li>Insert a key-value pair with a different type key into the MapAdapter.</li>
        *   <li>Call entrySet on the populated MapAdapter.</li>
        *   <li>Check that the returned EntrySet is not null.</li>
        * </ul>
        *<p>
        * Pre-Condition:
        *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
        *<p>
        * Post-Condition:
        *   The MapAdapter instance remains unchanged.
        * <p>
        * Expected Results:
        * <ul>
        *   <li>The EntrySet should not be null.</li>
        *   <li>The EntrySet should not be empty.</li>
        * </ul>
        */
    @Test
    public void testEntrySetOnPopulatedMapAdapterWithDifferentTypeOfKeys() {
        this.map.put(1, "testValue");
        assertNotNull("Entry set of a populated MapAdapter with different type keys should not be null", this.map.entrySet());
        assertFalse("Entry set of a populated MapAdapter with different type keys should not be empty", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *  Verifies that the entrySet method on a populated MapAdapter with different type values returns a non-null EntrySet.
     * <p>
     * Test Case Design:
     *  This test ensures that the entrySet method correctly handles different type values in the map.
     * The rationale is to confirm the basic contract of the entrySet method when using mixed types values.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Insert a key-value pair with a different type value into the MapAdapter.</li>
     *   <li>Call entrySet on the populated MapAdapter.</li>
     *   <li>Check that the returned EntrySet is not null.</li>
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
     *   <li>The EntrySet should not be null.</li>
     *   <li>The EntrySet should not be empty.</li>
     * </ul>
     */
    @Test
    public void testEntrySetOnPopulatedMapAdapterWithDifferentTypeOfValues() {
        this.map.put("testKey", 1);
        assertNotNull("Entry set of a populated MapAdapter with different type values should not be null", this.map.entrySet());
        assertFalse("Entry set of a populated MapAdapter with different type values should not be empty", this.map.entrySet().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the equals method on a populated MapAdapter behaves correctly with various scenarios.
     *<p>
     * Test Case Design:
     *   This test ensures that the equals method correctly identifies equality between maps with the same content and different content.
     *   The rationale is to confirm the basic contract of the equals method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check equality with another map containing the same entries.</li>
     *   <li>Check inequality with an empty map.</li>
     *   <li>Check inequality with a map containing different entries.</li>
     *   <li>Check inequality with a map having the same keys but different values.</li>
     *   <li>Check inequality with a map having different keys but same values.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should be equal to another map with the same content.</li>
     *   <li>The map should not be equal to an empty map.</li>
     *   <li>The map should not be equal to a map with different content.</li>
     *   <li>The map should not be equal to a map with the same keys but different values.</li>
     *   <li>The map should not be equal to a map with different keys but same values.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapter() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("Two maps with the same content should be equal", this.map.equals(anotherMap));
        anotherMap.clear();
        assertFalse("A map should not be equal to an empty map", this.map.equals(anotherMap));
        anotherMap.put("extraKey", "extraValue");
        assertFalse("Two maps with different content should not be equal", this.map.equals(anotherMap));
        anotherMap.clear();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("testKey" + i, "differentValue" + i);
        }
        assertFalse("Two maps with the same size and keys but different values should not be equal", this.map.equals(anotherMap));
        anotherMap.clear();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("differentKey" + i, "testValue" + i);
        }   
        assertFalse("Two maps with the same size and values values but different keys should not be equal", this.map.equals(anotherMap));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter does not equal a non-HMap object.
     *<p>
     * Test Case Design:
     *  This test ensures that the equals method correctly identifies that a map is not equal to a non-HMap object.
     *  The rationale is to confirm that the equals method adheres to the contract of not being equal to non-HMap objects.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the equals method returns false when comparing the map to a string object.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should not be equal to a non-HMap object.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterWithNonHMapObject() {
        assertFalse("A map should not be equal to an object of a different type", this.map.equals("not a map"));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter does not equal to null.
     *<p>
     * Test Case Design:
     *  This test ensures that the equals method correctly identifies that a map is not equal to null.
     *  The rationale is to confirm that the equals method adheres to the contract of not being equal to null.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the equals method returns false when comparing the map to null.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should not be equal to null.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterWithNull() {
        assertFalse("A map should not be equal to null", this.map.equals(null));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter is reflexive.
     *<p>
     * Test Case Design:
     *  This test ensures that the equals method correctly identifies that a map is equal to itself.
     *  The rationale is to confirm the reflexive property of the equals method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the equals method returns true when comparing the map to itself.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should be equal to itself.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterWithItself() {
        assertTrue("A map should be equal to itself", this.map.equals(this.map));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter is symmetric.
     *<p>
     * Test Case Design:
     *  This test ensures that if one map is equal to another, then the second map is equal to the first.
     *  The rationale is to confirm the symmetric property of the equals method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the equals method returns true when comparing two equal MapAdapters in both directions.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>If MapAdapter A equals MapAdapter B, then MapAdapter B should equal MapAdapter A.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterIsSymmetric() {
        HMap anotherMap = new MapAdapter(this.map);
        assertTrue("If MapAdapter A equals MapAdapter B, then MapAdapter B should equal MapAdapter A", anotherMap.equals(this.map) && this.map.equals(anotherMap));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter is not equal to its entrySet.
     *<p>
     * Test Case Design:
     *  This test ensures that the equals method correctly identifies that a map is not equal to its entry set.
     *  The rationale is to confirm that the map and its entry set are distinct objects.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Get the entry set of the populated MapAdapter.</li>
     *   <li>Check that the equals method returns false when comparing the map to its entry set.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should not be equal to its entry set.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterWithEntrySet() {
        HSet entrySet = this.map.entrySet();
        assertFalse("MapAdapter should not be equal to its entry set", this.map.equals(entrySet));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter is transitive.
     *<p>
     * Test Case Design:
     *  This test ensures that if one map is equal to a second, and the second is equal to a third, then the first map is equal to the third.
     *  The rationale is to confirm the transitive property of the equals method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create two additional MapAdapters with the same content as the original map.</li>
     *   <li>Check that if the first map equals the second, and the second equals the third, then the first map equals the third.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>If MapAdapter A equals MapAdapter B and MapAdapter B equals MapAdapter C, then MapAdapter A should equal MapAdapter C.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterIsTransitive() {
        HMap anotherMap = new MapAdapter(this.map);
        HMap anotherAnotherMap = new MapAdapter(this.map);
        assertTrue("If MapAdapter A equals MapAdapter B and MapAdapter B equals MapAdapter C, then MapAdapter A should equal MapAdapter C", 
                    this.map.equals(anotherMap) && anotherMap.equals(anotherAnotherMap) && this.map.equals(anotherAnotherMap));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter is not equal to a map with different type keys.
     * <p>
     * Test Case Design:
     *  This test ensures that the equals method correctly identifies that a MapAdapter is not equal to another MapAdapter with different type keys.
     *  The rationale is to confirm that the MapAdapter and another MapAdapter with different type keys are different.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with different type keys.</li>
     *   <li>Check that the equals method returns false when comparing the MapAdapter to the other MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should not be equal to another MapAdapter with different type keys.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeKeys() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put(i, "testValue" + i);
        }
        assertFalse("A map with different type keys should not be equal to the original map", this.map.equals(anotherMap));
    }

    /**
     * Summary:
     *  Verifies that the equals method on a populated MapAdapter is not equal to a map with different type values.
     * <p>
     * Test Case Design:
     *  This test ensures that the equals method correctly identifies that a MapAdapter is not equal to another MapAdapter with different type values.
     *  The rationale is to confirm that the MapAdapter and another MapAdapter with different type values are different.
     * <p>
     * Test Description:
     * <ul>
     *   <li>Create another MapAdapter with different type values.</li>
     *   <li>Check that the equals method returns false when comparing the MapAdapter to the other MapAdapter.</li>
     * </ul>
     * <p>
     * Pre-Condition:
     *  MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     * <p>
     * Post-Condition:
     *  The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should not be equal to another map with different type values.</li>
     * </ul>
     */
    @Test
    public void testEqualsOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("testKey" + i, i);
        }
        assertFalse("A map with different type values should not be equal to the original map", this.map.equals(anotherMap));
    }

    /**
     * Summary:
     *   Verifies that the get method on a populated MapAdapter returns the correct value for existing keys and null for non-existing keys.
     *<p>
     * Test Case Design:
     *   This test ensures that the get method correctly retrieves values associated with keys in the MapAdapter.
     *   The rationale is to confirm the basic contract of the get method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Iterate over the keys that were inserted into the MapAdapter.</li>
     *   <li>Check that get returns the correct value for each key.</li>
     *   <li>Check that get returns null for a key that was not inserted.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The map should return the correct value for every key that was inserted.</li>
     *   <li>The map should return null for a key that was not inserted.</li>
     * </ul>
     */
    @Test
    public void testGetOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertEquals("MapAdapter should return the correct value for each key", "testValue" + i, this.map.get("testKey" + i));
        }
        assertNull("MapAdapter should return null for a non-existent key", this.map.get("nonExistentKey"));
    }

    /**
     * Summary:
     *   Verifies that the get method on a populated MapAdapter throws a NullPointerException when called with a null key.
     *<p>
     * Test Case Design:
     *   This test ensures that the get method correctly handles null keys by throwing a NullPointerException.
     *   The rationale is to confirm that null keys are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call get with a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>get should throw a NullPointerException when called with a null key.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testGetOnPopulatedMapAdapterWithNullKey() {
        this.map.get(null);
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on a populated MapAdapter returns the correct hash code based on its entries.
     *<p>
     * Test Case Design:
     *   This test ensures that the hashCode method correctly computes the hash code based on the keys and values in the MapAdapter.
     *   The rationale is to confirm the basic contract of the hashCode method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Iterate over the entries in the MapAdapter and compute the expected hash code.</li>
     *   <li>Check that the computed hash code matches the hashCode method's return value.</li>
     *   <li>Check that two MapAdapters with the same content have equal hash codes.</li>
     * </ul>
     * The hash code of the MapAdapter is calculated as the sum of the XOR of the hash codes of each key and value pair.
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hash code should be calculated correctly based on the entries in the MapAdapter.</li>
     *   <li>Two MapAdapters with the same content should have equal hash codes.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnPopulatedMapAdapter() {
        HMap anotherMap = new MapAdapter(this.map);
        int hashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            hashCode += entry.getKey().hashCode() ^ entry.getValue().hashCode();
        }
        assertEquals("Hash code of the map should match the calculated hash code", hashCode, this.map.hashCode());
        assertEquals("Hash code of two maps with the same content should be equal", this.map.hashCode(), anotherMap.hashCode());
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on a populated MapAdapter with different type keys returns the correct hash code.
     *<p>
     * Test Case Design:
     *   This test ensures that the hashCode method correctly computes the hash code based on keys and values in the MapAdapter, even when keys are of different types.
     *   The rationale is to confirm the basic contract of the hashCode method with mixed type keys.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Insert a key-value pair with a different type key into the MapAdapter.</li>
     *   <li>Iterate over the entries in the MapAdapter and compute the expected hash code.</li>
     *   <li>Check that the computed hash code matches the hashCode method's return value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hash code should be calculated correctly based on the entries in the MapAdapter, even with different type keys.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnPopulatedMapAdapterWithDifferentTypeOfKeys() {
        this.map.put(1, "testValue");
        int hashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            hashCode += entry.getKey().hashCode() ^ entry.getValue().hashCode();
        }
        assertEquals("Hash code should be calculated correctly with different type keys", hashCode, this.map.hashCode());
    }

    /**
     * Summary:
     *   Verifies that the hashCode method on a populated MapAdapter with different type values returns the correct hash code.
     *<p>
     * Test Case Design:
     *   This test ensures that the hashCode method correctly computes the hash code based on keys and values in the MapAdapter, even when values are of different types.
     *   The rationale is to confirm the basic contract of the hashCode method with mixed type values.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Insert a key-value pair with a different type value into the MapAdapter.</li>
     *   <li>Iterate over the entries in the MapAdapter and compute the expected hash code.</li>
     *   <li>Check that the computed hash code matches the hashCode method's return value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The hash code should be calculated correctly based on the entries in the MapAdapter, even with different type values.</li>
     * </ul>
     */
    @Test
    public void testHashCodeOnPopulatedMapAdapterWithDifferentTypeOfValues() {
        this.map.put("testKey", 1);
        int hashCode = 0;
        HIterator iterator = this.map.entrySet().iterator();
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            hashCode += entry.getKey().hashCode() ^ entry.getValue().hashCode();
        }
        assertEquals("Hash code should be calculated correctly with different type values", hashCode, this.map.hashCode());
    }

    /**
     * Summary:
     *   Verifies that the isEmpty method on a populated MapAdapter behaves correctly.
     *<p>
     * Test Case Design:
     *   This test ensures that the isEmpty method correctly identifies whether the map is empty or not.
     *   The rationale is to confirm the basic contract of the isEmpty method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that isEmpty returns false on a populated MapAdapter.</li>
     *   <li>Clear the MapAdapter and check that isEmpty returns true.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter is empty.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should not be empty after adding entries.</li>
     *   <li>The MapAdapter should be empty after clearing.</li>
     * </ul>
     */
    @Test
    public void testIsEmptyOnPopulatedMapAdapter() {
        assertFalse("MapAdapter should not be empty after adding entries", this.map.isEmpty());
        this.map.clear();
        assertTrue("MapAdapter should be empty after clearing", this.map.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the keySet method on a populated MapAdapter returns a non-null and non-empty KeySet.
     *<p>
     * Test Case Design:
     *   This test ensures that the keySet method correctly retrieves the KeySet of keys in the MapAdapter.
     *   The rationale is to confirm the basic contract of the keySet method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call keySet on the populated MapAdapter.</li>
     *   <li>Check that the returned KeySet is not null and not empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The KeySet should not be null.</li>
     *   <li>The KeySet should not be empty.</li>
     * </ul>
     */
    @Test
    public void testKeySetOnPopulatedMapAdapter() {
        HSet keySet = this.map.keySet();
        assertNotNull("KeySet of a populated MapAdapter should not be null", keySet);
        assertFalse("KeySet of a populated MapAdapter should not be empty", keySet.isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the keySet method on a populated MapAdapter with different type keys returns a non-null KeySet.
     *<p>
     * Test Case Design:
     *   This test ensures that the keySet method correctly retrieves the KeySet of keys in the MapAdapter, even when keys are of different types.
     *   The rationale is to confirm the basic contract of the keySet method with mixed type keys.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Insert a key-value pair with a different type key into the MapAdapter.</li>
     *   <li>Call keySet on the populated MapAdapter.</li>
     *   <li>Check that the returned KeySet is not null.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The KeySet should not be null.</li>
     * </ul>
     */
    @Test
    public void testKeySetOnPopulatedMapAdapterWithDifferentTypeOfKeys() {
        this.map.put(1, "testValue");
        assertNotNull("KeySet of a populated MapAdapter with different type keys should not be null", this.map.keySet());
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter behaves correctly.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly updates existing keys and adds new keys to the MapAdapter.
     *   The rationale is to confirm the basic contract of the put method.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that put returns the previous value for an existing key.</li>
     *   <li>Check that the new value replaces the old value for an existing key.</li>
     *   <li>Check that the size of the map remains the same after updating an existing key.</li>
     *   <li>Check that put returns null for a new key.</li>
     *   <li>Check that the size of the map increases by 1 after adding a new key.</li>
     *   <li>Check that the new key is retrievable after put.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter contains a new key-value pair and the corresponding value of the key testKey0 is updated.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The put method should return the previous value for an existing key.</li>
     *   <li>The new value should replace the old value for an existing key.</li>
     *   <li>The size of the map should remain the same after updating an existing key.</li>
     *   <li>The put method should return null for a new key.</li>
     *   <li>The size of the map should increase by 1 after adding a new key.</li>
     *   <li>The new key should be retrievable after put.</li>
     * </ul>
     */
    @Test
    public void testPutOnPopulatedMapAdapter() {
        assertEquals("Put should return the previous value for an existing key", "testValue0", this.map.put("testKey0", "newValue0"));
        assertEquals("New value should have replaced the old value", "newValue0", this.map.get("testKey0"));
        assertEquals("Size of the map should remain the same after updating an existing key", MAP_INITIAL_CAPACITY, this.map.size());
        assertNull("Put should return null for a new key", this.map.put("newKey", "newValue"));
        assertEquals("Size of the map should increase by 1 after adding a new key", MAP_INITIAL_CAPACITY + 1, this.map.size());
        assertEquals("New key should be retrievable after put", "newValue", this.map.get("newKey"));
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter allows different type keys.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles different type keys in the MapAdapter.
     *   The rationale is to confirm that the MapAdapter can store mixed type keys.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that put allows different type keys.</li>
     *   <li>Check that the size of the map increases by 1 after adding a different type key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter contains new key-value pairs with different types.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow different type keys.</li>
     *   <li>The size of the map should increase by 1 after adding a different type keys.</li>
     * </ul>
     */
    @Test
    public void testPutOnPopulatedMapAdapterWithDifferentTypeOfkey() {
        this.map.put(1, "testValue");
        assertTrue("MapAdapter should allow different type keys", this.map.containsKey(1));
        assertEquals("Value for the different type key should be retrievable", "testValue", this.map.get(1));
        assertEquals("Size of the map should increase by 1 after adding a different type key", MAP_INITIAL_CAPACITY + 1, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter allows different type values.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles different type values in the MapAdapter.
     *   The rationale is to confirm that the MapAdapter can store mixed type values.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that put allows different type values.</li>
     *   <li>Check that the value for the different type value is retrievable.</li>
     *   <li>Check that the size of the map increases by 1 after adding a different type value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter contains new key-value pairs with different types.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow different type values.</li>
     *   <li> The value for the different type value should be retrievable.</li>
     *   <li>The size of the map should increase by 1 after adding a different type value.</li>
     * </ul>
     */
    @Test
    public void testPutOnPopulatedMapAdapterWithDifferentTypeOfValue() {
        this.map.put("testKey", 1);
        assertTrue("MapAdapter should allow different type values", this.map.containsValue(1));
        assertEquals("Value for the different type value should be retrievable", 1, this.map.get("testKey"));
        assertEquals("Size of the map should increase by 1 after adding a different type value", MAP_INITIAL_CAPACITY + 1, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter throws a NullPointerException when called with null keys.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles null keys by throwing a NullPointerException.
     *   The rationale is to confirm that null keys are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call put with a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>put should throw a NullPointerException when called with a null key.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutOnPopulatedMapAdapterWithNullKey() {
        this.map.put(null, "value");
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter throws a NullPointerException when called with null values.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles null values by throwing a NullPointerException.
     *   The rationale is to confirm that null values are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call put with a null value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>put should throw a NullPointerException when called with a null value.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutOnPopulatedMapAdapterWithNullValue() {
        this.map.put("key", null);
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter throws a NullPointerException when called with null keys and values.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles null keys and values by throwing a NullPointerException.
     *   The rationale is to confirm that null keys and values are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call put with both key and value as null.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>put should throw a NullPointerException when called with both key and value as null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutOnPopulatedMapAdapterWithNullKeyAndValue() {
        this.map.put(null, null);
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter behaves correctly when using the MapAdapter itself as a key.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles the MapAdapter itself as a key.
     *   The rationale is to confirm that the put method can be used with the MapAdapter itself as a key.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that using the MapAdapter itself as a key inserts a new entry.</li>
     *   <li>Check that the size of the map increases by 1.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains a new entry with itself as the key.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow itself as a key.</li>
     *   <li>The size of the map should increase by 1.</li>
     * </ul>
     */
    @Test
    public void testPutOnPopulatedMapAdapterWithThisMapAdapterAsAKeyInsertsANewEntry() {
        this.map.put(this.map, "testValue");
        assertEquals("Using the MapAdapter itself as a key should increase the size of the map", MAP_INITIAL_CAPACITY + 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter behaves correctly when using the MapAdapter itself as a value.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles the MapAdapter itself as a value.
     *   The rationale is to confirm that the put method can be used with the MapAdapter itself as a value.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that using the MapAdapter itself as a value inserts a new entry.</li>
     *   <li>Check that the size of the map increases by 1.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains a new entry with itself as the value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow itself as a value.</li>
     *   <li>The size of the map should increase by 1.</li>
     * </ul>
     */
    @Test
    public void testPutOnPopulatedMapAdapterWithThisMapAdapterAsAValueInsertsANewEntry() {
        this.map.put("testKey", this.map);
        assertEquals("Using the MapAdapter itself as a value should increase the size of the map", MAP_INITIAL_CAPACITY + 1, this.map.size());
        assertEquals("Using the MapAdapter itself as a value should be retrievable", this.map, this.map.get("testKey"));
    }

    /**
     * Summary:
     *   Verifies that the put method on a populated MapAdapter behaves correctly when using the MapAdapter itself as both a key and a value.
     *<p>
     * Test Case Design:
     *   This test ensures that the put method correctly handles the MapAdapter itself as both a key and a value.
     *   The rationale is to confirm that the put method can be used with the MapAdapter itself as both a key and a value.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that using the MapAdapter itself as both a key and a value inserts a new entry.</li>
     *   <li>Check that the size of the map increases by 1.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains a new entry with itself as both the key and value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow itself as both a key and a value.</li>
     *   <li>The size of the map should increase by 1.</li>
     * </ul>
     */
    @Test
    public void testPutOnPopulatedMapAdapterWithThisMapAsAKeyAndThisMapAsAValueInsertsANewEntry () {
        this.map.put(this.map, this.map);
        assertFalse("MapAdapter should not be empty after put with this map as a key and value", this.map.isEmpty());
        assertEquals("MapAdapter after put with this map as a key and value should have size " + (MAP_INITIAL_CAPACITY + 1), MAP_INITIAL_CAPACITY + 1, this.map.size());
        //cannot invoke this.map.get(this.map), it would cause a stack overflow
        //and cannot check if this.map.equals(this.map.get(this.map)) for the same reason
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter behaves correctly.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles adding all entries from another map.
     *   The rationale is to confirm that the putAll method can be used to add multiple entries at once.
     *<p>
     * Test Description:
     * <ul>
     * <li>Create another MapAdapter and populate it with MAP_INITIAL_CAPACITY entries that are formed as "anotherKey" + i, "anotherValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.</li>
     *   <li>Call putAll on the populated MapAdapter with the other MapAdapter.</li>
     *   <li>Check that the size of the map increases by the number of entries contained in the other MapAdapter.</li>
     *   <li>Check that all keys and values from the other MapAdapter are retrievable.</li>
     *   <li>Check that the original entries in the MapAdapter are still present.</li>
     *   <li>Check that the putAll method does not overwrite existing entries unless the keys are the same.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains all entries from the other MapAdapter.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow adding multiple entries at once.</li>
     *   <li>The size of the map should increase by the number of entries contained in the other MapAdapter.</li>
     *   <li>All keys and values from the other MapAdapter should be retrievable.</li>
     *   <li>The original entries in the MapAdapter should still be present.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapter() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("anotherKey" + i, "anotherValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain all keys from the other map", this.map.containsKey("anotherKey" + i));
            assertTrue("MapAdapter should contain all values from the other map", this.map.containsValue("anotherValue" + i));
        }
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter with an empty map does not change the size or contents of the MapAdapter.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles an empty map without modifying the existing entries in the MapAdapter.
     *   The rationale is to confirm that putting an empty map does not affect the current state of the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create an empty MapAdapter.</li>
     *   <li>Call putAll on the populated MapAdapter with the empty MapAdapter.</li>
     *   <li>Check that the size of the map remains unchanged.</li>
     *   <li>Check that all original keys and values are still present.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should remain the same after putting an empty map.</li>
     *   <li>All original keys and values should still be present in the MapAdapter.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapterWithEmptyMap() {
        HMap emptyMap = new MapAdapter();
        this.map.putAll(emptyMap);
        assertEquals("Size of the map should remain the same after putting an empty map", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter with a map containing the same keys and values does not change the size or contents of the MapAdapter.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles a map with the same keys and values without modifying the existing entries in the MapAdapter.
     *   The rationale is to confirm that putting a map with the same keys and values does not affect the current state of the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a new MapAdapter with the same entries as the original.</li>
     *   <li>Call putAll on the populated MapAdapter with the new MapAdapter.</li>
     *   <li>Check that the size of the map remains unchanged.</li>
     *   <li>Check that all original keys and values are still present.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should remain the same after putting a map with the same keys and values.</li>
     *   <li>All original keys and values should still be present in the MapAdapter.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapterWithAMapWithTheSameKeysAndValues() {
        HMap anotherMap = new MapAdapter(this.map);
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should remain the same after putting a map with the same keys and values", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter with a map containing the same keys but different values does not change the size but only the values of the MapAdapter.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles a map with the same keys but different values, correctly updating the existing entries in the MapAdapter.
     *   The rationale is to confirm that putting a map with the same keys but different values updates the existing entries in the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a new MapAdapter with the same keys but different values (differentValue + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY).</li>
     *   <li>Call putAll on the populated MapAdapter with the new MapAdapter.</li>
     *   <li>Check that the size of the map remains unchanged.</li>
     *   <li>Check that all original keys are still present and contain the new values.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance has been updated with the new values.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should remain the same after putting a map with the same keys but different values.</li>
     *   <li>All original keys should still be present and contain their new values.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapterWithAMapWithSameKeysButDifferentValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("testKey" + i, "differentValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should remain the same after putting a map with the same keys but different values", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should contain the different values", this.map.containsValue("differentValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter with a map with different keys but same values increases the size and retains all original keys and values.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles a map with different keys but same values, increasing the size of the MapAdapter.
     *   The rationale is to confirm that putting a map with different keys but same values adds new entries without affecting existing ones.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a new MapAdapter with different keys (differentKey + i) but same values (testValue + i).</li>
     *   <li>Call putAll on the populated MapAdapter with the new MapAdapter.</li>
     *   <li>Check that the size of the map increases by the number of entries in the new MapAdapter.</li>
     *   <li>Check that the MapAdapter contains all the new keys.</li>
     *   <li>Check that all original keys and values are still present.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains all the original entries and the ones from the new MapAdapter.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should increase by the number of entries in the new MapAdapter.</li>
     *   <li>All original keys and values should still be present in the MapAdapter.</li>
     *   <li>The MapAdapter should contain the new keys.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapterWithAMapWithDifferentKeysButSameValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("differentKey" + i, "testValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map with different keys and same values", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain the different keys", this.map.containsKey("differentKey" + i));
            assertTrue("MapAdapter should still contain all the original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all the original values", this.map.containsValue("testValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter with another map with different type keys increases the size and retains all original keys and values.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles a map with different type keys, increasing the size of the MapAdapter.
     *   The rationale is to confirm that putting a map with different type keys adds new entries without affecting existing ones.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a new MapAdapter with different type keys (integer keys) and string values.</li>
     *   <li>Call putAll on the populated MapAdapter with the new MapAdapter.</li>
     *   <li>Check that the size of the map increases by the number of entries in the new MapAdapter.</li>
     *   <li>Check that the MapAdapter contains all the new keys.</li>
     *   <li>Check that all original keys and values are still present.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains all the original entries and the ones from the new MapAdapter.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should increase by the number of entries in the new MapAdapter.</li>
     *   <li>All original keys and values should still be present in the MapAdapter.</li>
     *   <li>The MapAdapter should contain the new keys.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeKeys() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put(i, "differentValue" + i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map with different type keys", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain all keys from the other map with different type keys", this.map.containsKey(i));
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
            assertTrue("MapAdapter should contain all values from the other map with different type keys", this.map.containsValue("differentValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter with another map with different type values increases the size and retains all original keys and values.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles a map with different type values, increasing the size of the MapAdapter.
     *   The rationale is to confirm that putting a map with different type values adds new entries without affecting existing ones.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a new MapAdapter with string keys and integer values.</li>
     *   <li>Call putAll on the populated MapAdapter with the new MapAdapter.</li>
     *   <li>Check that the size of the map increases by the number of entries in the new MapAdapter.</li>
     *   <li>Check that the MapAdapter contains all the new keys.</li>
     *   <li>Check that all original keys and values are still present.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance contains all the original entries and the ones from the new MapAdapter.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should increase by the number of entries in the new MapAdapter.</li>
     *   <li>All original keys and values should still be present in the MapAdapter.</li>
     *   <li>The MapAdapter should contain the new values.</li>
     * </ul>
     */
    @Test
    public void testPutAllOnPopulatedMapAdapterWithAnotherMapWithDifferentTypeValues() {
        HMap anotherMap = new MapAdapter();
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            anotherMap.put("differentKey" + i, i);
        }
        this.map.putAll(anotherMap);
        assertEquals("Size of the map should increase by the size of the other map with different type values", MAP_INITIAL_CAPACITY + anotherMap.size(), this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain all keys from the other map with different type values", this.map.containsKey("differentKey" + i));
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should contain all the original values", this.map.containsValue("testValue" + i));
            assertTrue("MapAdapter should contain all values from the other map with different type values", this.map.containsValue(i));
        }
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter throws a NullPointerException when called with null.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles null input by throwing a NullPointerException.
     *   The rationale is to confirm that null maps are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call putAll with a null map.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>putAll should throw a NullPointerException when called with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapAdapterWithNullMap() {
        this.map.putAll(null);
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter throws a NullPointerException when called with a map containing null keys.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles maps with null keys by throwing a NullPointerException.
     *   The rationale is to confirm that null keys are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap and add an entry with a null key.</li>
     *   <li>Call putAll on the populated MapAdapter with the other MapAdapter.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>putAll should throw a NullPointerException when called with a map containing null keys.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapWithMapContainingNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "value");
        this.map.putAll(anotherMap);
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter throws a NullPointerException when called with a map containing null values.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles maps with null values by throwing a NullPointerException.
     *   The rationale is to confirm that null values are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap and add an entry with a null value.</li>
     *   <li>Call putAll on the populated MapAdapter with the other MapAdapter.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>putAll should throw a NullPointerException when called with a map containing null values.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapWithMapContainingNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("key", null);
        this.map.putAll(anotherMap);
    }

    /**
     * Summary:
     *   Verifies that the putAll method on a populated MapAdapter throws a NullPointerException when called with a map containing both null keys and values.
     *<p>
     * Test Case Design:
     *   This test ensures that the putAll method correctly handles maps with null keys and values by throwing a NullPointerException.
     *   The rationale is to confirm that null keys and values are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Create a DummyTestMap and add an entry with both null key and value.</li>
     *   <li>Call putAll on the populated MapAdapter with the other MapAdapter.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>putAll should throw a NullPointerException when called with a map containing both null keys and values.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllOnPopulatedMapWithMapContainingNullKeyAndValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        this.map.putAll(anotherMap);
    }

    /**
     * Summary:
     *   Verifies that calling putAll on a populated MapAdapter with itself leaves the map unchanged.
     *<p>
     * Test Case Design:
     *   This test ensures that self-assignment via {@code putAll(this.map)} does not corrupt iteration
     *   or alter existing mappings in a populated {@code MapAdapter}.
     *   The rationale is to confirm safe self-operation of {@code putAll}.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call {@code this.map.putAll(this.map)} on the populated map.</li>
     *   <li>Verify the size and all original key-value mappings remain intact.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated and populated with MAP_INITIAL_CAPACITY entries.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     *<p>
     * Expected Results:
     * <ul>
     *   <li>The size remains MAP_INITIAL_CAPACITY and all entries are preserved.</li>
     * </ul>
     */
    @Test
    public void testPutAllWithThisMapAdapterOnPopulatedMap() {
        this.map.putAll(this.map);
        assertEquals("Size should remain unchanged after putAll with itself", MAP_INITIAL_CAPACITY, this.map.size());
        for (int i = 0; i < MAP_INITIAL_CAPACITY; i++) {
            assertEquals("Value should remain unchanged for testKey" + i, "testValue" + i, this.map.get("testKey" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the remove method on a populated MapAdapter behaves correctly.
     *<p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles removing entries from the MapAdapter.
     *   The rationale is to confirm that the remove method can be used to delete entries from the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that removing an entry by key returns the correct value.</li>
     *   <li>Check that the MapAdapter no longer contains the key after removal.</li>
     *   <li>Check that the MapAdapter no longer contains the value after removal.</li>
     *   <li>Check that the size of the MapAdapter decreases by 1 after each removal.</li>
     *   <li>Check that the MapAdapter is empty after removing all entries.</li>
     *   <li>Check that the size of the MapAdapter is 0 after removing all entries.</li>
     *   <li>Check that removing a non-existent key returns null.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance is empty after removing all entries.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow removing entries by key.</li>
     *   <li>The size of the MapAdapter should decrease by 1 after each removal.</li>
     *   <li>The MapAdapter should be empty after removing all entries.</li>
     *   <li>The size of the MapAdapter should be 0 after removing all entries.</li>
     *   <li>Removing a non-existent key should return null.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertEquals("Remove should return the value for the key being removed", "testValue" + i, this.map.remove("testKey" + i));
            assertFalse("MapAdapter should not contain the key after removal", this.map.containsKey("testKey" + i));
            assertFalse("MapAdapter should not contain the value after removal", this.map.containsValue("testValue" + i));
            assertEquals("Size of the map should decrease by 1 after each removal", MAP_INITIAL_CAPACITY - i - 1, this.map.size());
        }
        assertTrue("MapAdapter should be empty after removing all entries", this.map.isEmpty());
        assertEquals("Size of the map should be 0 after removing all entries", 0, this.map.size());
        assertNull("Remove should return null for a non-existent key", this.map.remove("nonExistentKey"));
    }

    /**
     * Summary:
     *   Verifies that the remove method on a populated MapAdapter with different type keys behaves correctly.
     *<p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles removing entries with different type keys.
     *   The rationale is to confirm that the remove method can be used to delete entries with different types from the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>insert an entry with a different type key (e.g., Integer key).</li>
     *   <li>Check that removing an entry with a different type key returns the correct value.</li>
     *   <li>Check that the MapAdapter no longer contains the different type key after removal.</li>
     *   <li>Check that the MapAdapter no longer contains value associated with the different type key after removal.</li>
     *   <li>Check that the size of the MapAdapter remains unchanged after removing a different type key or value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance retains its original size and contents after entering and removing a different type key.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow removing entries with different type keys.</li>
     *   <li>The size of the MapAdapter should remain unchanged after entering and removing a different type key.</li>
     *   <li>The MapAdapter should still contain all original keys and values after entering and removing a different type key.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedMapAdapterWithDifferentTypeOfKey() {
        this.map.put(1, "testValue");
        assertEquals("Remove should return the value for the different type key", "testValue", this.map.remove(1));
        assertFalse("MapAdapter should not contain the different type key after removal", this.map.containsKey(1));
        assertEquals("Size of MapAdapter should be the same after removing the different type key", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the remove method on a populated MapAdapter with different type values behaves correctly.
     *<p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles removing entries with different type values.
     *   The rationale is to confirm that the remove method can be used to delete entries with different types from the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>insert an entry with a different type value (e.g., Integer value).</li>
     *   <li>Check that removing an entry with a different type value returns the correct value.</li>
     *   <li>Check that the MapAdapter no longer contains the different type value after removal.</li>
     *   <li>Check that the MapAdapter retains its original size and contents after removing a different type value.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance retains its original size and contents after entering and removing entering and removing a different type value.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The MapAdapter should allow removing entries with different type values.</li>
     *   <li>The size of the MapAdapter should remain unchanged after entering and removing a different type value.</li>
     *   <li>The MapAdapter should still contain all original keys and values after entering removing a different type value.</li>
     * </ul>
     */
    @Test
    public void testRemoveOnPopulatedMapAdapterWithDifferentTypeOfValue() {
        this.map.put("testKey", 1);
        assertEquals("Remove should return the value for the different type value", 1, this.map.remove("testKey"));
        assertFalse("MapAdapter should not contain the different type value after removal", this.map.containsValue(1));
        assertEquals("Size of MapAdapter should be the same after removing the different type value", MAP_INITIAL_CAPACITY, this.map.size());
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should still contain all original keys", this.map.containsKey("testKey" + i));
            assertTrue("MapAdapter should still contain all original values", this.map.containsValue("testValue" + i));
        }
    }

    /**
     * Summary:
     *   Verifies that the remove method on a populated MapAdapter throws a NullPointerException when called with null.
     *<p>
     * Test Case Design:
     *   This test ensures that the remove method correctly handles null input by throwing a NullPointerException.
     *   The rationale is to confirm that null keys are not allowed in MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Call remove with a null key.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>remove should throw a NullPointerException when called with null.</li>
     * </ul>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveOnPopulatedMapAdapterWithNullKey() {
        this.map.remove(null);
    }

    /**
     * Summary:
     *   Verifies that the size method on a populated MapAdapter returns the correct size.
     *<p>
     * Test Case Design:
     *   This test ensures that the size method correctly returns the number of entries in the MapAdapter.
     *   The rationale is to confirm that the size method accurately reflects the number of entries in the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the size of the map matches the initial capacity.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The size of the map should match the initial capacity.</li>
     * </ul>
     */
    @Test
    public void testSizeOnPopulatedMapAdapter() {
        assertEquals("Size of the map should match the initial capacity", MAP_INITIAL_CAPACITY, this.map.size());
    }

    /**
     * Summary:
     *   Verifies that the values method on a populated MapAdapter returns a non-null and non-empty ValueCollection.
     *<p>
     * Test Case Design:
     *   This test ensures that the values method correctly returns a set of values from the MapAdapter.
     *   The rationale is to confirm that the values method provides access to the values in the MapAdapter.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the ValueCollection of a populated MapAdapter is not null.</li>
     *   <li>Check that the ValueCollection of a populated MapAdapter is not empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection of a populated MapAdapter should not be null.</li>
     *   <li>The ValueCollection of a populated MapAdapter should not be empty.</li>
     * </ul>
     */
    @Test
    public void testValuesOnPopulatedMapAdapter() {
        HCollection values = this.map.values();
        assertNotNull("Values collection of a populated MapAdapter should not be null", values);
        assertFalse("Values collection of a populated MapAdapter should not be empty", values.isEmpty());
    }   

    /**
     * Summary:
     *   Verifies that the values method on a populated MapAdapter with different type of values returns a non-null and non-empty ValueCollection.
     *<p>
     * Test Case Design:
     *   This test ensures that the values method correctly returns a set of values from the MapAdapter with different type values.
     *   The rationale is to confirm that the values method provides access to the values in the MapAdapter, even when they are of different types.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Insert an entry with a different type value (e.g., Integer value).</li>
     *   <li>Check that the ValueCollection of a populated MapAdapter with different type values is not null.</li>
     *   <li>Check that the ValueCollection of a populated MapAdapter with different type values is not empty.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The ValueCollection of a populated MapAdapter with different type values should not be null.</li>
     *   <li>The ValueCollection of a populated MapAdapter with different type values should not be empty.</li>
     * </ul>
     */
    @Test
    public void testValuesOnPopulatedMapAdapterWithDifferentTypeOfValues() {
        this.map.put("testKey", 1);
        assertNotNull("Values collection of a populated MapAdapter with different type values should not be null", this.map.values());
        assertFalse("Values collection of a populated MapAdapter with different type values should not be empty", this.map.values().isEmpty());
    }

    /**
     * Summary:
     *   Verifies that the toString method on a populated MapAdapter returns a string representation of the map.
     *<p>
     * Test Case Design:
     *   This test ensures that the toString method correctly returns a string representation of the MapAdapter.
     *   The rationale is to confirm that the toString method provides a readable format of the map's contents.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the toString method returns a string representation of the map.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string representation of the map.</li>
     * </ul>
     */
    @Test
    public void testToStringOnPopulatedMapAdapter() {
        HIterator iterator = this.map.entrySet().iterator();
        String expected = "{";
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expected += entry.toString() + (iterator.hasNext() ? ", " : "}");
        }

        assertEquals("toString() should return a string representation of the map created this way", expected, this.map.toString());
    }

    /**
     * Summary:
     *   Verifies that the toString method on a populated MapAdapter with different type keys and values returns a string representation of the map.
     *<p>
     * Test Case Design:
     *   This test ensures that the toString method correctly returns a string representation of the MapAdapter with different type keys and values.
     *   The rationale is to confirm that the toString method provides a readable format of the map's contents.
     *<p>
     * Test Description:
     * <ul>
     *   <li>Check that the toString method returns a string representation of the map.</li>
     * </ul>
     *<p>
     * Pre-Condition:
     *   MapAdapter is instantiated using the default constructor and is populated with MAP_INITIAL_CAPACITY entries that are formed as "testKey" + i, "testValue" + i where 0 &lt;= i &lt; MAP_INITIAL_CAPACITY.
     *<p>
     * Post-Condition:
     *   The MapAdapter instance remains unchanged.
     * <p>
     * Expected Results:
     * <ul>
     *   <li>The toString method should return a string representation of the map.</li>
     * </ul>
     */
    @Test
    public void testToStringOnPopulatedMapAdapterWithDifferentTypeOfKeysAndValues() {
        this.map.put(1, 1);
        HIterator iterator = this.map.entrySet().iterator();
        String expected = "{";
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expected += entry.toString() + (iterator.hasNext() ? ", " : "}");
        }

        assertEquals("toString() should return a string representation of the map created this way", expected, this.map.toString());
    }
}