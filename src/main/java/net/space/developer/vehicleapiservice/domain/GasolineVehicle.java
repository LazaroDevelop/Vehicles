package net.space.developer.vehicleapiservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.util.List;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.*;

/**
 * Gasoline vehicle entity class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue(value = DISCRIMINATOR_GASOLINE)
public class GasolineVehicle extends Vehicle{

    /**
     * Gasoline type
     */
    private List<GasolineType> gasolineType;

    /**
     * All args constructor
     */
    public GasolineVehicle(Long id, String vehicleRegistration, String vehicleIdentificationNumber, List<GasolineType> gasolineType){
        super(id, vehicleRegistration, vehicleIdentificationNumber, VehicleType.GASOLINE);
        this.gasolineType = gasolineType;
    }
}
