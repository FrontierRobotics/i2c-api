package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.driver.I2CDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class I2CBus(val driver: I2CDriver, val selfAddress: I2CAddress)
{
    constructor(driver: I2CDriver, address: Int) : this(driver, address.toI2CAddress())

    val log: Logger = LoggerFactory.getLogger(I2CBus::class.java)

    fun send(device: I2CDevice, data: I2CData)
    {
        if (!isDeviceValid(device))
        {
            throw InvalidDeviceException(device)
        }

        log.info("Sending $data to $device")

        driver.send(device, data)
    }

    fun receive(device: I2CDevice, size: Int? = 0): I2CData
    {
        if (!isDeviceValid(device))
        {
            throw InvalidDeviceException(device)
        }

        log.info("Requesting $size bytes from: $device")

        return driver.receive(device, size ?: 0)
    }

    fun isDeviceValid(device: I2CDevice) = device.isValid() && selfAddress != device.busAddress
}