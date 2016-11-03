package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.api.Controller
import io.frontierrobotics.i2c.api.Server
import io.frontierrobotics.i2c.I2CAddress
import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.driver.I2CDriver
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

    val bus = I2CBus(driver, 0x1B)
    val i2cController = Controller(bus)
    val server = Server(i2cController)

    server.start()
}
