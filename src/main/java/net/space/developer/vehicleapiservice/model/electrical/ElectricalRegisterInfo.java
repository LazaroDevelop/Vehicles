package net.space.developer.vehicleapiservice.model.electrical;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.common.custom_serializer.ConverterSerializer;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ConverterSerializer.class)
public class ElectricalRegisterInfo {

    private String vehicleIdentificationNumber;
    private BigDecimal batteryVoltage;
    private BigDecimal batteryCurrent;
    private BatteryType batteryType;
    private boolean reconverted;
    private ConvertedInfo convertedInfo;
}
