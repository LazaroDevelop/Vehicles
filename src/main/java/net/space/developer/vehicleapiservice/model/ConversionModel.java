package net.space.developer.vehicleapiservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionModel {
    Set<GasolineType> fuelType;
}
