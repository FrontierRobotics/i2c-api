package io.frontierrobotics.i2c.api

import org.wasabifx.wasabi.app.AppServer
import org.wasabifx.wasabi.interceptors.enableContentNegotiation

class Server (val i2cController: I2CController)
{
    fun start()
    {
        val server = AppServer()

        server.enableContentNegotiation()
        server.put("/bus/:bus/address/:address", i2cController.sendCommand)
        server.put("/bus/:bus/address/:address/internal_address/:internal_address", i2cController.sendCommand)

        server.exception(IllegalArgumentException::class, {
            response.setStatus(418, "My brew is not as strong as yours!")
            response.send("Out of beans: ${exception.message}")
        })

        server.start()
    }
}