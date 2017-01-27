package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.I2CBus
import io.frontierrobotics.i2c.I2CData
import io.frontierrobotics.i2c.I2CDevice
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller(val i2CBus: I2CBus)
{
    @PutMapping("/bus/{bus}/address/{address}")
    fun sendCommandToAddress(@PathVariable bus: Int,
                             @PathVariable address: Int,
                             @RequestBody data: I2CData)
    {
        val device = I2CDevice(address)

        i2CBus.send(device, data)
    }

    @PutMapping("/bus/{bus}/address/{address}/internal_address/{internalAddress}")
    fun sendCommandToInternalAddress(@PathVariable bus: Int,
                                     @PathVariable address: Int,
                                     @PathVariable internalAddress: Int,
                                     @RequestBody data: I2CData)
    {
        val device = I2CDevice(address, internalAddress)

        i2CBus.send(device, data)
    }

    @GetMapping("/bus/{bus}/address/{address}")
    fun receiveCommandFromAddress(@PathVariable bus: Int,
                                  @PathVariable address: Int,
                                  @RequestParam(required = false) size: Int?) : ResponseEntity<I2CData>
    {
        val device = I2CDevice(address)
        val data = i2CBus.receive(device, size)

        return ResponseEntity.ok(data)
    }

    @GetMapping("/bus/{bus}/address/{address}/internal_address/{internalAddress}")
    fun receiveCommandFromInternalAddress(@PathVariable bus: Int,
                                          @PathVariable address: Int,
                                          @PathVariable internalAddress: Int,
                                          @RequestParam(required = false) size: Int?) : ResponseEntity<I2CData>
    {
        val device = I2CDevice(address, internalAddress)
        val data = i2CBus.receive(device, size)

        return ResponseEntity.ok(data)
    }
}