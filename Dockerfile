FROM tomcat:9.0-jdk21-openjdk

COPY target/GestionTempsTravail-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
