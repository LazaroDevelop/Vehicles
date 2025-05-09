package net.space.developer.vehicleapiservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import net.space.developer.vehicleapiservice.service.InventoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.API_BASE_URL;

/**
 * Inventory controller class to manage all the endpoints operations
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(API_BASE_URL)
@RequiredArgsConstructor
public class InventoryController {

    /**
     * Inventory service dependency injection
     */
    private final InventoryService inventoryService;

    /**
     * Endpoint to get all the vehicles
     *
     * @return a {@link ResponseEntity} with the response information
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles(){
        var response = inventoryService.getAllVehicles();

        if(Objects.isNull(response) || response.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get all the vehicles using pagination
     *
     * @param page the page number
     * @param size the page size
     * @param sort the sort params
     * @return a {@link ResponseEntity} with the paginated response information
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") List<String> sort
    ){
        String field = sort.getFirst();
        String dir = sort.getLast();

        Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest request = PageRequest.of(page, size, Sort.by(direction, field));

        var response = inventoryService.getAllVehicles(request);

        if(Objects.isNull(response) || response.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get all the vehicles by type
     *
     * @param type the type of vehicle {@link VehicleType}
     * @return a {@link ResponseEntity} with the response information
     */
    @GetMapping("/all/by-vehicles")
    public ResponseEntity<?> getAllByVehicleType(@RequestParam("type") String type){

        VehicleType vehicleType = VehicleType.valueOf(type);

        var response = inventoryService.getVehiclesByType(vehicleType);

        if(Objects.isNull(response) || response.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get all the vehicles by type using pagination
     *
     * @param type the type of vehicle {@link VehicleType}
     * @return a {@link ResponseEntity} with the paginated response information
     */
    @GetMapping("/all/by-vehicles")
    public ResponseEntity<?> getAllByVehicleTypePaginated(
            @RequestParam("type") String type,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") List<String> sort
    ){

        String field = sort.getFirst();
        String dir = sort.getLast();

        Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest request = PageRequest.of(page, size, Sort.by(direction, field));

        VehicleType vehicleType = VehicleType.valueOf(type);

        var response = inventoryService.getVehiclesByType(vehicleType, request);

        if(Objects.isNull(response) || response.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get a specific vehicle by identifier
     *
     * @param id the given id
     * @return a {@link ResponseEntity} of the found instance of {@link VehicleModel}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") Long id){
        var response = inventoryService.getVehicleById(id);

        if(Objects.isNull(response)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to create and store a new vehicle information
     *
     * @param vehicle the vehicle information {@link VehicleModel}
     * @return a {@link ResponseEntity} with saved or stored {@link VehicleModel} instance
     */
    @PostMapping("/create")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleModel vehicle){
        var response = inventoryService.createVehicle(vehicle);

        if(Objects.isNull(response)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to update an existing vehicle by identifier
     *
     * @param id the vehicle identifier
     * @param vehicle new information to update
     * @return a {@link ResponseEntity} with the updated instance of {@link VehicleModel}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable("id") Long id, @RequestBody VehicleModel vehicle){
        var response = inventoryService.updateVehicle(vehicle, id);

        if(Objects.isNull(response)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to transform or convert an electrical vehicle to a gasoline vehicle
     *
     * @param id the identifier of the electrical vehicle
     * @param gasolineTypes the set of gasoline's that use the transformed car
     * @return a {@link ResponseEntity} the converted instance of the vehicle {@link VehicleModel}
     */
    @PostMapping("/convert/{id}")
    public ResponseEntity<?> convertVehicle(@PathVariable("id") Long id, @RequestBody Set<GasolineType> gasolineTypes){

        var response = inventoryService.transformIntoGasoline(id, gasolineTypes);

        if(Objects.isNull(response)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to delete a specific vehicle by identifier
     *
     * @param id the identifier of the vehicle
     * @return a {@link ResponseEntity} with status content
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id){
        inventoryService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
