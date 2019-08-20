package com.shoprunner.kotlinspringdynamoexample.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id

@DynamoDBTable(tableName = "user")
data class User (
    @field:Id
    @DynamoDBIgnore
    @JsonIgnore
    val compositeKey: UserCompositeKey = UserCompositeKey()
) {
    // Key annotations are still required
    @DynamoDBHashKey
    fun getFirstName() = compositeKey.firstName

    fun setFirstName(firstName: String) {
        compositeKey.firstName = firstName
    }

    @DynamoDBRangeKey
    fun getLastName() = compositeKey.lastName

    fun setLastName(lastName: String) {
        compositeKey.lastName = lastName
    }

    @DynamoDBAttribute(attributeName = "title")
    var userTitle: UserTitle? = null
}
