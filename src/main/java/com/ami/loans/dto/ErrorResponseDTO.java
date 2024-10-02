package com.ami.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name="ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDTO {

    @Schema(
            description = "Path Invoked by client"
    )
    private String apiPath;
    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;
    @Schema(
            description = "Time represnting when the error happened"
    )
    private LocalDateTime errorTime;
}
