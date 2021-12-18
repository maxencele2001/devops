#!groovy

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        TEST = 'TEST'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '150'))
        ansiColor('xterm')
    }
    stages {
        stage('Job description') {
            steps {
                script {
                    currentBuild.displayName = "#${BUILD_NUMBER} ${params.PARAM1}"
                }
            }
        }
        stage('git checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/Ozz007/sb3t'
            }
        }
        stage('Job compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Job unit test (UT)') {
            when {
                expression { params.SKIP_TESTS == false }
            }
            steps {
                sh 'mvn test'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Job integartion test (IT)') {
            when {
                expression { params.SKIP_TESTS == false }
            }
            steps {
                sh 'mvn verify'
            }
        }
        stage('Job rename') {
            steps {
                script{
                    sh "mv sb3t-ws/target/sb3t-ws-1.0-SNAPSHOT.jar sb3t-${params.VERSION}-${params.VERSION_TYPE}.jar"
                }
            }
        }
    }
}