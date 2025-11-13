package com.example;

import com.example.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class ExampleTest {

    private AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() throws IOException {
        JSONObject config = ConfigReader.getConfig();
        JSONObject androidConfig = config.getJSONObject("android");

        DesiredCapabilities caps = new DesiredCapabilities();
        for (String key : androidConfig.keySet()) {
            caps.setCapability(key, androidConfig.get(key));
        }

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void testExample() {
        // Your test logic here
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
