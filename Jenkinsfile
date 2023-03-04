pipeline {
    agent any
      tools {
            // Install the Maven version configured as "M3" and add it to the path.
            maven 'apache-maven-3.0.1'
        }
    stages {
        stage('Compile') {
            steps {
                // Run Maven on a Unix agent.
                sh "mvn -DSkipTests clean package"
            }
        }
        stage('Run Unit Tests') {
            steps {
                sh "mvn test"
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