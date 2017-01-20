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

    @GetMapping("/bus/{bus}/address/{address}")
    @ResponseBody
    fun receiveCommandFromAddress(@PathVariable bus: Int,
                                  @PathVariable address: Int,
                                  @RequestParam(required = false) size: Int?) : I2CData
    {
        val device = I2CDevice(address)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        log.info("Requesting $size bytes from: $device")

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

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        log.info("Requesting $size bytes from: $device")

        return i2CBus.receive(device, size)
    }

    @PutMapping("/bus/{bus}/address/{address}")
    fun sendCommandToAddress(@PathVariable bus: Int,
                             @PathVariable address: Int,
                             @RequestBody data: I2CData)
    {
        val device = I2CDevice(address)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        log.info("Sending $data to $device")

        i2CBus.send(device, data)

//        response.send(Result.SUCCESS)
        //response.send("hello")
    }

    @PutMapping("/bus/{bus}/address/{address}/internal_address/{internal_address}")
    fun sendCommandToInternalAddress(@PathVariable bus: Int,
                                     @PathVariable address: Int,
                                     @PathVariable internalAddress: Int,
                                     @RequestBody data: I2CData)
    {
        val device = I2CDevice(address, internalAddress)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        log.info("Sending $data to $device")

        i2CBus.send(device, data)

//        response.send(Result.SUCCESS)
        //response.send("hello")
    }
}