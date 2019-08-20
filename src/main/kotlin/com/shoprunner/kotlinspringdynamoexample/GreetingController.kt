package com.shoprunner.kotlinspringdynamoexample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import com.shoprunner.kotlinspringdynamoexample.dynamo.UserRepository
import com.shoprunner.kotlinspringdynamoexample.dynamo.User
import com.shoprunner.kotlinspringdynamoexample.dynamo.UserId

@RestController
class GreetingController(
    val userRepository: UserRepository
) {
    @GetMapping("/user/{firstName}")
    fun user(@PathVariable firstName: String): List<User> =
        userRepository.findByFirstName(firstName)

    @GetMapping("/save/{firstName}/{lastName}")
    fun save(@PathVariable firstName: String, @PathVariable lastName: String) =
        userRepository.save(User(UserId(firstName, lastName)))

    @GetMapping("/delete/{firstName}/{lastName}")
    fun delete(@PathVariable firstName: String, @PathVariable lastName: String) =
        userRepository.deleteById(UserId(firstName, lastName))

    @GetMapping("/deleteall")
    fun deleteAll() =
        userRepository.deleteAll()

}
