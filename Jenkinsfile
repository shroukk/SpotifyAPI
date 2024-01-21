pipeline {
    agent any
    
    environment {
        MAVEN_HOME = tool 'Maven'
    }
        
    stages {
     stage('Checkout') {
            steps {
                git 'https://github.com/shroukk/SpotifyAPI.git'
            }
        }

      stage('Build and Test') {
            steps {
                script {
                    sh "${MAVEN_HOME}/bin/mvn clean test"
                }
            }
        }
        
        stage('Generate Allure Report') {
            steps {
                script {
                    sh "${MAVEN_HOME}/bin/mvn allure:report"
                }
            }
        }
    }
    
    post {
        success {
            script {
                echo 'Build and test successful!'
                
                // Archive the Allure report
                archiveArtifacts 'target/site/allure-maven-plugin/**/*'
            }
        }
        failure {
            script {
                echo 'Build or test failed!'
            }
        }
    }
}
