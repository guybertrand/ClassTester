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
Il est r�-�crit � chaque fois.

Les donn�es XML sont �crit dans rundata-"la date".xml
Donc ces fichiers s'accumule avec le temps, donc notre historique.

Avec Ant, un fichier 'output.txt' est cr�er avec les r�sultats des tests (un cas de test)
Pour une suite des test, les r�sultats sont affich�s � l'�cran.
