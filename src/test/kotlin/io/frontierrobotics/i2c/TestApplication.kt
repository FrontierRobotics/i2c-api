package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.api.Controller
import io.frontierrobotics.i2c.api.Server
import io.frontierrobotics.i2c.driver.I2CDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main(args: Array<String>)
{
    val log: Logger = LoggerFactory.getLogger("main")

    val driver = object : I2CDriver
    {
        override fun send(device: I2CDevice, data: I2CData)
        {
            log.info(data.toString())
        }

    }

    val bus = I2CBus(driver, 0x1B)
    val i2cController = Controller(bus)
    val server = Server(i2cController)

    server.start()
}
