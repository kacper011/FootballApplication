# Wybór obrazu bazowego (Java 21)
FROM openjdk:21-jdk-slim

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Kopiowanie pliku JAR do katalogu roboczego
COPY target/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# Określenie portu, na którym będzie nasłuchiwać aplikacja
EXPOSE 8761

# Komenda uruchomienia aplikacji
CMD ["java", "-jar", "eureka-server.jar"]


