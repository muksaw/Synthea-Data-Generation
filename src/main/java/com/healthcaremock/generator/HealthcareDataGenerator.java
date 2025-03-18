package com.healthcaremock.generator;

import org.mitre.synthea.engine.Generator;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Main class for the Healthcare Data Generator.
 */
public class HealthcareDataGenerator {
    
    /**
     * Main method to run the generator.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Starting Healthcare Data Generator...");
            
            // Parse command line arguments
            int population = 10; // Default
            if (args.length > 0) {
                try {
                    population = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid population count: " + args[0]);
                    System.err.println("Using default population of 10");
                }
            }
            
            // Create generator options
            Generator.GeneratorOptions options = new Generator.GeneratorOptions();
            options.population = population;
            options.overflow = false; // Don't generate beyond the requested population count
            
            // Create and run the enhanced generator
            System.out.println("Generating " + population + " patients...");
            EnhancedGenerator generator = new EnhancedGenerator(options);
            List<EnhancedPatientData> patients = generator.generatePatients(population);
            
            // Output results
            System.out.println("Generated " + patients.size() + " patients successfully.");
            
            // Export to JSON
            exportToJson(patients);
            
            System.out.println("Data generation complete!");
            
        } catch (Exception e) {
            System.err.println("Error generating healthcare data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Export patient data to JSON files.
     * 
     * @param patients The list of enhanced patient data
     */
    private static void exportToJson(List<EnhancedPatientData> patients) {
        try {
            // Create output directory
            File outputDir = new File("output/enhanced");
            outputDir.mkdirs();
            
            System.out.println("Exporting patient data to " + outputDir.getAbsolutePath());
            
            // Export each patient
            for (EnhancedPatientData patient : patients) {
                // Generate a simple JSON representation
                StringBuilder json = new StringBuilder();
                json.append("{\n");
                
                // Basic patient info
                json.append("  \"uuid\": \"").append(patient.getPerson().randUUID().toString()).append("\",\n");
                
                // Patient attributes
                json.append("  \"attributes\": {\n");
                
                // Add basic demographics
                json.append("    \"gender\": \"").append(patient.getPerson().attributes.getOrDefault("gender", "")).append("\",\n");
                json.append("    \"age\": ").append(patient.getPerson().attributes.getOrDefault("age", 0)).append(",\n");
                json.append("    \"race\": \"").append(patient.getPerson().attributes.getOrDefault("race", "")).append("\",\n");
                json.append("    \"ethnicity\": \"").append(patient.getPerson().attributes.getOrDefault("ethnicity", "")).append("\"\n");
                
                json.append("  },\n");
                
                // Patient-reported data
                json.append("  \"patientReportedData\": {\n");
                patient.getPatientReportedData().forEach((key, value) -> {
                    if (value instanceof String) {
                        json.append("    \"").append(key).append("\": \"").append(value).append("\",\n");
                    } else {
                        json.append("    \"").append(key).append("\": ").append(value).append(",\n");
                    }
                });
                // Remove trailing comma
                if (!patient.getPatientReportedData().isEmpty()) {
                    json.setLength(json.length() - 2);
                    json.append("\n");
                }
                
                json.append("  }\n");
                json.append("}\n");
                
                // Write to file
                String fileName = "patient_" + patient.getPerson().randUUID().toString() + ".json";
                try (FileWriter writer = new FileWriter(new File(outputDir, fileName))) {
                    writer.write(json.toString());
                }
            }
            
            System.out.println("Exported " + patients.size() + " patients to JSON");
            
        } catch (Exception e) {
            System.err.println("Error exporting patient data: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 