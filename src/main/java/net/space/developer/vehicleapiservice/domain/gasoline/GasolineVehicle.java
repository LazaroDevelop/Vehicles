package net.space.developer.vehicleapiservice.domain.gasoline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.util.Set;

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
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(value = DISCRIMINATOR_GASOLINE)
public class GasolineVehicle extends Vehicle {

    /**
     * Gasoline type
     */
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private Set<GasolineType> gasolineType;

    /**
     * All args constructor
     */
    public GasolineVehicle(Long id, String vehicleRegistration, String vehicleIdentificationNumber, Set<GasolineType> gasolineType){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.gasolineType = gasolineType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public VehicleType getVehicleType(){
        return VehicleType.GASOLINE;
    }
}
