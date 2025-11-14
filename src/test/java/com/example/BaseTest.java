package com.example;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverManager;

/** A base class for all tests, handling driver initialization and teardown. */
public class BaseTest {
  protected AppiumDriver driver;

  /**
   * Sets up the driver before each test method. The platform can be passed as a parameter from the
   * testng.xml file.
   *
   * @param platform The mobile platform to run the test on ("android" or "ios").
   */
  @Parameters("platform")
  @BeforeMethod
  public void setUp(@Optional("android") String platform) {
    try {
      DriverManager.initializeDriver(platform);
      driver = DriverManager.getDriver();
    } catch (Exception e) {
      throw new RuntimeException("Failed to set up the driver", e);
    }
  }

  /** Tears down the driver after each test method. */
  @AfterMethod
  public void tearDown() {
    DriverManager.quitDriver();
  }
}
