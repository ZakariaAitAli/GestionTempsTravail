
#!/bin/bash

# Définissez le chemin du répertoire de votre application Java EE
APP_DIR="/Users/mac/Desktop/GestionTempsTravail"

CLASS_NAME="BEANS.EmailSender"

# Set the name of your method to be executed

# Navigate to the project directory
cd "$APP_DIR"

# Use Maven to run your Java application
mvn exec:java -Dexec.mainClass="$CLASS_NAME"
