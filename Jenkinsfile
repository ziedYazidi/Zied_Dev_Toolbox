pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                echo 'Compile the source code'
            }
        }
        stage('Run Unit Tests') {
            steps {
                echo 'Run Unit Tests' 
            }
        }
        stage('Run Integration Tests') {
            steps {
                echo 'Run integration tests'
            }
        }
        stage('Publish Artifacts') {
            steps {
                echo 'Save the assemblies generated from the compilation'
            }
        }
    }
}
