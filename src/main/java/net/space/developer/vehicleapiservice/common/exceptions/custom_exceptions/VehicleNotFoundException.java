package net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_NOT_FOUND_MESSAGE;

/**
 * Vehicle not found custom exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException() {
        super(VEHICLE_NOT_FOUND_MESSAGE);
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }

    public VehicleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
