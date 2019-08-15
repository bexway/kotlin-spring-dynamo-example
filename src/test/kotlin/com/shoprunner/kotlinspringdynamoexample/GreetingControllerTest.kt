package com.shoprunner.kotlinspringdynamoexample

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.mock
import org.mockito.Mockito

import com.shoprunner.kotlinspringdynamoexample.dynamo.User
import com.shoprunner.kotlinspringdynamoexample.dynamo.UserCompositeKey
import com.shoprunner.kotlinspringdynamoexample.dynamo.UserRepository

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GreetingControllerTest {
    private val userRepository = mock(UserRepository::class.java)
    private val controller = GreetingController(userRepository)

    @Test
    fun `user GET request 200`() {
        val mockedUserList = listOf(User(UserCompositeKey("mock name", "mock last name")))
        Mockito.`when`(userRepository.findByFirstName("mock name"))
            .thenReturn(mockedUserList)
        assertThat(controller.user("mock name")).isEqualTo(mockedUserList)
    }

}
