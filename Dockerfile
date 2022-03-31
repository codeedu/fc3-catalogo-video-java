FROM openjdk:11-alpine

USER gradle
VOLUME "/home/gradle/.gradle"
WORKDIR /home/gradle