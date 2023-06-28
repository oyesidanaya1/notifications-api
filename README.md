# Usage

* Make sure to use a jdk of Java 17
* Build the app using `mvn clean install`
* Run the app in your favourite IDE or running `mvn spring-boot:run`
* To test the Notifications API endpoint you could have different options:
  * Go to the swagger documentation at http://localhost:9090/swagger-ui/index.html And execute the published API there.
  * Import in postman the sample collection located in __docs__ directory
  * Through a simple curl command like `curl --location 'http://localhost:9090/notifications/publish' \
    --data '{
    "category": "sports",
    "message": "Lionel Messi is the new player for Inter of Miami!!!"
    }'`

* Check the log in the console to review the users that were notified of the message.
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

