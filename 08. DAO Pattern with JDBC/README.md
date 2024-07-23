# Modèle DAO Java

## Modèle DAO Java
+ **DAO** - Objet d'accès aux données
+ **Pattern** était un **précurseur** de **JPA**, avant que les ORM ne deviennent populaires
+ Plus ancien et utilise JDBC pour l'accès aux données.
+ Commun à voir dans les applications J2EE héritées
+ Bien que son utilisation ne soit plus courante, c'est un bon moyen d'utiliser JDBC.
+ Très similaire au modèle de référentiel utilisé par Spring Data

# Modèle DAO Java
+ **Modèle DAO** : le but est d'isoler les opérations de persistance de la couche d'application.
+ Par exemple, lorsque l'application doit conserver un objet, elle ne devrait pas avoir besoin de comprendre la technologie de persistance sous-jacente.
+ **Classe de domaine** – POJO simples, identiques aux entités JPA.
  + REMARQUE : le modèle DAO n'utilisera pas les annotations JPA.
+ **API DAO** – Fournit une interface pour les opérations CRUD (similaire au référentiel)
+ **Implémentation de DAO** - Implémentation de la fonctionnalité de persistance

# Modèle DAO Java
![Modèle DAO Java](images/image1.jpeg)

# Spring BootJDBC
+ **Connexion à la base de données** : généralement avec JDBC, vous devez créer et gérer la connexion à la base de données.
+ Spring Boot configurera automatiquement la connexion à la base de données pour nous
+ Les composants de connexion à la base de données sont disponibles sous forme de Spring Beans dans le contexte Spring.

# À venir dans le cours
+ **JDBC DAO** – Créer une couche DAO à l'aide de JDBC uniquement – abordé dans cette section.
+ **Modèle JDBC** – Spring Framework fournit une abstraction d'assistance sur JDBC.
+ **Modèle DAO JDBC** – à traiter dans la section suivante du cours.

---

L'initialisation de la base de données avec Flyway présente plusieurs avantages par rapport à l'utilisation de Hibernate. Voici quelques raisons pour lesquelles Flyway est souvent préféré :

### 1. **Gestion des versions de schéma**

Flyway permet de versionner les scripts de migration de base de données, ce qui est essentiel pour le contrôle des versions. Chaque modification du schéma est enregistrée avec un numéro de version, permettant de suivre l'historique des modifications.

**Exemple** :
```plaintext
V1__init.sql
V2__add_new_table.sql
V3__modify_column.sql
```
Cela permet une migration cohérente et ordonnée des schémas, évitant les conflits et les incohérences.

### 2. **Reproductibilité et contrôles de qualité**

Avec Flyway, les migrations de la base de données sont explicitement définies dans des scripts SQL ou des fichiers Java, assurant une reproductibilité fiable. Cela permet de garantir que les schémas de base de données sont les mêmes dans tous les environnements (développement, test, production).

### 3. **Portabilité**

Les scripts Flyway peuvent être utilisés avec plusieurs bases de données sans modification majeure, ce qui rend les migrations portables. Hibernate est souvent étroitement couplé à une implémentation spécifique de base de données, ce qui peut poser des problèmes de portabilité.

### 4. **Contrôle granulaire**

Flyway offre un contrôle granulaire sur les migrations, permettant d'écrire des migrations complexes et d'exécuter des scripts spécifiques selon les besoins. Cela inclut la possibilité de corriger manuellement les migrations échouées et de gérer des migrations en production de manière plus fine.

### 5. **Indépendance des outils ORM**

Flyway fonctionne indépendamment des ORM (Object-Relational Mapping) comme Hibernate. Cela signifie que les changements de schéma sont effectués directement sur la base de données, sans dépendre des outils spécifiques à l'ORM. Cela peut être utile si vous changez d'ORM ou si vous utilisez plusieurs ORM dans le même projet.

### 6. **Facilité d'intégration avec CI/CD**

Flyway s'intègre facilement avec les pipelines d'intégration continue et de déploiement continu (CI/CD), permettant l'automatisation des migrations de base de données. Cela améliore l'efficacité des déploiements et assure la synchronisation des bases de données avec les versions du code.

### 7. **Support pour les migrations conditionnelles**

Flyway permet des migrations conditionnelles basées sur l'état actuel de la base de données, ce qui est utile pour gérer des environnements avec des configurations de base de données différentes.

### Comparaison rapide

| **Critère**                    | **Flyway**                                | **Hibernate**                               |
|-------------------------------|-------------------------------------------|--------------------------------------------|
| **Gestion des versions**      | Versioning explicite des migrations       | Pas de gestion native des versions         |
| **Reproductibilité**          | Scripts explicites, facilement reproductibles | Dépend du mapping ORM                      |
| **Portabilité**               | Hautement portable entre bases de données | Peut nécessiter des ajustements spécifiques|
| **Contrôle granulaire**       | Haut contrôle sur les migrations          | Contrôle limité aux configurations ORM     |
| **Indépendance des outils**   | Indépendant des ORM                       | Couplé à l'ORM utilisé                     |
| **Intégration CI/CD**         | Facile à intégrer                         | Peut nécessiter des scripts supplémentaires|
| **Migrations conditionnelles**| Supporté                                  | Non supporté                               |

En conclusion, Flyway offre une solution plus robuste, flexible et portable pour la gestion des schémas de base de données par rapport à l'initialisation automatique de Hibernate. Cela permet une meilleure gestion des versions, une reproductibilité fiable et une intégration fluide avec les pipelines de déploiement, ce qui en fait un choix préféré pour de nombreux projets.


----

Les instructions préparées, ou requêtes préparées, sont des fonctionnalités offertes par de nombreux systèmes de gestion de bases de données (SGBD) qui permettent d'exécuter des requêtes SQL de manière plus sécurisée et efficace. Voici quelques raisons pour lesquelles les instructions préparées sont recommandées :

### 1. Sécurité : Prévention des attaques par injection SQL

L'une des principales raisons d'utiliser des instructions préparées est de protéger contre les attaques par injection SQL. L'injection SQL se produit lorsque des attaquants exploitent des vulnérabilités dans les applications pour exécuter du code SQL malveillant.

Avec les instructions préparées, les paramètres des requêtes sont traités séparément de la logique SQL. Ainsi, même si un attaquant tente d'injecter du code SQL malveillant dans un champ de saisie, ce code sera traité comme une simple chaîne de caractères, empêchant ainsi l'exécution d'instructions malveillantes.

### 2. Performance : Amélioration de l'efficacité des requêtes

Les instructions préparées peuvent améliorer les performances de vos applications de plusieurs manières :

- **Réutilisation des plans d'exécution** : Lorsque vous préparez une instruction SQL, le SGBD analyse et compile la requête une seule fois, puis réutilise ce plan d'exécution pour chaque exécution ultérieure de la requête avec différents paramètres. Cela réduit la surcharge de compilation répétée pour des requêtes similaires.
- **Optimisation du cache** : Les instructions préparées permettent au SGBD de mieux gérer le cache des plans d'exécution, ce qui peut entraîner une utilisation plus efficace des ressources système.

### 3. Lisibilité et Maintenabilité du Code

Les instructions préparées rendent le code plus lisible et plus facile à maintenir. En séparant la logique SQL des données, il est plus facile de comprendre et de modifier les requêtes SQL dans votre application.

### 4. Gestion des Paramètres

Avec les instructions préparées, les développeurs peuvent facilement gérer les paramètres et les types de données. Cela garantit que les valeurs insérées dans les requêtes sont correctement formatées et typées, réduisant ainsi les erreurs potentielles liées aux types de données incorrects.

### Exemple en Java avec JDBC

Voici un exemple simple d'utilisation d'instructions préparées en Java avec JDBC :

```java
String query = "SELECT * FROM users WHERE username = ? AND password = ?";
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(query)) {

    // Définir les paramètres
    pstmt.setString(1, "johndoe");
    pstmt.setString(2, "password123");

    // Exécuter la requête
    try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            System.out.println("User ID: " + rs.getInt("id"));
            System.out.println("Username: " + rs.getString("username"));
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

### Conclusion

Utiliser des instructions préparées est une pratique de développement recommandée pour plusieurs raisons :

- **Sécurité** : Protection contre les injections SQL.
- **Performance** : Optimisation des ressources et amélioration de la vitesse d'exécution des requêtes.
- **Lisibilité** : Code plus propre et plus facile à comprendre.
- **Gestion des paramètres** : Meilleure gestion des types de données et des valeurs des paramètres.

En adoptant les instructions préparées, vous améliorez non seulement la sécurité et les performances de vos applications, mais aussi la qualité et la maintenabilité de votre code.

---

Le modèle DAO (Data Access Object) est un motif de conception utilisé pour séparer la logique métier de la logique d'accès aux données. Cette séparation offre plusieurs avantages significatifs pour le développement logiciel, notamment en termes de maintenabilité, de testabilité et de flexibilité. Voici les principales raisons d'utiliser le modèle DAO :

### 1. Séparation des responsabilités

En utilisant le modèle DAO, vous séparez clairement la logique d'accès aux données (DAO) de la logique métier. Cela permet à chaque couche de se concentrer sur une responsabilité spécifique :

- **DAO** : Gère les interactions avec la base de données, y compris les opérations CRUD (Create, Read, Update, Delete).
- **Logique métier** : Contient la logique applicative et les règles métier, sans se préoccuper des détails de l'accès aux données.

### 2. Facilitation des tests unitaires

Le modèle DAO permet de tester indépendamment la logique métier et la logique d'accès aux données. Vous pouvez facilement remplacer les implémentations DAO par des mocks ou des stubs lors des tests unitaires, ce qui simplifie le test des composants métier sans nécessiter de connexion à une base de données réelle.

### 3. Réutilisabilité

Les classes DAO peuvent être réutilisées dans différentes parties de l'application ou même dans d'autres applications. Une fois que vous avez une implémentation DAO pour une entité, vous pouvez l'utiliser partout où vous avez besoin d'interagir avec cette entité dans la base de données.

### 4. Maintenabilité

Le modèle DAO rend le code plus facile à maintenir et à étendre. Si vous avez besoin de modifier la façon dont vous accédez aux données, vous n'avez qu'à modifier le DAO correspondant sans affecter la logique métier. Cette isolation facilite les mises à jour et les corrections de bogues.

### 5. Abstraction

DAO fournit une couche d'abstraction entre l'application et les opérations de la base de données. Cela signifie que les détails de l'implémentation de la base de données (par exemple, SQL spécifique au SGBD) sont cachés derrière les interfaces DAO, ce qui permet de changer la base de données sous-jacente sans affecter le reste de l'application.

### 6. Gestion des transactions

Le modèle DAO permet de centraliser la gestion des transactions, facilitant ainsi le contrôle des transactions dans l'application. Vous pouvez gérer les débuts, les commits et les rollbacks des transactions au sein des méthodes DAO, assurant une gestion cohérente et centralisée des transactions.

### Exemple en Java

Voici un exemple simplifié d'implémentation du modèle DAO en Java :

**Interface DAO pour une entité User :**

```java
public interface UserDAO {
    void insertUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
```

**Implémentation de l'interface UserDAO :**

```java
public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertUser(User user) {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

**Utilisation dans la logique métier :**

```java
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) {
        userDAO.insertUser(user);
    }

    public User getUserDetails(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> listAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
```

### Conclusion

Le modèle DAO est une pratique de développement couramment adoptée pour améliorer la structure, la maintenabilité et la testabilité du code. En séparant la logique d'accès aux données de la logique métier, il facilite les modifications, la réutilisation et la gestion des données, tout en permettant des tests plus efficaces et une meilleure organisation du code.

---

Après avoir défini les services métiers dans une application utilisant le modèle DAO, vous devez généralement les intégrer dans les couches supérieures de l'application, telles que les contrôleurs (ou servlets dans une application web Java), et parfois dans les vues si vous suivez le modèle MVC (Modèle-Vue-Contrôleur). Voici une vue d'ensemble des étapes suivantes :

### 1. Intégration dans les contrôleurs

Les contrôleurs sont responsables de gérer les requêtes entrantes, d'appeler les services métiers appropriés et de retourner les réponses aux clients. Dans une application web Java, par exemple, vous pourriez utiliser des servlets ou des frameworks MVC comme Spring MVC pour ce faire.

**Exemple de contrôleur avec Spring MVC :**

```java
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUserDetails(id);
        model.addAttribute("user", user);
        return "userDetails"; // view name
    }

    @GetMapping
    public String listAllUsers(Model model) {
        List<User> users = userService.listAllUsers();
        model.addAttribute("users", users);
        return "userList"; // view name
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
```

### 2. Configuration de la vue

Les vues sont responsables de la présentation des données à l'utilisateur. Dans le contexte d'une application web Java, cela pourrait être des JSP, des Thymeleaf templates, ou d'autres technologies de rendu HTML.

**Exemple de vue avec JSP :**

```jsp
<!-- userDetails.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>
    <h1>User Details</h1>
    <p>ID: ${user.id}</p>
    <p>Name: ${user.name}</p>
    <p>Email: ${user.email}</p>
    <a href="/users">Back to list</a>
</body>
</html>
```

### 3. Sécurité et gestion des sessions

Intégrez la sécurité pour protéger votre application contre les accès non autorisés. Dans une application Java, vous pouvez utiliser Spring Security pour gérer l'authentification et l'autorisation.

**Exemple de configuration Spring Security :**

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}
```

### 4. Gestion des transactions

Assurez-vous que les opérations de votre service métier sont transactionnelles, surtout si elles impliquent plusieurs opérations DAO. En utilisant Spring, cela peut être facilement configuré avec les annotations @Transactional.

**Exemple d'annotation transactionnelle :**

```java
@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void registerUser(User user) {
        userDAO.insertUser(user);
    }

    @Transactional(readOnly = true)
    public User getUserDetails(int id) {
        return userDAO.getUserById(id);
    }

    @Transactional(readOnly = true)
    public List<User> listAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
```

### 5. Tests d'intégration et de bout en bout

Après avoir intégré les contrôleurs et les vues, effectuez des tests d'intégration et de bout en bout pour vous assurer que toutes les couches de l'application fonctionnent correctement ensemble.

**Exemple de test d'intégration avec Spring Boot :**

```java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("userDetails"));
    }

    // Autres tests...
}
```

### Conclusion

En suivant ces étapes, vous pouvez construire une application bien structurée et maintenable qui sépare clairement les responsabilités, facilite les tests et offre une meilleure flexibilité et extensibilité. La clé est de maintenir une séparation claire entre les différentes couches de l'application et de s'assurer que chaque couche est bien testée et sécurisée.

