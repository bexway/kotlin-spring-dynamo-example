package com.shoprunner.kotlinspringdynamoexample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import com.shoprunner.kotlinspringdynamoexample.dynamo.UserRepository
import com.shoprunner.kotlinspringdynamoexample.dynamo.User
import com.shoprunner.kotlinspringdynamoexample.dynamo.UserId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class UserController(
    val userRepository: UserRepository
) {
    @GetMapping("/user/{firstName}")
    fun user(@PathVariable firstName: String): List<User> =
        userRepository.findByFirstName(firstName)

    @PostMapping("/save")
    fun save(@RequestBody userId: UserId): User =
        userRepository.save(User(userId))

    @DeleteMapping("/delete")
    fun delete(@RequestBody userId: UserId) =
        userRepository.deleteById(userId)

    @DeleteMapping("/deleteall")
    fun deleteAll() =
        userRepository.deleteAll()

}
