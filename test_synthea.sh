#!/bin/bash

# Set the classpath to include Synthea JAR files
SYNTHEA_JAR="lib/synthea/build/libs/synthea-with-dependencies.jar"

# Check if Synthea JAR exists
if [ ! -f "$SYNTHEA_JAR" ]; then
    echo "ERROR: Synthea JAR not found at $SYNTHEA_JAR"
    echo "Please ensure the Synthea library is available"
    exit 1
fi

# Create build directory if it doesn't exist
mkdir -p build
mkdir -p src/main/resources

# Create a default Synthea properties file if it doesn't exist
if [ ! -f "src/main/resources/synthea.properties" ]; then
    echo "Creating default synthea.properties file..."
    echo "# Default Synthea properties" > src/main/resources/synthea.properties
    echo "exporter.baseDirectory = ./output" >> src/main/resources/synthea.properties
    echo "exporter.use_uuid_filenames = false" >> src/main/resources/synthea.properties
fi

# Compile the test class
echo "Compiling SyntheaTest.java..."
javac -cp "$SYNTHEA_JAR" -d build src/main/java/com/healthcaremock/generator/SyntheaTest.java

# Check if compilation succeeded
if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

# Run the test
echo "Running SyntheaTest..."
java -cp "build:$SYNTHEA_JAR" com.healthcaremock.generator.SyntheaTest

# Return result
if [ $? -eq 0 ]; then
    echo "Test completed successfully"
else
    echo "Test failed"
    exit 1
fi 