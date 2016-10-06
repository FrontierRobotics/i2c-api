package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CData
import io.frontierrobotics.i2c.bus.toI2CAddress
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.wasabifx.wasabi.routing.routeHandler

class I2CController(val bus: I2CBus, val log: Logger = LoggerFactory.getLogger(I2CController::class.java))
{
    val sendCommand = routeHandler {
        val address = request.routeParams["address"]?.toInt()
        val internalAddress = request.routeParams["internal_address"]?.toInt()
        val data = request.bodyParams["data"]?.toString()

        log.info("Address: $address, Internal Address: $internalAddress, Data: $data")

        if (address != null && (data != null))
        {
            val busAddress = I2CAddress(address)
            val i2cdata = I2CData(data)

            bus.send(i2cdata, busAddress, internalAddress?.toI2CAddress())
        }

//        response.send(Result.SUCCESS)
        response.send("hello")
    }
}