# 基础镜像
FROM java:8
VOLUME /tmp
#复制jar包到镜像中，并且将名字改成app.jar
ADD springboot_project.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
