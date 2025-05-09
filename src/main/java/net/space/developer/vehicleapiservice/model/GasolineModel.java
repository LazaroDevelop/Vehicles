package net.space.developer.vehicleapiservice.model;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

/**
 * Gasoline vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GasolineModel extends VehicleModel{

    /**
     * Gasoline type
     */
    private Set<GasolineType> gasolineType;

    /**
     * Arguments constructor
     */
    public GasolineModel(Long id, String vehicleRegistration, String vehicleIdentificationNumber, Set<GasolineType> gasolineType){
        super(id, vehicleRegistration, vehicleIdentificationNumber, VehicleType.GASOLINE);
        this.gasolineType = gasolineType;
    }
}
