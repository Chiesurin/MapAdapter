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
 * {@code TestsSuite} class to group all test classes for execution.
 * It uses JUnit's Suite runner to run all specified test classes together.
 */
public class TestsSuite {
    
    public TestsSuite() {
        // intentionally left blank
    }
}