package io.frontierrobotics.i2c.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class DefaultExceptionHandler
{
    val log: Logger = LoggerFactory.getLogger(DefaultExceptionHandler::class.java)
    val DEFAULT_ERROR_VIEW = "error"

    @ExceptionHandler(Exception::class)
    @Throws(Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView
    {
        log.error("Exception Caught:", e)

        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.javaClass, ResponseStatus::class.java) != null)
        {
            throw e
        }

        // Otherwise setup and send the user to a default error-view.
        val mav = ModelAndView()
        mav.addObject("exception", e)
        mav.addObject("url", req.getRequestURL())
        mav.setViewName(DEFAULT_ERROR_VIEW)
        return mav
    }
}