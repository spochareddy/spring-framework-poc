# Dockerfile
FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:02c04793fa49ad5cd193c961403223755f9209a67894622e05438598b32f210e
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
