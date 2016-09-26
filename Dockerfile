FROM frolvlad/alpine-oraclejdk8:slim

COPY . /target
WORKDIR /target

EXPOSE 8080/tcp
EXPOSE 3306/tcp

ENTRYPOINT java -jar target/server-bd-1.0.jar