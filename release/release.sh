#
# Release creation script for CQ Extended Taglib
# 
# Author: dklco
#
export JAVA_HOME=/opt/java/jdk1.6.0_30
echo "Cleaning Workspace..."
rm -rf cqex-tld
echo "Checking out TRUNK..."
svn co http://sourcecontrol.6dlabs.com/svn/sandbox/cqex-tld/trunk/cqex-tld/
cd cqex-tld
echo "Preparing release..."
mvn -DskipTests=true release:clean release:prepare -P release
echo "Performing release..."
mvn -DskipTests=true release:perform -P release
echo "Release Complete!"
cd ..