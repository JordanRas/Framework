javac -d . *.java

jar cvf framework.jar *
copy "./framework.jar" "../Test/WEB-INF/lib/framework.jar"
del framework.jar
cd ./../Test/WEB-INF/classes
javac -cp "../lib/framework.jar" Modele/*.java
cd ./../../
jar cvf test_framework.war *
copy "./test_framework.war" "./../"
del test_framework.war

