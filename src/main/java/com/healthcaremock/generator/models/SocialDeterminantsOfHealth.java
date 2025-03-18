package com.healthcaremock.generator.models;

import org.mitre.synthea.helpers.RandomNumberGenerator;
import org.mitre.synthea.world.agents.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents social determinants of health data for a patient.
 * Extends beyond what's available in Synthea's Person model.
 */
public class SocialDeterminantsOfHealth implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String patientId;
    private Map<String, Object> factors;
    
    /**
     * Constructor.
     */
    public SocialDeterminantsOfHealth() {
        this.factors = new HashMap<>();
    }
    
    /**
     * Constructor with patient ID.
     * @param patientId The patient ID.
     */
    public SocialDeterminantsOfHealth(String patientId) {
        this();
        this.patientId = patientId;
    }
    
    /**
     * Generate random social determinants of health data.
     * @param random The random number generator.
     * @param person The Synthea Person.
     * @return The generated social determinants of health data.
     */
    public static SocialDeterminantsOfHealth generateRandom(
            RandomNumberGenerator random, Person person) {
        
        SocialDeterminantsOfHealth sdoh = new SocialDeterminantsOfHealth(person.attributes.get(Person.ID).toString());
        
        // Add standard SDOH factors that might already be in the Person model
        // but we consolidate them here for easy access
        if (person.attributes.containsKey(Person.INCOME)) {
            sdoh.setFactor("income", person.attributes.get(Person.INCOME));
        }
        
        if (person.attributes.containsKey(Person.EDUCATION)) {
            sdoh.setFactor("education", person.attributes.get(Person.EDUCATION));
        }
        
        if (person.attributes.containsKey(Person.HOMELESS)) {
            sdoh.setFactor("homelessness", person.attributes.get(Person.HOMELESS));
        }
        
        if (person.attributes.containsKey(Person.OCCUPATION_LEVEL)) {
            sdoh.setFactor("occupation_level", person.attributes.get(Person.OCCUPATION_LEVEL));
        }
        
        // Additional SDOH factors not in the standard Person model
        
        // Food security (1-4 scale, where 1 is high food security, 4 is very low)
        int foodSecurity = (int) Math.ceil(random.rand(1, 4));
        sdoh.setFactor("food_security_scale", foodSecurity);
        sdoh.setFactor("food_insecurity", foodSecurity >= 3);
        
        // Transportation access
        boolean hasTransportationAccess = random.rand() < 0.85;
        sdoh.setFactor("transportation_access", hasTransportationAccess);
        
        // Community safety perception (0-10 scale, where 10 is very safe)
        double communitySafetyPerception = random.rand(0, 10);
        sdoh.setFactor("community_safety_perception", communitySafetyPerception);
        
        // Digital literacy (0-10 scale, where 10 is very digitally literate)
        double digitalLiteracy = random.rand(0, 10);
        sdoh.setFactor("digital_literacy", digitalLiteracy);
        
        // Social isolation risk (0-10 scale, where 10 is most isolated)
        double socialIsolationRisk = random.rand(0, 10);
        sdoh.setFactor("social_isolation_risk", socialIsolationRisk);
        
        // Housing stability (0-10 scale, where 10 is most stable)
        double housingStability = random.rand(0, 10);
        sdoh.setFactor("housing_stability", housingStability);
        
        // Health literacy (0-10 scale, where 10 is high health literacy)
        double healthLiteracy = random.rand(0, 10);
        sdoh.setFactor("health_literacy", healthLiteracy);
        
        // Discrimination experience (0-10 scale, where 10 is frequent discrimination)
        double discriminationExperience = random.rand(0, 10);
        sdoh.setFactor("discrimination_experience", discriminationExperience);
        
        // Access to healthy food (0-10 scale, where 10 is excellent access)
        double accessToHealthyFood = random.rand(0, 10);
        sdoh.setFactor("access_to_healthy_food", accessToHealthyFood);
        
        // Social support (0-10 scale, where 10 is excellent social support)
        double socialSupport = random.rand(0, 10);
        sdoh.setFactor("social_support", socialSupport);
        
        return sdoh;
    }
    
    /**
     * Set a social determinant factor.
     * @param key The factor key.
     * @param value The factor value.
     */
    public void setFactor(String key, Object value) {
        factors.put(key, value);
    }
    
    /**
     * Get a social determinant factor.
     * @param key The factor key.
     * @return The factor value.
     */
    public Object getFactor(String key) {
        return factors.get(key);
    }
    
    /**
     * Get all social determinant factors.
     * @return The factors map.
     */
    public Map<String, Object> getFactors() {
        return factors;
    }
    
    /**
     * Set all social determinant factors.
     * @param factors The factors map.
     */
    public void setFactors(Map<String, Object> factors) {
        this.factors = factors;
    }
    
    /**
     * Get the patient ID.
     * @return The patient ID.
     */
    public String getPatientId() {
        return patientId;
    }
    
    /**
     * Set the patient ID.
     * @param patientId The patient ID.
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
} 