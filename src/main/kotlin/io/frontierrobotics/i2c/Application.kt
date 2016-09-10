package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.api.I2CController
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.driver.Pi4jI2CDriver
import org.wasabifx.wasabi.app.AppServer

fun main(args: Array<String>)
{
    val driver = Pi4jI2CDriver()
    val bus = I2CBus(driver)
    val i2cController = I2CController(bus)
    val appServer = AppServer()

    appServer.put("/bus/{bus}/address/{address}", i2cController.sendCommand )
    appServer.put("/bus/{bus}/address/{address}/internal_address/{internal_address}", i2cController.sendCommand )

    appServer.start()
}