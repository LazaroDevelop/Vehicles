package net.space.developer.vehicleapiservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.diesel.InjectionType;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_DIESEL;

/**
 * Diesel vehicle entity class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(DISCRIMINATOR_DIESEL)
public class DieselVehicle extends Vehicle{

    /**
     * Injection bomb type
     */
    @Enumerated(EnumType.ORDINAL)
    private InjectionType pumpType;

    /**
     * All args constructor
     */
    public DieselVehicle(Long id, String vehicleRegistration, String vehicleIdentificationNumber, InjectionType pumpType){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.pumpType = pumpType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public VehicleType getVehicleType() {
        return VehicleType.DIESEL;
    }
}
