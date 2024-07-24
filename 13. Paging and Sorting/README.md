# Pagination et tri

## Présentation de la pagination et du tri
+ **Paging** - Est un moyen d'obtenir une « page » de données à partir d'une longue liste de valeurs
  + Par exemple, page trois sur 100 enregistrements
    + Commun dans les résultats de recherche ou les catalogues des sites Web
+ **Sorting** – Comment les données sont ordonnées
  + Peut être naturel (la commande est sortie de la base de données)
  + Ou par une ou plusieurs colonnes

## Pagination SQL
+ **pagination SQL** – utilise des clauses SQL de limite et de décalage.
+ **Limit** - Limite le nombre de lignes renvoyées
+ **Offset/Décalage** - Nombre de lignes à ignorer
+ Exemple 30 enregistrements, 10 par page
  + Page 1 - Limite 10, décalage 0
  + Page 2 - Limite 10, décalage 10
  + Page 3 - Limite 10, décalage 20

## Tri SQL
+ **Tri SQL** – utilise la clause SQL triée par ordre
  + **ASC** - (par défaut) Ordre croissant
  + **Desc** - Décroissant
+ **Ordre physique** - Lorsqu'aucune clause de tri n'est fournie. Quel que soit l'ordre dans lequel les enregistrements sont stockés dans la base de données.
  + Renvoie souvent les lignes dans le même ordre, MAIS cela **n'est pas garanti**

## Performances de pagination et de tri
+ Serait-il plus rapide d'effectuer la pagination et le tri sur le serveur d'applications ?
  + En général, NON
  + Les bases de données modernes sont TRÈS efficaces en termes de pagination et de tri.
  + De plus, il faut transférer de grands ensembles de données entre le serveur de base de données et le serveur d'applications.
  + Il existe des cas extrêmes où le déchargement de la charge de travail de calcul est judicieux.