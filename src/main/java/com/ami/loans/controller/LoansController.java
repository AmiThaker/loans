package com.ami.loans.controller;

import com.ami.loans.constants.LoansConstants;
import com.ami.loans.dto.ErrorResponseDTO;
import com.ami.loans.dto.LoansDTO;
import com.ami.loans.dto.ResponseDTO;
import com.ami.loans.service.LoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="CRUD REST APIs for Loans in Bank",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE LOAN"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    private LoansService loansService;

    public LoansController(LoansService loansService){
        this.loansService=loansService;
    }

    @Operation(
            summary="Create Loan REST API",
            description = "REST API to create new loan inside Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                        @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number should be 10 digits")
                                                              String mobileNumber){
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDTO> fetchLoanDTO(@RequestParam
                                                     @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number should be 10 digits")
                                                             String mobileNumber){
        LoansDTO loansDTO=loansService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansDTO);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode="417",
                    description = "Exception failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoansDTO loansDTO){
        boolean isUpdated= loansService.updateLoan(loansDTO);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Loan REST API",
            description = "REST API to delete loan based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode="417",
                    description = "Exception failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number should be 10 digits")
                                                              String mobileNumber){
        boolean isDeleted=loansService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }
    }

}
