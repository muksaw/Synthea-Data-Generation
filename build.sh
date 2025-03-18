#!/bin/bash

# Create directories
mkdir -p build
mkdir -p src/main/resources

# Create a default Synthea properties file if it doesn't exist
if [ ! -f "src/main/resources/synthea.properties" ]; then
    echo "Creating default synthea.properties file..."
    echo "# Default Synthea properties" > src/main/resources/synthea.properties
    echo "exporter.baseDirectory = ./output" >> src/main/resources/synthea.properties
    echo "exporter.use_uuid_filenames = false" >> src/main/resources/synthea.properties
fi

# Set the classpath
SYNTHEA_JAR="lib/synthea/build/libs/synthea-with-dependencies.jar"

# Check if Synthea JAR exists
if [ ! -f "$SYNTHEA_JAR" ]; then
    echo "ERROR: Synthea JAR not found at $SYNTHEA_JAR"
    echo "Please ensure the Synthea library is available"
    exit 1
fi

# Compile the code
echo "Compiling source code..."
javac -cp "$SYNTHEA_JAR" -d build src/main/java/com/healthcaremock/generator/EnhancedPatientData.java
javac -cp "$SYNTHEA_JAR:build" -d build src/main/java/com/healthcaremock/generator/EnhancedGenerator.java
javac -cp "$SYNTHEA_JAR:build" -d build src/main/java/com/healthcaremock/generator/HealthcareDataGenerator.java

# Check if compilation succeeded
if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

# Run the application
echo "Running Healthcare Data Generator..."
java -cp "build:$SYNTHEA_JAR" com.healthcaremock.generator.HealthcareDataGenerator "$@" 