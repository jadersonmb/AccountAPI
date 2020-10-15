FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
COPY target/AccountAPI-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]
