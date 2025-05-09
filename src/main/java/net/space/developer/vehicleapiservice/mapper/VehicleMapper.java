package net.space.developer.vehicleapiservice.mapper;

import net.space.developer.vehicleapiservice.domain.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.model.DieselModel;
import net.space.developer.vehicleapiservice.model.ElectricalModel;
import net.space.developer.vehicleapiservice.model.GasolineModel;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Vehicle mapper interface
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Mapper
@Component
public interface VehicleMapper {

    /**
     * Map from vehicle entity to model
     *
     * @param vehicle the {@link Vehicle} entity instance
     * @return the mapped {@link VehicleModel} model instance
     */
    @Mapping(target = "vehicleType", expression = "java(vehicle.getVehicleType())")
    VehicleModel toVehicleModel(Vehicle vehicle);

    /**
     * Map from vehicle model to dto
     *
     * @param vehicleModel the {@link VehicleModel} model instance
     * @return the mapped {@link Vehicle} entity instance
     */
    Vehicle toVehicle(VehicleModel vehicleModel);

    /**
     * Map from Diesel entity to the model
     *
     * @param vehicle the {@link DieselVehicle} entity instance
     * @return the mapped {@link DieselModel} model instance
     */
    DieselModel toDieselModel(DieselVehicle vehicle);

    /**
     * Map from Diesel model to the entity
     *
     * @param dieselModel the {@link DieselModel} model instance
     * @return the mapped {@link DieselVehicle} entity instance
     */
    DieselVehicle toDieselVehicle(DieselModel dieselModel);

    /**
     * Map from Gasoline entity to the model
     *
     * @param gasolineVehicle the {@link GasolineVehicle} entity instance
     * @return the mapped {@link GasolineModel} model instance
     */
    GasolineModel toGasolineModel(GasolineVehicle gasolineVehicle);

    /**
     * Map from Gasoline model to the entity
     *
     * @param gasolineModel the {@link GasolineModel} model instance
     * @return the mapped {@link GasolineVehicle} entity instance
     */
    GasolineVehicle toGasolineVehicle(GasolineModel gasolineModel);

    /**
     * Map from Electrical vehicle entity to model
     *
     * @param vehicle the {@link ElectricalVehicle} entity instance
     * @return the mapped {@link ElectricalModel} model instance
     */
    ElectricalModel toElectricalModel(ElectricalVehicle vehicle);

    /**
     * Map from Electrical model to entity
     *
     * @param electricalModel the {@link ElectricalModel} model instance
     * @return the mapped {@link ElectricalVehicle} entity instance
     */
    ElectricalVehicle toElectricalVehicle(ElectricalModel electricalModel);
}
