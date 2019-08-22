FROM openjdk:8-jdk-alpine

# Create user
RUN addgroup junk-t && adduser -D -u 1000 -G junk-t junk-t
USER junk-t

WORKDIR /home/junk-t/
RUN mkdir static

# Start web application after db server has started
WORKDIR /home/junk-t/server/
RUN pwd
RUN ls -l
ENTRYPOINT ./gradlew bootRun --args='--spring.profiles.active=dev'