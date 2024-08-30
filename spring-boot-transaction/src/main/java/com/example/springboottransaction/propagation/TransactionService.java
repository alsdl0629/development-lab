package com.example.springboottransaction.propagation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createTransactionHistoryWithPropagationREQUIRED(Member member, int amount) {
        TransactionHistory transactionHistory = new TransactionHistory(member, amount);
        transactionHistoryRepository.save(transactionHistory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTransactionHistoryWithPropagationREQUIRES_NEW(Member member, int amount) {
        TransactionHistory transactionHistory = new TransactionHistory(member, amount);
        transactionHistoryRepository.save(transactionHistory);
    }
}
