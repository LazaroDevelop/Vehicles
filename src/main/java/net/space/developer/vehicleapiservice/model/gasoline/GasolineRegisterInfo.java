package net.space.developer.vehicleapiservice.model.gasoline;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.util.Set;

/**
 * Gasoline register information DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GasolineRegisterInfo {

    /**
     * Vehicle registration number
     */
    @JsonProperty("registration")
    private String vehicleRegistration;

    /**
     * Gasoline type
     */
    @JsonProperty("fuelType")
    private Set<GasolineType> types;

}
