FROM openjdk:17-oracle
ARG JAR_FILE
COPY ${JAR_FILE} restaurant.jar
ENTRYPOINT ["java","-jar","/restaurant.jar"]