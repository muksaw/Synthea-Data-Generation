package com.healthcaremock.generator;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.helpers.Config;

import java.io.File;

/**
 * Simple test class to verify Synthea integration.
 */
public class SyntheaTest {
    
    /**
     * Main method to test Synthea integration.
     * @param args Command line arguments.
     * @throws Exception if an error occurs.
     */
    public static void main(String[] args) throws Exception {
        // Print Synthea version info
        System.out.println("Testing Synthea integration");
        
        // Try to access some Synthea classes
        System.out.println("Loading Synthea configuration...");
        // Load default config
        Config.load(new File("src/main/resources/synthea.properties"));
        
        // Check what generator options are available
        System.out.println("Creating Generator options...");
        Generator.GeneratorOptions options = new Generator.GeneratorOptions();
        options.population = 10;
        
        // Create a generator
        System.out.println("Creating Generator with " + options.population + " population...");
        Generator generator = new Generator(options);
        
        System.out.println("Synthea integration test completed successfully!");
    }
} 