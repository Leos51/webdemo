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
//         stage('Clean workspace') {
//             steps {
//                 clearWs()
//             }
//         }
        stage('Git Checkout') {
            steps {
                script {
                   git branch: 'main',
                       credentialsId: 'jenkins-ci-token',
                       url: 'https://github.com/Leos51/webdemo.git'
                       echo 'checkout'
        //             checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'git_token', url: 'https://github.com/Leos51/webdemo']])
                }
            }
        }
        stage('Build Maven') {
            steps {
               bat 'mvn clean package'
               echo 'build maven'
            }
        }
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
}