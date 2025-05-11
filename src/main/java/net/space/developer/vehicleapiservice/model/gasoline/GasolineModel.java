package net.space.developer.vehicleapiservice.model.gasoline;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.VehicleModel;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_NAME;

/**
 * Gasoline vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(DISCRIMINATOR_NAME)
public class GasolineModel extends VehicleModel {

    /**
     * Gasoline type
     */
    private Set<GasolineType> gasolineType;

    /**
     * Empty constructor
     */
    public GasolineModel(){
        super.setVehicleType(VehicleType.GASOLINE);
    }

    /**
     * Arguments constructor
     */
    public GasolineModel(Long id, String vehicleRegistration, String vehicleIdentificationNumber, Set<GasolineType> gasolineType){
        super(id, vehicleRegistration, vehicleIdentificationNumber, VehicleType.GASOLINE);
        this.gasolineType = gasolineType;
    }
}
