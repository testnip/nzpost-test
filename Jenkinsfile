#!groovy​

// set the timezone to
TimeZone.setDefault(TimeZone.getTimeZone("Pacific/Auckland"))

// start time
Calendar start = Calendar.getInstance()
start.set(Calendar.HOUR_OF_DAY, 4)

// end time
Calendar end = Calendar.getInstance()
end.set(Calendar.HOUR_OF_DAY, 6)

// now
Calendar now = Calendar.getInstance()

/**
 * Email list
 * Jenkins job will use the stakeHolderEmail list when the build runs between 4am - 6am everyday
 * At other times, use the email of the committer
 */
String stakeHolderEmails = 'web-re-platform@nzpost.co.nz'
boolean isNightlyBuildTime = now.after(start) && now.before(end)
String additionalEmails = isNightlyBuildTime ? stakeHolderEmails : ''
String ownerEmails = 'nick.tran@nzpost.co.nz,nipun.mahajan@nzpost.co.nz'

pipeline {
    agent none
    triggers {
        cron('TZ=Pacific/Auckland\n10 4 * * 1-5')
    }

    stages {
        stage('sit') {
            when {
                beforeAgent true
                branch 'sit'
            }
            agent {
                docker {
                    image 'maven:3.5.4-jdk-8-alpine'
                    args '-u root:root -v /home/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean verify -Dspring.profiles.active=cloud,$BRANCH_NAME'
            }
            post {
                always {
                    cucumber '**/cucumber-json-report.json'
                    step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: ownerEmails, sendToIndividuals: true])
                    emailext mimeType: 'text/html', to: additionalEmails, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'NZ Post Automated Test Report - $BRANCH_NAME', body: '${FILE, path="target/cucumber-single-page-report/cucumber-results-agg-test-results.html"}'
                }
            }
        }

        stage('clean up workspace') {
            agent any
            steps {
                cleanWs()
            }
        }
    }

}
