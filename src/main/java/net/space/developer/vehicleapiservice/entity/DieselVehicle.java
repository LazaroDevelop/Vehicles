package net.space.developer.vehicleapiservice.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import net.space.developer.vehicleapiservice.enums.diesel.INJECTION_TYPE;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_DIESEL;

@Getter
@Setter
@Entity
@DiscriminatorValue(DISCRIMINATOR_DIESEL)
public class DieselVehicle extends Vehicle{

    @Enumerated(EnumType.STRING)
    private INJECTION_TYPE bombType;

    public DieselVehicle() {
        super();
    }

    public DieselVehicle(Vehicle vehicle, INJECTION_TYPE bombType) {
        super(vehicle.getId(), vehicle.getVehicleRegistration(), vehicle.getVehicleRegistration());
        this.bombType = bombType;
    }
}
