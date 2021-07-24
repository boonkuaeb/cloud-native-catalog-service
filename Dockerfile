FROM  adoptopenjdk/openjdk16:jre-16.0.1_9

ARG JAR_FILE=target/cloud-native-catalog-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} cloud-native-catalog-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "cloud-native-catalog-service-0.0.1-SNAPSHOT.jar"]