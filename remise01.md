# Matricule G53735

## Le modèle v0.1

### Énumération SquareType et Classe Square

Package `g53735.humbug.model` : OK

Javadoc mise à jour : OK

### Énumération Direction

##### Littéraux

Aucune remarque.

#### Attributs

Aucune remarque.

#### Méthodes

Pourquoi les attributs ont des mutateurs ?

### Classe Position

#### Généralités

##### Documentation

##### Tests unitaires 

Valides et suffisants.

#### Attributs

Aucune remarque.

#### Méthodes

##### `equals` et `hascode`

Aucune remarque.

##### Méthode `next(Direction)`

Aucune remarque.

### Classe Board

#### Généralités

##### Documentation

##### Tests unitaires 

Valides et suffisants.

#### Attributs

L'attribut peut être déclaré `final`.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `getInitialBoard()`

Aucune remarque.

##### Méthode `isInside(Position)`

Le code peut être simplifié. Chaque passage dans le `if/else`modifie la valeur d'un booléen pour au final retourné ce booléen. On peut écrire uniquement un `return`.

##### Méthode `getSquareType(Position)`

Aucune remarque.

##### Méthode `getNbRow()` `getNbColumn()`

Aucune remarque.

##### Méthode `setSquareType`

Ton code sait-il gérer si le `SquareType` passé en paramètre est `null` ?

### Classe View

#### Généralités

##### Documentation

Javadoc de l'interface incomplète, les méthodes ne sont pas documentées.

#### Attributs

Un attribut de type `Scanner` éviterai l’instanciation dans les méthodes de la classe. 

#### Méthodes

##### Constructeur

Utilisation du constructeur par défaut. Aucune remarque..

##### Méthode `displayBoard(Board board)`

Cette méthode doit être divisée en sous méthode afin d'améliorer sa lisibilité. On peut imaginer par exemple une décomposition utilisant des méthodes dédiées à chaque parties à afficher: 

- `displayHeader`
- `displayRow`
- `displaySquare`

Dans l'état actuel elle est très difficile à lire/modifier. 

##### Méthode `displayError(String message)`

Aucune remarque.

##### Méthode `askPosition()`

Aucune remarque.

##### Méthode `askDirection()`

Aucune remarque.

##### Méthode `cardinalDirection`

L'utilisation du `switch` peut être simplifiée.Par exemple tu peux écrire : 

```java
 switch (direction) {
            case "N":
            case "NORTH":
                return Direction.NORTH;
            case "E":
            case "EAST":
                return  Direction.EAST;    
         ... 
```



## Le modèle v0.2

### Classe Abstraite Animal

#### Généralités

##### Documentation

#### Attributs

Quel est l'impact de changer la visibilité des attributs de `private` vers `protected` ?

Est-ce un changement que tu implémenterai ?

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `abstract Position move()`

Aucune remarque.

### Classe Snail

#### Généralités

##### Documentation

Tests unitaires

Modification des tests au lieu d'une correction des  plugins de Maven comme proposé sur poEsi : https://poesi.esi-bru.be/mod/page/view.php?id=1968

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

Aucune remarque.

### Classe Spider

#### Généralités

##### Documentation

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

En soit `Spider` fait le même types de déplacements que `Snail` mais en boucle. C'est pourquoi il y a de la redondances de code entre les deux classe. Tu peux modifier ces méthodes pour qu’elles utilisent un code commun afin d'éviter la redondance.'

### Classe Game

#### Généralités

##### Documentation

##### Implémentation de l'interface

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Board getBoard()`

Aucune remarque.

##### Méthode `Animal[] getAnimals()`

Aucune remarque.

##### Méthode `void startLevel(int level)`

Aucune remarque.

##### Méthode `boolean levelIsOver()`

Aucune remarque.

##### Méthode `void move(Position position, Direction direction)`

La méthode `move` de `Game` ne doit pas calculer de nouvelle position. Elle doit appeler la méthode `move` de l'animal à déplacer pour cette partie.

### Classe Controller

#### Généralités

##### Documentation

##### Gestion des exceptions

#### Attributs et interfaces

Les attributs peuvent être déclarés `final`.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `startGame`

N'hésite pas ajouter des méthodes `private` pour améliorer la lisibilité. par exemple : 

- `validPosition` qui vérifie si un animal est présent