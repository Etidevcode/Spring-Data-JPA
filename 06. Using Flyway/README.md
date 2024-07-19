# Introduction à la voie de migration

## Qu’est-ce qu’une migration ?
+ Les migrations sont le processus de déplacement du code de programmation d'un système à un autre.
    + Il s'agit d'un sujet assez vaste et complexe concernant la maintenance des applications informatiques.
+ Les migrations de bases de données doivent généralement avoir lieu avant ou conjointement avec le code d'application.
  + Peut entraîner des erreurs d'exécution si la base de données ne correspond pas à ce qui est attendu
+ Les migrations de bases de données constituent une partie très importante du processus de migration du code de votre application vers la production.
  + Gardez à l'esprit que dans les grandes organisations, en tant que développeur, vous n'effectuerez PAS la migration.

## Pourquoi utiliser un outil de migration ?
+ Hibernate peut gérer correctement mon schéma, pourquoi utiliser un outil de migration ?
+ Gestion de nombreux environnements et bases de données :
  + Développement **(H2), CI/CD, QA, UAT, Production** 
+ Les bases de données de développement et CI/CD sont faciles, il suffit de les reconstruire à chaque fois.
  + **QA, UAT, Production** sont des bases de données permanentes
  + Dans quel état sont-ils ?
  + Un script a-t-il été appliqué ?
  + Comment créer une nouvelle base de données pour une release ?

## Pourquoi utiliser un outil de migration ?
+ Les outils de migration de bases de données peuvent :
    + Créer une nouvelle base de données
    + Conserver l'historique des migrations
    + Avoir un état reproductible de la base de données
    + Aider à gérer les modifications appliquées à de nombreuses instances de base de données
+ Outils de migration de bases de données Open Source populaires (liste non exhaustive) :
  + Liquibase
  + Flyway

## Liquibase et Flyway
+ Caractéristiques communes :
    + Outils de ligne de commande
    + Intégration avec **Maven** et **Gradle**
    + Intégration de **Spring Boot**
    + Utilisez des fichiers de script qui peuvent être versionnés et suivis.
    + Utiliser la table de base de données pour suivre les modifications
    + Avoir un support commercial

## Liquibase / Flyway - Intégration Spring Boot
+ Spring Boot prend en charge **Liquibase** et **Flyway**.
+ Fondamentalement, **Spring Boot** appliquera les ensembles de modifications
+ La prise en charge de **Spring Boot** est limitée
+ Les deux outils disposent de fonctionnalités supplémentaires disponibles à partir de la ligne de commande ou des plugins d'outils de construction.
+ L'intégration de Spring Boot est pratique pour les migrations
  + Les deux outils peuvent être utilisés indépendamment de **Spring Boot**.

## Liquibase et Flyway
+ **Liquibase et Flyway** sont très similaires en termes de fonctionnalités
+ Partagez les mêmes concepts, mais une terminologie légèrement différente.
+ **Liquibase** prend en charge les scripts de modification en **SQL, XML, YAML** et **JSON**
  + **SQL** abstrait **XML**, **YAML** et **JSON**, qui peut être bénéfique pour différentes technologies de base de données
+ **Flyway** prend en charge SQL et Java uniquement
+ **Liquibase** est un produit plus grand et plus robuste
+ **Flyway** semble avoir plus de popularité
+ Les deux sont matures et largement utilisés

## Liquibase vs Flyway - Lequel utiliser ?
+ **Liquibase** est probablement une meilleure solution pour les grandes entreprises avec des environnements complexes
+ **Flyway** convient à 90% des applications qui n'ont pas besoin de fonctionnalités supplémentaires
+ Recommandation :
  + Si l'un ou l'autre est utilisé dans l'organisation, utilisez-le
  + En cas de doute, faites vos propres recherches sur chaque option
  + La préférence de John est **Flyway** : simple et facile à utiliser

## Commandes de voie de migration
+ **Migrer** : Migrer vers la dernière version
+ **Nettoyer** - Supprime tous les objets de base de données - PAS POUR UNE UTILISATION EN PRODUCTION
+ **Info** : imprime des informations sur les migrations
+ **Valider** : valide les migrations appliquées par rapport aux migrations disponibles.
+ **Annuler** : annule la migration la plus récemment appliquée.
+ **Baseline** – Base de base d'une base de données existante
+ **Réparation** : utilisé pour résoudre les problèmes liés à la table d'historique du schéma.

## Voie de migration en cours d'exécution
+ **Ligne de commande (CLI)** – CLI disponible pour Windows, MacOS et Linux
    + Non couvert dans ce cours
+ **Plugins Maven/Gradle**
  + Non couvert dans ce cours
+ **Spring Boot** - Exécutera **Flyway** au démarrage pour mettre à jour la base de données configurée avec le dernier ensemble de modifications.

## Nos prochaines étapes
+ Configurer la prise en charge de **Spring Boot** pour **Flyway**
+ Modifier le tableau existant avec **Flyway**
+ Ajouter une nouvelle table avec **Flyway**
+ **REMARQUE** : le code source commencera à la fin de la section **Hibernate with MySQL**.
  + même point de départ que la section Liquibase bien sûr

## Pourquoi pas une ligne de base ?
+ La ligne de base sert à introduire **Flyway** dans un schéma de base de données existant.
  + Suppose que les tables et les objets de la base de données seront présents
+ Nous souhaitons repartir d'une base de données vide