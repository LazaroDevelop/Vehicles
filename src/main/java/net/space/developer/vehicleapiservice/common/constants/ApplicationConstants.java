package net.space.developer.vehicleapiservice.common.constants;

/**
 * Application constants class to manage utility variables and methods
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public final class ApplicationConstants {

    // Entities related messages
    public static final  String VEHICLE_TABLE_NAME = "T_VEHICLE";
    public static final  String VEHICLE_ID = "VEHICLE_ID";
    public static final  String VEHICLE_REGISTRATION = "VEHICLE_REGISTRATION";
    public static final  String VEHICLE_IDENTIFICATION_NUMBER = "VEHICLE_IDENTIFICATION_NUMBER";

    // Discriminator values
    public static final String DISCRIMINATOR_NAME = "_type";
    public static final String DISCRIMINATOR_DIESEL = "_diesel";
    public static final String DISCRIMINATOR_GASOLINE = "_gasoline";
    public static final String DISCRIMINATOR_ELECTRICAL = "_electrical";

    // Validation messages
    public static final String VEHICLE_REGISTRATION_NOT_NULL = "Vehicle Registration field cannot be null";
    public static final String VEHICLE_REGISTRATION_NOT_EMPTY = "Vehicle Registration field cannot be empty";
    public static final String VIN_NOT_NULL = "Vehicle Identification Number field cannot be null";
    public static final String VIN_NOT_EMPTY = "Vehicle Identification Number field cannot be empty";

    // Custom exception messages
    public static final String VEHICLE_NOT_FOUND_MESSAGE = "Vehicle was not found";
    public static final String VEHICLE_ALREADY_EXISTS_MESSAGE = "This vehicle is already registered";
    public static final String VEHICLE_INVALID_MESSAGE = "Invalid vehicle registration";
    public static final String VEHICLE_INVALID_WITH_BODY = "Invalid vehicle registration for vehicle model {0}";
    public static final String VEHICLE_CONVERSION_FAILED_MESSAGE = "Invalid conversion from electrical model to gasoline model";

    //Routes
    public static final String API_BASE_URL = "/api/v1/vehicles";

    private ApplicationConstants(){}

}
