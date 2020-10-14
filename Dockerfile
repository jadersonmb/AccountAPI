FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY target/spring-boot-docker-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/opt/app/app.jar"]
