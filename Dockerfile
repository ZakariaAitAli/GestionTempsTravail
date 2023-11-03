FROM tomcat:9.0-jdk21-openjdk

RUN apt-get update && apt-get upgrade -y

# Install MySQL server
RUN apt-get update && apt-get install -y mysql-server

# Create a directory for MySQL configuration
RUN mkdir -p /etc/mysql/conf.d

# Copy MySQL configuration file to the container
COPY ./mysql/my.cnf /etc/mysql/conf.d/my.cnf

# Set the MySQL root password
ENV MYSQL_ROOT_PASSWORD=root

# Copy the WAR file to Tomcat's webapps directory
COPY ./target/GestionTempsTravail-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose ports
EXPOSE 8080 3306

# Start Tomcat and MySQL
CMD ["catalina.sh", "run"]
