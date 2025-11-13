package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.nio.file.Paths;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static ConfigReader instance;
    private JsonObject config;
    
    private ConfigReader() {
        try {
            String configPath = Paths.get("config", "config.json").toString();
            logger.info("Loading configuration from: {}", configPath);
            FileReader reader = new FileReader(configPath);
            config = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            logger.info("Configuration loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to load config.json", e);
            throw new RuntimeException("Failed to load config.json: " + e.getMessage(), e);
        }
    }
    
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
    
    public String getAndroidCapability(String key) {
        return getCapability("android", key);
    }
    
    public String getIosCapability(String key) {
        return getCapability("ios", key);
    }
    
    private String getCapability(String platform, String key) {
        try {
            String value = config.getAsJsonObject(platform).get(key).getAsString();
            return resolveEnvironmentVariable(value);
        } catch (Exception e) {
            logger.warn("Failed to get capability: {}.{}", platform, key);
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
    
    private String resolveEnvironmentVariable(String value) {
        if (value == null) {
            return null;
        }
        
        // Support environment variables: ${ENV_VAR:-default_value}
        if (value.startsWith("${") && value.endsWith("}")) {
            String content = value.substring(2, value.length() - 1);
            String[] parts = content.split(":-");
            String envVar = parts[0];
            String defaultValue = parts.length > 1 ? parts[1] : "";
            
            String envValue = System.getenv(envVar);
            if (envValue == null || envValue.isEmpty()) {
                envValue = System.getProperty(envVar, defaultValue);
            }
            
            return envValue;
        }
        
        return value;
    }
    
    public String getAppiumUrl() {
        JsonObject appium = config.getAsJsonObject("appium");
        String host = resolveEnvironmentVariable(appium.get("host").getAsString());
        String portStr = resolveEnvironmentVariable(appium.get("port").getAsString());
        int port = Integer.parseInt(portStr);
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
