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


---

Pour utiliser le fichier `application-local.properties` dans une application Spring Boot, vous pouvez activer un profil spécifique ou charger explicitement le fichier de propriétés. Voici les deux méthodes détaillées :

### Méthode 1: Utilisation des Profils Spring Boot

Spring Boot permet de charger différents fichiers de configuration en fonction des profils actifs. Pour utiliser `application-local.properties`, vous pouvez activer le profil `local`.

#### Étape 1: Créer le fichier `application-local.properties`

Assurez-vous que le fichier `application-local.properties` est présent dans le répertoire `src/main/resources`.

#### Étape 2: Activer le profil `local`

Il y a plusieurs façons d'activer le profil `local`.

1. **Via la ligne de commande**

   Lorsque vous démarrez votre application, spécifiez le profil `local` en utilisant l'argument `--spring.profiles.active=local`:

   ```sh
   java -jar myapp.jar --spring.profiles.active=local
   ```

2. **Via les arguments de la JVM**

   Ajoutez l'argument `-Dspring.profiles.active=local` :

   ```sh
   java -Dspring.profiles.active=local -jar myapp.jar
   ```

3. **Via les configurations de l'IDE**

  - **IntelliJ IDEA** :
    - Allez dans `Run -> Edit Configurations`.
    - Sélectionnez votre configuration de démarrage.
    - Ajoutez `-Dspring.profiles.active=local` dans le champ `VM options`.

  - **Eclipse/STS** :
    - Allez dans `Run -> Run Configurations`.
    - Sélectionnez votre configuration de démarrage.
    - Ajoutez `-Dspring.profiles.active=local` dans l'onglet `Arguments`, section `VM arguments`.

4. **Dans le fichier `application.properties`**

   Vous pouvez également définir le profil actif par défaut dans `application.properties` :

   ```properties
   # application.properties
   spring.profiles.active=local
   ```

### Méthode 2: Chargement explicite de fichiers de propriétés

Si vous ne souhaitez pas utiliser les profils, vous pouvez configurer explicitement Spring Boot pour charger `application-local.properties`.

#### Étape 1: Créer la classe de configuration

Ajoutez une classe de configuration qui charge les fichiers de propriétés en utilisant `@PropertySource`.

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-local.properties")
public class AppConfig {
    // Vos beans et configurations ici
}
```

Avec cette configuration, Spring Boot chargera à la fois `application.properties` et `application-local.properties`.

### Conclusion

En utilisant l'une de ces méthodes, vous pouvez charger et utiliser `application-local.properties` sans créer de conflits. Si vous utilisez les profils Spring Boot, cela vous permet de gérer facilement différentes configurations pour différents environnements. Le chargement explicite des fichiers de propriétés offre une alternative flexible si vous préférez ne pas utiliser les profils.