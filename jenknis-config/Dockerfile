FROM jenkins/jenkins:latest
USER root
RUN apt update
RUN apt install -y --no-install-recommends gnupg curl ca-certificates apt-transport-https
RUN curl -sSfL https://apt.octopus.com/public.key | apt-key add -
#RUN sh -c "echo deb https://apt.octopus.com/ stable main > /etc/apt/sources.list.d/octopus.com.list"
#RUN apt update  \
#RUN apt install -y octopuscli

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml
USER jenkins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
#RUN  jenkins-plugin-cli -f  /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

# so we can use jenkins cli
COPY jenkins.properties /usr/share/jenkins/ref/

# remove executors in master
COPY *.groovy /usr/share/jenkins/ref/init.groovy.d/

# lets configure Jenkins with some defaults
COPY *.xml /usr/share/jenkins/ref/
COPY casc.yaml /var/jenkins_home/casc.yaml
