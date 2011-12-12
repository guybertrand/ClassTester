Exemple d'un cas de test (via la ligne de commande)
$ ant 
ou 
$ cd ClassTester/build/classes/main
$ java -classpath . -ea ClassTesterRunner SimpleTest "" false   

Exemple d'une suite de tests
$ and suite
ou
$ cd ClassTester/build/classes/main
$ java -classpath . -ea SimpleTestSuite

Le fichier HTML est TEST-SOMMAIRE.HTML
Il est ré-écrit à chaque fois.

Les données XML sont écrit dans rundata-"la date".xml
Donc ces fichiers s'accumule avec le temps, donc notre historique.

Avec Ant, un fichier 'output.txt' est créer avec les résultats des tests (un cas de test)
Pour une suite des test, les résultats sont affichés à l'écran.
