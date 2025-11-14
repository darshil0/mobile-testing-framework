package com.example;

import org.testng.annotations.Test;

/**
 * An example test class to demonstrate the framework's usage.
 */
public class ExampleTest extends BaseTest {

    /**
     * An example test case.
     */
    @Test
    public void testExample() {
        // Your test logic here
        System.out.println("Driver initialized successfully!");
        // Example: You can now use the 'driver' instance from BaseTest
        // String pageSource = driver.getPageSource();
        // System.out.println("Page source length: " + pageSource.length());
    }
}
