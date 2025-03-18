package com.healthcaremock.generator.models;

import org.mitre.synthea.helpers.RandomNumberGenerator;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents wearable device data for a patient.
 */
public class WearableData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String deviceType;
    private String deviceId;
    private long recordedAt;
    private String patientId;
    private List<Measurement> measurements;
    
    /**
     * Constructor.
     */
    public WearableData() {
        this.measurements = new ArrayList<>();
    }
    
    /**
     * Constructor with patient ID.
     * @param patientId The patient ID.
     */
    public WearableData(String patientId) {
        this();
        this.patientId = patientId;
    }
    
    /**
     * Generate a random wearable data record.
     * @param random Random number generator.
     * @param time The time the data was recorded.
     * @param includeHeartRate Whether to include heart rate measurements.
     * @param includeSteps Whether to include step measurements.
     * @param includeSleep Whether to include sleep measurements.
     * @param includeBloodOxygen Whether to include blood oxygen measurements.
     * @param noiseFactor The amount of random noise to add to the data (0.0-1.0).
     * @return The generated wearable data.
     */
    public static WearableData generateRandom(
            RandomNumberGenerator random, 
            long time, 
            String patientId,
            boolean includeHeartRate,
            boolean includeSteps,
            boolean includeSleep,
            boolean includeBloodOxygen,
            double noiseFactor) {
        
        WearableData data = new WearableData(patientId);
        data.deviceType = "SmartWatch";
        data.deviceId = "WD-" + Math.abs(random.randInt());
        data.recordedAt = time;
        
        // Generate measurements based on configuration
        if (includeHeartRate) {
            // Base heart rate between 60-100 bpm with noise
            double baseHeartRate = 60 + random.rand(0, 40);
            double noise = baseHeartRate * noiseFactor * (random.rand() - 0.5);
            double heartRate = Math.round(baseHeartRate + noise);
            data.addMeasurement("heart_rate", "bpm", heartRate);
        }
        
        if (includeSteps) {
            // Generate a random step count for this time period (0-250 steps)
            double steps = random.rand(0, 250);
            data.addMeasurement("steps", "count", steps);
        }
        
        if (includeSleep) {
            // Sleep quality score (0-100)
            // Only add sleep data occasionally to simulate sleep tracking periods
            if (random.rand() < 0.3) {
                double sleepQuality = random.rand(0, 100);
                data.addMeasurement("sleep_quality", "score", sleepQuality);
            }
        }
        
        if (includeBloodOxygen) {
            // Blood oxygen (SpO2) typically 95-100%
            double baseSpO2 = 95 + random.rand(0, 5);
            double noise = noiseFactor * (random.rand() - 0.5);
            double spO2 = Math.min(100, Math.max(80, baseSpO2 + noise));
            data.addMeasurement("blood_oxygen", "percent", spO2);
        }
        
        return data;
    }
    
    /**
     * Add a measurement to this wearable data record.
     * @param type The measurement type.
     * @param unit The measurement unit.
     * @param value The measurement value.
     */
    public void addMeasurement(String type, String unit, double value) {
        measurements.add(new Measurement(type, unit, value));
    }
    
    // Getters and setters
    
    public String getDeviceType() {
        return deviceType;
    }
    
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public long getRecordedAt() {
        return recordedAt;
    }
    
    public void setRecordedAt(long recordedAt) {
        this.recordedAt = recordedAt;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public List<Measurement> getMeasurements() {
        return measurements;
    }
    
    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
    
    /**
     * Inner class representing a single measurement.
     */
    public static class Measurement implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String type;
        private String unit;
        private double value;
        
        /**
         * Constructor.
         * @param type The measurement type.
         * @param unit The measurement unit.
         * @param value The measurement value.
         */
        public Measurement(String type, String unit, double value) {
            this.type = type;
            this.unit = unit;
            this.value = value;
        }
        
        // Getters and setters
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public String getUnit() {
            return unit;
        }
        
        public void setUnit(String unit) {
            this.unit = unit;
        }
        
        public double getValue() {
            return value;
        }
        
        public void setValue(double value) {
            this.value = value;
        }
    }
} 