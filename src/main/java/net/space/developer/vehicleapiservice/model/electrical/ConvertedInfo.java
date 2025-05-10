package net.space.developer.vehicleapiservice.model.electrical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConvertedInfo {
    private String vehicleRegistration;
    private Set<GasolineType> gasolineTypes;
}
