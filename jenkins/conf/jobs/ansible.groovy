#!groovy

pipeline {
    agent any
    environment {
        ANSIBLE = 'ANSIBLE'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '150'))
        ansiColor('xterm')
    }
    stages {
        stage('Modify hosts folder') {
            steps {
                sh "echo \"${params.ip}\n\" > ${params.folder}/inventory/hosts"
            }
        }
        stage('Execute playbook') {
            steps {
                dir("${params.folder}"){
                    sh "ansible-playbook -i inventory/hosts playbooks/init_script.yml"
                }
            }
        }
    }
}