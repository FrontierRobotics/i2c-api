package io.frontierrobotics.i2c

fun Any.toI2CData() = I2CData(this.toString())

data class I2CData(val data: String)
{
    constructor() : this("")

    constructor(buffer: ByteArray) : this(buffer.toString(Charsets.UTF_8))

    fun asByteArray() = data.toByteArray(Charsets.UTF_8)
}