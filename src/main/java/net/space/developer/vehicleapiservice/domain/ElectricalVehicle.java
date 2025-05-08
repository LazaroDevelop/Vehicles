package net.space.developer.vehicleapiservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;

import java.math.BigDecimal;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_ELECTRICAL;

/**
 * Electrical vehicle entity class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue(DISCRIMINATOR_ELECTRICAL)
public class ElectricalVehicle extends Vehicle{

    /**
     * Battery vehicle type
     */
    @Enumerated(EnumType.ORDINAL)
    private BatteryType batteryType;

    /**
     * Voltage of the battery
     */
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal voltage;

    /**
     * Current of the battery
     */
    @Column(precision = 6, scale = 3, nullable = false)
    private BigDecimal current;

    /**
     * All args constructor
     */
    public ElectricalVehicle(
            Long id,
            String vehicleRegistration,
            String vehicleIdentificationNumber,
            BatteryType batteryType,
            BigDecimal voltage,
            BigDecimal current
    ){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.batteryType = batteryType;
        this.voltage = voltage;
        this.current = current;
    }
}
