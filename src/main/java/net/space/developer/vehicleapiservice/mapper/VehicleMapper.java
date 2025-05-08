package net.space.developer.vehicleapiservice.mapper;

import net.space.developer.vehicleapiservice.domain.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import net.space.developer.vehicleapiservice.models.DieselModel;
import net.space.developer.vehicleapiservice.models.ElectricalModel;
import net.space.developer.vehicleapiservice.models.GasolineModel;
import org.mapstruct.Mapper;

/**
 * Vehicle mapper interface
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Mapper
public interface VehicleMapper {

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
