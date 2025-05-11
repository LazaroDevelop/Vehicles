package net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_CONVERSION_FAILED_MESSAGE;

/**
 * Vehicle conversion failed custom exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

public class VehicleConversionFailedException extends RuntimeException{

    /**
     * Default constructor for VehicleConversionFailedException
     */
    public VehicleConversionFailedException(){
        super(VEHICLE_CONVERSION_FAILED_MESSAGE);
    }

    /**
     * Constructor for VehicleConversionFailedException with a custom message
     *
     * @param message the custom message for the exception
     */
    public VehicleConversionFailedException(String message) {
        super(message);
    }

    /**
     * Constructor for VehicleConversionFailedException with a custom message and cause
     *
     * @param message the custom message for the exception
     * @param cause the cause of the exception
     */
    public VehicleConversionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
