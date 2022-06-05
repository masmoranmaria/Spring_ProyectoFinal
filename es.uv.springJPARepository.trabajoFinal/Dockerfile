FROM maven:3-openjdk-17 AS builder
# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/
# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
From openjdk:17 
# copy jar from the first stage
COPY --from=builder target/trabajoFinal-0.0.1-SNAPSHOT.jar microservice.jar
# run jar
CMD ["java", "-jar", "microservice.jar"]
