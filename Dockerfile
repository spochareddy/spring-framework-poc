# Dockerfile
FROM openjdk:latest
WORKDIR /app
RUN addgroup --system javauser && adduser -S -s /usr/sbin/nologin -G javauser javauser
ARG JAR_FILE
COPY . .
RUN chown -R javauser:javauser .
USER javauser
ENTRYPOINT ["java", "-jar", "spring-framework-poc-0.0.1-SNAPSHOT.jar"]

# syntax=docker/dockerfile:1
#FROM alpine:latest
#RUN apk add --no-cache python2 g++ make
#WORKDIR /app
#COPY . .
#RUN yarn install --production
#CMD ["node", "src/index.js"]
EXPOSE 8080
