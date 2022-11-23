pipeline {
    agent any
    stages {
           stage('Build') {
            steps {
                sh 'mvn clean package -pl service_discovery'
            }
        }
           stage ('Deploy'){
             steps{
               sshagent (credentials: ['chaves-ohio']){
                    sh 'scp ./service_discovery/target/service_discovery-0.0.1-SNAPSHOT.jar ec2-user@ec2-3-143-243-68.us-east-2.compute.amazonaws.com:/home/ec2-user'
                 }
               }
           }
    }
}
