package net.space.developer.vehicleapiservice.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_GASOLINE;

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
    @Enumerated(EnumType.ORDINAL)
    private GasolineType gasolineType;

    /**
     * All args constructor
     */
    public GasolineVehicle(Long id, String vehicleRegistration, String vehicleIdentificationNumber, GasolineType gasolineType){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.gasolineType = gasolineType;
    }
}
