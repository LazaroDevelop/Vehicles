package net.space.developer.vehicleapiservice;

import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleAlreadyRegisteredException;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleNotFoundException;
import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.domain.diesel.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.electrical.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.gasoline.GasolineVehicle;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.mapper.VehicleMapper;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import net.space.developer.vehicleapiservice.model.diesel.DieselModel;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalModel;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineModel;
import net.space.developer.vehicleapiservice.repository.InventoryRepository;
import net.space.developer.vehicleapiservice.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Vehicle Api Service Application Tests
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

class VehicleApiServiceApplicationTests {

    /**
     * Inject the inventory repository bean
     */
    @Mock
    private InventoryRepository inventoryRepository;

    /**
     * Inject the vehicle mapper bean
     */
    @Mock
    private VehicleMapper vehicleMapper;

    /**
     * Inject the inventory service bean
     */
    @InjectMocks
    private InventoryServiceImpl inventoryService;

    /**
     * Set up the test class
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Should retrieve all vehicles
     */
    @Test
    @DisplayName("Should retrieve all vehicles")
    void shouldRetrieveAllVehicles() {
        Vehicle vehicle = new GasolineVehicle();
        VehicleModel vehicleModel = new VehicleModel();

        when(inventoryRepository.findAllWithPerformance()).thenReturn(List.of(vehicle));
        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);

        List<VehicleModel> result = inventoryService.getAllVehicles();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryRepository, times(1)).findAllWithPerformance();
        verify(vehicleMapper, times(1)).toVehicleModel(vehicle);
    }

    /**
     * Should retrieve all vehicles with pagination
     */
    @Test
    @DisplayName("Should retrieve vehicles with pagination")
    void shouldRetrieveVehiclesWithPagination() {
        Vehicle vehicle = new DieselVehicle();
        VehicleModel vehicleModel = new VehicleModel();
        PageRequest pageable = PageRequest.of(0, 5);
        Page<Vehicle> vehiclePage = new PageImpl<>(List.of(vehicle), pageable, 1);

        when(inventoryRepository.findAll(pageable)).thenReturn(vehiclePage);
        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);

        Page<VehicleModel> result = inventoryService.getAllVehicles(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(inventoryRepository, times(1)).findAll(pageable);
        verify(vehicleMapper, times(1)).toVehicleModel(vehicle);
    }

    /**
     * Should retrieve an electrical vehicle by ID
     */
    @Test
    @DisplayName("Should retrieve an electrical vehicle by ID")
    void shouldRetrieveVehicleByIdElectrical() {
        long id = 1L;
        ElectricalVehicle vehicle = new ElectricalVehicle();
        ElectricalModel vehicleModel = new ElectricalModel();
        vehicleModel.setVehicleType(VehicleType.ELECTRICAL);

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toElectricalModel(vehicle)).thenReturn(vehicleModel);

        VehicleModel result = inventoryService.getVehicleById(id);

        assertNotNull(result);
        verify(inventoryRepository, times(1)).findById(id);
        verify(vehicleMapper, times(1)).toElectricalModel(vehicle);
    }

    /**
     * Should retrieve a diesel vehicle by ID
     */
    @Test
    @DisplayName("Should retrieve an electrical vehicle by ID")
    void shouldRetrieveVehicleByIdDiesel() {
        long id = 1L;
        DieselVehicle vehicle = new DieselVehicle();
        DieselModel vehicleModel = new DieselModel();
        vehicleModel.setVehicleType(VehicleType.DIESEL);

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toDieselModel(vehicle)).thenReturn(vehicleModel);

        VehicleModel result = inventoryService.getVehicleById(id);

        assertNotNull(result);
        verify(inventoryRepository, times(1)).findById(id);
        verify(vehicleMapper, times(1)).toDieselModel(vehicle);
    }


    /**
     * Should retrieve a gasoline vehicle by ID
     */
    @Test
    @DisplayName("Should retrieve an electrical vehicle by ID")
    void shouldRetrieveVehicleByIdGasoline() {
        long id = 1L;
        GasolineVehicle vehicle = new GasolineVehicle();
        vehicle.setId(id);
        vehicle.setVehicleRegistration("REG123");
        vehicle.setVehicleIdentificationNumber("VIN123");

        GasolineModel vehicleModel = new GasolineModel();
        vehicle.setVehicleRegistration("REG123");
        vehicle.setVehicleIdentificationNumber("VIN123");
        vehicleModel.setVehicleType(VehicleType.GASOLINE);
        vehicleModel.setGasolineType(Set.of(GasolineType.B83));
        vehicleModel.setId(id);

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toGasolineModel(vehicle)).thenReturn(vehicleModel);

        VehicleModel result = inventoryService.getVehicleById(id);

        assertNotNull(result);
        verify(inventoryRepository, times(1)).findById(id);
        verify(vehicleMapper, times(1)).toGasolineModel(vehicle);
    }

    /**
     * Should throw exception when vehicle not found by ID
     */
    @Test
    @DisplayName("Should throw exception when vehicle not found by ID")
    void shouldThrowExceptionWhenVehicleNotFoundById() {
        long id = 1L;

        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> inventoryService.getVehicleById(id));
        verify(inventoryRepository, times(1)).findById(id);
    }

    /**
     * Should create a new vehicle
     */
    @Test
    @DisplayName("Should create a new vehicle diesel")
    void shouldCreateNewVehicleDiesel() {
        DieselVehicle vehicle = new DieselVehicle();
        VehicleModel vehicleModel = new DieselModel();
        vehicleModel.setVehicleType(VehicleType.DIESEL);

        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);
        when(vehicleMapper.toDieselVehicle(any())).thenReturn(vehicle);
        when(inventoryRepository.save(vehicle)).thenReturn(vehicle);

        VehicleModel result = inventoryService.createVehicle(vehicleModel);

        assertNotNull(result);
        verify(inventoryRepository, times(1)).save(vehicle);
        verify(vehicleMapper, times(1)).toVehicleModel(vehicle);
    }

    /**
     * Should create a new vehicle
     */
    @Test
    @DisplayName("Should create a new vehicle electrical")
    void shouldCreateNewVehicleElectrical() {
        ElectricalVehicle vehicle = new ElectricalVehicle();
        VehicleModel vehicleModel = new ElectricalModel();
        vehicleModel.setVehicleType(VehicleType.ELECTRICAL);

        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);
        when(vehicleMapper.toElectricalVehicle(any())).thenReturn(vehicle);
        when(inventoryRepository.save(vehicle)).thenReturn(vehicle);

        VehicleModel result = inventoryService.createVehicle(vehicleModel);

        assertNotNull(result);
        verify(inventoryRepository, times(1)).save(vehicle);
        verify(vehicleMapper, times(1)).toVehicleModel(vehicle);
    }

    /**
     * Should create a new vehicle
     */
    @Test
    @DisplayName("Should create a new vehicle gasoline")
    void shouldCreateNewVehicleGasoline() {
        GasolineVehicle vehicle = new GasolineVehicle();
        VehicleModel vehicleModel = new GasolineModel();
        vehicleModel.setVehicleType(VehicleType.GASOLINE);

        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);
        when(vehicleMapper.toGasolineVehicle(any())).thenReturn(vehicle);
        when(inventoryRepository.save(vehicle)).thenReturn(vehicle);

        VehicleModel result = inventoryService.createVehicle(vehicleModel);

        assertNotNull(result);
        verify(inventoryRepository, times(1)).save(vehicle);
        verify(vehicleMapper, times(1)).toVehicleModel(vehicle);
    }

    /**
     * Should not create a new vehicle
     */
    @Test
    @DisplayName("Should not create a new vehicle diesel")
    void shouldNotCreateNewVehicleDiesel() {
        DieselVehicle vehicle = new DieselVehicle();
        vehicle.setVehicleRegistration("REG123");
        vehicle.setVehicleIdentificationNumber("VIN123");

        VehicleModel vehicleModel = new DieselModel();
        vehicleModel.setVehicleType(VehicleType.DIESEL);

        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);
        when(vehicleMapper.toDieselVehicle(any())).thenReturn(vehicle);
        when(inventoryRepository.existsByVehicleRegistration(anyString())).thenReturn(true);

        assertThrows(VehicleAlreadyRegisteredException.class, () -> inventoryService.createVehicle(vehicleModel));
    }

    /**
     * Should not create a new vehicle
     */
    @Test
    @DisplayName("Should not create a new vehicle electrical")
    void shouldNotCreateNewVehicleElectrical() {
        ElectricalVehicle vehicle = new ElectricalVehicle();
        vehicle.setVehicleRegistration("REG123");
        vehicle.setVehicleIdentificationNumber("VIN123");

        VehicleModel vehicleModel = new ElectricalModel();
        vehicleModel.setVehicleType(VehicleType.ELECTRICAL);

        when(vehicleMapper.toVehicleModel(vehicle)).thenReturn(vehicleModel);
        when(vehicleMapper.toElectricalVehicle(any())).thenReturn(vehicle);
        when(inventoryRepository.existsByVehicleIdentificationNumber(anyString())).thenReturn(true);

        assertThrows(VehicleAlreadyRegisteredException.class, () -> inventoryService.createVehicle(vehicleModel));
    }

    /**
     * Should delete a vehicle by ID
     */
    @Test
    @DisplayName("Should delete a vehicle by ID")
    void shouldDeleteVehicleById() {
        long id = 1L;
        Vehicle vehicle = new ElectricalVehicle();

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(vehicle));

        inventoryService.deleteVehicle(id);

        verify(inventoryRepository, times(1)).findById(id);
        verify(inventoryRepository, times(1)).delete(vehicle);
    }
}
