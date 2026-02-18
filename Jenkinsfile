pipeline {
    agent any
    environment {
        registry = "leos51/webdemo"
        registryCredential = "dockerhub-credentials"
    }

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }
    stages {
        stage('Clean workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Git Checkout') {
            steps {
                script {
                   git branch: 'main',
                       credentialsId: 'jenkins-ci-token',
                       url: 'https://github.com/Leos51/webdemo.git'
                       echo 'checkout'
                }
            }
        }
        stage('Build Maven') {
            steps {
               bat 'mvn clean package'
               echo 'build maven'
            }
        }
//         stage('Generate allure Report') {
//             steps {
//                 bat 'mvn allure:report'
//             }
//         }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('leos51/webdemo:latest', '-f Dockerfile .')
                    echo 'build Docker Image'
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
                         docker.image('leos51/webdemo:latest').push()
                    }
                }
                echo 'Push Docker Image'
            }
        }
        stage('Deploy docker-compose') {
            steps {
                script {
                    bat 'docker compose up -d --build --force-recreate --remove-orphans'
                }
            }
        }
    }
    post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
        success {
            discordSend description: 'PipelineCI/CD AppWeb réussie',
                result: 'SUCCESS', title: 'AppWeb CI/CD Pipeline',
                webhookURL: 'https://discordapp.com/api/webhooks/1473618206441869403/u-F3T-loXSh00iScapJdA3ZENWbC50bQe57Av9UuhhYBh0KZr3U-TAV6koIS9Ols1puj'
        }
        failure {
            discordSend description: 'PipelineCI/CD AppWeb a echoué',
                result: 'FAILURE', title: 'AppWeb CI/CD Pipeline',
                webhookURL: 'https://discordapp.com/api/webhooks/1473618206441869403/u-F3T-loXSh00iScapJdA3ZENWbC50bQe57Av9UuhhYBh0KZr3U-TAV6koIS9Ols1puj'
        }
    }
}