package io.frontierrobotics.i2c.bus.driver

import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CData

interface I2CDriver
{
    fun send(data: I2CData, address: I2CAddress, internalAddress: I2CAddress? = null)
}