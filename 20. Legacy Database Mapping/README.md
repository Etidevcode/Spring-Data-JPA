## Présentation du mappage de base de données héritée
+ **Base de données héritée** - Une base de données plus ancienne, initialement non écrite pour être utilisée avec JPA
+ JPA a la capacité de nommer des tables et des colonnes
  + Jusqu'à présent dans le cours, nous avons suivi les conventions de dénomination par défaut
  + firstName = FIRST_NAME
+ Wordpress est un bon exemple de base de données héritée - conçue pour une application PHP
+ Nous utiliserons le schéma de base de données Wordpress pour explorer les fonctionnalités de mappage de base de données JPA

## Exigences
+ Le projet utilisera Spring Framework 6 et Spring Boot 3
+ Java 17 ou supérieur requis
+ JPA 3.0 est désormais utilisé
  + JPA 2.x utilise le package javax.persistence.*
  + JPA3.0 utilise le package jakarta.persistence.*