package net.space.developer.vehicleapiservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;

import java.math.BigDecimal;

/**
 * Electrical vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ElectricalModel extends VehicleModel{

    /**
     * Vehicle battery type
     */
    private BatteryType batteryType;

    /**
     * Vehicle battery voltage
     */
    private BigDecimal voltage;

    /**
     * Vehicle battery current
     */
    private BigDecimal current;

    /**
     * Arguments constructor
     */
    public ElectricalModel(
            Long id,
            String vehicleRegistration,
            String vehicleIdentificationNumber,
            BatteryType batteryType,
            BigDecimal voltage,
            BigDecimal current
    ){
        super(id, vehicleRegistration, vehicleIdentificationNumber, VehicleType.ELECTRICAL);
        this.batteryType = batteryType;
        this.current = current;
        this.voltage = voltage;
    }

}
