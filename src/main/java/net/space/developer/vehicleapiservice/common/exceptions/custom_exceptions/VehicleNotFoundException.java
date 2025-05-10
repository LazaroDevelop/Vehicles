package net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_NOT_FOUND_MESSAGE;

/**
 * Vehicle not found custom exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public class VehicleNotFoundException extends RuntimeException{

    /**
     * Default constructor for VehicleNotFoundException
     */
    public VehicleNotFoundException() {
        super(VEHICLE_NOT_FOUND_MESSAGE);
    }

    /**
     * Constructor for VehicleNotFoundException with a custom message
     *
     * @param message the custom message for the exception
     */
    public VehicleNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor for VehicleNotFoundException with a custom message and cause
     *
     * @param message the custom message for the exception
     * @param cause the cause of the exception
     */
    public VehicleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
