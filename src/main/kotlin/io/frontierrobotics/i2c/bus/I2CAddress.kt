package io.frontierrobotics.i2c.bus

fun String.toI2CAddress(): I2CAddress
{
    return this.toInt().toI2CAddress()
}

fun Int.toI2CAddress(): I2CAddress
{
    return I2CAddress(this)
}

data class I2CAddress(val value: Int)
{
    fun isValid(): Boolean
    {
        return isInRange()
    }

    fun isInRange(): Boolean = value in 0..255

    fun toInt(): Int = value
}