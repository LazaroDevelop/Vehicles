package net.space.developer.vehicleapiservice.model;


import com.fasterxml.jackson.annotation.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.model.diesel.DieselModel;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalModel;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineModel;
import org.springframework.hateoas.RepresentationModel;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.*;

/**
 * Vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = DISCRIMINATOR_NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DieselModel.class, name = DISCRIMINATOR_DIESEL),
        @JsonSubTypes.Type(value = GasolineModel.class, name = DISCRIMINATOR_GASOLINE),
        @JsonSubTypes.Type(value = ElectricalModel.class, name = DISCRIMINATOR_ELECTRICAL),
})
@JsonIgnoreProperties(DISCRIMINATOR_NAME)
public class VehicleModel extends RepresentationModel<VehicleModel> {

    /**
     * Vehicle id
     */
    private Long id;

    /**
     * Vehicle registration
     */
    @Size(max = 7, min = 7)
    @NotNull(message = VEHICLE_REGISTRATION_NOT_NULL)
    @NotBlank(message = VEHICLE_REGISTRATION_NOT_EMPTY)
    private String vehicleRegistration;

    /**
     * Vehicle Identification number
     */
    @Size(max = 17, min = 8)
    @NotNull(message = VIN_NOT_NULL)
    @NotBlank(message = VIN_NOT_EMPTY)
    private String vehicleIdentificationNumber;

    /**
     * Vehicles types
     */
    private VehicleType vehicleType;
}
