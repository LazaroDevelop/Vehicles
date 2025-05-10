package net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_ALREADY_EXISTS_MESSAGE;

/**
 * Vehicle already exists custom exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public class VehicleAlreadyRegisteredException extends RuntimeException{

    /**
     * Default constructor for VehicleAlreadyRegisteredException
     */
    public VehicleAlreadyRegisteredException(){
        super(VEHICLE_ALREADY_EXISTS_MESSAGE);
    }

    /**
     * Constructor for VehicleAlreadyRegisteredException with a custom message
     *
     * @param message the custom message for the exception
     */
    public VehicleAlreadyRegisteredException(String message) {
        super(message);
    }

    /**
     * Constructor for VehicleAlreadyRegisteredException with a custom message and cause
     *
     * @param message the custom message for the exception
     * @param cause the cause of the exception
     */
    public VehicleAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

}
