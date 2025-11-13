package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class GestureHelper {
    private static final Logger logger = LoggerFactory.getLogger(GestureHelper.class);
    
    public static void swipeUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        
        swipe(driver, startX, startY, startX, endY, 1000);
    }
    
    public static void swipeDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);
        
        swipe(driver, startX, startY, startX, endY, 1000);
    }
    
    public static void swipeLeft(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int y = size.height / 2;
        
        swipe(driver, startX, y, endX, y, 1000);
    }
    
    public static void swipeRight(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);
        int y = size.height / 2;
        
        swipe(driver, startX, y, endX, y, 1000);
    }
    
    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY, int durationMs) {
        try {
            logger.info("Swiping from ({}, {}) to ({}, {}) in {}ms", startX, startY, endX, endY, durationMs);
            new TouchAction(driver)
                    .press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationMs)))
                    .moveTo(PointOption.point(endX, endY))
                    .release()
                    .perform();
        } catch (Exception e) {
            logger.error("Failed to perform swipe", e);
            throw e;
        }
    }
    
    public static void tap(AppiumDriver driver, WebElement element) {
        try {
            logger.info("Tapping on element");
            new TouchAction(driver)
                    .tap(PointOption.point(element.getLocation().getX() + element.getSize().getWidth() / 2,
                            element.getLocation().getY() + element.getSize().getHeight() / 2))
                    .perform();
        } catch (Exception e) {
            logger.error("Failed to tap element", e);
            throw e;
        }
    }
    
    public static void longPress(AppiumDriver driver, WebElement element, int durationSeconds) {
        try {
            logger.info("Long pressing element for {} seconds", durationSeconds);
            new TouchAction(driver)
                    .longPress(PointOption.point(element.getLocation().getX() + element.getSize().getWidth() / 2,
                            element.getLocation().getY() + element.getSize().getHeight() / 2))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationSeconds)))
                    .release()
                    .perform();
        } catch (Exception e) {
            logger.error("Failed to long press element", e);
            throw e;
        }
    }
    
    public static void scrollToElement(AppiumDriver driver, WebElement element) {
        try {
            logger.info("Scrolling to element");
            int maxScrolls = 10;
            int scrollCount = 0;
            
            while (!isElementInView(driver, element) && scrollCount < maxScrolls) {
                swipeUp(driver);
                scrollCount++;
            }
            
            if (scrollCount == maxScrolls) {
                logger.warn("Element not found after {} scrolls", maxScrolls);
            }
        } catch (Exception e) {
            logger.error("Failed to scroll to element", e);
            throw e;
        }
    }
    
    private static boolean isElementInView(AppiumDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
