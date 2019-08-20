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
    val id: UserId = UserId()
) {
    // Key annotations are still required
    @DynamoDBHashKey
    fun getFirstName() = id.firstName

    fun setFirstName(firstName: String) {
        id.firstName = firstName
    }

    @DynamoDBRangeKey
    fun getLastName() = id.lastName

    fun setLastName(lastName: String) {
        id.lastName = lastName
    }

    @DynamoDBAttribute(attributeName = "title")
    var userTitle: UserTitle? = null
}
