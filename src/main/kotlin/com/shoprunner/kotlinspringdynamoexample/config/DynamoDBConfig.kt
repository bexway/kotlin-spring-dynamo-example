package com.shoprunner.kotlinspringdynamoexample.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableDynamoDBRepositories("com.shoprunner.kotlinspringdynamoexample.dynamo")
class DynamoDBConfig {

    @Value("\${config.env}")
    var env: String? = null

    // Only needed to load localhost endpoint for development
    // With established AWS configuration, endpoint isn't needed
    @Value("\${aws.dynamodb.endpoint:}")
    var amazonDynamoDBEndpoint: String? = null

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        val builder = AmazonDynamoDBClientBuilder.standard()

        println ( "DB aws.dynamodb.endpoint = $amazonDynamoDBEndpoint" )

        if (amazonDynamoDBEndpoint?.isNotBlank() ?: false) {
            println ( "Using local Dynamo DB." )
            builder.withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "us-east-1"))
        }

        return builder.build()
    }

    // @Suppress("DEPRECATION")
    // @Bean
    // fun dynamoDBMapperConfig(): DynamoDBMapperConfig {
    //     // From https://github.com/derjust/spring-data-dynamodb/wiki/Alter-table-name-during-runtime
    //     val builder = DynamoDBMapperConfig.Builder()
    //         .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix("${env}_"))
    //
    //     // Sadly this is a @deprecated method but new DynamoDBMapperConfig.Builder() is
    //     // incomplete compared to DynamoDBMapperConfig.DEFAULT
    //     val config = DynamoDBMapperConfig(DynamoDBMapperConfig.DEFAULT, builder.build())
    //
    //     return config
    // }
    //
    // // Needed to get the custom client and mapperconfig above.
    // @Bean
    // @Primary
    // fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB, dynamoDBMapperConfig: DynamoDBMapperConfig): DynamoDBMapper {
    //     return DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig)
    // }
}
