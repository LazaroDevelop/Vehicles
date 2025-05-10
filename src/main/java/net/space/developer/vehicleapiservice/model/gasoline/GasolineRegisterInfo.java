package net.space.developer.vehicleapiservice.model.gasoline;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GasolineRegisterInfo {

    @JsonProperty("registration")
    private String vehicleRegistration;

    @JsonProperty("fuelType")
    private Set<GasolineType> types;

}
