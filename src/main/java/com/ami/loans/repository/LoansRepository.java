package com.ami.loans.repository;

import com.ami.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans,Long> {

    Optional<Loans> findByLoanNumber(String LoanNumber);
    Optional<Loans> findByMobileNumber(String mobileNumber);
}
