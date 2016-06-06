package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CDriver
import io.frontierrobotics.i2c.bus.I2CData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/bus/{bus}/address/{address}")
class APIController @Autowired constructor(val bus: I2CBus)
{
    @RequestMapping (method = arrayOf(PUT))
    fun sendComand(
            @PathVariable(value = "address") address: Byte,
            @RequestParam(value = "data") data: String): ResponseEntity<Result>
    {
        val busAddress = I2CAddress(address)
        val i2cdata = I2CData(data)

        if (!busAddress.isValid())
        {
            return ResponseEntity(Result("Not a valid I2C address."), HttpStatus.BAD_REQUEST)
        }

        bus.send(i2cdata, busAddress)

        return ResponseEntity(Result(data), HttpStatus.OK)
    }

    @RequestMapping (value = "/internal_address/{internal_address}", method = arrayOf(PUT))
    fun sendComandToInternalAddress(
            @PathVariable(value = "address") address: Byte,
            @PathVariable(value = "internal_address") internalAddress: Byte?,
            @RequestParam(value = "data") data: String): ResponseEntity<Result>
    {
        val busAddress = I2CAddress(address)
        val i2cdata = I2CData(data)

        if (!busAddress.isValid())
        {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        bus.send(i2cdata, busAddress, internalAddress)

        return ResponseEntity(Result(data), HttpStatus.OK)
    }
}