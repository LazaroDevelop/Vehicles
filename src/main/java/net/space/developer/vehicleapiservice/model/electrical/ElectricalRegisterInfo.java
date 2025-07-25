package net.space.developer.vehicleapiservice.model.electrical;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import net.space.developer.vehicleapiservice.common.serializer.ConverterSerializer;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

/**
 * Electrical register information DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = ConverterSerializer.class)
public class ElectricalRegisterInfo extends RepresentationModel<ElectricalRegisterInfo> {

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
