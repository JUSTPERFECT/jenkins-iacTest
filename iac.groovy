pipelineJob('Restarent-App-build') {
    triggers {
    scm('* * * * *')
  }
  definition {
    cps {
      sandbox()
      script("""
        node('master') {
            stage ('Checkout') {
               git url: 'https://github.com/JUSTPERFECT/restarent.git'
               }
            stage ('build') {
              sh '/opt/maven/bin/mvn package'
              }
            stage ('upload to nexus') {
            nexusArtifactUploader artifacts: [[artifactId: 'newtonn', classifier: '', file: 'target/newtonn.war', type: 'war']], credentialsId: 'd10d7797-c0be-4036-b375-b3fb06cb93ba', groupId: 'org.restarent.newton', nexusUrl: '13.126.250.227:8081/nexus', nexusVersion: 'nexus2', protocol: 'http', repository: 'releases', version: '${BUILD_NUMBER}'
              }
         }
        """.stripIndent())      
    }
  }
}
pipelineJob('AWS-VPC-creation') {
  definition {
    cps {
      sandbox()
      script("""
        node('master') {
            stage ('Checkout') {
               git url: 'https://github.com/JUSTPERFECT/resta-vpc.git'
               }
            stage ('plan') {
              sh 'export AWS_ACCESS_KEY_ID=${env.AWS_ACCESS_KEY_ID}'
              sh 'export AWS_SECRET_ACCESS_KEY=${env.AWS_SECRET_ACCESS_KEY}'
              sh '/opt/terraform/terraform plan'
              }
            stage ('apply') {
            sh '/opt/terraform/terraform apply'
            }
         }
        """.stripIndent())      
    }
  }
}
