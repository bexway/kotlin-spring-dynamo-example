package com.shoprunner.kotlinspringdynamoexample.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

@DynamoDBDocument
data class UserTitle(
    @DynamoDBAttribute
    var intro: TitleIntro? = null,
    @DynamoDBAttribute
    var prefix: String? = null,
    @DynamoDBAttribute
    var suffix: String? = null
)
