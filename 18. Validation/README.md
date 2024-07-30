# Validation Java Bean

## JSR 303 - Validation Java Bean
+ JSR 303 a introduit la validation Java Bean (version 1.0)
  + Ensemble d'annotations utilisées pour valider les propriétés Java Bean
+ Approuvé le 16 novembre 2009.
+ Fait partie de JEE v6 et supérieur
+ JSR 303 pris en charge par Spring depuis la version 3
+ L'objectif principal était de définir des annotations pour la validation des données
  + Principalement des propriétés au niveau du champ

## JSR 349 Validation Bean 1.1
+ JSR 349 - Validation Java Bean 1.1 publiée le 10 avril 2013.
  + JEE v7, Spring Framework 4
+ S'appuie sur la spécification 1.0
+ Étendu à la validation au niveau de la méthode
  + Pour valider les paramètres d'entrée
+ Inclut l'injection de dépendances pour les composants de validation de bean

## JSR 380 - Validation Bean 2.0
+ Approuvé en août 2017
+ Ajouté à Spring Framework 5.0 RC2
+ Disponible dans Spring Boot 2.0.0 +
+ Utilise Hibernate Validator 6.0 + (implémentation de Bean Validation 2.0)
+ L'objectif principal de Bean Validation 2.0 est les fonctionnalités du langage Java 8
+ Ajout d'environ 11 nouvelles annotations de validation intégrées
+ Le reste de la présentation se concentrera sur Bean Validation 2.0

## Définitions de contraintes intégrées
+ **@Null** - Vérifie que la valeur est nulle
+ **@NotNull** - Vérifie que la valeur n'est pas nulle
+ **@AssertTrue** - La valeur est vraie
+ **@AssertFalse** - La valeur est fausse
+ **@Min** - Le nombre est égal ou supérieur
+ **@Max** - Le nombre est égal ou inférieur

## Définitions de contraintes intégrées
+ **@DecimalMin** - La valeur est plus grande
+ **@DecimalMax** - La valeur est inférieure que
+ **@Negative** - La valeur est inférieure à zéro. Zéro non valide.
+ **@NegativeOrZero** - La valeur est nulle ou inférieure à zéro
+ **@Positive** - La valeur est supérieure à zéro. Zéro non valide.
+ **@PositiveOrZero** - La valeur est nulle ou supérieure à zéro.
+ **@Size** - vérifie si la chaîne ou la collection est comprise entre un minimum et un maximum

## Définitions de contraintes intégrées
+ **@Digits** - vérifie les chiffres entiers et les chiffres fractionnaires
+ **@Past** - Vérifie si la date est dans le passé
+ **@PastOrPresent** - Vérifie si la date est dans le passé ou le présent
+ **@Future** - Vérifie si la date est dans le futur
+ **@FutureOrPresent** - Vérifie si la date est présente ou dans le futur
+ **@Pattern** - vérifie par rapport au modèle RegEx

## Définitions de contraintes intégrées
+ **@NotEmpty** - Vérifie si la valeur n'est ni nulle ni vide (caractères d'espacement ou collection vide)
+ **@NonBlank** - Vérifie que la chaîne n'est pas nulle ou ne contient pas de caractères d'espacement
+ **@Email** - Vérifie si la valeur de la chaîne est une adresse e-mail

## Contraintes du validateur Hiberate
+ **@ScriptAssert** - Annotation au niveau de la classe, vérifie la classe par rapport à script
+ **@CreditCardNumber** - Vérifie que la valeur est un numéro de carte de crédit
+ **@Currency** - Montant en devise valide
+ **@DurationMax** - Durée inférieure à la valeur donnée
+ **@DurationMin** - Durée supérieure à la valeur donnée
+ **@EAN** - Code-barres EAN valide
+ **@ISBN** - Valeur ISBN valide

## Contraintes du validateur Hiberate
+ **@Length** - Longueur de chaîne comprise entre le minimum et le maximum donnés
+ **@CodePointLength** - Valide que la longueur du point de code de la séquence de caractères annotée est comprise entre le minimum et le maximum inclus.
+ **@LuhnCheck** - Somme de contrôle Luhn
+ **@Mod10Check** - Somme de contrôle Mod 10
+ **@Mod11Check** - Somme de contrôle Mod 11

## Contraintes du validateur Hiberate
+ **@Range** - vérifie si le nombre est compris entre le minimum et le maximum donnés (inclus)
+ **@SafeHtml** - Vérifie la sécurité du HTML
+ **@UniqueElements** - Vérifie si la collection contient des éléments uniques
+ **@Url** - vérifie la validité de l'URL

## Contraintes du validateur Hiberate
+ **@CNPJ** - Numéro de registre des contribuables des sociétés brésiliennes
+ **@CPF** - Numéro de registre des contribuables individuels brésiliens
+ **@TituloEleitoral** - Numéro d'électeur brésilien
+ **@NIP** - ID VAR polonais
+ **@PESEL** - Numéro de validation national polonais
+ **@REGON** - ID de contribuable polonais

## Validation et Spring Framework
+ Spring Framework offre un support robuste pour Validation des haricots
+ La prise en charge de la validation peut être utilisée dans les contrôleurs, les services et d'autres composants gérés par Spring
+ L'accent sera mis dans ce cours sur la prise en charge dans **Spring Data JPA**
+ Les entités annotées seront validées avant les opérations de persistance
+ Une exception d'exécution est levée s'il y a une erreur de contrainte de validation

## Spring Boot et validation
+ Spring Boot configurera automatiquement la validation lorsque l'implémentation de validation est trouvée sur le chemin de classe
  + Si l'API est uniquement sur le chemin de classe (sans implémentation), vous pouvez utiliser les annotations, MAIS la validation ne se produira **PAS**
+ Avant Spring Boot 2.3, la validation était incluse dans les dépendances de démarrage
  + Après Spring Boot 2.3, vous devez inclure le démarreur de validation Spring Boot

## Que valider ?
+ En général, les contraintes de validation doivent refléter les contraintes de la base de données
  + Les erreurs de contrainte de validation sont beaucoup plus conviviales que les erreurs de contrainte de base de données
  + De plus, vous recevrez des informations sur toutes les erreurs de contrainte (par rapport à la base de données qui n'est que la première erreur)
+ Si une chaîne de base de données a une longueur maximale de 50, l'entité doit également refléter cela
+ Utilisez **@NonEmpty ou **@NonBlank** pour les propriétés de chaîne requises - un espace est une chaîne valide
+ En général, NE validez PAS les propriétés gérées par Hibernate
  + Par exemple, exiger une propriété d'ID gérée par la base de données ou une propriété de version peut entraîner des erreurs



## Quand et où valider ?
+ Quand et où valider les données ?
+ L'accent a bien sûr été mis sur la validation préalable à la persistance
+ Envisagez une **application à plusieurs niveaux**
  + **Front-end ReactJS**, **API Spring Boot RESTFul**, **JPA Spring Data**
  + **Validation des entrées utilisateurs**, **Validation API**, **Validation de la persistance**

## Quand et où valider ?
+ Principe général : valider tôt et échouer tôt. Ensuite, faire confiance, mais vérifier
+ Validation des entrées utilisateurs
  + Valider toutes les entrées utilisateur, fournir des commentaires riches à l'utilisateur final
+ Validation API : valider les données, fournir des commentaires riches - HTTP 400 : mauvaise requête
+ Validation de la persistance : dernière ligne de défense, valider les données, fournir des données riches en cas d'exception
  + Doit être HTTP 400, mais peut être HTTP 500
+ Les échecs précoces sont plus conviviaux pour le consommateur