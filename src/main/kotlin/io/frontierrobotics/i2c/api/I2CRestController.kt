package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.I2CAddress
import io.frontierrobotics.i2c.bus.I2CBus
import io.frontierrobotics.i2c.bus.I2CData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/bus/{bus}/address/{address}")
class I2CRestController @Autowired constructor(val bus: I2CBus)
{
    @RequestMapping (method = arrayOf(PUT))
    fun sendCommand(
            @PathVariable(value = "address") address: Byte,
            @RequestParam(value = "data") data: String): ResponseEntity<Result<String>>
    {
        val busAddress = I2CAddress(address)
        val i2cdata = I2CData(data)

        return handleErrors {
            bus.send(i2cdata, busAddress)
            Result.SUCCESS
        }
    }

    @RequestMapping (value = "/internal_address/{internal_address}", method = arrayOf(PUT))
    fun sendCommandToInternalAddress(
            @PathVariable(value = "address") address: Byte,
            @PathVariable(value = "internal_address") internalAddress: Byte?,
            @RequestParam(value = "data") data: String): ResponseEntity<Result<String>>
    {
        val busAddress = I2CAddress(address)
        val i2cdata = I2CData(data)

        return handleErrors {
            bus.send(i2cdata, busAddress, internalAddress)
            Result.SUCCESS
        }
    }

    private fun <T> handleErrors(body: () -> Result<T>): ResponseEntity<Result<T>>
    {
        try
        {
            return ResponseEntity(body.invoke(), HttpStatus.OK)
        }
        catch(iae: IllegalArgumentException)
        {
            return ResponseEntity(Result(error = iae.message), HttpStatus.BAD_REQUEST)
        }
        catch(e: Exception)
        {
            return ResponseEntity(Result(error = e.message), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}