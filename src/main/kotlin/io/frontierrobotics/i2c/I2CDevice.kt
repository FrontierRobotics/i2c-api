package io.frontierrobotics.i2c


data class I2CDevice(val busAddress: I2CAddress, val internalAddress: I2CAddress?)
{
    fun isValid(): Boolean
    {
        return busAddress.isValid() && (internalAddress?.isValid() ?: true)
    }
}