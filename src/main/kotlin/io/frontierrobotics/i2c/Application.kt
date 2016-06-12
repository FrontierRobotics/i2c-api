package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.driver.Pi4jI2CDriver
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
open class Application

@Configuration
open class Configuration
{
    @Bean
    open fun bus() : I2CBus{
        val driver = Pi4jI2CDriver()

        return I2CBus(driver)
    }
}

fun main(args: Array<String>)
{
    SpringApplication.run(Application::class.java, *args)
}