package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class Controller @Autowired constructor(val i2CBus: I2CBus)
{
    val log: Logger = LoggerFactory.getLogger(Controller::class.java)

    @GetMapping("/bus/{bus}/address/{address}")
    fun receiveCommandFromAddress(@PathVariable bus: Int,
                                  @PathVariable address: Int,
                                  @RequestParam size: Int)
    {
        val device = I2CDevice(address)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        log.info("Requesting $size bytes from: $device")

        val data = i2CBus.receive(device, size)

//        response.send(Result.SUCCESS)
        //response.send(data.toString())
    }

    @GetMapping("/bus/{bus}/address/{address}/internal_address/{internal_address}")
    fun receiveCommandFromInternalAddress(@PathVariable bus: Int,
                                          @PathVariable address: Int,
                                          @PathVariable internalAddress: Int,
                                          @RequestParam size: Int)
    {
        val device = I2CDevice(address, internalAddress)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        log.info("Requesting $size bytes from: $device")

        val data = i2CBus.receive(device, size)

//        response.send(Result.SUCCESS)
        //response.send(data.toString())
    }

    @PutMapping("/bus/{bus}/address/{address}")
    fun sendCommandToAddress(@PathVariable bus: Int,
                             @PathVariable address: Int,
                             @RequestBody data: String)
    {
        val device = I2CDevice(address)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        val i2cData = I2CData(data)

        log.info("Sending $i2cData to $device")

        i2CBus.send(device, i2cData)

//        response.send(Result.SUCCESS)
        //response.send("hello")
    }

    @PutMapping("/bus/{bus}/address/{address}/internal_address/{internal_address}")
    fun sendCommandToInternalAddress(@PathVariable bus: Int,
                                     @PathVariable address: Int,
                                     @PathVariable internalAddress: Int,
                                     @RequestBody data: String)
    {
        val device = I2CDevice(address, internalAddress)

        if (!i2CBus.isDeviceValid(device))
        {
            //response.setStatus(400, "Validation Error!")
            //response.send("$device is not a valid I2C device.")

            //return response
        }

        val i2cData = I2CData(data)

        log.info("Sending $i2cData to $device")

        i2CBus.send(device, i2cData)

//        response.send(Result.SUCCESS)
        //response.send("hello")
    }
}