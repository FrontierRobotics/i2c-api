package io.frontierrobotics.i2c.bus

import com.pi4j.io.i2c.I2CFactory
import org.springframework.stereotype.Service

@Service
class Pi4jI2CBus : I2CBus, AutoCloseable {
    val bus = I2CFactory.getInstance(com.pi4j.io.i2c.I2CBus.BUS_1)
    val device = bus.getDevice(0x1A)

    override fun send(data: I2CData, address: I2CAddress, internalAddress: Byte?)
    {
        val bytes = data.asByteArray()

        bus.getDevice(address.value.toInt())
        println(data)
        device.write(bytes, 0, bytes.size)
    }

    override fun close() {
        bus.close()
    }
}