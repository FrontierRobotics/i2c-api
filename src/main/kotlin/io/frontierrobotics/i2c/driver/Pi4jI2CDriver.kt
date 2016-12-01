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
        val buffer = data.asByteArray()
        val pi4jDevice = bus.getDevice(device.busAddress.toInt())

        if (device.internalAddress != null)
        {
            pi4jDevice.write(device.internalAddress.toInt(), buffer, 0, buffer.size)
        }
        else
        {
            pi4jDevice.write(buffer, 0, buffer.size)
        }
    }

    override fun receive(device: I2CDevice, size: Int): I2CData
    {
        val buffer = ByteArray(size)
        val pi4jDevice = bus.getDevice(device.busAddress.toInt())

        if (device.internalAddress != null)
        {
            pi4jDevice.read(device.internalAddress.toInt(), buffer, 0, buffer.size)
        }
        else
        {
            pi4jDevice.read(buffer, 0, buffer.size)
        }

        return I2CData(buffer)
    }

    override fun close() = bus.close()
}