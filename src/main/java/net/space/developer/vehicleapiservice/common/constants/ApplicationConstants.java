package net.space.developer.vehicleapiservice.common.constants;

import lombok.experimental.UtilityClass;

/**
 * Application constants class to manage utility variables and methods
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@UtilityClass
public class ApplicationConstants {

    public final String VEHICLE_TABLE_NAME = "T_VEHICLE";

    // Discriminator values
    public final String DISCRIMINATOR_NAME = "vehicle_type";
    public final String DISCRIMINATOR_DIESEL = "DIESEL";
    public final String DISCRIMINATOR_GASOLINE = "GASOLINE";
    public final String DISCRIMINATOR_ELECTRICAL = "ELECTRICAL";

    // Validation messages
    public final String VEHICLE_REGISTRATION_NOT_NULL = "Vehicle Registration field cannot be null";
    public final String VEHICLE_REGISTRATION_NOT_EMPTY = "Vehicle Registration field cannot be empty";
    public final String VIN_NOT_NULL = "Vehicle Identification Number field cannot be null";
    public final String VIN_NOT_EMPTY = "Vehicle Identification Number field cannot be empty";
}
