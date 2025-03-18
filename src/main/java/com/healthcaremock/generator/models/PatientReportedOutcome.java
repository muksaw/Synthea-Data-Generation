package com.healthcaremock.generator.models;

import org.mitre.synthea.helpers.RandomNumberGenerator;
import org.mitre.synthea.world.agents.Person;
import org.mitre.synthea.world.concepts.HealthRecord.Encounter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents patient-reported outcome measures (PROMs).
 * These are standardized questionnaires that patients complete to report 
 * on their health status, quality of life, and treatment satisfaction.
 */
public class PatientReportedOutcome implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String patientId;
    private String encounterId;
    private long timestamp;
    private String instrumentType;
    private int instrumentVersion;
    private String condition;
    private Map<String, Object> responses;
    private double totalScore;
    private String interpretation;
    
    /**
     * Constructor.
     */
    public PatientReportedOutcome() {
        this.id = UUID.randomUUID().toString();
        this.responses = new HashMap<>();
    }
    
    /**
     * Constructor with patient ID and encounter ID.
     * @param patientId The patient ID.
     * @param encounterId The encounter ID.
     */
    public PatientReportedOutcome(String patientId, String encounterId) {
        this();
        this.patientId = patientId;
        this.encounterId = encounterId;
    }
    
    /**
     * Generates a random pain assessment outcome.
     * @param random The random number generator.
     * @param person The person.
     * @param encounter The encounter.
     * @return The generated patient-reported outcome.
     */
    public static PatientReportedOutcome generatePainAssessment(
            RandomNumberGenerator random, Person person, Encounter encounter) {
        
        PatientReportedOutcome pro = new PatientReportedOutcome(
                person.attributes.get(Person.ID).toString(),
                encounter.uuid);
        
        pro.timestamp = encounter.start;
        pro.instrumentType = "Visual Analog Scale for Pain";
        pro.instrumentVersion = 1;
        pro.condition = "Pain";
        
        // Generate a random pain score (0-10)
        int painScore = (int) Math.round(random.rand(0, 10));
        pro.responses.put("pain_level", painScore);
        pro.totalScore = painScore;
        
        // Set interpretation based on pain score
        if (painScore <= 3) {
            pro.interpretation = "Mild pain";
        } else if (painScore <= 6) {
            pro.interpretation = "Moderate pain";
        } else {
            pro.interpretation = "Severe pain";
        }
        
        return pro;
    }
    
    /**
     * Generates a random PHQ-9 depression assessment.
     * @param random The random number generator.
     * @param person The person.
     * @param encounter The encounter.
     * @return The generated patient-reported outcome.
     */
    public static PatientReportedOutcome generatePHQ9(
            RandomNumberGenerator random, Person person, Encounter encounter) {
        
        PatientReportedOutcome pro = new PatientReportedOutcome(
                person.attributes.get(Person.ID).toString(),
                encounter.uuid);
        
        pro.timestamp = encounter.start;
        pro.instrumentType = "PHQ-9";
        pro.instrumentVersion = 1;
        pro.condition = "Depression";
        
        // PHQ-9 has 9 questions, each scored 0-3
        String[] questions = {
            "Little interest or pleasure in doing things",
            "Feeling down, depressed, or hopeless",
            "Trouble falling or staying asleep, or sleeping too much",
            "Feeling tired or having little energy",
            "Poor appetite or overeating",
            "Feeling bad about yourself — or that you are a failure or have let yourself or your family down",
            "Trouble concentrating on things, such as reading the newspaper or watching television",
            "Moving or speaking so slowly that other people could have noticed. Or the opposite — being so fidgety or restless that you have been moving around a lot more than usual",
            "Thoughts that you would be better off dead, or of hurting yourself"
        };
        
        int totalScore = 0;
        
        // Generate random responses for each question
        for (int i = 0; i < questions.length; i++) {
            String questionKey = "q" + (i + 1);
            int score = (int) Math.round(random.rand(0, 3));
            pro.responses.put(questionKey, score);
            pro.responses.put(questionKey + "_text", questions[i]);
            totalScore += score;
        }
        
        pro.totalScore = totalScore;
        
        // Set interpretation based on total score
        if (totalScore <= 4) {
            pro.interpretation = "Minimal or no depression";
        } else if (totalScore <= 9) {
            pro.interpretation = "Mild depression";
        } else if (totalScore <= 14) {
            pro.interpretation = "Moderate depression";
        } else if (totalScore <= 19) {
            pro.interpretation = "Moderately severe depression";
        } else {
            pro.interpretation = "Severe depression";
        }
        
        return pro;
    }
    
    /**
     * Generates a random EQ-5D-5L health status assessment.
     * @param random The random number generator.
     * @param person The person.
     * @param encounter The encounter.
     * @return The generated patient-reported outcome.
     */
    public static PatientReportedOutcome generateEQ5D5L(
            RandomNumberGenerator random, Person person, Encounter encounter) {
        
        PatientReportedOutcome pro = new PatientReportedOutcome(
                person.attributes.get(Person.ID).toString(),
                encounter.uuid);
        
        pro.timestamp = encounter.start;
        pro.instrumentType = "EQ-5D-5L";
        pro.instrumentVersion = 1;
        pro.condition = "Quality of Life";
        
        // EQ-5D-5L has 5 dimensions, each scored 1-5
        String[] dimensions = {
            "Mobility",
            "Self-Care",
            "Usual Activities",
            "Pain/Discomfort",
            "Anxiety/Depression"
        };
        
        int totalScore = 0;
        
        // Generate random responses for each dimension
        for (int i = 0; i < dimensions.length; i++) {
            String dimensionKey = dimensions[i].toLowerCase().replace("/", "_");
            int score = (int) Math.round(random.rand(1, 5));
            pro.responses.put(dimensionKey, score);
            totalScore += score;
        }
        
        // Add VAS health score (0-100)
        int vasScore = (int) Math.round(random.rand(0, 100));
        pro.responses.put("vas_health_score", vasScore);
        
        // Calculate index value (simplified version)
        // In reality, the EQ-5D-5L index is calculated using country-specific value sets
        double indexValue = 1.0 - (totalScore - 5) / 20.0;
        pro.responses.put("index_value", indexValue);
        
        pro.totalScore = indexValue;
        
        // Set interpretation
        if (indexValue >= 0.8) {
            pro.interpretation = "Good health state";
        } else if (indexValue >= 0.5) {
            pro.interpretation = "Moderate health state";
        } else {
            pro.interpretation = "Poor health state";
        }
        
        return pro;
    }
    
    // Getters and setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public String getEncounterId() {
        return encounterId;
    }
    
    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getInstrumentType() {
        return instrumentType;
    }
    
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }
    
    public int getInstrumentVersion() {
        return instrumentVersion;
    }
    
    public void setInstrumentVersion(int instrumentVersion) {
        this.instrumentVersion = instrumentVersion;
    }
    
    public String getCondition() {
        return condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    public Map<String, Object> getResponses() {
        return responses;
    }
    
    public void setResponses(Map<String, Object> responses) {
        this.responses = responses;
    }
    
    public void addResponse(String key, Object value) {
        this.responses.put(key, value);
    }
    
    public double getTotalScore() {
        return totalScore;
    }
    
    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
    
    public String getInterpretation() {
        return interpretation;
    }
    
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
} 