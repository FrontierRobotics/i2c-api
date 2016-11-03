package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.api.Controller
import io.frontierrobotics.i2c.api.Server
import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.driver.Pi4jI2CDriver

fun main(args: Array<String>)
{
    val driver = Pi4jI2CDriver()
    val bus = I2CBus(driver, 0x1B)
    val i2cController = Controller(bus)
    val server = Server(i2cController)

    server.start()
}