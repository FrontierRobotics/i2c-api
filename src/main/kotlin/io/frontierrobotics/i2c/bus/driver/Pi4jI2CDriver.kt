package io.frontierrobotics.i2c.bus.driver

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CFactory
import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CData

class Pi4jI2CDriver : I2CDriver, AutoCloseable
{
    val bus = I2CFactory.getInstance(I2CBus.BUS_1)

    override fun send(data: I2CData, address: I2CAddress, internalAddress: I2CAddress?)
    {
        val bytes = data.asByteArray()
        val device = bus.getDevice(address.toInt())

        if (internalAddress != null)
        {
            device.write(internalAddress.toInt(), bytes, 0, bytes.size)
        }
        else
        {
            device.write(bytes, 0, bytes.size)
        }
    }

    override fun close()
    {
        bus.close()
    }
}