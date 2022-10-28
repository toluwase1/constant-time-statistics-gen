package com.example.seerbit.service.serviceimpl;

import com.example.seerbit.dto.StatisticResponse;
import com.example.seerbit.dto.TransactionsDto;
import com.example.seerbit.models.Transactions;
import com.example.seerbit.repository.TransactionRepository;
import com.example.seerbit.utils.ScheduleJob;
import org.assertj.core.api.BDDAssumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplementationTest {

    @InjectMocks
    private TransactionServiceImplementation transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ScheduleJob scheduleJob;

    StatisticResponse response;

    @Test
    void createTransaction() {
        TransactionsDto transactionsDto = new TransactionsDto();
        transactionsDto.setAmount(BigDecimal.valueOf(12.3343));
        transactionsDto.setTimestamp("2018-07-17T09:59:51.312Z");

        Transactions transactions = new Transactions();
        transactions.setAmount(transactionsDto.getAmount());
        Instant dateValue = Instant.parse(transactionsDto.getTimestamp());
        transactions.setTimestamp(Date.from(dateValue));


        TransactionRepository.statistics.put("sum", BigDecimal.valueOf(12.2243));

        assertEquals(BigDecimal.valueOf(12.2243), TransactionRepository.statistics.get("sum"), "Not equal");
    }

    @Test
    void getStatistics() {
//        TransactionsDto transactionsDto = new TransactionsDto();
//        transactionsDto.setAmount(BigDecimal.valueOf(12.3343));
//        transactionsDto.setTimestamp("2018-07-17T09:59:51.312Z");
//
//        Transactions transactions = new Transactions();
//        transactions.setAmount(transactionsDto.getAmount());
//        Instant dateValue = Instant.parse(transactionsDto.getTimestamp());
//        transactions.setTimestamp(Date.from(dateValue));
//
//        TransactionRepository.statistics.get("sum");
        response.setSum(String.valueOf(TransactionRepository.statistics.get("sum").setScale(2, RoundingMode.HALF_UP)));
        response.setAvg(String.valueOf(TransactionRepository.statistics.get("avg").setScale(2, RoundingMode.HALF_UP)));
        response.setMin(String.valueOf(TransactionRepository.statistics.get("min").setScale(2, RoundingMode.HALF_UP)));
        response.setMax(String.valueOf(TransactionRepository.statistics.get("max").setScale(2, RoundingMode.HALF_UP)));
        response.setCount(String.valueOf(TransactionRepository.statistics.get("count")));
        transactionService.getStatistics(response);
        assertEquals(BigDecimal.valueOf(0.00), TransactionRepository.statistics.get("sum"), "Not equal");
    }

    @Test
    void deleteStatistics() {
    }
}