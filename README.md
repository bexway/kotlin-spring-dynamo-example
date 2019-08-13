# Using Kotlin and Data Classes to set up Spring Boot with Dynamo DB

Set up a Spring Boot service to read and output data from a [Dynamo DB](https://aws.amazon.com/dynamodb/).

## Tools
* [AWS Dynamo DB](https://aws.amazon.com/dynamodb/)
* [AWS Dynamo DB Docker Image](https://hub.docker.com/r/amazon/dynamodb-local/)
* [Spring Data DynamoDB](https://github.com/derjust/spring-data-dynamodb)

## Process

### Step 1: Spring Application

Follow the [KotlinLang guide for creating a Spring Boot Rest Service](https://kotlinlang.org/docs/tutorials/spring-boot-restful.html). 

If done correctly, you should be able to run `gradle bootRun` and access your service at `http://localhost:8080/greeting?name=kotlin`


### Step 2: Dynamo DB Data source

To connect to DynamoDB, we need to start by adding new dependencies to gradle:
1) Jackson (for JSON handling)
1) Reflect (for Spring Boot classes)
1) AWS Java SDK Dynamo (for Dynamo annotations and functions)
1) Spring Data DynamoDB (for Entity scanning and easier data retrieval)

Next, create a class to represent your table based on [the example from Spring Data DynamoDB](https://github.com/derjust/spring-data-dynamodb-examples/blob/master/src/main/java/com/github/derjust/spring_data_dynamodb_examples/simple/User.java). This should point to the table you'll be using, and specify some object attributes to look for. For now, we only need a Hash Key. Importantly, default values must be provided to the parameters in the arguments of the data class. The AWS Dynamo SDK depends on the parameter-less constructor, and this constructor is created when all arguments of the class have default values. See the NOTE section of Kotlin's [Secondary Constructors](https://kotlinlang.org/docs/reference/classes.html#secondary-constructors).

Create a class for the Repository, which will retrieve the data specified in your class from the relevant table. (The User class specifies what to retrieve, and the Repository handles the retrieval. Spring Data DynamoDB acts as a JPA (Java Persistence API) and handles the boilerplate of querying the table.)

Create a configuration to connect the REST service with a Dynamo DB. For the purposes of this example, I'll set it up for a Docker image running a DynamoDB. The environment, endpoint, and some table configuration are specified in the application.yml file. A Spring @Configuration class specifies where the Table/Repository classes are, and establishes the connection to the database. If no endpoint is provided, the AWS CLI configuration is used.

Finally, add the Repository created earlier to your Controller (letting Spring's dependency injection create the instance), create a route to query your table, and test that your Application spins up. For easy testing of data retrieval, spin up an aws dynamo-local Docker image and add some data with the AWS CLI. (If you're feeling ambitious, you can populate the table with dummy data using a Gradle task ([see createLocalDynamoDBData](./build.gradle)) and some [Dynamo DML](./src/main/dynamodml/user_dml.json))

If you see errors from missing classes, you may be missing a dependency. If you see `ResourceNotFoundException: Cannot do operations on a non-existent table`, you haven't created your table. You can create the table by using the AWS CLI, or by going to application.yml and ensuring that entity2ddl>auto is set to `create-only`. If you see `java.lang.NoSuchMethodException: com.shoprunner.kotlinspringdynamoexample.dynamo.User.<init>()`, the arguments for your table's data class need defaults
