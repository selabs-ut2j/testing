# Object-Oriented Software Testing
# Lucas Barros

Master 1 ICE, GLa - V&V - Labs ([course materials](http://combemale.fr/course/m1ice/))

> Créez un projet Java, récupérez la classe MyPoint, et rajoutez la librairie JUnit à votre projet. 

## Test Unitaire pour Java avec JUnit

> Q.1a Tester les constructeurs 1 et 2 (dans une classe de test TestMyPoint). Pour cela, utiliser la Javadoc pour
comprendre le comportement attendu des constructeurs. Utilisez des assertions pour vérifier les valeurs, par exemple :

    ...
    assertEquals (0d, point . getX (), 0.0001);
    ...

> Q.1b Le test de ces constructeurs utilisent les opérations getX et getY. Ne trouvez-vous pas cela étrange qu’un test utilise d’autres opérations ? Que faire ?

> R.1b : Le problème vient du fait que l'on utilise une méthode que l'on a pas encore testé dans un autre test. Cependant on se trouve face à une fonction get, qui a pour but de rendre visible en lecture un élément privé d'une classe. On a pas donc pas besoin de le tester c'est du ressort du langage.

> Q.1c Testez les accesseurs en lecture et écriture (les opérations get et set). Tout d’abord, testez getX et setX ensemble (car elles sont liées, idem pour y). Ensuite créez d’autres tests pour tester les opérations set avec la valeur Double.NaN (cf. la javadoc de ces opérations).

> Q.1d Testez le constructeur 3 et l’opération scale. Plusieurs tests (i.e. plusieurs opérations) seront nécessaires pour le constructeur 3. Vous pouvez constater que la plupart des tests nécessitent la création d’un point au début des opérations de test.

> R.1d : On remarque que l'on a pas à tester plusieurs cas avec le constructeur par compie car nous appellons les fonctions setters de x et y pour modifier les valeurs du nouveau point. Sauf que les setters verifie bien que le point de référence possède un x et un y valide (un vrai double contenant une vraie valeure utile)

> Q.1e Définissez et utilisez l’opération @Before setUp() et tout ce qui est également nécessaire pour déléguer cette création à l’opération setUp.

> Q.1f Testez l’opération horizontalSymmetry. Là encore, plusieurs tests (i.e. plusieurs opérations) seront nécessaires. Vous remarquerez que cette opération peut lever une exception. Utilisez le paramètre expected de l’annotation Test pour vérifier que cette exception est bien levée en temps voulu.

> R.1f : Ici encore on ne peut pas tester l'appelle de cette fonction avec un parametre non instancié car java le bloque lors de la compilation. On ne peut donc pas faire le test de la levé d'exception.

## Couverture de code

> Q.2a Utilisez l’outil de couverture de code fourni dans Eclipse (ou autre IDE) pour identifier les chemins dans le code non couvert par vos tests. Rajoutez quelques tests si besoins (n’y passez pas trop de temps).

> Q.2b Est-ce que votre code est sûr lorsque toutes les instructions sont couvertes par au moins un test ?

> R.2b : Non, évidement le parcours des branches ne vérifies pas tous les cas possibles. Il faudra par exemple faire des tests sur les limites.

> Q.2c Ajoutez le test unitaire suivant et exécutez-le. S’il passe, bien joué. Dans tout les cas cela peut certainement vous aidez à répondre à la question précédente.

    @Test public void testCentralSymmetryNULL ( ) {
        new MyPoint ( 1 0 , 2 0 ) . centralSymmetry ( null ) ;
    }

## Test d'intégration pour Java avec EasyMock ou Mockito

Cet exercice est une brève introduction au principe du mock.

L’opération setPoint(Random r1, Random r2) définit les coordonnées d’un point de manière aléatoire (x avec r1, et y avec r2).

> Q.3a Expliquez en quoi il est impossible de tester en l’état cette opération.
    >> On veut donc utiliser le principe du Mock pour tester cette opération.

> R.3a : On ne peux pas s'assurer de la valeur du random lors du test.

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