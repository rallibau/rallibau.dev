pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'Iniciando build'
        sh 'ls'
        withGradle() {
          sh 'chmod +x gradlew'
          sh './gradlew build --warning-mode all'
        }

      }
    }

    stage('build docker image') {
      steps {
        sh '''docker ps
'''
      }
    }

  }
}