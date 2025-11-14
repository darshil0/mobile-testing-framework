package com.example.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigReader {

    private static final String CONFIG_FILE = "config/config.json";

    public static JSONObject getConfig() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(CONFIG_FILE)));
        return new JSONObject(content);
    }
}
