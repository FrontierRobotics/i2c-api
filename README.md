[![Build Status](https://travis-ci.org/FrontierRobotics/i2c-api.svg?branch=develop)](https://travis-ci.org/FrontierRobotics/i2c-api)

# i2c-api
I2C Web API for the Raspberry Pi.

## Design and Usage

To find out what operations this API supports, see the [API Design Documentation](docs/api-design.md).

## Building

This is a Gradle project. To import it into IntelliJ follow these steps:

1. Open the Gradle Tool Window. _View->Tool Windows->Gradle_
2. Click the `+` button.
3. Find the `build.gradle` file, and click OK.

To build it on the command line simply run:

```
$ gradle build
```

To package it up for deployment run:
```
$ gradle distTar
```
