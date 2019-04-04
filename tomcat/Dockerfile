FROM tomcat:7.0

ADD config/server.xml /usr/local/tomcat/webapps/config

ADD config/ini /usr/local/tomcat/ini

RUN mkdir -p /usr/local/java

ADD environment/package/java /usr/local/java 

RUN ln -s /usr/local/java/jdk-7u80-linux-x64/jdk1.7.0_80/bin/javac /usr/bin/javacExercises 

RUN ln -s /usr/local/java/jdk-7u80-linux-x64/jdk1.7.0_80/bin/java /usr/bin/javaExercises

RUN rm -rf /usr/local/tomcat/webapps/ROOT


