package com.shoprunner.kotlinspringdynamoexample.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

@DynamoDBDocument
data class UserTitle(
    @DynamoDBAttribute
    var intro: Map<String, String>? = null,
    @DynamoDBAttribute
    var prefix: String? = null,
    @DynamoDBAttribute
    var suffix: String? = null
)
