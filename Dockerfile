FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
COPY target/AccountAPI-*.jar accountApp.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/accountApp.jar"]
