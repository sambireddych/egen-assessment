FROM openjdk:8

ADD build/libs/billingpayment-service-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8082

ENTRYPOINT ["java", "-jar","/opt/app/billingpayment-service-0.0.1-SNAPSHOT/bin/billingpayment-service"]