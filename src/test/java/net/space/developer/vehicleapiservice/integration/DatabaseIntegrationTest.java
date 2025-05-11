package net.space.developer.vehicleapiservice.integration;

import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.domain.diesel.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.electrical.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.gasoline.GasolineVehicle;
import net.space.developer.vehicleapiservice.enums.diesel.InjectionType;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;
import net.space.developer.vehicleapiservice.repository.InventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Database integration test class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@DataJpaTest
class DatabaseIntegrationTest {

    /**
     * Inject the inventory repository bean
     */
    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Inject the test entity manager bean
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * Should save and retrieve a DieselVehicle
     */
    @Test
    @DisplayName("Should save and retrieve a DieselVehicle")
    void shouldSaveAndRetrieveDieselVehicle() {
        DieselVehicle dieselVehicle = new DieselVehicle();
        dieselVehicle.setVehicleIdentificationNumber("VIN123");
        dieselVehicle.setVehicleRegistration("REG123");
        dieselVehicle.setPumpType(InjectionType.LINEAL);

        DieselVehicle savedVehicle = entityManager.persistFlushFind(dieselVehicle);
        assertNotNull(savedVehicle);
        assertEquals("VIN123", savedVehicle.getVehicleIdentificationNumber());
        assertEquals(InjectionType.LINEAL, savedVehicle.getPumpType());
    }

    /**
     * Should save and retrieve a GasolineVehicle
     */
    @Test
    @DisplayName("Should save and retrieve a GasolineVehicle")
    void shouldSaveAndRetrieveGasolineVehicle() {
        GasolineVehicle gasolineVehicle = new GasolineVehicle();
        gasolineVehicle.setVehicleIdentificationNumber("VIN456");
        gasolineVehicle.setVehicleRegistration("REG456");

        GasolineVehicle savedVehicle = entityManager.persistFlushFind(gasolineVehicle);
        assertNotNull(savedVehicle);
        assertEquals("VIN456", savedVehicle.getVehicleIdentificationNumber());
    }

    /**
     * Should save and retrieve an ElectricalVehicle
     */
    @Test
    @DisplayName("Should save and retrieve an ElectricalVehicle")
    void shouldSaveAndRetrieveElectricalVehicle() {
        ElectricalVehicle electricalVehicle = new ElectricalVehicle();
        electricalVehicle.setVehicleIdentificationNumber("VIN789");
        electricalVehicle.setVehicleRegistration("REG789");
        electricalVehicle.setBatteryType(BatteryType.LITHIUM);

        ElectricalVehicle savedVehicle = entityManager.persistFlushFind(electricalVehicle);
        assertNotNull(savedVehicle);
        assertEquals("VIN789", savedVehicle.getVehicleIdentificationNumber());
        assertEquals(BatteryType.LITHIUM, savedVehicle.getBatteryType());
    }

    /**
     * Should save and retrieve a Vehicle
     */
    @Test
    @DisplayName("Should find all vehicles with performance")
    void shouldFindAllWithPerformance() {
        DieselVehicle dieselVehicle = new DieselVehicle();
        dieselVehicle.setVehicleIdentificationNumber("VIN123");
        dieselVehicle.setVehicleRegistration("REG123");

        entityManager.persist(dieselVehicle);

        List<Vehicle> vehicles = inventoryRepository.findAllWithPerformance();
        assertNotNull(vehicles);
        assertEquals(1, vehicles.size());
        assertEquals("VIN123", vehicles.getFirst().getVehicleIdentificationNumber());
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should check if vehicle exists by VIN")
    void shouldCheckIfVehicleExistsByVin() {
        DieselVehicle dieselVehicle = new DieselVehicle();
        dieselVehicle.setVehicleIdentificationNumber("VIN123");
        dieselVehicle.setVehicleRegistration("REG123");

        entityManager.persistAndFlush(dieselVehicle);

        boolean exists = inventoryRepository.existsByVehicleIdentificationNumber("VIN123");
        assertTrue(exists);
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should check if vehicle exists by registration")
    void shouldCheckIfVehicleExistsByRegistration() {
        DieselVehicle dieselVehicle = new DieselVehicle();
        dieselVehicle.setVehicleIdentificationNumber("VIN123");
        dieselVehicle.setVehicleRegistration("REG123");

        entityManager.persistAndFlush(dieselVehicle);

        boolean exists = inventoryRepository.existsByVehicleRegistration("REG123");
        assertTrue(exists);
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should find vehicle by ID")
    void shouldFindVehicleById(){
        Vehicle vehicle = new ElectricalVehicle();
        vehicle.setVehicleIdentificationNumber("VIN123");
        vehicle.setVehicleRegistration("REG123");

        entityManager.persistAndFlush(vehicle);

        Vehicle foundVehicle = inventoryRepository.findById(vehicle.getId()).get();

        assertNotNull(foundVehicle);
        assertEquals(vehicle.getId(), foundVehicle.getId());
        assertEquals(vehicle.getVehicleIdentificationNumber(), foundVehicle.getVehicleIdentificationNumber());
        assertEquals(vehicle.getVehicleRegistration(), foundVehicle.getVehicleRegistration());
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should update an existing vehicle")
    void shouldUpdateAnExistingVehicle(){
        Vehicle vehicle = new ElectricalVehicle();
        vehicle.setVehicleIdentificationNumber("VIN123");
        vehicle.setVehicleRegistration("REG123");

        entityManager.persistAndFlush(vehicle);

        vehicle.setVehicleRegistration("REG456");
        entityManager.persistAndFlush(vehicle);

        Vehicle updatedVehicle = inventoryRepository.findById(vehicle.getId()).get();

        assertNotNull(updatedVehicle);
        assertEquals("REG456", updatedVehicle.getVehicleRegistration());
        assertEquals(vehicle.getVehicleIdentificationNumber(), updatedVehicle.getVehicleIdentificationNumber());
        assertEquals(vehicle.getId(), updatedVehicle.getId());
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should delete an existing vehicle")
    void shouldDeleteAnExistingVehicle() {
        Vehicle vehicle = new ElectricalVehicle();
        vehicle.setVehicleIdentificationNumber("VIN123");
        vehicle.setVehicleRegistration("REG123");

        entityManager.persistAndFlush(vehicle);

        inventoryRepository.deleteById(vehicle.getId());

        Vehicle deletedVehicle = inventoryRepository.findById(vehicle.getId()).orElse(null);

        assertNull(deletedVehicle);
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should not find vehicle by non-existing ID")
    void shouldNotFindVehicleByNonExistingId() {
        Vehicle vehicle = new ElectricalVehicle();
        vehicle.setVehicleIdentificationNumber("VIN123");
        vehicle.setVehicleRegistration("REG123");

        entityManager.persistAndFlush(vehicle);

        Vehicle foundVehicle = inventoryRepository.findById(999L).orElse(null);

        assertNull(foundVehicle);
    }

    /**
     * Should find all vehicles with performance and pagination
     */
    @Test
    @DisplayName("Should get all vehicles paginated")
    void shouldGetAllVehiclesPaginated() {
        for (int i = 0; i < 10; i++) {
            Vehicle vehicle = new ElectricalVehicle();
            vehicle.setVehicleIdentificationNumber("VIN" + i);
            vehicle.setVehicleRegistration("REG" + i);
            entityManager.persist(vehicle);
        }

        PageRequest pageable = PageRequest.of(0, 5);

        Page<Vehicle> vehicles = inventoryRepository.findAll(pageable);

        assertNotNull(vehicles);
        assertEquals(5, vehicles.getContent().size());
    }
}

