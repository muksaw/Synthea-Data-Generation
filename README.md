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

### Programming Languages & Core Technologies
- **Java:** Primary programming language for implementing the generator and data models
- **Java Standard Library:** Utilizing core Java functionality for collections, I/O operations, and data structures
- **Gradle:** Build automation tool for dependency management and project building
- **Bash Scripting:** Used for build and execution scripts for compilation and running

### Healthcare Data Technologies
- **Synthea API:** Leveraging the Synthea library to generate synthetic healthcare data
- **FHIR Standards Knowledge:** Understanding of healthcare interoperability standards
- **Healthcare Data Modeling:** Expertise in representing medical information in structured formats

### Software Design & Architecture
- **Object-Oriented Design:** Application of OOD principles for modular and extensible code
- **Design Patterns:** Implementation of software design patterns for maintainable architecture
- **API Integration:** Integration with external libraries through well-defined interfaces
- **Class Modeling:** Structured approach to modeling complex healthcare data entities

### Performance & Efficiency
- **Multi-threading:** Parallel patient generation using Java's ExecutorService
- **Concurrency Management:** Safe handling of shared resources in a multi-threaded environment
- **Performance Optimization:** Efficient data processing for large healthcare datasets

### Data Processing & Formats
- **JSON Serialization:** Custom JSON generation for data export
- **Data Transformation:** Converting complex nested objects to flat output formats
- **Random Data Generation:** Creating statistically realistic patient-reported values

### Application Features
- **Command-line Interface:** Parameter handling for customizing generation
- **Configuration Management:** Flexible application settings through property files
- **Error Handling:** Robust exception management throughout the application
- **Validation:** Ensuring generated data meets expected constraints and formats

### Development Practices
- **Version Control (Git):** Management of source code with proper branching strategy
- **Documentation:** Comprehensive documentation in README and USAGE guides
- **Testing:** Verification of generator functionality and output correctness
- **Collaborative Development:** Coordinated development between team members

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
