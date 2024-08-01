## Lecture: Callbacks JPA

### I) Introduction

Java Persistence API (JPA) fournit un mécanisme puissant pour interagir avec des bases de données relationnelles en utilisant des objets Java. Une des fonctionnalités avancées de JPA est la possibilité de définir des callbacks, qui permettent aux développeurs d'intégrer des logiques spécifiques aux événements du cycle de vie d'une entité. Ces callbacks peuvent être utilisés pour exécuter une logique spécifique à des moments prédéfinis du cycle de vie de l'entité, tels qu'avant ou après les opérations d'insertion, de mise à jour ou de suppression.

### II) Callback JPA sur les Méthodes d'Entité

JPA permet de définir des méthodes de callback directement dans la classe de l'entité. Ces méthodes peuvent être annotées avec des annotations spécifiques pour indiquer quand elles doivent être déclenchées :

- `@PrePersist` : Exécuté avant qu'une entité ne soit persistée (ajoutée à la base de données).
- `@PostPersist` : Exécuté après qu'une entité a été persistée.
- `@PreUpdate` : Exécuté avant qu'une entité ne soit mise à jour.
- `@PostUpdate` : Exécuté après qu'une entité a été mise à jour.
- `@PreRemove` : Exécuté avant qu'une entité ne soit supprimée.
- `@PostRemove` : Exécuté après qu'une entité a été supprimée.
- `@PostLoad` : Exécuté après qu'une entité a été chargée depuis la base de données.

Exemple :
```java
@Entity
public class MonEntite {
    @PrePersist
    public void prePersist() {
        // Logique à exécuter avant la persistance de l'entité
    }

    @PostLoad
    public void postLoad() {
        // Logique à exécuter après le chargement de l'entité
    }
}
```

### III) Listener de Callback JPA

En plus de définir des callbacks directement dans l'entité, JPA supporte également l'utilisation de listeners d'entité. Ce sont des classes séparées qui contiennent des méthodes de callback. Les listeners d'entité sont associés aux entités via l'annotation `@EntityListeners`.

Exemple :
```java
@EntityListeners(MonEntiteListener.class)
@Entity
public class MonEntite {
    // Champs et méthodes de l'entité
}

public class MonEntiteListener {
    @PrePersist
    public void prePersist(MonEntite entite) {
        // Logique à exécuter avant la persistance de l'entité
    }

    @PostLoad
    public void postLoad(MonEntite entite) {
        // Logique à exécuter après le chargement de l'entité
    }
}
```

### IV) Helper de Contexte Spring

Dans une application Spring, il est courant de tirer parti du contexte de l'application Spring au sein des callbacks JPA. Cela peut être réalisé en créant une classe helper qui permet d'accéder aux beans Spring dans les listeners d'entité JPA.

Exemple :
```java
@Component
public class SpringContextHelper implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
```

Cette classe helper peut ensuite être utilisée dans les listeners d'entité pour accéder aux beans gérés par Spring.

### V) Implémentation de l'Encryption dans les Callbacks

Un cas d'utilisation pratique des callbacks JPA est d'implémenter une logique de chiffrement et de déchiffrement pour les champs sensibles des entités. Par exemple, vous pouvez vouloir chiffrer les données avant de les persister et les déchiffrer après les avoir chargées depuis la base de données.

Exemple :
```java
@Entity
public class EntiteSecurisee {
    private String donneesSensibles;

    @PrePersist
    @PreUpdate
    public void chiffrer() {
        this.donneesSensibles = UtilChiffrement.chiffrer(this.donneesSensibles);
    }

    @PostLoad
    @PostUpdate
    public void dechiffrer() {
        this.donneesSensibles = UtilChiffrement.dechiffrer(this.donneesSensibles);
    }
}
```

### VI) Convertisseurs JPA

JPA 2.1 a introduit le concept de convertisseurs, qui peuvent être utilisés pour convertir automatiquement entre la représentation de la colonne de la base de données et la représentation de l'attribut de l'entité. Les convertisseurs sont une alternative à l'utilisation des méthodes de callback pour certains types de logique de transformation.

Exemple :
```java
@Converter
public class ConvertisseurBooleanToString implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "O" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "O".equals(dbData);
    }
}
```

Pour utiliser le convertisseur, vous annotez l'attribut de l'entité avec `@Convert` :
```java
@Entity
public class MonEntite {
    @Convert(converter = ConvertisseurBooleanToString.class)
    private Boolean actif;
}
```

Cette approche offre un moyen propre et réutilisable de gérer la logique de conversion des attributs.

### Conclusion

Les callbacks et les convertisseurs JPA fournissent des mécanismes robustes pour gérer les événements du cycle de vie des entités et les transformations des attributs. En comprenant et en utilisant ces fonctionnalités, les développeurs peuvent s'assurer que leurs applications gèrent la persistance et le chargement des entités de manière propre, maintenable et sécurisée.

### Tableau des Méthodes de Callback JPA

| Méthode de Callback | Annotation        | Description                                                  |
|---------------------|-------------------|--------------------------------------------------------------|
| `prePersist`        | `@PrePersist`     | Exécuté avant que l'entité ne soit persistée.                |
| `postPersist`       | `@PostPersist`    | Exécuté après que l'entité a été persistée.                  |
| `preUpdate`         | `@PreUpdate`      | Exécuté avant que l'entité ne soit mise à jour.              |
| `postUpdate`        | `@PostUpdate`     | Exécuté après que l'entité a été mise à jour.                |
| `preRemove`         | `@PreRemove`      | Exécuté avant que l'entité ne soit supprimée.                |
| `postRemove`        | `@PostRemove`     | Exécuté après que l'entité a été supprimée.                  |
| `postLoad`          | `@PostLoad`       | Exécuté après que l'entité a été chargée depuis la base de données. |

Ces méthodes de callback permettent d'ajouter des logiques personnalisées aux différentes étapes du cycle de vie des entités JPA.