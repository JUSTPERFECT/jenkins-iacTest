pipelineJob('Maven-Test') {
    triggers {
    scm('* * * * *')
  }
  definition {
    cps {
      sandbox()
      script("""
        node('master') {
            stage ('Checkout') {
               git url: 'https://github.com/JUSTPERFECT/jenkins-maven.git'
               }
            stage ('build') {
              sh '/opt/maven/bin/mvn package'
              }
            stage ('upload to nexus') {
            nexusArtifactUploader artifacts: [[artifactId: 'newton', classifier: '', file: 'target/newton.war', type: 'war']], credentialsId: 'd4b6cc01-94ed-4024-9dfd-50d99d8fd072', groupId: 'org.restarent.newton', nexusUrl: '54.255.234.64:8081/nexus', nexusVersion: 'nexus2', protocol: 'http', repository: 'releases', version: '${BUILD_NUMBER}'
              }
         }
        """.stripIndent())      
    }
  }
}
