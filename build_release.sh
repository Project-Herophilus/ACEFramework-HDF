developmentDir='/Development/Project-Herophilus/iDaas-KIC'

echo "Development Directory: " $HOME$developmentDir

submoduleDir='/iDaaS-KIC-Integration'
echo "Submodule: " $submoduleDir
echo "Complete Directory; " $HOME$developmentDir$submoduleDir
cd $HOME$developmentDir$submoduleDir

echo "Red Hat Specific Build"
mvn -B clean install -f $HOME$developmentDir$submoduleDir/pom-redhat.xml &
cd $HOME$developmentDir$submoduleDir/target/
cp *.jar $HOME$developmentDir/releases/

echo "Upstream Specific Build"
mvn -B clean install -f $HOME$developmentDir$submoduleDir/pom.xml &
cd $HOME$developmentDir$submoduleDir/target/
cp *.jar $HOME$developmentDir/releases/

echo "Remove Target Directory Location"
rm -r $HOME$developmentDir/target/
