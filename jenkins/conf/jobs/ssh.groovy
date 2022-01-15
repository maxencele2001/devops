#!groovy

pipeline {
    agent any
    environment {
        SSH = 'SSH'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '150'))
        ansiColor('xterm')
    }
    stages {
        stage('Key existing ?'){
            steps {
                script {
                    sh "if [ -f \"~/.ssh/id_rsa\" ]; then rm ~/.ssh/id_rsa; fi"
                }
            }
        }
        stage('Generate SSH key') {
            steps {
                script {
                    sh "ssh-keygen -t rsa -N \"\" -f ~/.ssh/id_rsa"
                }
            }
        }
        stage('Display SSH key') {
            steps {
                script {
                    sh "cat ~/.ssh/id_rsa.pub"
                }
            }
        }
    }
}