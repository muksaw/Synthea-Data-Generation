package com.healthcaremock.generator.models;

import org.mitre.synthea.world.agents.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A container class that holds all the enhanced patient data beyond what Synthea generates.
 */
public class EnhancedPatientData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String patientId;
    private SocialDeterminantsOfHealth socialDeterminants;
    private List<WearableData> wearableData;
    private List<ImagingReport> imagingReports;
    private List<PatientReportedOutcome> patientReportedOutcomes;
    private Map<String, Object> additionalData;
    
    /**
     * Constructor.
     */
    public EnhancedPatientData() {
        this.wearableData = new ArrayList<>();
        this.imagingReports = new ArrayList<>();
        this.patientReportedOutcomes = new ArrayList<>();
        this.additionalData = new HashMap<>();
    }
    
    /**
     * Constructor with patient ID.
     * @param patientId The patient ID.
     */
    public EnhancedPatientData(String patientId) {
        this();
        this.patientId = patientId;
    }
    
    /**
     * Constructor with person.
     * @param person The Synthea person.
     */
    public EnhancedPatientData(Person person) {
        this(person.attributes.get(Person.ID).toString());
    }
    
    /**
     * Add a wearable data record.
     * @param data The wearable data.
     */
    public void addWearableData(WearableData data) {
        this.wearableData.add(data);
    }
    
    /**
     * Add an imaging report.
     * @param report The imaging report.
     */
    public void addImagingReport(ImagingReport report) {
        this.imagingReports.add(report);
    }
    
    /**
     * Add a patient-reported outcome.
     * @param outcome The patient-reported outcome.
     */
    public void addPatientReportedOutcome(PatientReportedOutcome outcome) {
        this.patientReportedOutcomes.add(outcome);
    }
    
    /**
     * Add additional data.
     * @param key The data key.
     * @param value The data value.
     */
    public void addAdditionalData(String key, Object value) {
        this.additionalData.put(key, value);
    }
    
    // Getters and setters
    
    public String getPatientId() {
        return patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public SocialDeterminantsOfHealth getSocialDeterminants() {
        return socialDeterminants;
    }
    
    public void setSocialDeterminants(SocialDeterminantsOfHealth socialDeterminants) {
        this.socialDeterminants = socialDeterminants;
    }
    
    public List<WearableData> getWearableData() {
        return wearableData;
    }
    
    public void setWearableData(List<WearableData> wearableData) {
        this.wearableData = wearableData;
    }
    
    public List<ImagingReport> getImagingReports() {
        return imagingReports;
    }
    
    public void setImagingReports(List<ImagingReport> imagingReports) {
        this.imagingReports = imagingReports;
    }
    
    public List<PatientReportedOutcome> getPatientReportedOutcomes() {
        return patientReportedOutcomes;
    }
    
    public void setPatientReportedOutcomes(List<PatientReportedOutcome> patientReportedOutcomes) {
        this.patientReportedOutcomes = patientReportedOutcomes;
    }
    
    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }
    
    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }
} 