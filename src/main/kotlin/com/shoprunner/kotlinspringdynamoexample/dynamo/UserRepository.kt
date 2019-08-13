package com.shoprunner.kotlinspringdynamoexample.dynamo

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface UserRepository : CrudRepository<User, String> {
    fun findByLastName(lastName: String): List<User>
    fun findByFirstName(firstName: String): List<User>
}
