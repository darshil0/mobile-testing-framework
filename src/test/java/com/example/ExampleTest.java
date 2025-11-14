package com.example;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.DriverManager;

/**
 * An example test class to demonstrate the framework's usage.
 */
public class ExampleTest {
    private AppiumDriver driver;

    /**
     * Sets up the driver before each test method.
     */
    @BeforeMethod
    public void setUp() {
        String platform = System.getProperty("platform", "android");
        try {
            DriverManager.initializeDriver(platform);
            driver = DriverManager.getDriver();
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up the driver", e);
        }
    }

    /**
     * An example test case.
     */
    @Test
    public void testExample() {
        // Your test logic here
        System.out.println("Driver initialized successfully!");
    }

    /**
     * Tears down the driver after each test method.
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
