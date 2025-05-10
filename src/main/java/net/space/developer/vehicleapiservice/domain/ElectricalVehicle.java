package net.space.developer.vehicleapiservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.math.BigDecimal;
import java.util.Set;

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
@EqualsAndHashCode(callSuper = true)
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
    @Column(precision = 5, scale = 2)
    private BigDecimal voltage;

    /**
     * Current of the battery
     */
    @Column(precision = 6, scale = 3)
    private BigDecimal current;

    /**
     * Flag to indicate if the vehicle was converted
     */
    private boolean reconverted = false;

    /**
     * Fuel type post conversion
     */
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private Set<GasolineType> fuelTypePostConversion;

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

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public VehicleType getVehicleType() {
        return VehicleType.ELECTRICAL;
    }
}
