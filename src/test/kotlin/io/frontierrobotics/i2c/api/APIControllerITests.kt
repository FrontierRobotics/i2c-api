package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.I2CBus
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringJUnit4ClassRunner::class)
class APIControllerITests
{
    var mockMvc: MockMvc? = null

    fun setup()
    {
        val mockDriver = Mockito.`mock`(I2CBus::class.java)
        mockMvc = MockMvcBuilders.standaloneSetup(APIController(mockDriver)).build()
    }
}