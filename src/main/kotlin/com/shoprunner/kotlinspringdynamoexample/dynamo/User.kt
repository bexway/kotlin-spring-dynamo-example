package com.shoprunner.kotlinspringdynamoexample.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "user")
data class User (
    @DynamoDBHashKey
    var firstName: String
) {
    constructor() : this("")
    @DynamoDBAttribute
    var lastName: String? = null
    @DynamoDBAttribute(attributeName = "title")
    var userTitle: Map<String, String>? = null
}
