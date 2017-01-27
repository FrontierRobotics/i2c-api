package io.frontierrobotics.i2c

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidDeviceException(device: I2CDevice) : RuntimeException("$device is not a valid I2C device.")
