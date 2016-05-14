package io.frontierrobotics.i2c.bus

import org.springframework.stereotype.Service

@Service
class Pi4jI2CBus : I2CBus {
    override fun send(command: String) {
        println(command)
    }
}