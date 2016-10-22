package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.api.I2CController
import io.frontierrobotics.i2c.api.Server
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.driver.Pi4jI2CDriver

fun main(args: Array<String>)
{
    val driver = Pi4jI2CDriver()
    val bus = I2CBus(driver, 0x1B)
    val i2cController = I2CController(bus)
    val server = Server(i2cController)

    server.start()
}