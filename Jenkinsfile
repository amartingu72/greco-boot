pipeline { 
    agent any  
    stages { 
        stage('Build') { 
            steps { 
			   checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '38b5d5f4-326c-4bb8-8bf7-0f7e310e076d', url: 'https://github.com/amartingu72/greco-boot.git']]])
			   sh 'mvn clean verify -DskipTests'
            }
        }
		stage('Sonar testing') { 
            steps { 
				sh 'mvn sonar:sonar -Dsonar.organization=amartingu72-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=1cb306c45639ca7d78560ec89900e98d892e5af2' 
			}
		}
		stage('Deploy') { 
            steps { 
				echo 'This is a minimal deploy.' 
			}
		}
    }
}