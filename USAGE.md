# Healthcare Data Generator Usage Guide

This guide provides detailed instructions for running the Healthcare Data Generator to create enhanced synthetic patient data.

## Prerequisites

Before running the generator, ensure you have:

- Java 11 or later installed (check with `java -version`)
- Synthea library files in the `lib/synthea/build/libs/` directory

## Running the Generator

### Basic Usage

To generate the default number of patients (10):

```bash
./build.sh
```

### Generating a Custom Number of Patients

To specify a different number of patients, pass the number as an argument:

```bash
./build.sh 50  # Generates 50 patients
```

## Understanding the Output

The generator creates the following:

1. **Output Directory**: All generated files are saved in `output/enhanced/`
2. **Patient Files**: Each patient's data is stored in a separate JSON file named `patient_[UUID].json`
3. **Data Structure**: Each patient file contains:
   - UUID: Unique identifier for the patient
   - Attributes: Basic demographics (gender, age, race, ethnicity)
   - PatientReportedData: Simulated patient-reported information

### Example Output

```json
{
  "uuid": "e92fbe55-18dd-cd28-8816-d9b880813f0b",
  "attributes": {
    "gender": "M",
    "age": 0,
    "race": "asian",
    "ethnicity": "nonhispanic"
  },
  "patientReportedData": {
    "painLevel": 4,
    "stressLevel": 3,
    "dietQuality": "Good",
    "exerciseMinutesPerWeek": 163,
    "sleepQuality": "Good"
  }
}
```

## Data Fields Explained

### Attributes

- **gender**: "M" (male) or "F" (female)
- **age**: Patient's age in years
- **race**: Patient's race (e.g., "white", "black", "asian", "native", "other")
- **ethnicity**: Patient's ethnicity ("hispanic" or "nonhispanic")

### Patient-Reported Data

- **painLevel**: Self-reported pain level on a scale of 0-10
- **sleepQuality**: Self-reported sleep quality ("Poor", "Fair", "Good", "Excellent")
- **stressLevel**: Self-reported stress level on a scale of 0-10
- **exerciseMinutesPerWeek**: Self-reported minutes of exercise per week
- **dietQuality**: Self-reported diet quality ("Poor", "Fair", "Good", "Excellent")

## Customizing the Generator

To customize the generator behavior, modify the source files:

- `EnhancedPatientData.java`: Customize patient-reported data and data generation methods
- `EnhancedGenerator.java`: Modify patient generation logic
- `HealthcareDataGenerator.java`: Change export formats and main processing flow

After making changes, rebuild using the `./build.sh` script.

## Troubleshooting

If you encounter issues:

1. **Check Java version**: Ensure Java 11+ is installed and in your PATH
2. **Verify Synthea library**: Make sure the Synthea JAR file exists at the expected location
3. **Review logs**: Look for error messages in the console output
4. **Clean build**: Remove the `build` directory and run `./build.sh` again

## Additional Information

For more details on the Synthea project, visit [Synthea GitHub Repository](https://github.com/synthetichealth/synthea). 