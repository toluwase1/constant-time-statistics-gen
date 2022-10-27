package com.example.seerbit.service.serviceimpl;

import com.example.seerbit.dto.StatisticResponse;
import com.example.seerbit.dto.TransactionsDto;
import com.example.seerbit.models.Transactions;
import com.example.seerbit.repository.TransactionRepository;
import com.example.seerbit.utils.ScheduleJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImplementation {
    private final TransactionRepository transactionRepository;
    private final ScheduleJob scheduleJob;
    static Instant current = Instant.now();
    public static final HashMap<String, Instant> keepStartTime = new HashMap<>(){{
        this.put("startTime", Instant.now());
    }};
    public static final HashMap<String, Instant> keepCurrentTime = new HashMap<>(){{
        this.put("currentTime", current);
    }};
    int count = 0;
    String instantTimeForFirstRequest;
    TransactionsDto transactionsDto;
    public void createTransaction(TransactionsDto transactionRequest) {
        count++;
        Transactions transactions = new Transactions();
        Instant dateValue = Instant.parse(transactionRequest.getTimestamp());

        transactions.setAmount(new BigDecimal(String.valueOf(transactionRequest.getAmount())));
        transactions.setTimestamp(Date.from(dateValue));
//        if (count < 2) {
////            instantTimeForFirstRequest = String.valueOf(instant);
//            keepStartTime.put("startTime", instant);
//        }

        keepCurrentTime.put("currentTime", current=Instant.now());
        if (scheduleJob.isTimeDifferenceGreaterThan30Seconds()){
            transactionRepository.saveTransactionIfOlderThanThirtySeconds(transactions);
        } else {
            transactionRepository.saveTransactionIfLesserThanThirtySeconds(transactions);
        }
    }
    @GetMapping
    public StatisticResponse getStatistics(StatisticResponse response) {
        response.setSum(String.valueOf(TransactionRepository.statistics.get("sum")));
        response.setAvg(String.valueOf(TransactionRepository.statistics.get("avg")));
        response.setMin(String.valueOf(TransactionRepository.statistics.get("min")));
        response.setMax(String.valueOf(TransactionRepository.statistics.get("max")));
        response.setCount(String.valueOf(TransactionRepository.statistics.get("count")));
        return response;
    }
}