# ServerBD
Refonte de mon serveur en Spring Boot

docker build -t serverbd .

docker run -P serverbd



#Lancer dockercompose :
Mode container Linux
si probleme de HNS lancer dans powershell : Get-NetNat | Remove-NetNat

docker-compose up 
Si probleme lancer 
docker-compose down

Penser à créer un répertoire partagé dans docker "appli"