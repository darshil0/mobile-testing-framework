package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitHelper {
    private static final Logger logger = LoggerFactory.getLogger(WaitHelper.class);
    private static final int DEFAULT_TIMEOUT = ConfigReader.getInstance().getExplicitWait();
    
    public static void waitForElementToBeVisible(AppiumDriver driver, WebElement element) {
        waitForElementToBeVisible(driver, element, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementToBeVisible(AppiumDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element not visible within {} seconds", timeoutInSeconds, e);
            throw e;
        }
    }
    
    public static void waitForElementToBeVisible(AppiumDriver driver, By locator) {
        waitForElementToBeVisible(driver, locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementToBeVisible(AppiumDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element with locator {} not visible within {} seconds", locator, timeoutInSeconds, e);
            throw e;
        }
    }
    
    public static void waitForElementToBeClickable(AppiumDriver driver, WebElement element) {
        waitForElementToBeClickable(driver, element, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementToBeClickable(AppiumDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Element not clickable within {} seconds", timeoutInSeconds, e);
            throw e;
        }
    }
    
    public static void waitForElementToBeInvisible(AppiumDriver driver, WebElement element) {
        waitForElementToBeInvisible(driver, element, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementToBeInvisible(AppiumDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            logger.error("Element still visible after {} seconds", timeoutInSeconds, e);
            throw e;
        }
    }
    
    public static void waitForPresenceOfElement(AppiumDriver driver, By locator) {
        waitForPresenceOfElement(driver, locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForPresenceOfElement(AppiumDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element with locator {} not present within {} seconds", locator, timeoutInSeconds, e);
            throw e;
        }
    }
    
    public static boolean waitForElementToDisappear(AppiumDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.warn("Element with locator {} still present after {} seconds", locator, timeoutInSeconds);
            return false;
        }
    }
    
    public static void customWait(AppiumDriver driver, int timeoutInSeconds, 
                                 org.openqa.selenium.support.ui.ExpectedCondition<?> condition) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(condition);
        } catch (Exception e) {
            logger.error("Custom wait condition not met within {} seconds", timeoutInSeconds, e);
            throw e;
        }
    }
}
