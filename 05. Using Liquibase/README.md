# Qu’est-ce qu’une migration ?
+ Les migrations sont le processus de déplacement du code de programmation d'un système à un autre.
  + Il s'agit d'un sujet assez vaste et complexe concernant la maintenance des applications informatiques.
+ Les migrations de bases de données doivent généralement avoir lieu avant ou conjointement avec le code d'application.
  + Peut entraîner des erreurs d'exécution si la base de données ne correspond pas à ce qui est attendu
+ Les migrations de bases de données constituent une partie très importante du processus de migration du code de votre application vers la production.
  + Gardez à l'esprit que dans les grandes organisations, en tant que développeur, vous n'effectuerez **PAS** la migration.

# Pourquoi utiliser un outil de migration ?
+ Hibernate peut gérer correctement mon schéma, pourquoi utiliser un outil de migration ?
+ Gestion de nombreux environnements et bases de données :
  + Développement **(H2), CI/CD, QA, UAT, Production**
+ Les bases de données de développement et **CI/CD** sont faciles, il suffit de les reconstruire à chaque fois.
+ **QA**, **UAT**, Production sont des bases de données permanentes
  + Dans quel état sont-ils ?
  + Un script a-t-il été appliqué ?
  + Comment créer une nouvelle base de données pour une release ?

# Pourquoi utiliser un outil de migration ?
+ Les outils de migration de bases de données peuvent :
  + Créer une nouvelle base de données
  + Conserver l'historique des migrations
  + Avoir un état reproductible de la base de données
  + Aider à gérer les modifications appliquées à de nombreuses instances de base de données
+ Outils de migration de bases de données Open Source populaires (liste non exhaustive) :
  + **Liquibase**
  + **Flyway**

# Liquibase et Flyway
+ Caractéristiques communes :
    + Outils de ligne de commande
    + Intégration avec **Maven** et **Gradle**
    + Intégration de **Spring Boot**
    + Utilisez des fichiers de script qui peuvent être versionnés et suivis.
    + Utiliser la table de base de données pour suivre les modifications
    + Avoir un support commercial

# Liquibase / Flyway - Intégration Spring Boot
+ **Spring Boot** prend en charge **Liquibase** et **Flyway**.
+ Fondamentalement, **Spring Boot** appliquera les ensembles de modifications
+ La prise en charge de **Spring Boot** est limitée
+ Les deux outils disposent de fonctionnalités supplémentaires disponibles à partir de la ligne de commande ou des plugins d'outils de construction.
+ L'intégration de **Spring Boot** est pratique pour les migrations
  + Les deux outils peuvent être utilisés indépendamment de Spring Boot.

# Liquibase et Flyway
+ **Liquibase** et **Flyway** sont très similaires en termes de fonctionnalités
+ Partagez les mêmes concepts, mais une terminologie légèrement différente.
+ **Liquibase** prend en charge les scripts de modification en **SQL**, **XML**, **YAML** et **JSON**
  + **SQL** abstrait **XML**, **YAML** et **JSON**, qui peut être bénéfique pour différentes technologies de base de données
+ **Flyway** prend en charge **SQL** et **Java** uniquement
+ **Liquibase** est un produit plus grand et plus robuste
+ **Flyway** semble avoir plus de popularité
+ Les deux sont matures et largement utilisés

# Liquibase vs Flyway - Lequel utiliser ?
+ **Liquibase** est probablement une meilleure solution pour les grandes entreprises avec des environnements complexes
+ **Flyway** convient à 90% des applications qui n'ont pas besoin de fonctionnalités supplémentaires
+ Recommandation :
  + Si l'un ou l'autre est utilisé dans l'organisation, utilisez-le
  + En cas de doute, faites vos propres recherches sur chaque option
  + La préférence de John est **Flyway** : simple et facile à utiliser

# Terminologie Liquibase
+ **ChangeSet** - Un ensemble de modifications à appliquer à la base de données
+ **Change**: une seule modification à appliquer à la base de données.
+ **Changelog** - Un fichier contenant une liste de **changeSet** à appliquer
+ **Preconditions** - Conditions qui contrôlent l'exécution
+ **Context** : une expression permettant de contrôler si le script doit ou non s'exécuter.
+ **ChangeLog Parameters** - Espaces réservés qui peuvent être remplacés au moment de l'exécution.

# Meilleures pratiques Liquibase
+ **Organisation des journaux de modifications** : créez un journal principal des modifications pour organiser les ensembles de modifications.
• **Une modification par ensemble de modifications** : permet une restauration plus facile en cas d'échec.
• **Ne modifiez jamais un ensemble de modifications** : les modifications doivent être additives.
• **Utilisez des identifiants d'ensemble de modifications significatifs** : certains utilisent un numéro de séquence, d'autres un nom descriptif.

# Exécuter Liquibase
+ **Ligne de commande (CLI)** – CLI disponible pour Windows, MacOS et Linux
  + Non couvert dans ce cours
+ Plugins **Maven/Gradle**
  + Nous examinerons uniquement Maven.
+ **Spring Boot** - Exécutera Liquibase au démarrage pour mettre à jour la base de données configurée avec le dernier ensemble de modifications.

# Nos prochaines étapes
+ Configurer le plug-in Maven
+ Utiliser **Maven/Liquibase** pour générer un ensemble de modifications pour la base de données existante
  + Nous évite de créer manuellement
  + Excellent moyen de migrer une base de données existante vers Liquibase
+ Configurer la prise en charge de Spring Boot pour Liquibase
+ Modifier la table existante avec Liquibase
+ Ajouter une nouvelle table avec Liquibase


# Documentation

[Liquibase](https://docs.liquibase.com/tools-integrations/maven/home.html)