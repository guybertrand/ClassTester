CLASS TESTER
=============

Librairie de test pour JAVA

Prérequis
---------
Java [JDK][jdk] >= 6, [Git][git], [Gradle][gradle]
 
Documentation
---------
[Lussier-Tessier-Trudeau_Rapport.pdf][doc]
 
Utilisation de Gradle
---------------

    $ git clone https://guybertrand@github.com/guybertrand/ClassTester.git
    $ cd ClassTester
    $ gradle    

Exemple d'un cas de test (via la ligne de commande)
---------------    
	$ cd ClassTester/build/classes/main
	$ java -classpath . -ea ClassTesterRunner SimpleTest "" false	

Exemple d'une suite de tests
---------------    	
	$ cd ClassTester/build/classes/main
	$ java -classpath . -ea SimpleTestSuite

[gradle]: http://www.gradle.org/
[git]: http://git-scm.com/
[jdk]: http://www.oracle.com/technetwork/java/javase/downloads/java-se-jdk-7-download-432154.html
[doc]: https://github.com/potrudeau/ClassTester/blob/master/doc/Lussier-Tessier-Trudeau_Rapport.pdf?raw=true

