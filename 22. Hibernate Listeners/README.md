## Hibernate Listeners

### Introduction

Hibernate est un framework ORM (Object-Relational Mapping) qui facilite les interactions entre les applications Java et les bases de données relationnelles. Les Listeners Hibernate permettent d'intercepter et de réagir aux événements du cycle de vie des entités, comme les insertions, les mises à jour ou les suppressions. Ces Listeners sont des outils puissants pour ajouter des comportements personnalisés lors de ces événements.

### Create Hibernate Listeners

Pour créer un Listener Hibernate, il faut implémenter une ou plusieurs interfaces spécifiques fournies par Hibernate. Chaque interface correspond à un type d'événement particulier :

- `PreInsertEventListener`
- `PostInsertEventListener`
- `PreUpdateEventListener`
- `PostUpdateEventListener`
- `PreDeleteEventListener`
- `PostDeleteEventListener`
- `LoadEventListener`

Par exemple, pour créer un Listener qui réagit avant l'insertion d'une entité :
```java
public class MyPreInsertEventListener implements PreInsertEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        // Logique à exécuter avant l'insertion de l'entité
        return false;
    }
}
```

### Register Hibernate Listeners

Pour que les Listeners soient actifs, ils doivent être enregistrés auprès de la session ou de la configuration Hibernate. Cela peut se faire de différentes manières, notamment via la configuration XML ou via la configuration par code.

**Exemple de configuration par code :**
```java
Configuration configuration = new Configuration();
configuration.getEventListeners().setPreInsertEventListeners(new PreInsertEventListener[] { new MyPreInsertEventListener() });
SessionFactory sessionFactory = configuration.buildSessionFactory();
```

**Exemple de configuration XML :**
```xml
<hibernate-configuration>
    <session-factory>
        <event type="pre-insert">
            <listener class="com.example.MyPreInsertEventListener"/>
        </event>
    </session-factory>
</hibernate-configuration>
```

### Implement Hibernate Encryption Listeners

Un cas d'utilisation pratique des Listeners Hibernate est l'implémentation de la logique de chiffrement et de déchiffrement pour les champs sensibles des entités. Par exemple, vous pouvez vouloir chiffrer des données avant de les insérer ou les mettre à jour, et les déchiffrer après les avoir chargées depuis la base de données.

**Exemple :**
```java
public class EncryptionListener implements PreInsertEventListener, PreUpdateEventListener, PostLoadEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Object entity = event.getEntity();
        // Logique de chiffrement avant l'insertion
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Object entity = event.getEntity();
        // Logique de chiffrement avant la mise à jour
        return false;
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        Object entity = event.getEntity();
        // Logique de déchiffrement après le chargement
    }
}
```

### Tableaux des Méthodes de Listeners Hibernate

| Interface               | Méthode               | Description                                                     |
|-------------------------|-----------------------|-----------------------------------------------------------------|
| `PreInsertEventListener`| `onPreInsert`         | Appelée avant l'insertion d'une entité                          |
| `PostInsertEventListener`| `onPostInsert`       | Appelée après l'insertion d'une entité                          |
| `PreUpdateEventListener` | `onPreUpdate`        | Appelée avant la mise à jour d'une entité                       |
| `PostUpdateEventListener`| `onPostUpdate`       | Appelée après la mise à jour d'une entité                       |
| `PreDeleteEventListener` | `onPreDelete`        | Appelée avant la suppression d'une entité                       |
| `PostDeleteEventListener`| `onPostDelete`       | Appelée après la suppression d'une entité                       |
| `LoadEventListener`      | `onPostLoad`         | Appelée après le chargement d'une entité                        |

Ces interfaces et méthodes de callback permettent d'ajouter des logiques personnalisées aux différentes étapes du cycle de vie des entités Hibernate, facilitant ainsi des opérations comme le chiffrement, la validation, l'audit, etc.