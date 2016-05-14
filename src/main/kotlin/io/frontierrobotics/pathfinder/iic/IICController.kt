package io.frontierrobotics.pathfinder.iic

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class IICController {
    @RequestMapping ("/command")
    fun sendComand(@RequestParam(value = "command")command: String):Result{
        return Result("sdhdsgfajhgf")
    }
}