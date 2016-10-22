package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.wasabifx.wasabi.routing.RouteHandler


class I2CController(val bus: I2CBus, val log: Logger = LoggerFactory.getLogger(I2CController::class.java))
{
    val sendCommand = i2cRouteHandler { address, internalAddress ->
        val data = request.bodyParams["data"]?.toI2CData() ?: I2CData()

        log.info("Address: $address, Internal Address: $internalAddress, Data: $data")

        bus.send(data, address, internalAddress)

//        response.send(Result.SUCCESS)
        response.send("hello")
    }

    private fun i2cRouteHandler(f: RouteHandler.(address: I2CAddress, internalAddress: I2CAddress?) -> Unit): RouteHandler.() -> Unit = {
        val address = request.routeParams["address"]?.toI2CAddress()
        val internalAddress = request.routeParams["internal_address"]?.toI2CAddress()

        if (address == null || !bus.isAddressValid(address))
        {
            response.setStatus(400, "Validation Error!")
            response.send("$address is not a valid I2C address.")
        }
        else if (internalAddress != null && !internalAddress.isValid())
        {
            response.setStatus(400, "Validation Error!")
            response.send("$address is not a valid internal I2C address.")
        }
        else
        {
            f(address, internalAddress)
        }
    }
}