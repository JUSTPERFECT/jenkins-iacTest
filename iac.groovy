folder('project-a') {
    displayName('Project A')
    description('Folder for project A')
}

pipelineJob('Pipeline') {
    triggers {
    scm('H/2 * * * *')
  }
  definition {
    cps {
      sandbox()
      script("""
        node {
          stage('init') {
            sh 'sudo yum install httpd -y'
          } 
          stage('build') {
            sh 'sudo yum install nginix -y'
          }
        }
      """.stripIndent())      
    }
  }
}
