# GRANDCHAMPS Corentin

# Object-Oriented Software Testing

Master 1 ICE, GLa - V&V - Labs ([course materials](http://combemale.fr/course/m1ice/))

> Créez un projet Java, récupérez la classe MyPoint, et rajoutez la librairie JUnit à votre projet. 

## Test Unitaire pour Java avec JUnit

> Q.1a Tester les constructeurs 1 et 2 (dans une classe de test TestMyPoint). Pour cela, utiliser la Javadoc pour
comprendre le comportement attendu des constructeurs. Utilisez des assertions pour vérifier les valeurs, par exemple :

    ...
    assertEquals (0d, point . getX (), 0.0001);
    ...

> Q.1b Le test de ces constructeurs utilisent les opérations getX et getY. Ne trouvez-vous pas cela étrange qu’un test utilise d’autres opérations ? Que faire ?

Les tests de constructeurs ne sont pas utiles, si ce n'est que l'on souhaite tester l'instanciation ainsi que le mécanisme du ```new```. Tester les getteurs et setteurs semble aussi de trop, étant donné que la logique veut que ces méthodes aient un comportement unique et universel de récupération ou d'instanciation de propriétés. Néanmoins pour les tests je dirai qu'il faut passer par les getteurs et les setteurs pour manipuler les propriétés de l'objet et non en les pointant directement (monObjet.maPropriété).

> Q.1c Testez les accesseurs en lecture et écriture (les opérations get et set). Tout d’abord, testez getX et setX ensemble (car elles sont liées, idem pour y). Ensuite créez d’autres tests pour tester les opérations set avec la valeur Double.NaN (cf. la javadoc de ces opérations).

> Q.1d Testez le constructeur 3 et l’opération scale. Plusieurs tests (i.e. plusieurs opérations) seront nécessaires pour le constructeur 3. Vous pouvez constater que la plupart des tests nécessitent la création d’un point au début des opérations de test.

> Q.1e Définissez et utilisez l’opération @Before setUp() et tout ce qui est également nécessaire pour déléguer cette création à l’opération setUp.

> Q.1f Testez l’opération horizontalSymmetry. Là encore, plusieurs tests (i.e. plusieurs opérations) seront nécessaires. Vous remarquerez que cette opération peut lever une exception. Utilisez le paramètre expected de l’annotation Test pour vérifier que cette exception est bien levée en temps voulu.

## Couverture de code

> Q.2a Utilisez l’outil de couverture de code fourni dans Eclipse (ou autre IDE) pour identifier les chemins dans le code non couvert par vos tests. Rajoutez quelques tests si besoins (n’y passez pas trop de temps).

> Q.2b Est-ce que votre code est sûr lorsque toutes les instructions sont couvertes par au moins un test ?

Le code n'est absolument pas sûr une fois qu'il est couvert par les tests. En effet, la couverture de code ne voit pas les subtilités qui peuvent être introduites dans la complexité d'un code et cela ne peut déterminer tous les cas de tests manquant, ou à priori nécessaires pour avoir un code robuste. Néanmoins c'est un très bon outil pour donner au développeur une vue d'ensemble sur son avancement.

> Q.2c Ajoutez le test unitaire suivant et exécutez-le. S’il passe, bien joué. Dans tout les cas cela peut certainement vous aidez à répondre à la question précédente.

    @Test public void testCentralSymmetryNULL ( ) {
        new MyPoint ( 1 0 , 2 0 ) . centralSymmetry ( null ) ;
    }

## Test d'intégration pour Java avec EasyMock ou Mockito

Cet exercice est une brève introduction au principe du mock.

L’opération setPoint(Random r1, Random r2) définit les coordonnées d’un point de manière aléatoire (x avec r1, et y avec r2).

> Q.3a Expliquez en quoi il est impossible de tester en l’état cette opération.
    >> On veut donc utiliser le principe du Mock pour tester cette opération.

Etant donné que la méthode ```nextDouble()``` renvoie un random et que le but ici est de tester avec une valeur attendue et une valeur obtenue, il faut mocker cette méthode et choisir les valeurs qu'elle pourrait retourner de sorte à pouvoir faire des tests fiables.

> Q.3b Utilisez Easymock ou Mockito pour tester cette opération. 

Avec Mockito :
- N’oubliez pas @RunWith (MockitoJUnitRunner.class). Vous aurez besoin de 2 attributs Random annotée avec @Mock. Le but est de simuler l’opération nextInt(). 
- L’opération translate(ITranslation) déplace le point selon le vecteur de translation donné en paramètre. Cependant ITranslation est une interface, on ne peut donc pas l’instancier.

Avec Easymock : voir la refcard et les slides du cours.

> Q.3c Supposons que nous ne disposons pas d’une implémentation de ITranslation pour tester cette opération, utilisez Mockito ou Easymock et tester finalement cette opération.

## Evaluation 

1. faite un fork du repo github
2. répondez aux questions (dans le fichier README.md), dans lequel vous préciserez également les noms du binome.
3. rajoutez vos tests, modifications de la classe sous test, etc. 
4. soumettez votre pull request (qui servira à l'évaluation)