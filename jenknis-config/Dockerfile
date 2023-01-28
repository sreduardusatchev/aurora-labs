FROM jenkins/jenkins:latest
USER root
RUN apt update
RUN apt install -y --no-install-recommends gnupg curl ca-certificates apt-transport-https python
RUN curl -sSfL https://apt.octopus.com/public.key | apt-key add -
#RUN sh -c "echo deb https://apt.octopus.com/ stable main > /etc/apt/sources.list.d/octopus.com.list"
#RUN apt update  \
#RUN apt install -y octopuscli
ENV JENKINS_HOME /var/jenkins_home
ARG JAVA_OPTS
ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false ${JAVA_OPTS:-}"
ENV JAVA_OPTS -Dcasc.jenkins.config=/var/jenkins_home/casc_configs
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV java ${JVM_OPTS}                                \
 -Dhudson.udp=-1                                 \
 -Djava.awt.headless=true                        \
 -Dhudson.DNSMultiCast.disabled=true             \
 -Djenkins.install.runSetupWizard=false          \
 -jar ${JENKINS_FOLDER}/jenkins.war              \
 ${JENKINS_OPTS}                                 \
 --httpPort=${PORT1}                             \
 --webroot=${JENKINS_FOLDER}/war                 \
 --ajp13Port=-1                                  \
 --httpListenAddress=0.0.0.0                  \
 --ajp13ListenAddress=0.0.0.0                 \
 --argumentsRealm.passwd.admin=${PASSWORD}       \
 --argumentsRealm.roles.user=admin               \
 --argumentsRealm.roles.admin=admin               \
 --prefix=${JENKINS_CONTEXT}
USER jenkins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
#RUN  jenkins-plugin-cli -f  /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

# so we can use jenkins cli
COPY jenkins.properties /usr/share/jenkins/ref/
COPY Jenkinsfiles/hello.jenkinsfile /var/jenkins_home/jobs/
# remove executors in master
#COPY *.groovy /usr/share/jenkins/ref/init.groovy.d/

# lets configure Jenkins with some defaults
#COPY *.xml /usr/share/jenkins/ref/
COPY seedjob.groovy /usr/local/seedjob.groovy

COPY jenkins.yaml /usr/local/jenkins.yaml
ENV CASC_JENKINS_CONFIG /usr/local/jenkins.yaml
