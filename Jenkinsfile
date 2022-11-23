pipeline {
    agent any
    stages {
        stage('Build') {
            steps {

               // git 'https://github.com/guilhermebehs/ftgo'
                sh 'mvn clean package -pl service_discovery'
            }
        }
        stage('Deploy') {
            steps {

               sh "BUILD_ID='dontKillMe' java -jar ./service_discovery/target/service_discovery-0.0.1-SNAPSHOT.jar &"

            }
        }
    }
}
