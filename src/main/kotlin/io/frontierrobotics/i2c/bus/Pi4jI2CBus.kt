package io.frontierrobotics.i2c.bus

import com.pi4j.io.i2c.I2CFactory
import org.springframework.stereotype.Service

@Service
class Pi4jI2CBus : I2CBus {

    val bus = I2CFactory.getInstance(com.pi4j.io.i2c.I2CBus.BUS_1)
    val dives= bus.getDevice(0x1A)

    override fun send(command: String) {
        println(command)
        dives.write(0X02)
    }
}