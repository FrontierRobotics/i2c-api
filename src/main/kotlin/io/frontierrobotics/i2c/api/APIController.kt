package io.frontierrobotics.i2c.api

import io.frontierrobotics.i2c.bus.I2CBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class APIController @Autowired constructor(val bus: I2CBus) {
    @RequestMapping ("/command")
    fun sendComand(@RequestParam(value = "command")command: String): Result {
        bus.send(command)
        return Result(command)
    }
}