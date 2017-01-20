package io.frontierrobotics.i2c.api

import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.driver.I2CDriver
import io.frontierrobotics.i2c.driver.NoOpDriver
import io.frontierrobotics.i2c.driver.Pi4jI2CDriver
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class Application {
//    @Bean
    open fun realDriver() = Pi4jI2CDriver()

    @Bean
    open fun noOpDriver() = NoOpDriver()

    @Bean
    open fun bus(driver: I2CDriver) = I2CBus(driver, 0x1B)

    @Bean
    open fun kotlinModule() = KotlinModule()
}

fun main(args: Array<String>)
{
    SpringApplication.run(Application::class.java, *args)
}