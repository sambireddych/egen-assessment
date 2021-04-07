FROM openjdk:8

ADD build/libs/shipping-service-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar","/opt/app/shipping-service-0.0.1-SNAPSHOT/bin/shipping-service"]