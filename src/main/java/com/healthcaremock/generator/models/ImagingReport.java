package com.healthcaremock.generator.models;

import org.mitre.synthea.helpers.RandomNumberGenerator;
import org.mitre.synthea.world.agents.Person;
import org.mitre.synthea.world.concepts.HealthRecord.Encounter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an imaging report for a patient.
 */
public class ImagingReport implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String patientId;
    private String encounterId;
    private long timestamp;
    private String modality;
    private String bodyPart;
    private String procedureCode;
    private String procedureDisplay;
    private String reportText;
    private String impression;
    private String conclusion;
    private List<Finding> findings;
    
    /**
     * Constructor.
     */
    public ImagingReport() {
        this.id = UUID.randomUUID().toString();
        this.findings = new ArrayList<>();
    }
    
    /**
     * Constructor with patient ID and encounter ID.
     * @param patientId The patient ID.
     * @param encounterId The encounter ID.
     */
    public ImagingReport(String patientId, String encounterId) {
        this();
        this.patientId = patientId;
        this.encounterId = encounterId;
    }
    
    /**
     * Generates a random X-ray report.
     * @param random The random number generator.
     * @param person The person.
     * @param encounter The encounter.
     * @return The generated report.
     */
    public static ImagingReport generateRandomXray(
            RandomNumberGenerator random, Person person, Encounter encounter) {
        
        ImagingReport report = new ImagingReport(
                person.attributes.get(Person.ID).toString(),
                encounter.uuid);
        
        report.timestamp = encounter.start;
        report.modality = "X-ray";
        
        // Pick a random body part for the X-ray
        String[] bodyParts = {"Chest", "Abdomen", "Skull", "Thoracic Spine", "Lumbar Spine", 
                "Cervical Spine", "Pelvis", "Hip", "Knee", "Ankle", "Foot", "Shoulder", 
                "Elbow", "Wrist", "Hand"};
        report.bodyPart = bodyParts[(int) (random.rand() * bodyParts.length)];
        
        // Set procedure code and display based on body part
        if (report.bodyPart.equals("Chest")) {
            report.procedureCode = "71045";
            report.procedureDisplay = "X-ray Chest, single view";
        } else if (report.bodyPart.equals("Abdomen")) {
            report.procedureCode = "74019";
            report.procedureDisplay = "X-ray Abdomen, multiple views";
        } else {
            // Generic code for other body parts
            report.procedureCode = "70000";
            report.procedureDisplay = "X-ray " + report.bodyPart;
        }
        
        // Generate a full report text with realistic formatting
        StringBuilder reportTextBuilder = new StringBuilder();
        reportTextBuilder.append("EXAMINATION: ").append(report.procedureDisplay).append("\n\n");
        reportTextBuilder.append("CLINICAL INDICATION: ");
        
        String[] indications = {"Pain", "Trauma", "Follow-up", "Rule out fracture", 
                "Rule out pneumonia", "Shortness of breath", "Cough", "Post-operative evaluation"};
        reportTextBuilder.append(indications[(int) (random.rand() * indications.length)])
                .append(" in ").append(report.bodyPart.toLowerCase()).append("\n\n");
        
        reportTextBuilder.append("TECHNIQUE: Standard ").append(report.modality)
                .append(" views of the ").append(report.bodyPart.toLowerCase()).append(".\n\n");
        
        reportTextBuilder.append("FINDINGS:\n");
        
        // Generate 2-4 findings
        int numFindings = (int) (random.rand(2, 4));
        List<String> findingTexts = new ArrayList<>();
        
        for (int i = 0; i < numFindings; i++) {
            String findingText = generateRandomFinding(random, report.bodyPart);
            findingTexts.add(findingText);
            reportTextBuilder.append("- ").append(findingText).append("\n");
            
            // Add to the findings list
            Finding finding = new Finding();
            finding.setText(findingText);
            finding.setSignificance(random.rand() < 0.3 ? "Critical" : 
                                   (random.rand() < 0.6 ? "Significant" : "Normal"));
            report.findings.add(finding);
        }
        
        reportTextBuilder.append("\n");
        
        // Generate impression
        reportTextBuilder.append("IMPRESSION:\n");
        String impression = generateImpression(random, findingTexts);
        reportTextBuilder.append(impression).append("\n\n");
        report.impression = impression;
        
        // Generate conclusion
        String[] conclusions = {
            "No acute findings.",
            "Findings as described above.",
            "Findings consistent with patient's history.",
            "Follow-up imaging recommended in 6 weeks.",
            "Correlation with clinical findings is recommended.",
            "Consider further evaluation with MRI if clinically indicated."
        };
        String conclusion = conclusions[(int) (random.rand() * conclusions.length)];
        reportTextBuilder.append("CONCLUSION: ").append(conclusion);
        report.conclusion = conclusion;
        
        report.reportText = reportTextBuilder.toString();
        
        return report;
    }
    
    /**
     * Generates a random CT scan report.
     * @param random The random number generator.
     * @param person The person.
     * @param encounter The encounter.
     * @return The generated report.
     */
    public static ImagingReport generateRandomCT(
            RandomNumberGenerator random, Person person, Encounter encounter) {
        
        ImagingReport report = new ImagingReport(
                person.attributes.get(Person.ID).toString(),
                encounter.uuid);
        
        report.timestamp = encounter.start;
        report.modality = "CT";
        
        // Pick a random body part for the CT
        String[] bodyParts = {"Head", "Neck", "Chest", "Abdomen", "Pelvis", "Spine", 
                "Abdomen and Pelvis", "Chest/Abdomen/Pelvis"};
        report.bodyPart = bodyParts[(int) (random.rand() * bodyParts.length)];
        
        // Set procedure code and display based on body part
        if (report.bodyPart.equals("Head")) {
            report.procedureCode = "70450";
            report.procedureDisplay = "CT Head without contrast";
        } else if (report.bodyPart.equals("Chest")) {
            report.procedureCode = "71250";
            report.procedureDisplay = "CT Chest without contrast";
        } else if (report.bodyPart.equals("Abdomen")) {
            report.procedureCode = "74176";
            report.procedureDisplay = "CT Abdomen without contrast";
        } else {
            // Generic code for other body parts
            report.procedureCode = "70000";
            report.procedureDisplay = "CT " + report.bodyPart;
        }
        
        // Generate a full report text with realistic formatting
        StringBuilder reportTextBuilder = new StringBuilder();
        reportTextBuilder.append("EXAMINATION: ").append(report.procedureDisplay).append("\n\n");
        reportTextBuilder.append("CLINICAL INDICATION: ");
        
        String[] indications = {"Abdominal pain", "Headache", "Trauma", "Follow-up", 
                "Rule out malignancy", "Shortness of breath", "Unexplained weight loss", 
                "Surveillance"};
        reportTextBuilder.append(indications[(int) (random.rand() * indications.length)])
                .append("\n\n");
        
        reportTextBuilder.append("TECHNIQUE: Helical CT of the ").append(report.bodyPart.toLowerCase())
                .append(" was performed without intravenous contrast.\n\n");
        
        reportTextBuilder.append("FINDINGS:\n");
        
        // Generate 3-5 findings
        int numFindings = (int) (random.rand(3, 5));
        List<String> findingTexts = new ArrayList<>();
        
        for (int i = 0; i < numFindings; i++) {
            String findingText = generateRandomCTFinding(random, report.bodyPart);
            findingTexts.add(findingText);
            reportTextBuilder.append("- ").append(findingText).append("\n");
            
            // Add to the findings list
            Finding finding = new Finding();
            finding.setText(findingText);
            finding.setSignificance(random.rand() < 0.2 ? "Critical" : 
                                   (random.rand() < 0.5 ? "Significant" : "Normal"));
            report.findings.add(finding);
        }
        
        reportTextBuilder.append("\n");
        
        // Generate impression
        reportTextBuilder.append("IMPRESSION:\n");
        String impression = generateImpression(random, findingTexts);
        reportTextBuilder.append(impression).append("\n\n");
        report.impression = impression;
        
        // Generate conclusion
        String[] conclusions = {
            "No acute findings identified.",
            "Findings as described above.",
            "Findings require clinical correlation.",
            "Recommend follow-up imaging in 3 months.",
            "Consider MRI for further evaluation.",
            "Findings consistent with patient's clinical presentation."
        };
        String conclusion = conclusions[(int) (random.rand() * conclusions.length)];
        reportTextBuilder.append("CONCLUSION: ").append(conclusion);
        report.conclusion = conclusion;
        
        report.reportText = reportTextBuilder.toString();
        
        return report;
    }
    
    private static String generateRandomFinding(RandomNumberGenerator random, String bodyPart) {
        if (bodyPart.equals("Chest")) {
            String[] findings = {
                "Lungs are clear without evidence of infiltrate, effusion, or pneumothorax.",
                "Heart size is within normal limits.",
                "No pleural effusion identified.",
                "Mild hyperinflation consistent with COPD.",
                "Small patchy opacity in the right lower lobe, may represent early pneumonia.",
                "Cardiac silhouette appears mildly enlarged.",
                "No acute cardiopulmonary process identified."
            };
            return findings[(int) (random.rand() * findings.length)];
        } else if (bodyPart.equals("Abdomen")) {
            String[] findings = {
                "Bowel gas pattern is normal without evidence of obstruction or ileus.",
                "No evidence of pneumoperitoneum.",
                "Nonspecific gas pattern.",
                "Liver, spleen, and kidney shadows appear normal.",
                "No abnormal calcifications identified.",
                "Normal bowel gas pattern."
            };
            return findings[(int) (random.rand() * findings.length)];
        } else {
            // Generic findings for other body parts
            String[] findings = {
                "Normal alignment without evidence of fracture or dislocation.",
                "No acute osseous abnormality.",
                "Mild degenerative changes.",
                "Soft tissues are unremarkable.",
                "Normal mineralization.",
                "No significant arthritic changes.",
                "Incidental finding of minor degenerative joint disease."
            };
            return findings[(int) (random.rand() * findings.length)];
        }
    }
    
    private static String generateRandomCTFinding(RandomNumberGenerator random, String bodyPart) {
        if (bodyPart.equals("Head")) {
            String[] findings = {
                "No acute intracranial hemorrhage, mass effect, or midline shift.",
                "Ventricles are normal in size and configuration.",
                "Gray-white matter differentiation is preserved.",
                "No evidence of infarct or mass.",
                "Mild paranasal sinus disease noted.",
                "Mild generalized cerebral volume loss consistent with patient's age.",
                "No extra-axial fluid collections."
            };
            return findings[(int) (random.rand() * findings.length)];
        } else if (bodyPart.equals("Chest")) {
            String[] findings = {
                "Lungs are clear without evidence of focal consolidation, pneumothorax, or pleural effusion.",
                "No evidence of pulmonary embolism.",
                "Mediastinal and hilar contours are within normal limits.",
                "Small noncalcified pulmonary nodule measuring 4mm in the right upper lobe, likely benign.",
                "Mild emphysematous changes noted in the upper lobes.",
                "No axillary, mediastinal, or hilar lymphadenopathy.",
                "Cardiac size is within normal limits."
            };
            return findings[(int) (random.rand() * findings.length)];
        } else if (bodyPart.equals("Abdomen") || bodyPart.equals("Abdomen and Pelvis")) {
            String[] findings = {
                "Liver is normal in size and attenuation without focal lesions.",
                "Gallbladder is normal without evidence of stones or wall thickening.",
                "Spleen is normal in size and appearance.",
                "Pancreas is unremarkable.",
                "Kidneys are normal in size and enhancement without hydronephrosis or calculi.",
                "Small hiatal hernia noted.",
                "No abdominal lymphadenopathy or free fluid.",
                "Small nonobstructing 3mm calculus in the lower pole of the left kidney."
            };
            return findings[(int) (random.rand() * findings.length)];
        } else {
            // Generic findings for other body parts
            String[] findings = {
                "No acute osseous abnormality.",
                "Mild degenerative changes noted.",
                "No evidence of fracture or dislocation.",
                "Soft tissues are unremarkable.",
                "No significant lymphadenopathy.",
                "Normal alignment and mineralization.",
                "Incidental finding of benign appearing cyst measuring 1.2cm."
            };
            return findings[(int) (random.rand() * findings.length)];
        }
    }
    
    private static String generateImpression(RandomNumberGenerator random, List<String> findings) {
        if (findings.isEmpty()) {
            return "No significant findings.";
        }
        
        // Randomly select 1-2 findings to highlight in the impression
        int numToHighlight = Math.min(findings.size(), (int) (random.rand(1, 2)));
        List<String> selectedFindings = new ArrayList<>();
        for (int i = 0; i < numToHighlight; i++) {
            int index = (int) (random.rand() * findings.size());
            selectedFindings.add(findings.get(index));
            findings.remove(index);
        }
        
        if (selectedFindings.isEmpty()) {
            return "No significant findings.";
        } else if (selectedFindings.size() == 1) {
            return "1. " + selectedFindings.get(0);
        } else {
            StringBuilder impression = new StringBuilder();
            for (int i = 0; i < selectedFindings.size(); i++) {
                impression.append(i + 1).append(". ").append(selectedFindings.get(i));
                if (i < selectedFindings.size() - 1) {
                    impression.append("\n");
                }
            }
            return impression.toString();
        }
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
    
    public String getModality() {
        return modality;
    }
    
    public void setModality(String modality) {
        this.modality = modality;
    }
    
    public String getBodyPart() {
        return bodyPart;
    }
    
    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }
    
    public String getProcedureCode() {
        return procedureCode;
    }
    
    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }
    
    public String getProcedureDisplay() {
        return procedureDisplay;
    }
    
    public void setProcedureDisplay(String procedureDisplay) {
        this.procedureDisplay = procedureDisplay;
    }
    
    public String getReportText() {
        return reportText;
    }
    
    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
    
    public String getImpression() {
        return impression;
    }
    
    public void setImpression(String impression) {
        this.impression = impression;
    }
    
    public String getConclusion() {
        return conclusion;
    }
    
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    
    public List<Finding> getFindings() {
        return findings;
    }
    
    public void setFindings(List<Finding> findings) {
        this.findings = findings;
    }
    
    /**
     * Inner class representing a finding in an imaging report.
     */
    public static class Finding implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String text;
        private String significance; // "Critical", "Significant", "Normal"
        
        public String getText() {
            return text;
        }
        
        public void setText(String text) {
            this.text = text;
        }
        
        public String getSignificance() {
            return significance;
        }
        
        public void setSignificance(String significance) {
            this.significance = significance;
        }
    }
} 