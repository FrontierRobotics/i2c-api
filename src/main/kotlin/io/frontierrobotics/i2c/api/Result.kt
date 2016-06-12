package io.frontierrobotics.i2c.api

data class Result<T>(val data: T? = null, val error: String? = null)
{
    companion object Result
    {
        val SUCCESS = Result(data = "Success")
    }
}