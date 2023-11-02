#!/bin/bash

# Définissez le chemin du répertoire de votre application Java EE
APP_DIR="../../.."

CLASS_NAME="Beans.ReportGenerator"

# Set the name of your method to be executed
#METHOD_NAME="generateReport"

# Navigate to the project directory
cd "$APP_DIR"

# Use Maven to run your Java application
mvn exec:java -Dexec.mainClass="$CLASS_NAME"
