FROM java:8-alpine

ADD target/hello-api-clojure-0.0.1-SNAPSHOT-standalone.jar /hello-api-clojure/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/hello-api-clojure/app.jar"]
