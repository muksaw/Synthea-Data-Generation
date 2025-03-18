# Synthea-Data-Generation

Synthea-Data-Generation is a project that leverages Synthea to generate realistic synthetic healthcare data with enhanced patient-reported outcomes. This data can be used to develop and test machine learning models for disease detection, evaluate screening algorithms for false positives, and improve clinical decision support systems. It serves as a robust tool for researchers, data engineers, and data scientists who require high-quality healthcare data for simulation and testing purposes.

## Key Features

- **Synthetic Healthcare Data Generation:** Utilize Synthea to generate comprehensive patient records, clinical events, and healthcare-related datasets.
- **Patient-Reported Outcomes:** Enhance Synthea data with simulated patient-reported information including:
  - Pain levels
  - Sleep quality
  - Stress levels
  - Exercise frequency
  - Diet quality
- **Simple JSON Output:** Generate patient data in JSON format for easy integration with data analysis tools.

## Getting Started

### Prerequisites

- [Java](https://www.java.com/) (Java 11 or later)
- Synthea library (included in the `lib` directory)

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/Synthea-Data-Generation.git
   cd Synthea-Data-Generation
   ```

2. **Build and Run the Project:**
   ```bash
   ./build.sh
   ```

### Usage

Run the data generator with default settings (generates 10 patients):

```bash
./build.sh
```

Configure the generator with command-line arguments to specify population size:

```bash
./build.sh 20
```

This would generate data for 20 patients.

## Output Data

Generated data is stored in the `output/enhanced/` directory in JSON format. Each patient record includes:

- Basic demographic information (UUID, gender, age, race, ethnicity)
- Patient-reported data (pain level, sleep quality, stress level, exercise minutes, diet quality)

Example output:
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

## Future Enhancements

In future versions, we plan to add:

- **Extended Data Types:**
  - Wearable device data (heart rate, steps, sleep tracking)
  - Imaging reports
  - Social determinants of health
  - Medication adherence
- **Additional Output Formats:**
  - CSV exports
  - FHIR-compliant resources

## Skills and Technologies

- **Java:** Core programming language for implementing the generator and data models
- **Synthea API:** Leveraging the Synthea library to generate synthetic healthcare data
- **Multi-threading:** Parallel patient generation using Java's ExecutorService
- **JSON Serialization:** Custom JSON generation for data export
- **Bash Scripting:** Build and execution scripts for compilation and running
- **Object-Oriented Design:** Class modeling for enhanced patient data
- **API Integration:** Working with Synthea's Generator and Person classes
- **Data Modeling:** Structure design for patient-reported outcomes
- **Random Data Generation:** Creating realistic patient-reported values
- **Command-line Interface:** Parameter handling for customizing generation
- **Error Handling:** Robust exception management throughout the application
- **Documentation:** Comprehensive documentation in README and USAGE guides

## Developers

This project was created by:
- **Mukul Sauhta** ([muksaw on GitHub](https://github.com/muksaw))
- **James Kocak** ([Jokocak on GitHub](https://github.com/Jokocak))

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## Acknowledgments

- [Synthea](https://github.com/synthetichealth/synthea) for providing the core synthetic patient generation engine
- The healthcare data community for guidance on realistic data models
