//Diego Chiesurin - 2111553

package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Test;

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

/**
 * This {@code TestsSuite} class is used to group all test classes of {@link myTest} for execution.
 * It uses JUnit's Suite runner to run all specified test classes together.
 */
public class TestsSuite {
    
    /**
     * Default constructor for {@code TestsSuite}.
     * It is intentionally left empty as the class is used only for grouping tests.
     */
    public TestsSuite() {
        //intentionally left blank
    }
}