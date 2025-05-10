package net.space.developer.vehicleapiservice.model.diesel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.space.developer.vehicleapiservice.enums.diesel.InjectionType;

/**
 * Diesel register information DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DieselRegisterInfo {

    /**
     * Vehicle registration number
     */
    @JsonProperty("registration")
    private String vehicleRegistration;

    /**
     * Injection bomb type
     */
    private InjectionType pumpType;

}
