package io.frontierrobotics.i2c.bus


interface I2CBus
{
    fun send(data: I2CData, address: I2CAddress, internalAddress: Byte? = null)
}