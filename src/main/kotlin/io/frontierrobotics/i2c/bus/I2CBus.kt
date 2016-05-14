package io.frontierrobotics.i2c.bus


interface I2CBus {
    fun send(command: String)
}