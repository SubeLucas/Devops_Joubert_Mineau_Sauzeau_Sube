



# Fonctionnalités
Les fonctionnalités de notre librairie permettent de :
	- Stocker des objets générique Java dans des Dataframe
	- Créer un Dataframe à partir d'un fichier 
	- Afficher un Dataframe dans un format lisible dans le terminal
	- Selectionner/afficher des sous-ensembles d'un Dataframe
	- Opération arithmétique (min,max,mean)



```java
Vector<Object> getColumn(String columnName) : Getter of a specific column
Vector<Vector<Object>> getColumns(String[] colNames) : Getter of a multiple columns
Vector<Vector<Object>> getRows(int[] idxRows) : Select multiple rows using a tab of indexes
HashMap<String,Float> max() : Calculate the max of each column
Float max(String key) : Calculate the max of the chosen column
HashMap<String,Float> mean() : Calculate the mean of each column
Float mean(String key) : Calculate the mean of the chosen column
HashMap<String,Float> min() : Calculate the min of each column
Float min(String key) : Calculate the min of the chosen column
void print() : Print the content of the Dataframe
HashMap<String,Float> sum() : Calculate the sum of each columns
Float sum(String key) : Calculate the sum of the chosen column
```


# Documentation

Est généré avec :
```bash
javadoc -d docs -sourcepath projet/src/main/java/Joubert_Mineau_Sauzeau_Sube/*.java
```
Est accessible via :
https://subelucas.github.io/Devops_Joubert_Mineau_Sauzeau_Sube/

Ou en local :
```bash
xdg-open doc/index.html &
```


# Compilation

Pour compiler :
Dans le dossier racine ou le dossier **/projet**
```java
mvn clean install
```

# Choix d'outils

- Maven : Pour la génération de la structure du projet, compilation, tests.
- JUnit : Pour les tests unitaires, cet outil à été choisis plutot qu'un autre car tout le monde dans le groupe avait de l'expérience avec.
- JavaDoc : Pour avec une documentation plus propre, plus rapidement.
- Github action : Pour la mise en place de la pipeline d'intégration continue, compilation et tests automatique. (```.github/workflows/actions.yml```)
- Jacoco : ???


# Workflow git et validation

Nous avons mis en place la structure vide du projet dans la branche *main*, puis chaque nouveau blocs de fonctionnalités à été developpé et testé dans des branches de developpement.

Une fois la branche de developpement codé et testé, un PR est créé où elle est soumis à une revue de code et d'eventuellement des modifications. 

Lorsque le PR est accepté, il est "squash and merge" dans la branche *main*.

A chaque push et à chaque PR, les tests automatique sont lancé afin qu'il soit impossible d'intégrer du code non fonctionnel dans la branche *main*.

# Feedback

*Une partie feedback dans laquelle vous donnerez votre retour d’exp´erience sur les diff´erents outils
utilis´es durant le projet.*

- Git : Pas de problème particulier, car ayant tous suivis le cours de M1 Devops nous sommes tous des experts Git.
- Maven : Quelques difficulté à mettre en mettre l'héritage correctement entre le *pom* parent et enfant. 
- JUnit : boh ça va
- JavaDoc : Trop chiant mais bon résultat de dingo
- Jacoco : ça à bien servi 