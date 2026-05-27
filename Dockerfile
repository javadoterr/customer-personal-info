FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=target/customer-personal-info-uat.jar
COPY ${JAR_FILE} customer-personal-info-uat.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/customer-personal-info-uat.jar"]