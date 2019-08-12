# Using Kotlin and Data Classes to set up Spring Boot with Dynamo DB

Set up a Spring Boot service to read and output data from a [Dynamo DB](https://aws.amazon.com/dynamodb/).

## Tools
* [AWS Dynamo DB](https://aws.amazon.com/dynamodb/)
* [AWS Dynamo DB Docker Image]()
* [Spring Data DynamoDB](https://github.com/derjust/spring-data-dynamodb)

## Process

### Step 1: Spring Application

Follow the [KotlinLang guide for creating a Spring Boot Rest Service](https://kotlinlang.org/docs/tutorials/spring-boot-restful.html). 

If done correctly, you should be able to run `gradle bootRun` and access your service at `http://localhost:8080/greeting?name=kotlin`