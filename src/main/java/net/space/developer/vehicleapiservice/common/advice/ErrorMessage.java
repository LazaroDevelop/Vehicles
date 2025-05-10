package net.space.developer.vehicleapiservice.common.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Error message model dto class to manage custom exception messages
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int status;
    private String message;
    private String description;
    private LocalDateTime timestamp;
}
