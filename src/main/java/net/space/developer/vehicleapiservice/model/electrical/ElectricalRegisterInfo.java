package net.space.developer.vehicleapiservice.model.electrical;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.common.serializer.ConverterSerializer;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;

import java.math.BigDecimal;

/**
 * Electrical register information DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ConverterSerializer.class)
public class ElectricalRegisterInfo {

    /**
     * Vehicle registration number
     */
    private String vehicleIdentificationNumber;

    /**
     * Battery voltage
     */
    private BigDecimal batteryVoltage;

    /**
     * Battery current
     */
    private BigDecimal batteryCurrent;

    /**
     * Battery type
     */
    private BatteryType batteryType;

    /**
     * Vehicle registration number
     */
    private boolean reconverted;

    /**
     * Converted information
     */
    private ConvertedInfo convertedInfo;
}
