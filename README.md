R�AU Arthur

> Q.1b Le test de ces constructeurs utilisent les op�rations getX et getY. Ne trouvez-vous pas cela �trange qu�un test utilise d�autres op�rations ? Que faire ?
	
	On pourrait tester toutes les m�thodes afin d'avoir le moins de risque possible, cependant il serait inutile de tester certaines m�thodes comme les Getters.
	
> Q.2b Est-ce que votre code est s�r lorsque toutes les instructions sont couvertes par au moins un test ?
	Non, nous ne sommes pas certains que tout les cas possibles sont couverts mais �a nous assure que les tests sont pass�es par les diff�rents chemins (conditions, boucles, ...)

> Q.3a Expliquez en quoi il est impossible de tester en l��tat cette op�ration.
    >> On veut donc utiliser le principe du Mock pour tester cette op�ration.
    
    Il est imposssible de tester en l'�tat l'op�ration parce qu'il est impoosible de pr�voir la valeur donn�e de Random.nextInt() � l'avance
