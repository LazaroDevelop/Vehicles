package net.space.developer.vehicleapiservice.common.exceptions;

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

    public VehicleInvalidException() {
        super(VEHICLE_INVALID_MESSAGE);
    }

    public VehicleInvalidException(VehicleModel model){
        super(MessageFormat.format(VEHICLE_INVALID_WITH_BODY, model));
    }

    public VehicleInvalidException(String message) {
        super(message);
    }

    public VehicleInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
