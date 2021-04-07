FROM openjdk:8

ADD build/libs/orders-service-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080
#RUN jar -xvf order-manager-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","/opt/app/orders-service-0.0.1-SNAPSHOT/bin/orders-service"]