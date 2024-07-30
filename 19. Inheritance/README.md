## Héritage JPA
+ **L'héritage** est un concept orienté objet où les types peuvent hériter des propriétés et des comportements d'autres types
+ Le paradigme relationnel utilisé par les bases de données relationnelles ne prend pas directement en charge le concept d'héritage
+ Dans un contexte JPA, nous examinons comment les propriétés héritées peuvent être mappées dans le modèle relationnel de la base de données

## Stratégies d'héritage JPA
+ **MappedSuperclass** - Classe parent abstraite, le parent n'est pas dans la base de données
+ **Table par classe** - Similaire à MappedSuperClass, mais une table de base de données est attribuée à chaque type
+ **Table unique** - Une seule table est utilisée, possède des propriétés pour tous les types, utilise une valeur de discriminateur pour déterminer le type auquel la ligne de base de données est mappée
+ **Table jointe** - Chaque type a une table, les sous-types sont joints

## Héritage JPA Laquelle utiliser ?
+ **MappedSuperclass** - Idéal pour les propriétés d'entités communes
+ **Table par classe** - Meilleure option pour les performances, optimale pour la plupart des cas d'utilisation
+ **Table unique** - Idéal lorsque vous devez effectuer des requêtes polymorphes, mais que vous ne pouvez pas utiliser de contraintes non nulles sur les attributs de sous-classe et que cela peut entraîner des incohérences de données
+ **Table jointe** - Lorsque vous avez besoin de requêtes polymorphes et de cohérence des données (vous pouvez utiliser des contraintes non nulles sur les sous-types)