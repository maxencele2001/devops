#!groovy

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '150'))
        ansiColor('xterm')
    }
    stages {
        stage('Terraform Init') {
            steps {
                dir("/usr/share/terraform/tmp/"){
                    sh "terraform init"
                }
            }
        }
        stage('Terraform Plan') {
            steps {
                dir("/usr/share/terraform/tmp/"){
                    sh "terraform apply --auto-approve"
                }
            }
        }
    }
}