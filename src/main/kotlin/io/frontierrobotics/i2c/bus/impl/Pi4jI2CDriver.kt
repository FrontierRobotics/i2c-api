package io.frontierrobotics.i2c.bus.impl

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CFactory
import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CData
import io.frontierrobotics.i2c.bus.I2CDriver
import org.springframework.stereotype.Service

@Service
class Pi4jI2CDriver : I2CDriver, AutoCloseable
{
    val bus = I2CFactory.getInstance(I2CBus.BUS_1)
    val device = bus.getDevice(0x1A)

    override fun send(data: I2CData, address: I2CAddress, internalAddress: Byte?)
    {
        val bytes = data.asByteArray()

        bus.getDevice(address.value.toInt())

        if (internalAddress != null)
        {
            device.write(internalAddress.toInt(), bytes, 0, bytes.size)
        } else
        {
            device.write(bytes, 0, bytes.size)
        }
    }

    override fun close()
    {
        bus.close()
    }
}