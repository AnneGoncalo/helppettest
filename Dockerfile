<<<<<<< HEAD
FROM persapiens/openjre:8u121
=======
FROM persapiens/openjre:8u131
>>>>>>> 8daeef80b10131ae53f58fa24aa75ef7c7efef07

MAINTAINER Marcelo Fernandes "marcelo.fernandes@ifrn.edu.br"

VOLUME /tmp

ADD target/*.jar /app.jar

RUN sh -c 'touch /app.jar'

EXPOSE 8080

ENV JAVA_OPTS=""

ENV SPRING_BOOT_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar $SPRING_BOOT_OPTS" ]
