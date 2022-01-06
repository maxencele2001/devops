#!groovy

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '150'))
        ansiColor('xterm')
    }
    stages {
        stage('Terraform Destroy') {
            steps {
                dir("/usr/share/terraform/tmp/"){
                    sh "terraform destroy --auto-approve"
                }
            }
        }
    }
}