package io.frontierrobotics.i2c.api

import org.wasabifx.wasabi.app.AppServer
import org.wasabifx.wasabi.interceptors.enableContentNegotiation

class Server(val i2cController: Controller)
{
    fun start()
    {
        val server = AppServer()

        server.enableContentNegotiation()
        server.put("/bus/:bus/address/:address", i2cController.sendCommand)
        server.put("/bus/:bus/address/:address/internal_address/:internal_address", i2cController.sendCommand)

        server.start()
    }
}