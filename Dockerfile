FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom first to leverage Docker layer caching for dependencies
COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

# Copy source and build WAR
COPY src ./src
RUN mvn -q -DskipTests clean package

FROM tomcat:9.0-jdk17-temurin

WORKDIR /usr/local/tomcat

# Remove default apps to keep image smaller/cleaner
RUN rm -rf webapps/*

# Deploy app as ROOT context
COPY --from=builder /app/target/test-webapp.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
