pipelineJob('Maven-Test') {
    triggers {
    scm('* * * * *')
  }
  definition {
    cps {
      sandbox()
      script("""
        node {
            stage ('Checkout') {
               git url: 'https://github.com/JUSTPERFECT/jenkins-maven.git'
               }
            stage ('Initialize') {
              sh 'mvn package'
              }
        }
        """.stripIndent())      
    }
  }
}
