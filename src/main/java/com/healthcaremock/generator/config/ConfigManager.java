package com.healthcaremock.generator.config;

import org.mitre.synthea.helpers.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Manages configuration for the healthcare mock data generator.
 * Extends Synthea's configuration with additional parameters specific to this project.
 */
public class ConfigManager {
    private static final String CONFIG_FILE = "healthcare-generator.properties";
    private static Properties customProperties = new Properties();
    
    /**
     * Initializes the configuration manager.
     * Loads the custom properties and integrates them with Synthea's Config.
     */
    public static void initialize() {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Unable to find " + CONFIG_FILE);
                return;
            }
            
            // Load the custom properties
            customProperties.load(input);
            
            // Integrate custom properties with Synthea Config
            for (String name : customProperties.stringPropertyNames()) {
                String value = customProperties.getProperty(name);
                Config.set(name, value);
            }
            
            System.out.println("ConfigManager initialized successfully");
        } catch (IOException ex) {
            System.err.println("Error loading configuration: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Gets a property as a String.
     * @param key The property key.
     * @param defaultValue The default value if the property is not found.
     * @return The property value.
     */
    public static String getAsString(String key, String defaultValue) {
        return customProperties.getProperty(key, defaultValue);
    }
    
    /**
     * Gets a property as an integer.
     * @param key The property key.
     * @param defaultValue The default value if the property is not found.
     * @return The property value as an integer.
     */
    public static int getAsInteger(String key, int defaultValue) {
        String value = customProperties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Gets a property as a double.
     * @param key The property key.
     * @param defaultValue The default value if the property is not found.
     * @return The property value as a double.
     */
    public static double getAsDouble(String key, double defaultValue) {
        String value = customProperties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Gets a property as a boolean.
     * @param key The property key.
     * @param defaultValue The default value if the property is not found.
     * @return The property value as a boolean.
     */
    public static boolean getAsBoolean(String key, boolean defaultValue) {
        String value = customProperties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
} 