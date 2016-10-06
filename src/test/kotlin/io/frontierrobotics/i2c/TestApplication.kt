package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.api.I2CController
import io.frontierrobotics.i2c.api.Server
import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CData
import io.frontierrobotics.i2c.bus.driver.I2CDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main(args: Array<String>)
{
    val log: Logger = LoggerFactory.getLogger("main")

    val driver = object : I2CDriver
    {
        override fun send(data: I2CData, address: I2CAddress, internalAddress: I2CAddress?)
        {
            log.info(data.toString())
        }

    }

    val bus = I2CBus(driver)
    val i2cController = I2CController(bus)
    val server = Server(i2cController)

    server.start()
}