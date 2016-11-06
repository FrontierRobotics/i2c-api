package io.frontierrobotics.i2c

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.frontierrobotics.i2c.driver.I2CDriver
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class I2CBusSpecs : Spek({
    describe("an I2C Buss")
    {
        val driver = mock<I2CDriver>()
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

        on("sending a command to an out-of-bounds address")
        {
            it("should return an error")
            {
                val data = I2CData("hello")
                val device = I2CDevice(0x1FF)

                assertFailsWith<IllegalArgumentException> {
                    bus.send(device, data)
                }
            }
        }

        on("receiving data")
        {
            it("should receive the data over the bus")
            {
                val device = I2CDevice(0x1C)
                val expected = I2CData("123")
                whenever(driver.receive(device, 3)).thenReturn(expected)

                val actual = bus.receive(device, 3)

                verify(driver).receive(device, 3)
                assertEquals(expected, actual)
            }
        }

        on("receiving a command from an internal address")
        {
            it("should receive the data over the bus")
            {
                val device = I2CDevice(0x1C, 0x01)
                val expected = I2CData("123")
                whenever(driver.receive(device, 3)).thenReturn(expected)

                val actual = bus.receive(device, 3)

                verify(driver).receive(device, 3)
                assertEquals(expected, actual)
            }
        }

        on("receiving a command from a reserved address")
        {
            it("should return an error")
            {
                val device = I2CDevice(0x1B)

                assertFailsWith<IllegalArgumentException> {
                    bus.receive(device, 3)
                }
            }
        }

        on("receiving a command from an out-of-bounds address")
        {
            it("should return an error")
            {
                val device = I2CDevice(0x1FF)

                assertFailsWith<IllegalArgumentException> {
                    bus.receive(device, 3)
                }
            }
        }
    }
})