package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice
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
            log.info("Mock sending $data to $device")
        }

        override fun receive(device: I2CDevice, size: Int): I2CData
        {
            log.info("Mock receiving $size bytes from $device")
            return I2CData(ByteArray(size))
        }
    }

    val bus = I2CBus(driver, 0x1B)
    val i2cController = Controller(bus)
    val server = Server(i2cController)

    server.start()
}
