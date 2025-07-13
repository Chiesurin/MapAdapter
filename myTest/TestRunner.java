//Diego Chiesurin - 2111553

package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.AllTests;

public class TestRunner {
    public static void main(String[] args){
        System.out.println("\n-------------------- TESTS --------------------");
        System.out.println("\nStarting the test runner...");
        
        Result result = JUnitCore.runClasses(AllTests.class);

        System.out.println("\n------------------------------------------------");
        System.out.println("Total runtime: " + result.getRunTime() + " ms");
        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Total successful tests: " + (result.getRunCount() - result.getFailureCount() - result.getIgnoreCount()));
        System.out.println("Total failures: " + result.getFailureCount());
        System.out.println("Total ignored: " + result.getIgnoreCount());
        System.out.println("\n------------------------------------------------");

        if (result.wasSuccessful()) {
            System.out.println("\nAll tests passed successfully!");
        } else {
            System.out.println("\nSome tests failed: ");
            for (Failure failure : result.getFailures()) {
                System.out.println("Test: " + failure.getDescription().getMethodName());
                System.out.println("Message: " + failure.getMessage());
                System.out.println("Stack Trace: " + failure.getTrace());
                System.out.println("------------------------------------------------");
            }
        }
        System.out.println("\n------------------------------------------------");
        System.out.println("\nExiting the test runner...\n");
    }
}