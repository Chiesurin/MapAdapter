// Diego Chiesurin

package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class is used to group all test classes of {@link myTest} for execution.
 * It uses JUnit's Suite runner to run all specified test classes together.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestEmptyMapAdapter.class,
    TestEmptyAbstractCollections.class,
    TestPopulatedMapAdapter.class,
    TestEntry.class,
    TestPopulatedAbstractCollections.class,
    TestPopulatedAbstractCollectionIterators.class,
    TestEmptyAbstractCollectionIterators.class
})
public class TestsSuite {
    
    /**
     * Default constructor for {@code TestsSuite}.
     * It is intentionally left empty as the class is used only for grouping tests.
     */
    public TestsSuite() {
        //intentionally left blank
    }
}