package io.frontierrobotics.i2c

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Validation Error!")
class InvalidDeviceException(device: I2CDevice) : RuntimeException("$device is not a valid I2C device.")
