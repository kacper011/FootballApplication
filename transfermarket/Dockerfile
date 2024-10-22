# Wybór obrazu bazowego (Java 21)
FROM openjdk:21-jdk-slim

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Kopiowanie pliku JAR do katalogu roboczego
COPY target/transfermarket-0.0.1-SNAPSHOT.jar /app/transfermarket.jar

# Określenie portu, na którym będzie nasłuchiwać aplikacja
EXPOSE 8086

# Komenda uruchomienia aplikacji
CMD ["java", "-jar", "transfermarket.jar"]