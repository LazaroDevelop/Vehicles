package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Inventory repository to manage all the vehicles operations
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Repository
public interface InventoryRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByVehicleIdentificationNumber(String vehicleIdentificationNumber);

    boolean existsByVehicleRegistration(String vehicleRegistration);
}
