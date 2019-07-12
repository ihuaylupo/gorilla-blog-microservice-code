<h1 align="center">
  <br>
  <img src="https://github.com/ihuaylupo/assets/raw/master/illy.png" alt="Illary Huaylupo" width="100px" height="100px"></a>
  <br>
 
  <br>
</h1>

## Microservices with Spring Boot and Spring Cloud Developer Tutorial

## Introduction

Code used in the blog Microservices with Spring Boot and Spring Cloud Developer Tutorial (https://gorillalogic.com/blog/microservices-with-spring-boot-and-spring-cloud-developer-tutorial/)


Remember I used the Amazon Cognito JWT configuration that I previously explained in my Java Integration with Amazon Cognito blog post (https://gorillalogic.com/blog/java-integration-with-amazon-cognito/), but you can use any other JWT configuration.

## Initial Configuration

1.	Apache Maven (http://maven.apache.org)
2.	Git Client (http://git-scm.com)

## How To Use

To clone and run this application, you'll need [Git](https://git-scm.com), [Maven](https://maven.apache.org/), [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). From your command line:

```bash
# Clone this repository
$ git clone https://github.com/ihuaylupo/gorilla-blog-microservice-code.git

# Go into the repository
$ cd gateway
$ cd eurekaServer
$ cd order
$ cd product
$ cd CognitoAuthentication

# Install dependencies
$ mvn install

# Run the app
mvn spring-boot:run
```
IMPORTANT If you are running the command from the command line, make sure you are in the root directory. The root directory is the one that contains the pom.xml file. Otherwise, you will run into the No plugin found for prefix 'spring-boot' in the current project and in the plugin groups error.

## Contact

I'd like you to send me an email on <illaryhs@gmail.com> about anything you'd want to say about this software.

### Contributing
Feel free to file an issue if it doesn't work for your code sample. Thanks.
