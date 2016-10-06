package io.frontierrobotics.i2c.bus

fun Int.toI2CAddress(): I2CAddress
{
    return I2CAddress(this)
}

data class I2CAddress(val value: Int)
{
    val reserved = intArrayOf(0x1B)

    fun isValid(): Boolean
    {
        return !isReserved() && isInRange()
    }

    fun isInRange(): Boolean = value in 0..255

    fun isReserved(): Boolean = reserved.contains(value)

    fun toInt(): Int = value
}