# Wybór obrazu bazowego (Java 21)
FROM openjdk:21-jdk-slim

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Kopiowanie pliku JAR do katalogu roboczego
COPY target/bundesliga-api-0.0.1-SNAPSHOT.jar /app/bundesliga-api.jar

# Kopiowanie pliku .env do katalogu roboczego
COPY .env /app/.env

# Określenie portu, na którym będzie nasłuchiwać aplikacja
EXPOSE 8082

# Komenda uruchomienia aplikacji
CMD ["java", "-jar", "bundesliga-api.jar"]
