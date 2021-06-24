pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME'
  }
  stages {
      stage('run test') {
        steps {
            bat 'mvn test'
        }
      }
      stage('SonarQube analysis') {
        steps {
            bat 'mvn clean package sonar:sonar -Dsonar.login=3601a113013d1106c9010ae5c847fb76ab99b408'
        }
      }
      stage('Deployment') {
        when {
            branch 'master'
        } 
        steps {
            echo 'This is a deployment'
        }
      }
  }
}

