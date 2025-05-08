package net.space.developer.vehicleapiservice.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@DiscriminatorValue(DISCRIMINATOR_DIESEL)
public class DieselVehicle extends Vehicle{

    /**
     * Injection bomb type
     */
    @Enumerated(EnumType.ORDINAL)
    private InjectionType bombType;

    /**
     * All args constructor
     */
    public DieselVehicle(Long id, String vehicleRegistration, String vehicleIdentificationNumber, InjectionType bombType){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.bombType = bombType;
    }
}
