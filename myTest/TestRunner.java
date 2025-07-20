//Diego Chiesurin - 2111553

package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * {@code TestRunner} class to run all tests in the {@link TestsSuite}, 
 * regarding the {@link myAdapter.MapAdapter} and all its nested classes.
 * It prints the results of the tests including total runtime, number of tests run,
 * successful tests, failures, and ignored tests.
 */
public class TestRunner {

    /**
     * Default constructor for {@code TestRunner}.
     * It is intentionally left empty as the class is used only for running tests.
     */
    public TestRunner() {
        //intentionally left blank
    }

    /**
     * Main method to run the tests in {@link TestsSuite}.
     * It prints the results of the test execution.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args){
        System.out.println("\n-------------------- TESTS --------------------");
        
        Result result = JUnitCore.runClasses(TestsSuite.class);

        System.out.println("\nTotal runtime: " + result.getRunTime() + " ms");
        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Total successful tests: " + (result.getRunCount() - result.getFailureCount() - result.getIgnoreCount()));
        System.out.println("Total failures: " + result.getFailureCount());
        System.out.println("Total ignored: " + result.getIgnoreCount());
        System.out.println("\n------------------------------------------------");

        if (result.wasSuccessful()) {
            System.out.println("\nAll tests passed successfully!");
            System.out.println("\n------------------------------------------------");
        } else {
            System.out.println("\nSome tests failed: ");
            for (Failure failure : result.getFailures()) {
                System.out.println("Test class: " + failure.getDescription().getClassName());
                System.out.println("Test name: " + failure.getDescription().getMethodName());
                System.out.println("Message: " + failure.getMessage());
                System.out.println("\n------------------------------------------------");
            }
        }
        System.out.println("\nExiting the test runner...\n");
    }
}