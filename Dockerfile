FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} restaurant.jar
ENTRYPOINT ["java","-jar","/restaurant.jar"]