#!groovy
println('------------------------------------------------------------------Import Job CI/Terraform')
def pipelineScript = new File('/var/jenkins_config/jobs/terraform_pipeline.groovy').getText("UTF-8")

pipelineJob('CI/terraform') {
    parameters {
        stringParam {
            name('MY_BIG_KEY')
            defaultValue('')
            trim(false)
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}