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
            stage ('Initialize') {
              sh '/opt/maven/bin/mvn package'
              }
        }
        """.stripIndent())      
    }
  }
}
