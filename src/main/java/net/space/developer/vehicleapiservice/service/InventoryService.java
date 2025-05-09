package net.space.developer.vehicleapiservice.service;

import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.GasolineModel;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Inventory service interface with all the uses cases signatures
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public interface InventoryService {
    /**
     * Retrieve all the vehicles in the inventory
     *
     * @return a {@link List} of {@link VehicleModel}
     */
    List<VehicleModel> getAllVehicles();

    /**
     * Retrieve all the vehicles in the inventory with pagination and sorting
     *
     * @param pageable the page information to paginate the result
     * @return a {@link Page} of {@link VehicleModel}
     */
    Page<VehicleModel> getAllVehicles(Pageable pageable);

    /**
     * Retrieve all the vehicles in the inventory by vehicle type
     *
     * @param type the vehicle type
     * @return a {@link List} of {@link VehicleModel}
     */
    List<VehicleModel> getVehiclesByType(VehicleType type);

    /**
     * Retrieve all the vehicles in the inventory
     *
     * @param type the vehicle type
     * @param pageable the page information to paginate the result
     * @return a {@link Page} of {@link VehicleModel}
     */
    Page<VehicleModel> getVehiclesByType(VehicleType type, Pageable pageable);

    /**
     * Get a specific vehicle by identifier
     *
     * @param id the vehicle identifier
     * @return the found {@link VehicleModel}
     */
    VehicleModel getVehicleById(long id);

    /**
     * Register a new vehicle
     *
     * @param vehicleModel the vehicle information
     * @return the created or registered vehicle
     */
    VehicleModel createVehicle(VehicleModel vehicleModel);

    /**
     * Update an existing vehicle by identifier
     *
     * @param vehicleModel the new vehicle information
     * @param id the given identifier
     * @return the updated {@link VehicleModel}
     */
    VehicleModel updateVehicle(VehicleModel vehicleModel, long id);

    /**
     * Transform an existing electrical vehicle into a gasoline vehicle
     *
     * @param id the identifier of the electrical vehicle
     * @param gasolineTypes the types of gasoline fuel of the transformed vehicle
     * @return the transformed {@link GasolineModel}
     */
    GasolineModel transformIntoGasoline(long id, Set<GasolineType> gasolineTypes);

    /**
     * Drop or delete an existing vehicle by identifier
     *
     * @param id the vehicle identifier
     */
    void deleteVehicle(long id);
}
