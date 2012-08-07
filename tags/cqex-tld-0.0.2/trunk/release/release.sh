export JAVA_HOME=/opt/java/jdk1.6.0_30
rm -rf cqex-tld
svn co http://sourcecontrol.6dlabs.com/svn/sandbox/cqex-tld/trunk/cqex-tld/
cd cqex-tld
mvn -DskipTests=true release:clean release:prepare
mvn -DskipTests=true clean package site:site site:deploy
cd ..