package com.ami.loans.service;

import com.ami.loans.dto.LoansDTO;

public interface LoansService {

    void createLoan(String mobileNumber);

    LoansDTO fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDTO loansDTO);

    boolean deleteLoan(String mobileNumber);
}
