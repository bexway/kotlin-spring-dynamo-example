package com.shoprunner.kotlinspringdynamoexample.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

@DynamoDBDocument
data class TitleIntro(
    @DynamoDBAttribute
    var business: String? = null,
    @DynamoDBAttribute
    var casual: String? = null
)
