pipeline {
    agent any
    tools {
        // Install the Maven version configured as "apache-maven-3.0.1" and add it to the path., this need to be setup in the Manage Jenkins → Global Tool Configuration.
        maven 'maven-3.23'
    }
    stages {
        stage('ENVS') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "MAVEN_HOME = ${MAVEN_HOME}"
                '''
            }
        }
        stage('Compile') {
            steps {
                // Run Maven on a Unix agent.
                sh "mvn -DSkipTests clean package"
            }
        }
        stage('Run Unit Tests') {
            steps {
//                sh "mvn test"
                echo 'Run unit tests'
            }
        }
        stage('Run Integration Tests') {
            steps {
                echo 'Run only crucial integration tests from the source code'
            }
        }
        stage('Publish Artifacts') {
            steps {
                echo 'Save the assemblies generated from the compilation'
            }
        }
    }
}