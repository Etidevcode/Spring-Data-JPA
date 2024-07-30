## Intercepteurs et auditeurs Hibernate
+ Les intercepteurs et les auditeurs peuvent être utilisés ensemble
+ Chevauchement important des fonctionnalités
+ Les deux seront appliqués à toutes les entités
+ Les auditeurs doivent être considérés comme sans état
+ Partagés entre les requêtes et ne doivent PAS enregistrer l'état

## Intercepteurs Hibernate
+ Les intercepteurs Hibernate sont des rappels enregistrés dans les opérations internes d'Hibernate
  + Peuvent être définis par une session ou une fabrique de sessions
  + Peuvent être utilisés pour la sécurité, l'audit ou pour modifier des données
  + Implémentés via :
    + Interface **org.hibernate.Interceptor**
    + Classe **org.hibernate.EmptyInterceptor**
      + Obsolète dans Hibernate 6.x - migration vers les méthodes d'interface par défaut

## Écouteurs Hibernate
+ Hibernate dispose d'un système d'événements
+ Les écouteurs d'événements Hibernate s'abonnent aux événements Hibernate
+ Les événements Hibernate sont définis par org.hibernate.event.spi.EventType
  + En gros 36 types d'événements définis - SAVE, PRE_INSERT, POST_INSERT, etc.
+ Chaque type définit un écouteur par défaut, qui peut être étendu pour définir un écouteur personnalisé
+ Une fois créé, l'écouteur client doit être enregistré auprès de la SessionFactory Hibernate

## Rappels JPA
+ JPA définit 7 rappels via des annotations
+ Les rappels sont spécifiques à l'entité et peuvent être :
  + Sur une méthode d'entité
  + Ou sur une classe d'écouteur d'entité
    + Les écouteurs d'entité doivent :
      + Être sans état et avoir un constructeur sans argument
      + Les méthodes doivent renvoyer void
      + Ne pas utiliser **EntityManager**

## Rappels JPA
+ **@PrePersist** - Avant l'opération de persistance
+ **@PreRemove** - Avant l'opération de suppression
+ **@PostPersist** - Une fois l'opération de persistance terminée
+ **@PostRemove** - Une fois l'opération de suppression terminée
+ **@PreUpdate** - Avant l'opération de mise à jour
+ **@PostUpdate** - Après l'opération de mise à jour
+ **@PostLoad** - Une fois l'entité chargée ou rafraîchi