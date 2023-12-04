# Documentation Backend du projet "Suika Tchat" 

Une application de chat utilisant Spring Boot, PostgreSQL comme base de données et Swagger pour la documentation API.


## 1. Lancer le projet 

### 1.1. cloner le projet

```
$ git clone git@github.com:certification-M2i/back-end.git
```

### 1.2 Dépendances nécessaires

- Java 17
- Postgresql >= 14

### 1.3 Configuration de la base de données PostregSQL

En local, dans le fichier `src/resources/application.properties`: 
- vérifier l'url: `spring.datasource.url=jdbc:postgresql://localhost::5432/suika_tchat`
- remplacer `localhost` par `127.0.0.1` si nécessaire et le port 5432 par le port de votre choix. Par défaut, le port utilisé par postgreSQL est `5432`

- vérifier également username: `spring.datasource.username=postgres` et `spring.datasource.password=root` qui doivent correspondre à votre configuration PostgreSQL.

Pour information:
La configuration `spring.jpa.hibernate.ddl-auto=update`  indique à Hibernate comment gérer la création et la mise à jour de la structure de la base de données.
L'update va simplement mettre à jour le schèma de la BDD ou la créer si elle n'existe pas encore.

Enfin, la configuration  `spring.jpa.show-sql=true` indique à Spring de loguer les requêtes SQL générées par Hibernate. c'est-à-dire que toutes les requêtes SQL exécutées par Hibernate seront affichées dans la console.

Le port par défaut du projet est `8080`

### 1.4 Lancer le projet

Vous pouvez lancer le projet en exécutant la commande à la racine du projet

```
$ ./mvnw spring-boot:run
```

Vous pouvez également exécuter le projet via intellij. Pour cela, cliquer sur la commande Run dans le fichier SuikaApplication.java.

## 2. Documentation

Lorsque le projet est éxecuté sur le port par défaut, la documentation est accessible sur l'url : http://localhost:8080/swagger-ui/index.html


## 3. Structure du Projet

- `/src` : Contient le code source de l'application.
- `/pom.xml` : Fichier de configuration Maven.

## 4. Licence

Ce projet est sous licence MIT.