#!groovy

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '150'))
        ansiColor('xterm')
    }
    stages {
        stage('Init ssh key') {
            steps {
                dir("/usr/share/terraform/tmp/cloud-init/scripts/"){
                    sh "sed -i 's/SSH_PUBLIC_KEY/${params.SSH_PUBLIC_KEY}/g' add-ssh-web-app.yaml"
                }
            }
        }
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