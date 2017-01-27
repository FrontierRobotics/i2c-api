package io.frontierrobotics.i2c.driver

import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice
import io.frontierrobotics.i2c.api.Controller
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class NoOpDriver : I2CDriver
{
    val log: Logger = LoggerFactory.getLogger(Controller::class.java)

    override fun send(device: I2CDevice, data: I2CData)
    {
        log.info("No-Op Send")
    }

    override fun receive(device: I2CDevice, size: Int): I2CData
    {
        log.info("No-Op Receive")

        return I2CData("Empty")
    }
}