# Wybór obrazu bazowego
FROM openjdk:21-jdk-slim

# Instalacja netcat-openbsd
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie pliku JAR
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar

# Kopiowanie skryptu wait-for-it.sh
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# Określenie portu
EXPOSE 8080

# Uruchomienie aplikacji z mechanizmem opóźnienia
CMD ["/app/wait-for-it.sh", "eureka-server", "8761", "--", "java", "-jar", "api-gateway.jar"]
