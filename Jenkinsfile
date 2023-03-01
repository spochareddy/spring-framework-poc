// Jenkinsfile - DX Golden Path v0.1

// Jenkins Shared Libraries

// 1 - helm charts
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-helm-charts_nix@master', retriever: modernSCM([$class: 'GitSCMSource',
remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-helm-charts_nix.git',
credentialsId: 'github_token'])

// 2 - TruffleHog
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-trufflehog_nix@master', retriever: modernSCM([$class: 'GitSCMSource',
   remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-jenkins-sharedlib-trufflehog_nix.git',
   credentialsId: 'github_token'])

// 3 - SonarQube
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-sonarQube_multi@master', retriever: modernSCM([$class: 'GitSCMSource',
remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-sonarQube_multi.git',
credentialsId: 'github_token'])

// 4 - Dockerbuild (Podman)
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-dockerbuild@feature/ocir-change', retriever: modernSCM([$class: 'GitSCMSource',
   remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-dockerbuild.git',
   credentialsId: 'github_token'])

// 5 - Changelog (Semantic Versioning)
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-changelog@master', retriever: modernSCM([$class: 'GitSCMSource',
   remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-changelog.git',
   credentialsId: 'github_token'])

// 6 - ZAP
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-owasp-zap-scan-jenkins-sharedlib@master', retriever: modernSCM([$class: 'GitSCMSource',
remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-owasp-zap-scan-jenkins-sharedlib.git',
credentialsId: 'github_token'])

// 7 - Terraform
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-terraform@feature/oci-tf-shared-lib', retriever: modernSCM([$class: 'GitSCMSource',
   remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-terraform.git',
   credentialsId: 'github_token'])

// 8 - Release Lifecycle
library identifier: 'dx-golden-path-v0.1-neom-jenkins-sharedlib-release-lifecycle@master', retriever: modernSCM([$class: 'GitSCMSource',
remote: 'https://github.com/NEOM-KSA/dx-golden-path-v0.1-neom-jenkins-sharedlib-release-lifecycle.git',
credentialsId: 'github_token'])

// Jenkins Pipeline
pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: podman
            imagePullPolicy: Always
            image: jed.ocir.io/axnfm4jb3i73/podman-trivy-oci:latest
            command:
            - cat
            tty: true
            securityContext:
              privileged: true
            volumeMounts:
            - mountPath: /var/lib/containers
              name: podman-volume
            - mountPath: /dev/shm
              name: devshm-volume
            - mountPath: /var/run
              name: varrun-volume
            - mountPath: /tmp
              name: tmp-volume
          - name: trufflehog
            image: jed.ocir.io/axnfm4jb3i73/trufflehog1:latest
            command:
            - cat
            tty: true
          - name: ubuntu
            image: jed.ocir.io/axnfm4jb3i73/ubuntu1:latest
            command:
            - cat
            tty: true
          - name: git
            image: jed.ocir.io/axnfm4jb3i73/git1:latest
            command:
            - cat
            tty: true
          - name: build
            image: jed.ocir.io/axnfm4jb3i73/gradlejdk11:latest
            command:
            - cat
            tty: true
          - name: build1
            image: docker.io/maven:3.9.0
            command:
            - cat
            tty: true			
          - name: helm
            image: jed.ocir.io/axnfm4jb3i73/helm-oci:latest
            command:
            - cat
            tty: true
          - name: owasp-zap
            image: jed.ocir.io/axnfm4jb3i73/zap:latest
            command:
            - cat
            tty: true
          - name: changelog
            image: jed.ocir.io/axnfm4jb3i73/changlog1:latest
            command:
            - cat
            tty: true
          - name: mysql
            image: jed.ocir.io/axnfm4jb3i73/mysql:latest
            command:
            - cat
            tty: true
          restartPolicy: Never
          volumes:
          - name: podman-volume
            emptyDir: {}
          - emptyDir:
            medium: Memory
            name: devshm-volume
          - emptyDir: {}
            name: varrun-volume
          - emptyDir: {}
            name: tmp-volume
          imagePullSecrets:
          - name: ocirsecret
        '''
    }
  }

  environment{
    userpat= credentials('git_pass')

    // nexus_username = credentials('nexus_sbx_username')
    // nexus_password = credentials('nexus_sbx_password')
    // DB_USERNAME = credentials('db_username')
    // DB_PASS = credentials('db_pass')
    // DB_HOST=credentials('db_host')
    // REDIS_PASSWORD= credentials('redis-password')

    def environmentName = 'sindalah-demo-tech-api'
    def tenancyNamespace = 'axnfm4jb3i73' // newly defined for ocir push
    def projectName = 'sindalah-demo-tech-api'
    // def releaseName = 'dx-golden-path-release1'
    // def PROJECT_URL = "https://github.com/NEOM-KSA/dx-golden-path-v0.1.git"
    // GIT_URL = "https://github.com/NEOM-KSA/dx-golden-path-v0.1-jenkins-sharedlib-trufflehog_nix.git" // GIT_URL for TruffleHog - Example: "https://$GIT_HUB@github.com/mobilityhouse/testci.git" // newly defined for TruffleHog
    // def namespace = "dx-golden-path-namespace"
    def imagetype = "sindalah-demo-tech-api"
    def compartment_id='ocid1.compartment.oc1..aaaaaaaaeg7m7tc6lbn6ca4c22dddfwhzssp2mfsg676nszu2kexf4grs4uq'
    def tag = "1.0.1" // tag for docker image (podman)
    def version = "1.0.1"
    def BRANCH_NAME = "main"
  }

  stages {

    // 1 - Truffle Hog
    stage('Run Repo Scan') {
          steps {
            echo 'h1'
            echo 'BRANCH NAME:' + env.BRANCH_NAME
            echo 'GIT_URL:' + env.GIT_URL
            sh "echo ${env.BRANCH_NAME}"
            container('trufflehog') {
                repoScan([branchName: env.BRANCH_NAME])
	    }
      }
    }

    // 2 - Clean Up
    stage('Workspace clean') {
          steps {
            echo 'Stage Cleanup Workspace ...'
            // Cleanup before starting the stage
            cleanWs()
       }
    }

    // 3 - Semantic Versioning (SemVer)
    stage('Checkout Code') {
          steps {
                echo 'SemVer ...'
                script{
                  container('git') {
                    echo 'inside git(changelog) container ...'
                    sh 'hostname'
                    checkout scm
                        // (tag,flag) = semverVersion()
                        // env.version = tag
                      }
                  }
                }
          }

    // // 4 - Run PreReq
    // stage('Run Prerequisite Tasks') {
    //         when {
    //          	      branch 'master'
    //            }
    //    steps {
    //         script {
    //              def config = readYaml  file: 'helm/neom-fss-neompay-wallet-api/values.yaml'
    //                 for(env_var in config.env){
    //                       if(env_var.name == 'DB_NAME'){
    //                          data_base_name=env_var.value;
    //                             env.APP_DATABASE_NAME = data_base_name
    //                         }
    //                  }
    //             }
    //         script{
    //            def data_base_name=''
    //            container('mysql') {
    //               sh(script:'''
    //                 mysql -N -u $DB_USERNAME -p$DB_PASS -h $DB_HOST -e "CREATE DATABASE IF NOT EXISTS $APP_DATABASE_NAME"
    //                 mysql -N -u $DB_USERNAME -p$DB_PASS $APP_DATABASE_NAME -h $DB_HOST <library/sql/CREATE_DATABASECHANGELOG.sql
    //               ''')
    //         }
    //     }
    //   }
    // }

    // 5 - Sonar Quality Report
    stage('Sonar Quality Report') {
      steps {
        container('build1') {
            withSonarQubeEnv('sonar-server') {
              checkout scm
              sh '''
               
	      	#mvn clean verify sonar:sonar -Dsonar.login=myAuthenticationToken src
              mvn clean verify sonar:sonar 
		
              '''
        }
      }
    }
    }

    // 6 - Sonar Quality Gate
    stage('Quality Gate') {
      steps {
        container('ubuntu') {
     //    qualityGates()
        }
      }
    }

    
    // 7 - Code Build
    stage('Code Build') {
      steps {
        container('build1') {
          checkout scm
          sh 'echo "BEFORE CODE BUILD"'
          sh 'mvn test verify install  '
	 				
        }
      }
    }

    // 8 - Podman Build
    stage('Docker Image Build') {
      steps {
        echo 'I am inside Docker Image Build'
        script{
               container('podman') {
                podmanBuild("jed.ocir.io", "${tenancyNamespace}", "${projectName}", "${tag}", "${imagetype}")
               }
            }
        }
    }

    // 9 - Podman Scan
    stage('Docker Image scan') {
      steps {
        script{
              container('podman') {
               //     trivyScanocr("jed.ocir.io", "${tenancyNamespace}", "${projectName}", "${tag}", "${imagetype}")
            }
        }
      }
    }

    // 10 - Podman Push
    stage("Docker Image push") {
          //  when{
          //  	     branch 'master'
          //      }
          steps {
                  script{
                      container('podman') {
                        withCredentials ([file(credentialsId: 'oci_api_key', variable: 'oci_api_key'),file(credentialsId: 'oci_config', variable: 'oci_config')])
                        {
                          sh "mkdir -p ~/.oci/"
                          sh "cp \$oci_api_key ~/.oci/oci_api_key.pem"
                          sh "cp \$oci_config ~/.oci/config"
                        }
                        // tfci.config()
                            // call (String credentialsID, String ocrRegistry, String ocrNamespace, String name, String tag, String imageType)
                            podmanPush("ocr_login", "jed.ocir.io", "${tenancyNamespace}", "${projectName}", "${tag}", "${imagetype}")
                      }
                }
           }
    }

    // 11 - Zap Start
    stage('Starting Zap Proxy') {
	      // when{
	      //     branch 'master'
        //  }
                    steps {
                        echo "*********************** Starting ZAP proxy ***********************************"
                        script {
                            container("owasp-zap")  {
                             //   runOwaspZapProxy()
                            }
                        }
                    }
    }

    // // 12 - Deploy
   stage('Deploy') {
    when{
	      branch 'master'
      }
      steps {
        container('helm') {
		//withCredentials([file(credentialsId: 'PRIVATE_KEY', variable: 'key'),file(credentialsId: 'config', variable: 'CONFIG')]) {
    withCredentials([file(credentialsId: 'oci_api_key', variable: 'oci_api_key'),file(credentialsId: 'oci_config', variable: 'oci_config')]) {
                    sh "mkdir -p ~/.oci/"
                    sh "cp \$oci_api_key ~/.oci/oci_api_key.pem"
                    sh "cp \$oci_config ~/.oci/config"
              	    withKubeCredentials(kubectlCredentials: [[caCertificate: '', clusterName: 'bld-neompay-domain', contextName: 'bld-neompay-domain', credentialsId: 'ak-kubeapi-token-1', namespace: '', serverUrl: '']]) {
                      sh 'helm repo add bitnami https://charts.bitnami.com/bitnami'
                      sh 'helm repo list'

		        // dir("helm"){
	  	      //     upgradeReleaseV3(
	  	      //     chartName: "${projectName}",
	  	      //     releaseName: "${releaseName}",
	  	      //     namespace: "${namespace}",
	  	      //     options: "--set image.tag=${tag}"
	  	      //     )

	  	      //  upgradeReleaseV3(
            //     chartName: "istio-${releaseName}",
            //     releaseName: "istio-${releaseName}",
            //      namespace: "${namespace}"
            // )
        	//  }
	          }
	          }
     	     }
    	  }
  	}

    //   // 13 - BDD Tests
    //   /*stage('BDD Tests') {
    //         when{
    //             branch 'master'
    //         }
    //         steps{
    //           echo "*********************** Wallet app domain bdd trigger ${env.DOMAIN_BDD_TEST_DIR}***********************************"
    //           build job: "${env.DOMAIN_BDD_TEST_DIR}/${projectName}-bdd-test",
    //           parameters:[string(name: 'Environment',value: 'BUILD')],
    //           propagate: true, wait: true
    //       }
    //   }*/

    // 14 - ZAP Scan
    stage('Zap Proxy') {
            // when{
            //   branch 'master'
            //   }
                      steps {
                          echo "*********************** ZAP proxy scanning ***********************************"
                          script {
                              container("owasp-zap")  {
                            //      runActivescan()
                             //     generateHtmlReport()

                              }
                          }
                      }
    }

    // 15 - Release Artifact
  //  stage('Release Artifact') {
  //                steps {
  //                 script{
  //                  container('helm') {
  //                      sh "echo inside helm container"
                        //checkout scm
  //       		            def config = [
                 			  // Must contain imagename:imagetag
               			    //images: ["jed.ocir.io/axssefozft8h/${imagetype}/${projectName}:${tag}"],
                        //images: ["jed.ocir.io/axnfm4jb3i73/${imagetype}/${projectName}:${tag}"],
                        //images: ["jed.ocir.io/axnfm4jb3i73/akrtltest/${projectName}:${tag}"],
    //                    images: ["jed.ocir.io/${tenancyNamespace}/${imagetype}/${projectName}:${tag}"],
                 			  // Any folder with Chart.yaml will be released as a tarball by default
    //             			  releaseFileList: ["helm/*/*.yaml, helm-overrides/*.yaml"],
                 			  //dryRun: true,
    //                    dryRun: false,
    //           			    version: "${env.version}",
                        //version: "0.1",
    //             			  jenkinsCredentials: "github_token",
    //           		      ]
    //           			    releaseRTLHandler(config)
    //     	    }
    //       }
    //    }
   // }
	}
}
