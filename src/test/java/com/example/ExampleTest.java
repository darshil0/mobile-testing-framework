package com.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExampleTest {

    private AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() throws IOException {
        String configString = new String(Files.readAllBytes(Paths.get("config/config.json")));
        JSONObject config = new JSONObject(configString);
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
