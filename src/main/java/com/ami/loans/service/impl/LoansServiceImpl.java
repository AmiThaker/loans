package com.ami.loans.service.impl;

import com.ami.loans.constants.LoansConstants;
import com.ami.loans.dto.LoansDTO;
import com.ami.loans.entity.Loans;
import com.ami.loans.exception.LoansAlreadyExistsException;
import com.ami.loans.exception.ResourceNotFoundException;
import com.ami.loans.mapper.LoansMapper;
import com.ami.loans.repository.LoansRepository;
import com.ami.loans.service.LoansService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements LoansService {

    private LoansRepository loansRepository;

    public LoansServiceImpl(LoansRepository loansRepository){
        this.loansRepository=loansRepository;
    }

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> existingLoan=loansRepository.findByMobileNumber(mobileNumber);
        if(existingLoan.isPresent()){
            throw new LoansAlreadyExistsException("Loan already registered with given mobile number : "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
;    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan=new Loans();
        long randomLoanNumber = 1000000000L + (long)(Math.random() * 9000000000L);

        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);

        return newLoan;
    }

    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loans loan=loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        return LoansMapper.mapToLoansDTO(loan,new LoansDTO());
    }

    @Override
    public boolean updateLoan(LoansDTO loansDTO) {
        Loans loan=loansRepository.findByLoanNumber(loansDTO.getLoanNumber())
                .orElseThrow(()->new ResourceNotFoundException("Loan","loanNumber", loansDTO.getLoanNumber()));
        LoansMapper.mapToLoans(loansDTO,loan);
        loansRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan=loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        loansRepository.deleteById(loan.getLoanId());
        return true;
    }
}
