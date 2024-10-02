package com.ami.loans.mapper;

import com.ami.loans.dto.LoansDTO;
import com.ami.loans.entity.Loans;

public class LoansMapper {

    public static Loans mapToLoans(LoansDTO loansDTO, Loans loans){
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
        loans.setTotalLoan(loansDTO.getTotalLoan());

        return loans;
    }

    public static LoansDTO mapToLoansDTO(Loans loans, LoansDTO loansDTO){
        loansDTO.setLoanNumber(loans.getLoanNumber());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setAmountPaid(loans.getAmountPaid());
        loansDTO.setMobileNumber(loans.getMobileNumber());
        loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
        loansDTO.setTotalLoan(loans.getTotalLoan());

        return loansDTO;
    }
}
