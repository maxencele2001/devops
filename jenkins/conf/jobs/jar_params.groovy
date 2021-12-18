#!groovy
println('------------------------------------------------------------------Import Job CI/SB3T-JAR')
def pipelineScript = new File('/var/jenkins_config/jobs/jar_pipeline.groovy').getText("UTF-8")

pipelineJob('CI/jar') {
    description("Build .jar")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            trim(false)
        }
        booleanParam {
            name('SKIP_TEST')
            defaultValue(true)
        }
        choice {
            name('VERSION_TYPE')
            choices(['SNAPSHOT', 'RELEASE'])
        }
        stringParam {
            name('VERSION')
            defaultValue('1.0')
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