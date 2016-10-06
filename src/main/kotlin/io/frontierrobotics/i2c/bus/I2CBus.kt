package io.frontierrobotics.i2c.bus

import io.frontierrobotics.i2c.bus.driver.I2CDriver

class I2CBus(val driver: I2CDriver)
{
    fun send(data: I2CData, address: I2CAddress, internalAddress: I2CAddress? = null)
    {
        if (!address.isValid() || (internalAddress != null && !internalAddress.isValid()))
        {
            throw IllegalArgumentException("$address is not a valid I2C address.")
        }

        driver.send(data, address, internalAddress)
    }
}