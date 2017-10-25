folder('project-a') {
    displayName('Project A')
    description('Folder for project A')
}

pipelineJob('Pipeline') {
  definition {
    cps {
      sandbox()
      script("""
        node {
          stage('init') {
            sh 'yum install httpd -y'
          } 
          stage('build') {
            sh 'yum install nginix -y'
          }
        }
      """.stripIndent())      
    }
  }
}
