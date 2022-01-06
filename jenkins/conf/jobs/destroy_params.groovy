#!groovy
println('------------------------------------------------------------------Import Job CI/Destroy')
def pipelineScript = new File('/var/jenkins_config/jobs/destroy.groovy').getText("UTF-8")

pipelineJob('CI/destroy') {
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}