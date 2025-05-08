package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByVehicleType(VehicleType vehicleType);

    Page<Vehicle> findByVehicleType(VehicleType vehicleType, Pageable pageable);
}
