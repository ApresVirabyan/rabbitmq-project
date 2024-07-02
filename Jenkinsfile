pipeline {
    agent any

     tools {
            maven 'Maven' // Укажите имя Maven, настроенное в Global Tool Configuration, или удалите этот блок, если Maven установлен системно
        }


    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/ApresVirabyan/rabbitmq-project.git'
            }
        }
        stage('Build') {
            steps {
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Test') {
            steps {
                // Run tests
                sh "mvn test"
            }
            post {
                always {
                    // Record the test results
                    junit '**/rabbit-consumer/target/surefire-reports/TEST-*.xml'
                }
                success {
                    // Archive the jar file if tests succeed
                    archiveArtifacts 'target/*.jar'
                }
                failure {
                    // Handle test failures
                    echo 'Some tests failed'
                }
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                // Deployment steps (e.g., upload the jar to a server)
                echo 'Deploying...'
                // Example deployment command
                // sh "scp target/*.jar user@server:/path/to/deploy"
            }
        }
    }

    post {
        always {
            // Actions to perform after the entire pipeline, regardless of the outcome
            echo 'Pipeline finished'
        }
        success {
            echo 'Pipeline succeeded'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}