package com.example.seerbit.service;

import com.example.seerbit.dto.StatisticResponse;
import com.example.seerbit.dto.TransactionsDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void createTransaction(TransactionsDto transactionRequest);
    StatisticResponse getStatistics(StatisticResponse response);
    StatisticResponse deleteStatistics(StatisticResponse response);

}
