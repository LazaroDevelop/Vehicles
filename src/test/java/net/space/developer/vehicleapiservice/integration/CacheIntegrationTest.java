package net.space.developer.vehicleapiservice.integration;

import lombok.extern.slf4j.Slf4j;
import net.space.developer.vehicleapiservice.config.CacheConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Cache integration test class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Import(CacheConfig.class)
class CacheIntegrationTest {

    /**
     * Inject the cache manager bean
     */
    @Autowired
    CacheManager cacheManager;

    /**
     * Test the caffeine cache
     */
    @Test
    @DisplayName("Should work caffeine cache")
    void shouldWorkCaffeineCache() {
        String vehicleCache = "vehicle";
        String vehiclesCache = "vehicles";
        String vehicleRegistrationCache = "vehicle-registration";

        String key = "testKey";
        String value = "testValue";

        var cacheOfVehicle = cacheManager.getCache(vehicleCache);
        var cacheOfVehicles = cacheManager.getCache(vehiclesCache);
        var cacheOfVehiclesRegistration = cacheManager.getCache(vehicleRegistrationCache);

        assert cacheOfVehicle != null;
        cacheOfVehicle.put(key, value);

        assert cacheOfVehicles != null;
        cacheOfVehicles.put(key, value);

        assert cacheOfVehiclesRegistration != null;
        cacheOfVehiclesRegistration.put(key, value);

        var vehicleValue = cacheOfVehicle.get(key, String.class);
        var vehiclesValue = cacheOfVehicles.get(key, String.class);
        var vehicleRegistrationValue = cacheOfVehiclesRegistration.get(key, String.class);

        assertEquals(value, vehicleValue);
        assertEquals(value, vehiclesValue);
        assertEquals(value, vehicleRegistrationValue);
    }

}
