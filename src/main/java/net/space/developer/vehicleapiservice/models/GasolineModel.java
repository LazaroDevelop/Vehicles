package net.space.developer.vehicleapiservice.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    private GasolineType gasolineType;

    public GasolineModel(Long id, String vehicleRegistration, String vehicleIdentificationNumber, GasolineType gasolineType){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.gasolineType = gasolineType;
    }
}
