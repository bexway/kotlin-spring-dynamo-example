package com.shoprunner.kotlinspringdynamoexample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

import com.shoprunner.kotlinspringdynamoexample.dynamo.UserRepository
import com.shoprunner.kotlinspringdynamoexample.dynamo.User

@RestController
class GreetingController(
    val userRepository: UserRepository
) {
    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/user/{firstName}")
    fun user(@PathVariable firstName: String): List<User> {
        return userRepository.findByFirstName(firstName)
    }

}
