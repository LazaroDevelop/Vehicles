package net.space.developer.vehicleapiservice.model.electrical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;
import net.space.developer.vehicleapiservice.model.VehicleModel;

import java.math.BigDecimal;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_NAME;

/**
 * Electrical vehicle model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {DISCRIMINATOR_NAME})
public class ElectricalModel extends VehicleModel {

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
     * Flag to know it was reconverted or not
     */
    private boolean reconverted = false;

    /**
     * Empty constructor
     */
    public ElectricalModel(){
        super.setVehicleType(VehicleType.ELECTRICAL);
    }

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
