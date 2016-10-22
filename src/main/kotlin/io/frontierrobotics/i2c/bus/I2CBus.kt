package io.frontierrobotics.i2c.bus

import io.frontierrobotics.i2c.bus.driver.I2CDriver

class I2CBus(val driver: I2CDriver, val selfAddress: I2CAddress)
{
    constructor(driver: I2CDriver, address: Int) : this(driver, address.toI2CAddress())

    fun send(data: I2CData, address: I2CAddress, internalAddress: I2CAddress? = null)
    {
        if (!isAddressValid(address) || (internalAddress != null && !internalAddress.isValid()))
        {
            throw IllegalArgumentException("$address is not a valid I2C address.")
        }

        driver.send(data, address, internalAddress)
    }

    fun isAddressValid(address: I2CAddress): Boolean
    {
        return address.isValid() && selfAddress != address
    }
}