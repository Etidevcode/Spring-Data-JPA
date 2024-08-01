## SOURCES DE DONNÉES MULTIPLES SPRING DATA

### Introduction
Configurer des sources de données multiples dans une application Spring Data permet de se connecter à plusieurs bases de données. Cela peut être utile pour divers scénarios comme la séparation des responsabilités, l'amélioration des performances, ou la gestion des sauvegardes.

### Ajout du projet Lombok
Lombok est une bibliothèque Java qui réduit le code boilerplate. Pour ajouter Lombok au projet, incluez sa dépendance dans le fichier `pom.xml` (Maven) ou `build.gradle` (Gradle).

### Configuration MySQL
Configurer MySQL en tant que source de données implique de définir les propriétés de connexion dans le fichier de configuration de Spring (`application.properties` ou `application.yml`).

### Propriétés de la source de données
Définir les propriétés de connexion pour chaque source de données, incluant l'URL, le nom d'utilisateur, le mot de passe, et le pilote JDBC.

### Créer des connexions de source de données
Configurer les beans `DataSource` pour chaque source de données en utilisant les propriétés définies.

### Créer des gestionnaires d'entités
Configurer les `EntityManagerFactory` pour chaque source de données, ce qui permet à Hibernate de gérer les entités.

### Créer des gestionnaires de transactions
Configurer les `PlatformTransactionManager` pour chaque source de données afin de gérer les transactions.

### Configuration du référentiel JPA Spring Data
Configurer les référentiels JPA pour chaque source de données en utilisant les annotations `@EnableJpaRepositories` avec les gestionnaires d'entités et les gestionnaires de transactions spécifiques.

### Créer des scripts de migration Flyway
Flyway est une bibliothèque de migration de base de données. Créez des scripts de migration SQL pour gérer les schémas des bases de données.

### Configuration de la source de données Flyway
Configurer Flyway pour chaque source de données afin de gérer les migrations de schéma.

### Migrations Flyway personnalisées
Implémenter des migrations personnalisées en utilisant Flyway pour appliquer des modifications spécifiques aux schémas de base de données.

### Test de migration Flyway
Tester les migrations Flyway pour s'assurer qu'elles s'appliquent correctement et sans erreur.

### Propriétés de pool de bases de données Hikari
Configurer HikariCP, le pool de connexions utilisé par Spring Boot, pour chaque source de données afin d'améliorer les performances.

### Configurer la validation du schéma Hibernate
Configurer Hibernate pour valider les schémas des bases de données à chaque démarrage de l'application afin de s'assurer que les entités sont correctement mappées.

### Configurer les conventions de dénomination Hibernate
Configurer les conventions de dénomination des tables et des colonnes dans Hibernate pour s'assurer qu'elles suivent un certain standard.

### Propriétés transitoires Hibernate
Configurer des propriétés transitoires dans Hibernate pour gérer les états temporaires des entités qui ne doivent pas être persistés.

### Créer des références d'ID de carte de crédit
Créer une entité pour gérer les références d'ID de carte de crédit dans la base de données.

### Enregistrer une nouvelle carte de crédit
Implémenter la logique pour enregistrer une nouvelle carte de crédit dans la base de données en utilisant le référentiel JPA approprié.

### Obtenir une carte de crédit par ID
Implémenter la logique pour récupérer une carte de crédit par son ID en utilisant le référentiel JPA approprié.

---

Ce résumé offre une vue d'ensemble de la configuration et de l'utilisation de sources de données multiples avec Spring Data, en mettant l'accent sur les composants nécessaires et les étapes pour les configurer correctement.


### Documentation

[Hikari](https://github.com/brettwooldridge/HikariCP)