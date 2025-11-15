package com.example;

import io.appium.java_client.AppiumBy;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {

  @Test(description = "Verify app launches successfully")
  public void testAppLaunch() {
    // Wait for app to load
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    // Example: Verify an element is displayed
    // Replace with your actual element locator
    try {
      WebElement element =
          wait.until(
              ExpectedConditions.presenceOfElementLocated(
                  AppiumBy.id("com.example.app:id/main_screen")));
      Assert.assertTrue(element.isDisplayed(), "App did not launch successfully");
    } catch (Exception e) {
      Assert.fail("Failed to launch app: " + e.getMessage());
    }
  }

  @Test(description = "Example test with actions")
  public void testBasicInteraction() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    try {
      // Example: Find and click a button
      WebElement button =
          wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("buttonId")));
      button.click();

      // Verify result
      WebElement resultText =
          wait.until(
              ExpectedConditions.presenceOfElementLocated(
                  AppiumBy.xpath("//android.widget.TextView[@text='Expected Result']")));
      Assert.assertTrue(resultText.isDisplayed(), "Expected result not displayed");
    } catch (Exception e) {
      Assert.fail("Test interaction failed: " + e.getMessage());
    }
  }
}
