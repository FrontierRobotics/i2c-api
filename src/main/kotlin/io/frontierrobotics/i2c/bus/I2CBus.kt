package io.frontierrobotics.i2c.bus


class I2CBus(val driver: I2CDriver)
{
    fun send(data: I2CData, address: I2CAddress, internalAddress: Byte? = null)
    {
        if (!address.isValid())
        {
            //TODO
            throw RuntimeException("Not a valid I2C address.")
        }

        driver.send(data, address, internalAddress)
    }
}