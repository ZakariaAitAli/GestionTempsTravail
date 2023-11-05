## Gestion de Temps de Travail
### Projet de Gestion de Temps de Travail
### Membres du groupe
* [Fatehi Mohamed Alhabib](https://github.com/Simofatt)
* [Ait Ali Zakaria ](https://github.com/ZakariaAitAli)
* [Yakoubi Oumayma](https://github.com/yakoubi-oumayma)
* [Samadi Nada](https://github.com/nada-samadi)
* [Kaoutar BELKADI](https://github.com/kaoutarBELKADI)

### Introduction
Ce projet est une application web qui permet de gérer le temps de travail des employés d'une entreprise. Elle permet de gérer les employés, les projets, les tâches et les temps de travail. Elle permet aussi de générer des rapports sur les temps de travail des employés.

### Technologies utilisées
* [Java EE](https://www.oracle.com/java/technologies/java-ee-glance.html)
* [NextJS](https://nextjs.org/)
* [MySQL](https://www.mysql.com/fr/)
* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)
* [Maven](https://maven.apache.org/)
* [Git](https://git-scm.com/)
* [Github](https://github.com/)
* [Github Actions](https://docs.github.com/en/actions)

### Installation
#### Prérequis
* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)
* [Git](https://git-scm.com/)
* [Maven](https://maven.apache.org/)
* [Java 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
* [NodeJS](https://nodejs.org/en/)
* [NPM](https://www.npmjs.com/)
* [NextJS](https://nextjs.org/)
* [MySQL](https://www.mysql.com/fr/)
* [MySQL Workbench](https://www.mysql.com/fr/products/workbench/)
* [IntelliJ IDEA](https://www.jetbrains.com/fr-fr/idea/)

#### Installation
1. Cloner le projet
```bash
git clone
```
2. Lancer le serveur MySQL
```bash
docker-compose up -d
```
3. Créer la base de données
```bash
docker exec -i mysql mysql -uroot -psecret < ./database.sql
```
4. Lancer le serveur Java
```bash
mvn spring-boot:run
```
5. Lancer le serveur NextJS
```bash
npm run dev
```
6. Ouvrir l'application dans le navigateur
```bash
http://localhost:3000
```

