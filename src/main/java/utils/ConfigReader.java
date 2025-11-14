package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Handles reading and parsing the configuration file (config.json).
 * This class follows the Singleton pattern to ensure only one instance is created.
 */
public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static volatile ConfigReader instance;
    private JsonObject config;

    /**
     * Private constructor to prevent instantiation from outside.
     * Loads the config.json file and parses it into a JsonObject.
     */
    private ConfigReader() {
        try (FileReader reader = new FileReader(Paths.get("config", "config.json").toString())) {
            config = JsonParser.parseReader(reader).getAsJsonObject();
            logger.info("Configuration loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load config.json", e);
            throw new RuntimeException("Failed to load config.json", e);
        }
    }

    /**
     * Returns the singleton instance of the ConfigReader.
     *
     * @return The singleton instance.
     */
    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }
    
    /**
     * Gets a capability value for Android.
     *
     * @param key The capability key.
     * @return The capability value.
     */
    public String getAndroidCapability(String key) {
        return getCapability("android", key);
    }

    /**
     * Gets a capability value for iOS.
     *
     * @param key The capability key.
     * @return The capability value.
     */
    public String getIosCapability(String key) {
        return getCapability("ios", key);
    }

    /**
     * Generic method to get a capability for a given platform.
     *
     * @param platform The platform (e.g., "android", "ios").
     * @param key      The capability key.
     * @return The capability value.
     */
    private String getCapability(String platform, String key) {
        try {
            String value = config.getAsJsonObject(platform).get(key).getAsString();
            return resolveEnvironmentVariable(value);
        } catch (Exception e) {
            logger.warn("Failed to get capability: {}.{}", platform, key, e);
            return null;
        }
    }
    
    public boolean getAndroidBooleanCapability(String key) {
        try {
            return config.getAsJsonObject("android").get(key).getAsBoolean();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean getIosBooleanCapability(String key) {
        try {
            return config.getAsJsonObject("ios").get(key).getAsBoolean();
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getAndroidIntCapability(String key) {
        try {
            return config.getAsJsonObject("android").get(key).getAsInt();
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Resolves environment variables in the config value.
     * Supports the format ${ENV_VAR:-default_value}.
     *
     * @param value The value to resolve.
     * @return The resolved value.
     */
    private String resolveEnvironmentVariable(String value) {
        if (value == null || !value.startsWith("${") || !value.endsWith("}")) {
            return value;
        }
        String content = value.substring(2, value.length() - 1);
        String[] parts = content.split(":-");
        String envVar = parts[0];
        String defaultValue = parts.length > 1 ? parts[1] : null;

        String envValue = System.getenv(envVar);
        return envValue != null ? envValue : System.getProperty(envVar, defaultValue);
    }

    /**
     * Gets the Appium server URL from the config.
     *
     * @return The Appium server URL.
     */
    public String getAppiumUrl() {
        JsonObject appium = config.getAsJsonObject("appium");
        String host = resolveEnvironmentVariable(appium.get("host").getAsString());
        int port = Integer.parseInt(resolveEnvironmentVariable(appium.get("port").getAsString()));
        return String.format("http://%s:%d", host, port);
    }
    
    public int getImplicitWait() {
        try {
            return config.getAsJsonObject("testSettings").get("implicitWait").getAsInt();
        } catch (Exception e) {
            return 10;
        }
    }
    
    public int getExplicitWait() {
        try {
            return config.getAsJsonObject("testSettings").get("explicitWait").getAsInt();
        } catch (Exception e) {
            return 30;
        }
    }
    
    public boolean isScreenshotOnFailure() {
        try {
            return config.getAsJsonObject("testSettings").get("screenshotOnFailure").getAsBoolean();
        } catch (Exception e) {
            return true;
        }
    }
}
