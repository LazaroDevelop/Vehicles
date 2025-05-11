package net.space.developer.vehicleapiservice.end2end;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.space.developer.vehicleapiservice.common.assembler.ElectricalRegisterInfoModelAssembler;
import net.space.developer.vehicleapiservice.common.assembler.RegistrationModelAssembler;
import net.space.developer.vehicleapiservice.common.assembler.VehicleModelAssembler;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.electrical.BatteryType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.RegistrationModel;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import net.space.developer.vehicleapiservice.model.electrical.ConvertedInfo;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineRegisterInfo;
import net.space.developer.vehicleapiservice.service.InventoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * InventoryControllerEnd2EndTest class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class InventoryControllerEnd2EndTest {

    /**
     * MockMvc instance for testing
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * ObjectMapper instance for JSON serialization/deserialization
     */
    @Autowired
    private ObjectMapper mapper;

    /**
     * InventoryService instance for testing
     */
    @MockitoBean
    private InventoryService inventoryService;

    /**
     * VehicleModelAssembler instance for testing
     */
    @MockitoBean
    private VehicleModelAssembler vehicleModelAssembler;

    /**
     * RegistrationModelAssembler instance for testing
     */
    @MockitoBean
    private RegistrationModelAssembler registrationModelAssembler;

    /**
     * ElectricalRegisterInfoModelAssembler instance for testing
     */
    @MockitoBean
    private ElectricalRegisterInfoModelAssembler electricalRegisterInfoModelAssembler;

    /**
     * Test for retrieving all vehicles with 200 status code
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should retrieve all vehicles 200")
    void shouldRetrieveAllVehicles200() throws Exception {

        var dto = buildVehicleModel();

        var result = List.of(dto);

        var model = EntityModel.of(dto);

        when(this.vehicleModelAssembler.toModel(dto))
                .thenReturn(model);

        when(inventoryService.getAllVehicles())
                .thenReturn(result);

        mockMvc.perform(get("/api/v1/vehicles/all")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.vehicleModelList", notNullValue()))
                .andExpect(jsonPath("$._embedded.vehicleModelList", hasSize(1)))
                .andExpect(jsonPath("$._embedded.vehicleModelList", not(empty())))
                .andExpect(jsonPath("$._embedded.vehicleModelList[0].id", is(1)));


        verify(inventoryService, times(1)).getAllVehicles();
    }

    /**
     * Test for retrieving all vehicles with 204 status code
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should not retrieve all vehicles 204")
    void shouldNotRetrieveAllVehicles204() throws Exception {

        when(inventoryService.getAllVehicles())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/vehicles/all")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(inventoryService, times(1)).getAllVehicles();
    }

    /**
     * Test for retrieve vehicles by type (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should retrieve by vehicle type 200")
    void shouldRetrieveByVehicleType200() throws Exception {

        var dto = buildVehicleModel();

        var result = List.of(dto);

        var model = EntityModel.of(dto);

        when(this.vehicleModelAssembler.toModel(dto))
                .thenReturn(model);

        when(inventoryService.getVehiclesByType(ArgumentMatchers.any(VehicleType.class)))
                .thenReturn(result);

        mockMvc.perform(get("/api/v1/vehicles/all/by-vehicles?type={type}", VehicleType.DIESEL)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.vehicleModelList", notNullValue()))
                .andExpect(jsonPath("$._embedded.vehicleModelList", hasSize(1)))
                .andExpect(jsonPath("$._embedded.vehicleModelList", not(empty())))
                .andExpect(jsonPath("$._embedded.vehicleModelList[0].id", is(1)));


        verify(inventoryService, times(1)).getVehiclesByType(ArgumentMatchers.any(VehicleType.class));
    }

    /**
     * Test for not retrieving vehicles by type (204 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should not retrieve by vehicle type 204")
    void shouldNotRetrieveByVehicleType204() throws Exception {

        var dto = buildVehicleModel();

        var model = EntityModel.of(dto);

        when(this.vehicleModelAssembler.toModel(dto))
                .thenReturn(model);

        when(inventoryService.getVehiclesByType(ArgumentMatchers.any(VehicleType.class)))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/vehicles/all/by-vehicles?type={type}", VehicleType.DIESEL)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());


        verify(inventoryService, times(1)).getVehiclesByType(ArgumentMatchers.any(VehicleType.class));
    }

    /**
     * Test for retrieve vehicles paginated (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should retrieve vehicles paginated 200")
    void shouldRetrieveVehiclesPaginated200() throws Exception {
        var dto = buildVehicleModel();

        var result = List.of(dto);

        var model = EntityModel.of(dto);

        when(this.vehicleModelAssembler.toModel(dto))
                .thenReturn(model);

        when(inventoryService.getAllVehicles(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(new PageImpl<>(result));

        mockMvc.perform(
                get("/api/v1/vehicles/all/paginated?page={page}&size={10}&sort={sort}", 0, 10, "id, asc")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.vehicleModelList", notNullValue()))
                .andExpect(jsonPath("$._embedded.vehicleModelList", hasSize(1)))
                .andExpect(jsonPath("$._embedded.vehicleModelList", not(empty())))
                .andExpect(jsonPath("$._embedded.vehicleModelList[0].id", is(1)));

        verify(inventoryService, times(1)).getAllVehicles(ArgumentMatchers.any(Pageable.class));
    }

    /**
     * Test for retrieve vehicles paginated (204 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should not retrieve vehicles paginated 204")
    void shouldNotRetrieveVehiclesPaginated204() throws Exception {
        var dto = buildVehicleModel();

        var model = EntityModel.of(dto);

        when(this.vehicleModelAssembler.toModel(dto))
                .thenReturn(model);

        when(inventoryService.getAllVehicles(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(null);

        mockMvc.perform(
                        get("/api/v1/vehicles/all/paginated?page={page}&size={10}&sort={sort}", 0, 10, "id, asc")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(inventoryService, times(1)).getAllVehicles(ArgumentMatchers.any(Pageable.class));
    }

    /**
     * Test for retrieving the vehicle registration info (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should retrieve the vehicle registration info 200")
    void shouldRetrieveVehicleRegistrationInfo200() throws Exception {

        var dto = buildRegistrationModel();

        var model = EntityModel.of(dto);

        when(registrationModelAssembler.toModel(dto))
                .thenReturn(model);

        when(inventoryService.getVehiclesRegistration())
                .thenReturn(dto);

        mockMvc.perform(get("/api/v1/vehicles/registration")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).getVehiclesRegistration();
    }

    /**
     * Test for not retrieving the vehicle registration info (204 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should Not retrieve the vehicle registration info 204")
    void shouldNotRetrieveVehicleRegistrationInfo204() throws Exception {

        when(inventoryService.getVehiclesRegistration())
                .thenReturn(null);

        mockMvc.perform(get("/api/v1/vehicles/registration")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(inventoryService, times(1)).getVehiclesRegistration();
    }

    /**
     * Test for retrieving the vehicle registration info by id (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should retrieve a vehicle by ID with 200")
    void shouldRetrieveVehicleById200() throws Exception {
        long id = 1L;

        when(inventoryService.getVehicleById(id))
                .thenReturn(buildVehicleModel());

        mockMvc.perform(
                get("/api/v1/vehicles/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).getVehicleById(id);
    }

    /**
     * Test for not retrieving the vehicle registration info by id (404 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should not retrieve a vehicle by ID with 404")
    void shouldNotRetrieveVehicleById404() throws Exception {
        long id = 1L;

        when(inventoryService.getVehicleById(id))
                .thenReturn(null);

        mockMvc.perform(
                        get("/api/v1/vehicles/{id}", id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(inventoryService, times(1)).getVehicleById(id);
    }

    /**
     * Test for converting a vehicle (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should create a new vehicle with 201")
    void shouldCreateNewVehicle201() throws Exception {

        var body = buildVehicleModel();

        when(inventoryService.createVehicle(ArgumentMatchers.any()))
                .thenReturn(body);

        mockMvc.perform(post("/api/v1/vehicles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(inventoryService, times(1)).createVehicle(ArgumentMatchers.any());
    }

    /**
     * Test for not converting a vehicle (204 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should not create a new vehicle with 204")
    void shouldNotCreateNewVehicle204() throws Exception {

        var body = buildVehicleModel();

        when(inventoryService.createVehicle(ArgumentMatchers.any()))
                .thenReturn(null);

        mockMvc.perform(post("/api/v1/vehicles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(inventoryService, times(1)).createVehicle(ArgumentMatchers.any());
    }

    /**
     * Test for transforming a vehicle (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should update a vehicle 200")
    void shouldUpdateVehicle200() throws Exception {
        long id = 1L;

        var body = buildVehicleModel();

        var model = EntityModel.of(body);

        when(this.vehicleModelAssembler.toModel(body))
                .thenReturn(model);

        when(inventoryService.updateVehicle(body, id))
                .thenReturn(body);

        mockMvc.perform(put("/api/v1/vehicles/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleType", is("DIESEL")))
                .andExpect(jsonPath("$.vehicleIdentificationNumber", is("VIN123")));

        verify(inventoryService, times(1)).updateVehicle(body, id);
    }

    /**
     * Test for not transforming a vehicle (404 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should not update a vehicle 404")
    void shouldNotUpdateVehicle404() throws Exception {
        long id = 1L;

        var body = buildVehicleModel();

        var model = EntityModel.of(body);

        when(this.vehicleModelAssembler.toModel(body))
                .thenReturn(model);

        when(inventoryService.updateVehicle(body, id))
                .thenReturn(null);

        mockMvc.perform(put("/api/v1/vehicles/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(inventoryService, times(1)).updateVehicle(body, id);
    }

    /**
     * Test for transforming a vehicle (200 status code)
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    @DisplayName("Should delete a vehicle 204")
    void shouldDeleteVehicle204() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/api/v1/vehicles/delete/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /**
     * Build vehicle model instance for testing purposes
     *
     * @return a {@link VehicleModel} instance
     */
    private VehicleModel buildVehicleModel() {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(1L);
        vehicleModel.setVehicleType(VehicleType.DIESEL);
        vehicleModel.setVehicleIdentificationNumber("VIN123");
        vehicleModel.setVehicleRegistration("REG123");
        return vehicleModel;
    }

    /**
     * Build a gasoline register info instance for testing purposes
     *
     * @return a {@link GasolineRegisterInfo} instance
     */
    private GasolineRegisterInfo buildGasolineRegisterInfo(){
        GasolineRegisterInfo gasolineRegisterInfo = new GasolineRegisterInfo();
        gasolineRegisterInfo.setVehicleRegistration("REG123");
        gasolineRegisterInfo.setTypes(Set.of(GasolineType.B83));
        return gasolineRegisterInfo;
    }

    /**
     * Build an electrical register info instance for testing purposes
     *
     * @return a {@link ElectricalRegisterInfo} instance
     */
    private ElectricalRegisterInfo buildElectricalRegisterInfo(){
        ElectricalRegisterInfo electricalRegisterInfo = new ElectricalRegisterInfo();
        electricalRegisterInfo.setReconverted(true);
        electricalRegisterInfo.setConvertedInfo(new ConvertedInfo("REG123", Set.of(GasolineType.B83)));
        electricalRegisterInfo.setBatteryType(BatteryType.GEL);
        electricalRegisterInfo.setBatteryVoltage(new BigDecimal("13.4"));
        electricalRegisterInfo.setBatteryCurrent(new BigDecimal("131.213"));
        electricalRegisterInfo.setVehicleIdentificationNumber("VIN123");
        return electricalRegisterInfo;
    }

    /**
     * Build a registration model instance for testing purposes
     *
     * @return a {@link RegistrationModel} instance
     */
    private RegistrationModel buildRegistrationModel() {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setElectricalRegisterInfos(List.of(buildElectricalRegisterInfo()));
        registrationModel.setGasolineRegisterInfos(List.of(buildGasolineRegisterInfo()));
        return registrationModel;
    }
}
