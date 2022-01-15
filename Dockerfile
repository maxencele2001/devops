#
# Jenkins customized image
#
# Usage:
#   Build -> docker build -t jenkins-cust .
#   Run -> docker run --name myjenkins -p 8080:8080 -p 50000:50000 jenkins-cust
#
FROM jenkins/jenkins:lts-jdk11
MAINTAINER Adrien & Quentin

# Disable setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false -Dpermissive-script-security.enabled=true
#
#ENV JENKINS_USER admin
#ENV JENKINS_PASS ThisIs@StrongP@ssword


USER root

RUN apt-get update && \
    apt-get -y install apt-transport-https \
      ca-certificates \
      curl \
      gnupg2 \
      software-properties-common && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
      "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
      $(lsb_release -cs) \
      stable" && \
   apt-get update && \
   apt-get -y install docker-ce

# Load plugins w jenkins-plugin-cli
COPY --chown=jenkins:jenkins jenkins/conf/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

# Terraform
RUN apt-get -y install wget unzip
RUN wget https://releases.hashicorp.com/terraform/1.1.2/terraform_1.1.2_linux_arm64.zip
RUN unzip terraform_1.1.2_linux_arm64.zip
RUN mv terraform /usr/local/bin/
ADD terraform/ /usr/share/terraform
RUN chown -R jenkins /usr/share/terraform

# Ansible
RUN apt-get -y install ansible
ADD ansible/ /usr/share/ansible
RUN chown -R jenkins /usr/share/ansible

USER jenkins
# Jenkins runs all grovy files from init.groovy.d dir
##COPY --chown=jenkins:jenkins conf/scripts/admin-user.groovy /usr/share/jenkins/ref/init.groovy.d/
##VOLUME /var/jenkins_home