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

    // Entities related messages
    public final String VEHICLE_TABLE_NAME = "T_VEHICLE";
    public final String VEHICLE_ID = "VEHICLE_ID";
    public final String VEHICLE_TYPE = "VEHICLE_TYPE";
    public final String VEHICLE_REGISTRATION = "VEHICLE_REGISTRATION";
    public final String VEHICLE_IDENTIFICATION_NUMBER = "VEHICLE_IDENTIFICATION_NUMBER";

    public final String GASOLINE_TYPE = "GASOLINE_TYPE";

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

    // Custom exception messages
    public final String VEHICLE_NOT_FOUND_MESSAGE = "Vehicle was not found";
    public final String VEHICLE_ALREADY_EXISTS_MESSAGE = "This vehicle is already registered";
    public final String VEHICLE_INVALID_MESSAGE = "Invalid vehicle registration";
    public final String VEHICLE_INVALID_WITH_BODY = "Invalid vehicle registration for vehicle model {0}";
    public final String VEHICLE_CONVERSION_FAILED_MESSAGE = "Invalid conversion from electrical model to gasoline model";

    //Routes
    public final String API_BASE_URL = "/api/v1/vehicles";

}
