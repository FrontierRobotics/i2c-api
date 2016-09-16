package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CData
import org.wasabifx.wasabi.routing.routeHandler

class I2CController constructor(val bus: I2CBus)
{
    val sendCommand = routeHandler {
        val address = request.routeParams["address"]?.toByte()
        val internalAddress = request.routeParams["internal_address"]?.toByte()
        val data = request.routeParams["data"]

        print("Howdy!")

        if(address != null && (data != null))
        {
            val busAddress = I2CAddress(address)
            val i2cdata = I2CData(data)

            bus.send(i2cdata, busAddress, internalAddress)
        }

//        response.send(Result.SUCCESS)
        response.send("hello")

//        next()
    }
}