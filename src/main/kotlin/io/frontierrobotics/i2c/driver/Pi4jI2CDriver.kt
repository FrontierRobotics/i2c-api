package io.frontierrobotics.i2c.driver

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CFactory
import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice

class Pi4jI2CDriver : I2CDriver, AutoCloseable
{
    val bus = I2CFactory.getInstance(I2CBus.BUS_1)

    override fun send(device: I2CDevice, data: I2CData)
    {
        val bytes = data.asByteArray()
        val pi4jDevice = bus.getDevice(device.busAddress.toInt())

        if (device.internalAddress != null)
        {
            pi4jDevice.write(device.internalAddress.toInt(), bytes, 0, bytes.size)
        }
        else
        {
            pi4jDevice.write(bytes, 0, bytes.size)
        }
    }

    override fun close()
    {
        bus.close()
    }
}