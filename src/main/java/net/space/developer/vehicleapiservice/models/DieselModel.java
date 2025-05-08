package net.space.developer.vehicleapiservice.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    private InjectionType injectionType;

    /**
     * Argument constructor
     */
    public DieselModel(Long id, String vehicleRegistration, String vehicleIdentificationNumber, InjectionType injectionType){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.injectionType = injectionType;
    }
}
