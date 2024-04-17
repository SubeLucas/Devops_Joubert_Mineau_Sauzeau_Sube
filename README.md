
# Features

Un ficher README qui fournira une documentation aux utilisateurs, c’est-`a-dire, une pr´esentation de
l’ensemble des fonctionnalit´es fournies et une explication de leur utilisation. Quelques remarques
compl´ementaires sur le contenu du fichier README sont fournies dans la section 3.4.
3.4 Le fichier README
Le fichier README fera office de compte-rendu pour votre TP. Ainsi, il est attendu qu’il contienne au
moins les informations suivantes :
• Une description des fonctionnalit´es fournies par votre service.
• Une description des choix d’outils que vous avez fait.
• Une description du workflow git que vous avez mis en place pour votre projet, et de la proc´edure
de validation des Pull/Merge requests que vous avez adopt´ee.
• Le cas ´ech´eant, une liste et une courte description des images Docker produites et un lien vers
leur d´epˆot.
• Une partie feedback dans laquelle vous donnerez votre retour d’exp´erience sur les diff´erents outils
utilis´es durant le projet.


## Dataframe
Les fonctionnalités de notre librairie permettent de :
	- Stocker des objets générique Java dans des Dataframe
	- Créer un Dataframe à partir d'un fichier 
	- Afficher un Dataframe dans un format lisible dans le terminal
	- Selectionner/afficher des sous-ensembles d'un Dataframe
	- Opération arithmétique (min,max,mean)
	



# Documentation
Est généré avec :
```bash
javadoc -d doc -sourcepath projet/src/main/java/Joubert_Mineau_Sauzeau_Sube/*.java
```
Est accessible via :
```bash
xdg-open doc/index.html &
```

# Compilation

Pour compiler :
Dans le dossier racine ou le dossier **/projet**
```java
mvn clean install
```

# Workflow git et validation

Nous avons mis en place la structure vide du projet dans la branche main, puis chaque nouveau blocs de fonctionnalités à été developpé et testé dans des branches de developpement.

Une fois la branche de developpement codé et testé, un PR est créé où elle est soumis à une revue de code et d'eventuellement des modifications. 

Lorsque le PR est accepté, il est "squash and merge" dans la branche main.