# Wybór obrazu bazowego
FROM openjdk:21-jdk-slim

# Instalacja netcat-openbsd
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie pliku JAR
COPY target/bundesliga-api-0.0.1-SNAPSHOT.jar /app/bundesliga-api.jar

# Kopiowanie skryptu wait-for-it.sh
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# Kopiowanie pliku .env
COPY .env /app/.env

# Określenie portu
EXPOSE 8082

# Uruchomienie aplikacji z mechanizmem opóźnienia
CMD ["/app/wait-for-it.sh", "eureka-server", "8761", "--", "java", "-jar", "bundesliga-api.jar"]
