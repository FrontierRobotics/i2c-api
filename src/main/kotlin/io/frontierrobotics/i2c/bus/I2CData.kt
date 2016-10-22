package io.frontierrobotics.i2c.bus

fun Any.toI2CData(): I2CData
{
    return I2CData(this.toString())
}

data class I2CData(val data: String)
{
    constructor() : this("")

    fun asByteArray(): ByteArray
    {
        return data.toByteArray(Charsets.UTF_8)
    }
}