package io.frontierrobotics.i2c.bus

interface I2CDriver
{
    fun send(data: I2CData, address: I2CAddress, internalAddress: Byte? = null)
}