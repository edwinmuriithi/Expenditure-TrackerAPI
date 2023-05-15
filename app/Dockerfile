FROM adoptopenjdk/openjdk11:jre-nightly

WORKDIR /app

COPY target/expenditureapi.jar expenditureapi.jar

EXPOSE $PORT

CMD ["java", "-jar", "/expenditureapi.jar"]

