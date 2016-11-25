FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD target/server-bd-1.0.jar server-bd-1.0.jar
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://wbbassac:3306/collectionbd?useSSL=false","-jar","/server-bd-1.0.jar"]