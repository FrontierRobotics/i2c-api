package io.frontierrobotics.i2c.bus

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice
import io.frontierrobotics.i2c.driver.I2CDriver
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFailsWith

class I2CBusSpecs : Spek({
    describe("an I2C Buss")
    {
        val driver: I2CDriver = mock()
        val bus = I2CBus(driver, 0x1B)

        on("sending a command")
        {
            it("should send the command over the bus")
            {
                val data = I2CData("hello")
                val device = I2CDevice(0x1C)

                bus.send(device, data)

                verify(driver).send(device, data)
            }
        }

        on("sending a command to an internal address")
        {
            it("should send the command over the bus")
            {
                val data = I2CData("hello")
                val device = I2CDevice(0x1C, 0x01)

                bus.send(device, data)

                verify(driver).send(device, data)
            }
        }

        on("sending a command to a reserved address")
        {
            it("should return an error")
            {
                val data = I2CData("hello")
                val device = I2CDevice(0x1B)

                assertFailsWith<IllegalArgumentException> {
                    bus.send(device, data)
                }
            }
        }
    }
})