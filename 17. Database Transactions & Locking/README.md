# Transactions de base de données

## Transactions de base de données SQL - ACID
+ **ACID**
  + **Atomicité** - Toutes les opérations sont terminées avec succès ou la base de données est renvoyée à l'état précédent.
  + **Cohérence** - Les opérations ne violent pas les contraintes d'intégrité du système.
  + **Isolé** - Les résultats sont indépendants des transactions simultanées.
  + **Durable** - Les résultats sont rendus persistants en cas de défaillance du système (c'est-à-dire écrits sur le disque)

## Termes importants
+ **Transaction** - Une unité de travail. Une ou plusieurs opérations SQL
  + Généralement des instructions **DML** (et **non DDL**) qui modifient les données.
  + Peut être une seule ; peut être des centaines ou des milliers.
+ **Commit/Validation** - Indique la fin de la transaction et indique à la base de données de rendre les modifications permanentes.
  + Il est plus efficace d'effectuer plusieurs opérations dans une transaction. Les validations ont un « coût ».
+ **Rollback** - Annuler toutes les modifications de la transaction
+ **Save Point/Point de sauvegarde** - Point programmatique que vous pouvez définir, qui vous permet de revenir en arrière (c'est-à-dire de revenir en arrière sur une partie d'une transaction)

## Verrous de base de données
+ La base de données verrouille les enregistrements (dans certains cas la table entière ou la base de données) pour empêcher d'autres processus de modifier les données
  + Conformité ACID
+ Dans une transaction, les instructions DML suivantes verrouillent les enregistrements des lignes affectées :
  + *SELECT FOR UPDATE; UPDATE; DELETE*
+ Pendant les transactions, d'autres sessions tentant de modifier des enregistrements verrouillés attendent par défaut que le verrou soit libéré. ​​(c'est-à-dire que de manière interactive, il semblera que les choses se bloquent)
+ Blocage - Se produit lorsque deux transactions se verrouillent mutuellement et ne peuvent jamais se terminer.
  + Les deux échouent et reviennent en arrière.

## Niveaux d'isolement des transactions
+ **Lecture répétable/Repeatable Read** - Niveau d'isolement par défaut. Votre instruction reçoit une vue cohérente de la base de données, même si d'autres transactions sont validées pendant votre transaction.
  + Votre transaction obtient un instantané des données, qui ne change pas.
+ **Lecture validée/Read Committed** - Les lectures au sein de votre transaction recevront un nouvel instantané des données.
+ **Lecture non validée/Read Uncommitted** - Les lectures ne sont pas cohérentes, mais peuvent éviter des verrous de base de données supplémentaires.
  + également appelé « lecture sale »
+ **Sérialisable/Serializable** - Similaire à la lecture répétable, mais peut verrouiller les lignes sélectionnées dans la transaction.

## Concepts pragmatiques à retenir
+ En utilisant le niveau d'isolement de transaction par défaut, votre transaction voit un instantané de la base de données telle qu'elle est au début de la transaction.
  + Les modifications apportées dans d'autres sessions et validées **NE SERONT PAS** visibles
  + Les modifications apportées par votre session **NE SERONT PAS** visibles pour les autres sessions jusqu'à la validation
+ La plupart des SGBDR modernes font du bon travail en matière de conformité ACID
  + La prise en charge d'ACID avec une base de données NoSQL varie considérablement selon le fournisseur
  + La conformité ACID est complexe et coûteuse - d'où les performances élevées des bases de données NoSQL

## La mise à jour « perdue »
+ L'enregistrement de la base de données a une quantité de 10
+ La session A lit 10, ajoute 5, ce qui donne une quantité de 15, l'enregistrement de la base de données est verrouillé pendant la mise à jour
+ La session B lit 10, ajoute 5, ce qui donne une quantité de 15, mais est bloquée par le verrou de la session A
  + Ce serait 20, si la session B pouvait voir la modification non validée
+ La session A valide l'enregistrement, libérant le verrou. L'enregistrement de la base de données est mis à jour à 15
+ La session B est libérée, met à jour l'enregistrement de la base de données à 15
  + Ainsi, la mise à jour de la session A est « perdue »

## Modes de verrouillage JDBC
+ Les pilotes JDBC prennent en charge plusieurs modes de verrouillage différents
+ Le mode s'applique à la durée de vie de la connexion
+ La configuration dépend **fortement** du fournisseur
+ Rarement utilisé dans la pratique.
+ JPA/Hibernate est généralement privilégié

## Verrouillage JPA
+ **Verrouillage pessimiste**
  + Les mécanismes de base de données sont utilisés pour verrouiller les enregistrements pour les mises à jour
  + Les capacités varient considérablement en fonction de la base de données et de la version du pilote JDBC utilisé
  + La version la plus simple est « SELECT FOR UPDATE… » - Verrouille une ou plusieurs lignes jusqu'à ce que la validation ou la restauration soit émise
+ **Verrouillage optimiste**
  + Effectué en vérifiant un attribut de version de l'entité

## Verrouillage JPA - Lequel utiliser ???
+ **Avez-vous besoin d'un verrouillage ?**
  + Votre application aura-t-elle des mises à jour simultanées des mêmes enregistrements ???
+ **Verrouillage pessimiste**
  + À utiliser si les données sont fréquemment mises à jour et si elles sont mises à jour simultanément
  + N'oubliez pas que l'exécution du verrouillage a un coût
+ **Verrouillage optimiste**
  + À utiliser si les données sont lues plus souvent que mises à jour
  + La majorité des applications utiliseront le verrouillage optimiste

## Conversations multi-requêtes
+ **Conversation multi-requêtes** - Se produit dans les applications de formulaire Web, ou éventuellement **RESTful** également, où la logique de mise à jour s'étend sur une ou plusieurs requêtes, laissant ainsi une fenêtre de temps plus large.
+ Le verrouillage pessimiste est très rapide, quelques millisecondes. Il ne protège contre les conflits qu'au moment de l'écriture.
+ Le verrouillage optimiste fournit un mécanisme permettant de détecter les données obsolètes sur une période plus longue (c'est-à-dire plusieurs requêtes)

## Verrouillage pessimiste JPA
+ **Modes de verrouillage pessimistes**
  + **PESSIMISTIC_READ** - utilise un verrou partagé, empêche la mise à jour ou la suppression des données
  + **PESSIMISTIC_WRITE** - utilise un verrou exclusif, empêche la lecture (dans certains niveaux d'isolement), la mise à jour ou la suppression des données
  + **PESSIMISTIC_FORCE_INCREMENT** - utilise un verrou exclusif, incrémente la propriété de version de l'entité
+ La plupart des bases de données prennent en charge PESSIMISTIC_WRITE, c'est l'option que vous utiliserez généralement
  + Utilisez **PESSIMISTIC_FORCE_INCREMENT** si l'entité a une propriété de version

## Verrouillage optimiste JPA
+ Utilise une propriété de version, qui est incrémentée à chaque mise à jour
+ Peut être de type int, Integer, long, Long, short, Short ou java.sql.Timestamp
  + Le plus courant est Integer
+ Avant une mise à jour, Hibernate lira l'enregistrement de base de données correspondant. Si la version ne correspond pas, une exception est levée
+ **Inconvénients :**
  + Les mises à jour en dehors de JPA/Hibernate qui ne mettent pas à jour la propriété de version interrompront cette opération
  + Performances : lecture avant chaque mise à jour

## Modes de verrouillage optimiste JPA
+ **OPTIMISTIC** : obtient un verrou de lecture optimiste pour toutes les entités avec l'attribut de version
+ **OPTIMISTIC_FORCE_INCREMENT** : identique à **OPTIMISTIC, mais incrémente la valeur de la version
+ **READ** : **JPA 1.x**, identique à **OPTIMISTIC**
+ **WRITE** : **JPA 1.x**, identique à **OPTIMISTIC_FORCE_INCREMENT**