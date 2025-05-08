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

    public final String DISCRIMINATOR_NAME = "vehicle_type";
    public final String DISCRIMINATOR_DIESEL = "DIESEL";
}
