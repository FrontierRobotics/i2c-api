package io.frontierrobotics.i2c.api

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CDriver
import io.frontierrobotics.i2c.bus.I2CData
import org.jetbrains.spek.api.Spek

class APIControllerSpecs : Spek()
{
    init
    {
        val driver: I2CDriver = mock()
        val bus = I2CBus(driver)
        val controller = APIController(bus)

        given("a valid address and data")
        {
            on("sending a command")
            {
                it("should send the command over the bus")
                {
                    controller.sendComand(0x1C, "hello")
                    val data = I2CData("hello")
                    val address = I2CAddress(0x1C)

                    verify(driver).send(data, address)
                }
            }

            on("sending a command to an internal address")
            {
                it("should send the command over the bus")
                {
                    controller.sendComandToInternalAddress(0x1C, 0x01, "hello")
                    val data = I2CData("hello")
                    val address = I2CAddress(0x1C)
                    val internalAddress: Byte = 0x01

                    verify(driver).send(data, address, internalAddress)
                }
            }
        }
        given("a reserved address")
        {
            on("sending a command")
            {
                it("should return an error")
                {

                }
            }
        }
    }
}