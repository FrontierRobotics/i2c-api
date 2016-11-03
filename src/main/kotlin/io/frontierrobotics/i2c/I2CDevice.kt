package io.frontierrobotics.i2c

data class I2CDevice(val busAddress: I2CAddress, val internalAddress: I2CAddress? = null)
{
    constructor(busAddress: Int, internalAddress: Int? = null) : this(busAddress.toI2CAddress(), internalAddress?.toI2CAddress())

    fun isValid(): Boolean
    {
        return busAddress.isValid() && (internalAddress?.isValid() ?: true)
    }
}