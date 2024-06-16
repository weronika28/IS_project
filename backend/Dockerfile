FROM openjdk:22

COPY ./target/*.jar /backend.jar

ENV spring_profiles_active docker

EXPOSE 5000

CMD ["java", "-jar", "/backend.jar"]