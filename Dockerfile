FROM gradle:latest
USER root

RUN gradle build
