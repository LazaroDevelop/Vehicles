package net.space.developer.vehicleapiservice.common.exceptions;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_ALREADY_EXISTS_MESSAGE;

/**
 * Vehicle already exists custom exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public class VehicleAlreadyRegisteredException extends RuntimeException{

    public VehicleAlreadyRegisteredException(){
        super(VEHICLE_ALREADY_EXISTS_MESSAGE);
    }

    public VehicleAlreadyRegisteredException(String message) {
        super(message);
    }

    public VehicleAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

}
