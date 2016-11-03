package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.driver.I2CDriver

class I2CBus(val driver: I2CDriver, val selfAddress: I2CAddress)
{
    constructor(driver: I2CDriver, address: Int) : this(driver, address.toI2CAddress())

    fun send(device: I2CDevice, data: I2CData)
    {
        if (!device.isValid())
        {
            throw IllegalArgumentException("$device is not a valid I2C device.")
        }

        driver.send(device, data)
    }

    fun isDeviceValid(device: I2CDevice): Boolean
    {
        return device.isValid() && selfAddress != device.busAddress
    }
}