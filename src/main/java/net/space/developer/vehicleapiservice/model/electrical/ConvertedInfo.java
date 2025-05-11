package net.space.developer.vehicleapiservice.model.electrical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.util.Set;

/**
 * Converted information DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConvertedInfo {

    /**
     * Vehicle registration number
     */
    private String vehicleRegistration;

    /**
     * Converted vehicle
     */
    private Set<GasolineType> gasolineTypes;
}
