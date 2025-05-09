package net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_CONVERSION_FAILED_MESSAGE;

/**
 * Vehicle conversion failed custom exception class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

public class VehicleConversionFailedException extends RuntimeException{

    public VehicleConversionFailedException(){
        super(VEHICLE_CONVERSION_FAILED_MESSAGE);
    }

    public VehicleConversionFailedException(String message) {
        super(message);
    }

    public VehicleConversionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
