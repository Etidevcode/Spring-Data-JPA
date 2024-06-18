## Outil de génération de schéma DDL Hibernate

### 1) Outil de génération de schéma DDL Hibernate
+ Outil de génération de schéma Hibernate - alias - propriété de configuration hbm2ddl.auto
+ Hibernate a la capacité de réfléchir aux classes annotées JPA pour déterminer la base de données nécessaire.
structure
+ La mise en veille prolongée peut :
  + Créer des instructions **DDL** à classer
  + Exécuter des instructions **DDL** pour créer ou mettre à jour des tables de base de données
+ Spring Boot configure automatiquement cette propriété pour générer automatiquement des tables de base de données.

### 2) Propriétés automatiques HBM DDL
+ **none** - Désactive l'outil de génération de schéma
+ **create-only** - Créer un schéma de base de données à partir d'entités JPA
+ **drop** - supprime les tables de base de données liées aux entités JPA
+ **create** - supprime le schéma de base de données et recrée à partir des entités JPA
+ **create-drop** - supprime le schéma de base de données et recrée à partir des entités JPA, puis sera supprimé lors de l'arrêt.
+ **validate** - Valide le schéma, erreur fatale en cas d'erreur
+ **update** - met à jour le schéma des entités JPA

### 3) Comment ça fonctionne
+ L'outil de génération de schéma Hibernate utilise la réflexion sur les entités JPA pour déterminer la structure de la base de données.
+ Permet une configuration JPA minimaliste
  + Noms de tables et de colonnes déduits des noms de types et de propriétés.
    + La valeur par défaut est l'étui chameau ou l'étui serpent.
    + productDescription -> PRODUCT_DESCRIPTION
  + Les types de données sont également par défaut
+ Si des mappages JPA sont présents, ils seront utilisés (vous pouvez définir des noms de tables, des noms de colonnes, des types, etc. dans JPA)

### 4) Quel mode utiliser
+ L'outil de génération de schéma Hibernate est idéal pour un développement rapide.
+ Vous permet de faire évoluer rapidement votre modèle objet sans maintenir les instructions SQL DDL
+ NON recommandé pour les bases de données de production
+ Pour les bases de données de production, utilisez **validate ou none**.
  + Valider est une bonne option puisque le démarrage échouera si le schéma de la base de données est erroné.
  + Sans erreur, des erreurs pourraient se produire au moment de l'exécution.
+ Utilise également l'utilisateur de la source de données, nécessitant des privilèges de base de données élevés.

### 5) Gestion des schémas de base de données
+ La gestion des schémas de bases de données de production est toujours un sujet difficile
+ Il n'existe pas de solution universelle
+ Dans certaines organisations, vous aurez plus d'accès, d'autres beaucoup moins
+ Les environnements réglementaires exigeront souvent une **séparation des tâches**, de sorte que seuls les administrateurs de base de données seront autorisés à modifier les schémas.
+ La gestion des schémas peut être manuelle ou automatisée avec des outils tels que **flyway ou liquidbase**
+ Plus à venir sur ce sujet !