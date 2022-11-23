pipeline{
   agent any
   stages {
      stage ('build'){
        git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'
        withMaven{
           sh "mvn clean package -pl service_discovery"
        }
      }
   }
}