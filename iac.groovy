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
            sh 'hi'
          } 
          stage('build') {
            sh 'how are you'
          }
        }
      """.stripIndent())      
    }
  }
}
