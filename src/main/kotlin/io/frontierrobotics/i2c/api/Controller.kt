package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class Controller(val i2CBus: I2CBus)
{
    val log: Logger = LoggerFactory.getLogger(Controller::class.java)

    @PutMapping("/bus/{bus}/address/{address}")
    fun sendCommandToAddress(@PathVariable bus: Int,
                             @PathVariable address: Int,
                             @RequestBody data: I2CData)
    {
        val device = I2CDevice(address)

        i2CBus.send(device, data)
    }

    @PutMapping("/bus/{bus}/address/{address}/internal_address/{internal_address}")
    fun sendCommandToInternalAddress(@PathVariable bus: Int,
                                     @PathVariable address: Int,
                                     @PathVariable internalAddress: Int,
                                     @RequestBody data: I2CData)
    {
        val device = I2CDevice(address, internalAddress)

        i2CBus.send(device, data)
    }

    @GetMapping("/bus/{bus}/address/{address}")
    @ResponseBody
    fun receiveCommandFromAddress(@PathVariable bus: Int,
                                  @PathVariable address: Int,
                                  @RequestParam(required = false) size: Int?) : I2CData
    {
        val device = I2CDevice(address)

        return i2CBus.receive(device, size)
    }

    @GetMapping("/bus/{bus}/address/{address}/internal_address/{internal_address}")
    @ResponseBody
    fun receiveCommandFromInternalAddress(@PathVariable bus: Int,
                                          @PathVariable address: Int,
                                          @PathVariable internalAddress: Int,
                                          @RequestParam(required = false) size: Int?) : I2CData
    {
        val device = I2CDevice(address, internalAddress)

        return i2CBus.receive(device, size)
    }
}