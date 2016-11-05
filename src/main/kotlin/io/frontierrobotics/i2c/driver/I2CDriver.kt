package io.frontierrobotics.i2c.driver

import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice

interface I2CDriver
{
    fun send(device: I2CDevice, data: I2CData)
}