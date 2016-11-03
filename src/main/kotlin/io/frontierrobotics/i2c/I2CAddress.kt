package io.frontierrobotics.i2c

fun String.toI2CAddress() = this.toInt().toI2CAddress()

fun Int.toI2CAddress() = I2CAddress(this)

data class I2CAddress(val value: Int)
{
    fun isValid() = isInRange()

    fun isInRange() = value in 0..255

    fun toInt() = value

    override fun toString(): String = java.lang.String.format("0x%02X", toInt())
}