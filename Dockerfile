FROM openjdk:8-jdk-alpine

LABEL maintainer="praveenk@idexcel.net"

COPY ./target/idexceldemo-admin-service-0.0.1-SNAPSHOT.jar idexceldemo-admin-service-0.0.1-SNAPSHOT.jar

EXPOSE 8111

ENTRYPOINT ["java","-jar","idexceldemo-admin-service-0.0.1-SNAPSHOT.jar"]

