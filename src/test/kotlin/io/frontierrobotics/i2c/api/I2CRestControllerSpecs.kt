package io.frontierrobotics.i2c.api

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CData
import io.frontierrobotics.i2c.bus.driver.I2CDriver
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class I2CRestControllerSpecs : Spek()
{
    init
    {
        val driver: I2CDriver = mock()
        val bus = I2CBus(driver)
        val controller = I2CRestController(bus)

        given("a valid address and data")
        {
            on("sending a command")
            {
                it("should send the command over the bus")
                {
                    controller.sendCommand(0x1C, "hello")
                    val data = I2CData("hello")
                    val address = I2CAddress(0x1C)

                    verify(driver).send(data, address)
                }

                it("should return success")
                {
                    val result = controller.sendCommand(0x1C, "hello")

                    assertEquals("Success", result.body.data)
                }
            }

            on("sending a command to an internal address")
            {
                it("should send the command over the bus")
                {
                    controller.sendCommandToInternalAddress(0x1C, 0x01, "hello")
                    val data = I2CData("hello")
                    val address = I2CAddress(0x1C)
                    val internalAddress: Byte = 0x01

                    verify(driver).send(data, address, internalAddress)
                }

                it("should return success")
                {
                    val result = controller.sendCommandToInternalAddress(0x1C, 0x01, "hello")

                    assertEquals("Success", result.body.data)
                }
            }
        }
        given("a reserved address")
        {
            on("sending a command")
            {
                it("should return an error")
                {
                    val result = controller.sendCommand(0x1A, "hello")

                    assertEquals("Not a valid I2C address.", result.body.error)
                }
            }
        }
    }
}