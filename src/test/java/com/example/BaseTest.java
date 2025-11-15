package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AppiumDriver driver;
    private JsonObject config;

    @BeforeMethod
    @Parameters({"platform"})
    public void setUp(String platform) throws Exception {
        loadConfig();
        driver = initializeDriver(platform);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void loadConfig() throws Exception {
        try (FileReader reader = new FileReader("config/config.json")) {
            config = JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    private AppiumDriver initializeDriver(String platform) throws Exception {
        JsonObject platformConfig = config.getAsJsonObject(platform.toLowerCase());
        JsonObject serverConfig = config.getAsJsonObject("appiumServer");
        
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Set capabilities from config
        platformConfig.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value.toString().matches("\".*\"")) {
                caps.setCapability(key, value.getAsString());
            } else if (value.toString().matches("\\d+")) {
                caps.setCapability(key, value.getAsInt());
            } else {
                caps.setCapability(key, value.getAsBoolean());
            }
        });

        URL serverUrl = new URL(serverConfig.get("url").getAsString());
        
        if (platform.equalsIgnoreCase("android")) {
            return new AndroidDriver(serverUrl, caps);
        } else if (platform.equalsIgnoreCase("ios")) {
            return new IOSDriver(serverUrl, caps);
        } else {
            throw new IllegalArgumentException("Invalid platform: " + platform);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
