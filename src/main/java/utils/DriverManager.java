package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Manages the Appium driver instance.
 * This class is responsible for initializing and quitting the driver.
 */
public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    /**
     * Initializes the Appium driver for the specified platform.
     *
     * @param platform The platform to initialize the driver for (e.g., "android", "ios").
     * @throws MalformedURLException If the Appium URL is invalid.
     */
    public static void initializeDriver(String platform) throws MalformedURLException {
        if (driver.get() != null) {
            logger.warn("Driver already initialized. Quitting existing driver.");
            quitDriver();
        }
        
        ConfigReader config = ConfigReader.getInstance();
        DesiredCapabilities caps = new DesiredCapabilities();
        
        logger.info("Initializing driver for platform: {}", platform);
        
        if (platform.equalsIgnoreCase("android")) {
            caps.setCapability("platformName", config.getAndroidCapability("platformName"));
            caps.setCapability("appium:platformVersion", config.getAndroidCapability("platformVersion"));
            caps.setCapability("appium:deviceName", config.getAndroidCapability("deviceName"));
            caps.setCapability("appium:app", config.getAndroidCapability("app"));
            caps.setCapability("appium:appPackage", config.getAndroidCapability("appPackage"));
            caps.setCapability("appium:appActivity", config.getAndroidCapability("appActivity"));
            caps.setCapability("appium:automationName", config.getAndroidCapability("automationName"));
            caps.setCapability("appium:noReset", config.getAndroidBooleanCapability("noReset"));
            caps.setCapability("appium:fullReset", config.getAndroidBooleanCapability("fullReset"));
            caps.setCapability("appium:autoGrantPermissions", config.getAndroidBooleanCapability("autoGrantPermissions"));
            caps.setCapability("appium:newCommandTimeout", config.getAndroidIntCapability("newCommandTimeout"));
            
            logger.info("Creating AndroidDriver with URL: {}", config.getAppiumUrl());
            driver.set(new AndroidDriver(new URL(config.getAppiumUrl()), caps));
            
        } else if (platform.equalsIgnoreCase("ios")) {
            caps.setCapability("platformName", config.getIosCapability("platformName"));
            caps.setCapability("appium:platformVersion", config.getIosCapability("platformVersion"));
            caps.setCapability("appium:deviceName", config.getIosCapability("deviceName"));
            caps.setCapability("appium:app", config.getIosCapability("app"));
            caps.setCapability("appium:bundleId", config.getIosCapability("bundleId"));
            caps.setCapability("appium:automationName", config.getIosCapability("automationName"));
            caps.setCapability("appium:noReset", config.getIosBooleanCapability("noReset"));
            caps.setCapability("appium:fullReset", config.getIosBooleanCapability("fullReset"));
            caps.setCapability("appium:autoAcceptAlerts", config.getIosBooleanCapability("autoAcceptAlerts"));
            
            logger.info("Creating IOSDriver with URL: {}", config.getAppiumUrl());
            driver.set(new IOSDriver(new URL(config.getAppiumUrl()), caps));
            
        } else {
            throw new IllegalArgumentException("Invalid platform: " + platform + ". Must be 'android' or 'ios'");
        }
        
        if (getDriver() != null) {
            int implicitWait = ConfigReader.getInstance().getImplicitWait();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            logger.info("Driver initialized successfully with implicit wait of {} seconds", implicitWait);
        }
    }

    /**
     * Gets the current Appium driver instance.
     *
     * @return The Appium driver.
     */
    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call initializeDriver() first.");
        }
        return driver.get();
    }

    /**
     * Quits the Appium driver and removes it from the ThreadLocal storage.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                logger.info("Quitting driver");
                driver.get().quit();
                logger.info("Driver quit successfully");
            } catch (Exception e) {
                logger.error("Error while quitting driver", e);
            } finally {
                driver.remove();
            }
        }
    }
}
