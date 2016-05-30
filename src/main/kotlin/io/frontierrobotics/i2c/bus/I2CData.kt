package io.frontierrobotics.i2c.bus

data class I2CData(val data: String)
{
    fun asByteArray(): ByteArray
    {
        return data.toByteArray(Charsets.UTF_8)
    }
}