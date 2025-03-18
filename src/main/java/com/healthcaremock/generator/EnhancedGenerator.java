package com.healthcaremock.generator;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.helpers.Config;
import org.mitre.synthea.world.agents.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Enhanced generator that extends Synthea's Generator with additional functionality.
 */
public class EnhancedGenerator {
    private final Generator syntheaGenerator;
    private final List<EnhancedPatientData> generatedPatients;
    
    /**
     * Creates a new enhanced generator with default settings.
     * 
     * @throws Exception if configuration loading fails
     */
    public EnhancedGenerator() throws Exception {
        // Initialize configuration if not already loaded
        if (Config.get("exporter.baseDirectory") == null) {
            Config.load(new File("src/main/resources/synthea.properties"));
        }
        
        // Create Synthea generator with default options
        Generator.GeneratorOptions options = new Generator.GeneratorOptions();
        options.population = 10; // Default to 10 patients
        
        this.syntheaGenerator = new Generator(options);
        this.generatedPatients = new ArrayList<>();
    }
    
    /**
     * Creates a new enhanced generator with the specified options.
     * 
     * @param options The generator options
     * @throws Exception if configuration loading fails
     */
    public EnhancedGenerator(Generator.GeneratorOptions options) throws Exception {
        // Initialize configuration if not already loaded
        if (Config.get("exporter.baseDirectory") == null) {
            Config.load(new File("src/main/resources/synthea.properties"));
        }
        
        this.syntheaGenerator = new Generator(options);
        this.generatedPatients = new ArrayList<>();
    }
    
    /**
     * Generates a specified number of patients.
     * 
     * @param count The number of patients to generate
     * @return A list of enhanced patient data
     */
    public List<EnhancedPatientData> generatePatients(int count) {
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        
        for (int i = 0; i < count; i++) {
            final int patientNumber = i;
            threadPool.submit(() -> {
                try {
                    Person person = syntheaGenerator.generatePerson(patientNumber);
                    if (person != null) {
                        EnhancedPatientData enhancedPatient = new EnhancedPatientData(person);
                        synchronized (generatedPatients) {
                            generatedPatients.add(enhancedPatient);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error generating patient: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
        
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Patient generation interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        return new ArrayList<>(generatedPatients);
    }
    
    /**
     * Gets the list of generated patients.
     * 
     * @return The list of enhanced patient data
     */
    public List<EnhancedPatientData> getGeneratedPatients() {
        return new ArrayList<>(generatedPatients);
    }
    
    /**
     * Filters the generated patients based on a predicate.
     * 
     * @param predicate The filter predicate
     * @return A filtered list of enhanced patient data
     */
    public List<EnhancedPatientData> filterPatients(Predicate<EnhancedPatientData> predicate) {
        List<EnhancedPatientData> filteredPatients = new ArrayList<>();
        for (EnhancedPatientData patient : generatedPatients) {
            if (predicate.test(patient)) {
                filteredPatients.add(patient);
            }
        }
        return filteredPatients;
    }
} 