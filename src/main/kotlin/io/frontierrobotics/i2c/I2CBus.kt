package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.driver.I2CDriver

class I2CBus(val driver: I2CDriver, val selfAddress: I2CAddress)
{
    constructor(driver: I2CDriver, address: Int) : this(driver, address.toI2CAddress())

    fun send(device: I2CDevice, data: I2CData)
    {
        if (!isDeviceValid(device))
        {
            throw IllegalArgumentException("$device is not a valid I2C device.")
        }

        driver.send(device, data)
    }

    fun receive(device: I2CDevice, size: Int): I2CData
    {
        if (!isDeviceValid(device))
        {
            throw IllegalArgumentException("$device is not a valid I2C device.")
        }

        return driver.receive(device, size)
    }

    fun isDeviceValid(device: I2CDevice) = device.isValid() && selfAddress != device.busAddress
}