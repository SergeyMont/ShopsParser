FROM amazoncorretto:11

COPY target/*.jar /ShopsParser.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/ShopsParser.jar"]