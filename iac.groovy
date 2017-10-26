folder('project-a') {
    displayName('Project A')
    description('Folder for project A')
}

pipelineJob('Pipeline') {
    triggers {
    scm('* * * * *')
  }
  definition {
    cps {
      sandbox()
      script("""
        node {
          stage('init') {
            sh 'pwd'
          } 
          stage('build') {
            sh 'ls -lrt'
          }
        }
      """.stripIndent())      
    }
  }
}
