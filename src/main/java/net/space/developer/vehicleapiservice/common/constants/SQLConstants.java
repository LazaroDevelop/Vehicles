package net.space.developer.vehicleapiservice.common.constants;

import org.intellij.lang.annotations.Language;

/**
 * SQLConstants class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

public final class SQLConstants {

    // SQL Constants
    @Language("JPAQL")
    public static final String JPQL_PERFORMANCE_ALL = "SELECT v FROM Vehicle v LEFT JOIN FETCH v.fuelTypePostConversion LEFT JOIN FETCH v.gasolineType";

    @Language("PostgreSQL")
    public static final String SQL_PERFORMANCE_ALL =
           """
           SELECT v FROM t_vehicle v
           LEFT JOIN electrical_vehicle_fuel_type_post_conversion ev ON v.vehicle_id = ev.electrical_vehicle_vehicle_id
           LEFT_JOIN gasoline_vehicle_gasoline_type gvt ON v.vehicle_id = gvt.gasoline_vehicle_vehicle_id
           ORDER BY :field :direction
           LIMIT :size OFFSET :page
           """;

    private SQLConstants(){}
}
