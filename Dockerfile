FROM openjdk:17-jdk-slim
MAINTAINER housekeeping.dziadkouskaya.com
ENV DB_HOST=localhost\
    DB_PORT=5432\
    DB_NAME=housekeeping-db\
    DB_USER=housekeeping-db-admin\
    DB_PASSWORD=postgres_secret
COPY ./build/libs/housekeeping-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
