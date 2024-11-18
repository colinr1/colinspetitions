pipeline {
    agent any

    stages {
        stage('GetProject') {
            steps {
                git 'https://github.com/colinr1/colinspetitions.git'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean"
            }
        }
        stage('Package and Archive') {
            steps {
                sh "mvn package"
            }
            post {
                success {
                    archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/colinspetitions*.war'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp .'
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }
}