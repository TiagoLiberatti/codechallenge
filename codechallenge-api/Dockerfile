FROM maven:3.6.3-jdk-11-slim AS build
COPY ./codechallenge-api/src /home/app/src
COPY ./codechallenge-api/pom.xml /home/app
COPY ./contas_bancarias.csv /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/contas_bancarias.csv /usr/local/lib/contas_bancarias.csv
COPY --from=build /home/app/target/codechallenge-api-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]