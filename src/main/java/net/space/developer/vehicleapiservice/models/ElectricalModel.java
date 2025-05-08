package net.space.developer.vehicleapiservice.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    private BatteryType batteryType;

    private BigDecimal batteryVoltage;

    private BigDecimal batteryCurrent;

    public ElectricalModel(
            Long id,
            String vehicleRegistration,
            String vehicleIdentificationNumber,
            BatteryType batteryType,
            BigDecimal batteryVoltage,
            BigDecimal batteryCurrent
    ){
        super(id, vehicleRegistration, vehicleIdentificationNumber);
        this.batteryType = batteryType;
        this.batteryCurrent = batteryCurrent;
        this.batteryVoltage = batteryVoltage;
    }

}
