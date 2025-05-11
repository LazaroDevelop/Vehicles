package net.space.developer.vehicleapiservice.repository.diesel;

import net.space.developer.vehicleapiservice.domain.diesel.DieselVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Diesel repository class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@Repository
public interface DieselRepository extends JpaRepository<DieselVehicle, Long> {
}
