FROM tomcat:7.0

RUN apt-get update && apt-get install -y gcc libcunit1 libcunit1-dev

RUN rm -rf /usr/local/tomcat/webapps/ROOT

RUN rm -rf /usr/local/tomcat/webapps/ROOT.war

RUN mkdir -p /usr/local/tomcat/webapps/classes

ADD code/target/classes /usr/local/tomcat/webapps/classes

RUN mkdir -p /usr/local/tomcat/webapps/ROOT

ADD code/target/AssessmentSystem /usr/local/tomcat/webapps/ROOT

RUN mkdir -p /usr/local/tomcat/webapps/maven-archiver

ADD config/server.xml /usr/local/tomcat/webapps/config

ADD code/target/maven-archiver /usr/local/tomcat/webapps/maven-archiver

ADD code/target/AssessmentSystem.war /usr/local/tomcat/webapps/ROOT.war



