package net.space.developer.vehicleapiservice.model.diesel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.diesel.InjectionType;
import net.space.developer.vehicleapiservice.model.VehicleModel;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_NAME;

/**
 * Diesel vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(DISCRIMINATOR_NAME)
public class DieselModel extends VehicleModel {

    /**
     * Injection bomb type
     */
    private InjectionType pumpType;

    /**
     * Empty constructor
     */
    public DieselModel(){
        super.setVehicleType(VehicleType.DIESEL);
    }

    /**
     * Argument constructor
     */
    public DieselModel(Long id, String vehicleRegistration, String vehicleIdentificationNumber, InjectionType pumpType){
        super(id, vehicleRegistration, vehicleIdentificationNumber, VehicleType.DIESEL);
        this.pumpType = pumpType;
    }
}
