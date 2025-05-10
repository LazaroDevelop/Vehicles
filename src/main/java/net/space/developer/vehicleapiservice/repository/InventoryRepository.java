package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.space.developer.vehicleapiservice.common.constants.SQLConstants.JPQL_PERFORMANCE_ALL;
import static net.space.developer.vehicleapiservice.common.constants.SQLConstants.SQL_PERFORMANCE_ALL;

/**
 * Inventory repository to manage all the vehicles operations
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Repository
public interface InventoryRepository extends JpaRepository<Vehicle, Long> {

    /**
     * Find all the vehicles in the system with their relations
     *
     * @return a list of {@link Vehicle}
     */
    @Query(JPQL_PERFORMANCE_ALL)
    List<Vehicle> findAllWithPerformance();

    /**
     * Find all the vehicles in the system with their relations and pagination
     *
     * @param field database field to order the result
     * @param direction the direction of the order
     * @param size the size of the page
     * @param page the page number
     * @return a list of {@link Vehicle}
     */
    @Query(value = SQL_PERFORMANCE_ALL, nativeQuery = true)
    List<Vehicle> findAllWithPerformance(
            @Param("field") String field,
            @Param("direction") String direction,
            @Param("size") int size,
            @Param("page") int page
    );

    /**
     * Check if a vehicle with the given vehicle identification number exists in the system.
     *
     * @param vehicleIdentificationNumber the vehicle identification number
     * @return true if the vehicle identification number exists, false otherwise
     */
    boolean existsByVehicleIdentificationNumber(String vehicleIdentificationNumber);

    /**
     * Check if a vehicle with the given vehicle registration exists in the system.
     *
     * @param vehicleRegistration the vehicle registration
     * @return true if the vehicle registration exists, false otherwise
     */
    boolean existsByVehicleRegistration(String vehicleRegistration);
}
