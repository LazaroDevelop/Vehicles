package net.space.developer.vehicleapiservice.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.domain.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import net.space.developer.vehicleapiservice.enums.VehicleType;

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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = DISCRIMINATOR_NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DieselVehicle.class, name = DISCRIMINATOR_DIESEL),
        @JsonSubTypes.Type(value = GasolineVehicle.class, name = DISCRIMINATOR_GASOLINE),
        @JsonSubTypes.Type(value = ElectricalVehicle.class, name = DISCRIMINATOR_ELECTRICAL),
})
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

    /**
     * Vehicles types
     */
    private VehicleType vehicleType;
}
