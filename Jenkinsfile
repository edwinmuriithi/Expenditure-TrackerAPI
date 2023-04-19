pipeline{
    agent any
    tools{
        maven 'maven'
    }
    stages{
        stage('Clone repository'){
            steps {
                 git 'https://github.com/edwinmuriithi/Expenditure-TrackerAPI.git'
            }
        }
        stage('Install dependencies'){
            steps{
                sh 'mvn install'
            }
        }
        stage('Tests'){
            post{
                failure{
                    mail bcc: '', body: 'Build failure. Check repository', cc: '', from: '', replyTo: '', subject: 'Expenditure API ', to: 'kabuimuriithi@gmail.com'
                }

            }
            steps{
                sh 'mvn clean verify'
            }
        }
        stage('Deploy on Heroku'){
            steps{
                withCredentials([usernameColonPassword(credentialsId: 'heroku', variable: 'HEROKU_CREDENTIALS')]) {
                sh 'git push https://api.render.com/deploy/srv-cgqpti3k9u5es1410ss0?key=_s20vqRg2mo'

            }
        }

    }
    stage('Slack integration'){
                steps{
                    slackSend channel: '#nodejs-gallery', color: '#00FF00', message: "Build ${env.BUILD_NUMBER} has been successful (<https://galler-nodejs.onrender.com/|Open>)", teamDomain: 'edwinmip1', tokenCredentialId: 'Slack'
                    }
                 }
            

    }
}
