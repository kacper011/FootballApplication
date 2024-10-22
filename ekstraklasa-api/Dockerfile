# Wybór obrazu bazowego (Java 21)
FROM openjdk:21-jdk-slim

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Kopiowanie pliku JAR do katalogu roboczego
COPY target/ekstraklasa-api-0.0.1-SNAPSHOT.jar /app/ekstraklasa-api.jar

# Określenie portu, na którym będzie nasłuchiwać aplikacja
EXPOSE 8084

# Komenda uruchomienia aplikacji
CMD ["java", "-jar", "ekstraklasa-api.jar"]