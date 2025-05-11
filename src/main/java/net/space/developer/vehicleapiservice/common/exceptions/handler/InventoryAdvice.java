package net.space.developer.vehicleapiservice.common.exceptions.handler;

import net.space.developer.vehicleapiservice.common.advice.ErrorMessage;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleAlreadyRegisteredException;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleInvalidException;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Custom controller advice to manage all the exception message into a custom message
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@RestControllerAdvice
public class InventoryAdvice {

    /**
     * Handle the {@link VehicleNotFoundException} class and transform the default message into a custom format
     *
     * @param ex the instance of {@link VehicleNotFoundException} class
     * @param req the web request information
     * @return an instance of {@link ErrorMessage} with custom formatted message
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VehicleNotFoundException.class)
    public ErrorMessage vehicleNotFoundException(VehicleNotFoundException ex, WebRequest req){
        return ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(req.getDescription(false))
                .build();
    }

    /**
     * Handle the {@link VehicleAlreadyRegisteredException} class and transform the default message into a custom format
     *
     * @param ex the instance of {@link VehicleAlreadyRegisteredException} class
     * @param req the web request information
     * @return an instance of {@link ErrorMessage} with custom formatted message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VehicleAlreadyRegisteredException.class)
    public ErrorMessage vehicleAlreadyRegisteredException(VehicleAlreadyRegisteredException ex, WebRequest req){
        return ErrorMessage.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(req.getDescription(false))
                .build();
    }

    /**
     * Handle the {@link VehicleInvalidException} class and transform the default message into a custom format
     *
     * @param ex the instance of {@link VehicleInvalidException} class
     * @param req the web request information
     * @return an instance of {@link ErrorMessage} with custom formatted message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VehicleInvalidException.class)
    public ErrorMessage vehicleInvalidException(VehicleInvalidException ex, WebRequest req){
        return ErrorMessage.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(req.getDescription(false))
                .build();
    }

    /**
     * Global exception handler the {@link Exception} class and transform the default message into a custom format
     *
     * @param ex the instance of {@link Exception} class
     * @param req the web request information
     * @return an instance of {@link ErrorMessage} with custom formatted message
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessage globalException(Exception ex, WebRequest req){
        return ErrorMessage.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(req.getDescription(false))
                .build();
    }

}
