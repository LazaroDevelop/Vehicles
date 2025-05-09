package net.space.developer.vehicleapiservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.diesel.InjectionType;

/**
 * Diesel vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DieselModel extends VehicleModel{

    /**
     * Injection bomb type
     */
    private InjectionType pumpType;

    /**
     * Argument constructor
     */
    public DieselModel(Long id, String vehicleRegistration, String vehicleIdentificationNumber, InjectionType pumpType){
        super(id, vehicleRegistration, vehicleIdentificationNumber, VehicleType.DIESEL);
        this.pumpType = pumpType;
    }
}
