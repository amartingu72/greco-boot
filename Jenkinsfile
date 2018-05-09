pipeline { 
    agent any  
    stages { 
        stage('Build') { 
            steps { 
               echo 'This is a minimal pipeline.' 
			   checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '38b5d5f4-326c-4bb8-8bf7-0f7e310e076d', url: 'https://github.com/amartingu72/greco-boot.git']]])
			   sh 'mvn clean verify'
            }
        }
    }
}