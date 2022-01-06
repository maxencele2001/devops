#!groovy
println('------------------------------------------------------------------Import Job CI/Terraform')
def pipelineScript = new File('/var/jenkins_config/jobs/terraform_pipeline.groovy').getText("UTF-8")

pipelineJob('CI/terraform') {
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}