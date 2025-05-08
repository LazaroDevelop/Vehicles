package net.space.developer.vehicleapiservice.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.*;

/**
 * Vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {

    /**
     * Vehicle id
     */
    private Long id;

    /**
     * Vehicle registration
     */
    @NotNull(message = VEHICLE_REGISTRATION_NOT_NULL)
    @NotBlank(message = VEHICLE_REGISTRATION_NOT_EMPTY)
    private String vehicleRegistration;

    /**
     * Vehicle Identification number
     */
    @NotNull(message = VIN_NOT_NULL)
    @NotBlank(message = VIN_NOT_EMPTY)
    private String vehicleIdentificationNumber;

}
