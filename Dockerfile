FROM tomcat:9.0-jdk21-openjdk

# Install MySQL client
RUN apt-get update && apt-get install -y mysql-client && rm -rf /var/lib/apt

# Copy the WAR file to Tomcat's webapps directory
COPY ./target/GestionTempsTravail-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose port
EXPOSE 8080
