package com.ami.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name="Loans",
        description = "Schema to hold Loans Information"
)
public class LoansDTO {

    @NotEmpty(message="Mobile Number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile Number should be 10 digits")
    @Schema(
            description = "Mobile Number of Customer",example = "1234567890"
    )
    private String mobileNumber;

    @NotEmpty(message="Loan Number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message="Loan Number should be 10 digits")
    @Schema(
            description= "Loan Number of Customer", example="12345"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan Type should not be empty!")
    @Schema(
            description="Type of the Loan",example = "Home Loan"
    )
    private String loanType;

    @Positive(message="Total Loan should be greater than 0")
    @Schema(
            description="Total Loan Amount",example = "1000"
    )
    private int totalLoan;

    @PositiveOrZero(message="Amount Paid should be 0 or greater than 0")
    @Schema(
            description = "Total Loan AmountPaid",example = "100"
    )
    private int amountPaid;

    @PositiveOrZero(message="Outstanding Amount should be 0 or greater than 0")
    @Schema(
            description="Outstanding Amount against the laon", example = "990"
    )
    private int outstandingAmount;
}
