#!groovy

def application = 'conta'
def docker_version = 'latest'
def build_image_version = '3.3.9-jdk-8-onbuild'
def git_repository = 'conta'
def git_branch = 'master'
def maven_build_options = '-T 1C'
def maven_test_options = '-T 1C -Djava.security.auth.login.config=ignoreMe.conf' 

node {
  docker.image('maven:' + build_image_version).inside("-v ${env.MAVEN_LOCAL_REPOSITORY}:/root/.m2") {
    stage ('Checkout') {
      git ([url: 'https://gitlab.devops.ifrn.edu.br/corporativo/' + git_repository + '.git', branch: git_branch])
    }

    stage ('Build') {
      sh "mvn " + maven_build_options + " -Dmaven.test.skip=true -Pcheck-checkstyle,check-cpd,check-pmd,check-cycles,check-duplicate,check-findbugs clean verify"
      
      step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
    }
  }

  stage ('Package') {
    docker.withRegistry('https://nexus.devops.ifrn.edu.br') {
      docker.build("corporativo/" + application.toLowerCase() + ":" + docker_version).push(docker_version)
    }
    
    sh "docker rmi nexus.devops.ifrn.edu.br/corporativo/" + application.toLowerCase() + ":" + docker_version
    sh "docker rmi corporativo/" + application.toLowerCase() + ":" + docker_version
  }

  stage ('Homologacao') {
    wrap([$class: 'AnsiColorBuildWrapper', colorMapName: "xterm"]) {
      ansiblePlaybook colorized: true, credentialsId: 'homologacao', inventory: 'hosts', playbook: 'homologacao.yml', sudoUser: null
    }
  }  

  stage ('Producao') {
    wrap([$class: 'AnsiColorBuildWrapper', colorMapName: "xterm"]) {
      ansiblePlaybook colorized: true, credentialsId: 'producao', inventory: 'hosts', playbook: 'producao.yml', sudoUser: null
    }
  }  
}
