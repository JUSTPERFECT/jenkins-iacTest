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
            sh 'echo hi'
          } 
          stage('build') {
            sh 'echo how are you'
          }
        }
      """.stripIndent())      
    }
  }
}
