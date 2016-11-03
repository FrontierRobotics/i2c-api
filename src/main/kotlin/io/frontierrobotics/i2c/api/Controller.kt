package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.wasabifx.wasabi.routing.RouteHandler

class Controller(val bus: I2CBus, val log: Logger = LoggerFactory.getLogger(Controller::class.java))
{
    val sendCommand = i2cRouteHandler { device ->
        val data = request.bodyParams["data"]?.toI2CData() ?: I2CData()

        log.info("Sending $data to $device")

        bus.send(device, data)

//        response.send(Result.SUCCESS)
        response.send("hello")
    }

    val receiveCommand = i2cRouteHandler { device ->
        val size = request.queryParams["size"]?.toString()?.toInt() ?: 0

        log.info("Requesting $size bytes from: $device")

        val data = bus.receive(device, size)

//        response.send(Result.SUCCESS)
        response.send(data.toString())
    }

    private fun i2cRouteHandler(routeHandler: RouteHandler.(device: I2CDevice) -> Unit): RouteHandler.() -> Unit = {
        val address = request.routeParams["address"]?.toI2CAddress()
        val internalAddress = request.routeParams["internal_address"]?.toI2CAddress()

        if (address == null)
        {
            response.setStatus(400, "Validation Error!")
            response.send("Must specify an address.")
        }
        else
        {
            val device = I2CDevice(address, internalAddress)

            if (!bus.isDeviceValid(device))
            {
                response.setStatus(400, "Validation Error!")
                response.send("$device is not a valid I2C device.")
            }
            else
            {
                routeHandler(device)
            }
        }
    }
}