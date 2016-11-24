
cls
call git pull
call mvn clean package
call java -Dspring.datasource.url=jdbc:mysql://localhost:3306/collectionbd?useSSL=false -jar target\server-bd-1.0.jar