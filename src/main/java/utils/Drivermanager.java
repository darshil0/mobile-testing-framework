package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    
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
            caps.setCapability("platformVersion", config.getAndroidCapability("platformVersion"));
            caps.setCapability("deviceName", config.getAndroidCapability("deviceName"));
            caps.setCapability("app", config.getAndroidCapability("app"));
            caps.setCapability("appPackage", config.getAndroidCapability("appPackage"));
            caps.setCapability("appActivity", config.getAndroidCapability("appActivity"));
            caps.setCapability("automationName", config.getAndroidCapability("automationName"));
            caps.setCapability("noReset", config.getAndroidBooleanCapability("noReset"));
            caps.setCapability("fullReset", config.getAndroidBooleanCapability("fullReset"));
            caps.setCapability("autoGrantPermissions", config.getAndroidBooleanCapability("autoGrantPermissions"));
            caps.setCapability("newCommandTimeout", config.getAndroidIntCapability("newCommandTimeout"));
            
            logger.info("Creating AndroidDriver with URL: {}", config.getAppiumUrl());
            driver.set(new AndroidDriver(new URL(config.getAppiumUrl()), caps));
            
        } else if (platform.equalsIgnoreCase("ios")) {
            caps.setCapability("platformName", config.getIosCapability("platformName"));
            caps.setCapability("platformVersion", config.getIosCapability("platformVersion"));
            caps.setCapability("deviceName", config.getIosCapability("deviceName"));
            caps.setCapability("app", config.getIosCapability("app"));
            caps.setCapability("bundleId", config.getIosCapability("bundleId"));
            caps.setCapability("automationName", config.getIosCapability("automationName"));
            caps.setCapability("noReset", config.getIosBooleanCapability("noReset"));
            caps.setCapability("fullReset", config.getIosBooleanCapability("fullReset"));
            caps.setCapability("autoAcceptAlerts", config.getIosBooleanCapability("autoAcceptAlerts"));
            
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
    
    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call initializeDriver() first.");
        }
        return driver.get();
    }
    
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
