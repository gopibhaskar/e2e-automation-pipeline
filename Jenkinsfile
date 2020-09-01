#!groovy
def tagName="""${JOB_NAME}-${BUILD_TIMESTAMP}"""
def branchName;
def commit_username;
def commit_Email;
node {
    stage('Git checkout') { // for display purposes
        checkout scm
        sh 'git log --format="%ae" | head -1 > commit-author.txt'
        commit_Email= readFile('commit-author.txt').trim()
        branchName= "master"
        echo branchName
        commit_username='${CHANGES, format="%a"}'
        //commit_Email="Gopibhaskar.Papanaboina@sabre.com"
        echo commit_username
        echo commit_Email
    }
    stage('Smoke') {
        try {
            sh "mvn clean verify -Dtags='type:Smoke'"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "Smoke tests report"
            ])
        }
    }
    stage('API') {
        try {
            sh "mvn clean verify -Dtags='type:API'"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "API tests report"
            ])
        }
    }
    stage('UI') {
        try {
            sh "mvn clean verify -Dtags='type:UI'"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "UI tests report"
            ])
        }
    }
    stage('Results') {
        junit '**/target/failsafe-reports/*.xml'
    }
}