package io.frontierrobotics.i2c

import io.frontierrobotics.i2c.api.I2CController
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.driver.Pi4jI2CDriver
import org.wasabifx.wasabi.app.AppServer
import org.wasabifx.wasabi.interceptors.enableContentNegotiation

fun main(args: Array<String>)
{
//    val driver = object : I2CDriver
//    {
//        override fun send(data: I2CData, address: I2CAddress, internalAddress: Byte?)
//        {
//            print(data)
//        }
//
//    }

    val driver = Pi4jI2CDriver()
    val bus = I2CBus(driver)
    val i2cController = I2CController(bus)
    val server = AppServer()

    server.enableContentNegotiation()
    server.put("/bus/:bus/address/:address", i2cController.sendCommand)
    server.put("/bus/:bus/address/:address/internal_address/:internal_address", i2cController.sendCommand)

    server.start()
}