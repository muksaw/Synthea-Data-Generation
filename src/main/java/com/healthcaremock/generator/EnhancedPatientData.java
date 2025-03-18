package com.healthcaremock.generator;

import org.mitre.synthea.world.agents.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Enhanced patient data that extends Synthea's Person model with additional information.
 */
public class EnhancedPatientData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // The underlying Synthea person
    private final Person person;
    
    // Additional patient-reported data
    private final Map<String, Object> patientReportedData;
    
    /**
     * Creates a new enhanced patient data instance.
     * 
     * @param person The Synthea person to enhance
     */
    public EnhancedPatientData(Person person) {
        this.person = person;
        this.patientReportedData = new HashMap<>();
        
        // Initialize with default values
        initializePatientReportedData();
    }
    
    /**
     * Initializes patient-reported data with default values.
     */
    private void initializePatientReportedData() {
        // Add examples of patient-reported data
        patientReportedData.put("painLevel", generateRandomPainLevel());
        patientReportedData.put("sleepQuality", generateRandomSleepQuality());
        patientReportedData.put("stressLevel", generateRandomStressLevel());
        patientReportedData.put("exerciseMinutesPerWeek", generateRandomExerciseMinutes());
        patientReportedData.put("dietQuality", generateRandomDietQuality());
    }
    
    /**
     * Gets the underlying Synthea person.
     * 
     * @return The Synthea person
     */
    public Person getPerson() {
        return person;
    }
    
    /**
     * Gets all patient-reported data.
     * 
     * @return A map of patient-reported data
     */
    public Map<String, Object> getPatientReportedData() {
        return new HashMap<>(patientReportedData);
    }
    
    /**
     * Gets a specific patient-reported data point.
     * 
     * @param key The data point key
     * @return The data point value, or null if not found
     */
    public Object getPatientReportedData(String key) {
        return patientReportedData.get(key);
    }
    
    /**
     * Sets a patient-reported data point.
     * 
     * @param key The data point key
     * @param value The data point value
     */
    public void setPatientReportedData(String key, Object value) {
        patientReportedData.put(key, value);
    }
    
    // Utility methods to generate random patient-reported data
    
    private int generateRandomPainLevel() {
        return (int) (Math.random() * 10);
    }
    
    private String generateRandomSleepQuality() {
        String[] options = {"Poor", "Fair", "Good", "Excellent"};
        return options[(int) (Math.random() * options.length)];
    }
    
    private int generateRandomStressLevel() {
        return (int) (Math.random() * 10);
    }
    
    private int generateRandomExerciseMinutes() {
        return (int) (Math.random() * 300);
    }
    
    private String generateRandomDietQuality() {
        String[] options = {"Poor", "Fair", "Good", "Excellent"};
        return options[(int) (Math.random() * options.length)];
    }
} 