package net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions;

import net.space.developer.vehicleapiservice.model.VehicleModel;

import java.text.MessageFormat;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_INVALID_MESSAGE;
import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_INVALID_WITH_BODY;

/**
 * Invalid vehicle registration exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

public class VehicleInvalidException extends RuntimeException{

    /**
     * Default constructor for VehicleInvalidException
     */
    public VehicleInvalidException() {
        super(VEHICLE_INVALID_MESSAGE);
    }

    /**
     * Constructor for VehicleInvalidException with a custom message
     *
     * @param model the vehicle model that caused the exception
     */
    public VehicleInvalidException(VehicleModel model){
        super(MessageFormat.format(VEHICLE_INVALID_WITH_BODY, model));
    }

    /**
     * Constructor for VehicleInvalidException with a custom message
     *
     * @param message the custom message for the exception
     */
    public VehicleInvalidException(String message) {
        super(message);
    }

    /**
     * Constructor for VehicleInvalidException with a custom message and cause
     *
     * @param message the custom message for the exception
     * @param cause the cause of the exception
     */
    public VehicleInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
