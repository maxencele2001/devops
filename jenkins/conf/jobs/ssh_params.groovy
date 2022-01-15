#!groovy
println('------------------------------------------------------------------Import Job CI/Destroy')
def pipelineScript = new File('/var/jenkins_config/jobs/ssh.groovy').getText("UTF-8")

pipelineJob('CI/keygen') {
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}