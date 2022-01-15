#!groovy
println('------------------------------------------------------------------Import Job ansible')
def pipelineScript = new File('/var/jenkins_config/jobs/ansible.groovy').getText("UTF-8")

pipelineJob('CaC/ansible') {
    description("Ansible")
    parameters {
        stringParam {
            name('folder')
            defaultValue('/usr/share/ansible/tmp')
            trim(false)
        }
        stringParam {
            name('ip')
            defaultValue('java ansible_host=[public_ip] ansible_user=deploy')
            description('Enter the public IP address given by Terraform')
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