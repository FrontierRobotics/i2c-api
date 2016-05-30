package io.frontierrobotics.i2c.bus

data class I2CAddress (val value: Byte) {
    val reserved = byteArrayOf(0x1A)

    fun isValid() : Boolean {
        return !isReserved()
    }

    fun isReserved() : Boolean {
        return reserved.contains(value)
    }
}