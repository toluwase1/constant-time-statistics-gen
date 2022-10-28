package com.example.seerbit.service.serviceimpl;

import com.example.seerbit.dto.StatisticResponse;
import com.example.seerbit.dto.TransactionsDto;
import com.example.seerbit.models.Transactions;
import com.example.seerbit.repository.TransactionRepository;
import com.example.seerbit.service.TransactionService;
import com.example.seerbit.utils.ScheduleJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImplementation implements TransactionService {
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

    @Override
    public void createTransaction(TransactionsDto transactionRequest) {
        count++;
        Transactions transactions = new Transactions();
        Instant dateValue = Instant.parse(transactionRequest.getTimestamp());

        transactions.setAmount(new BigDecimal(String.valueOf(transactionRequest.getAmount())));
        transactions.setTimestamp(Date.from(dateValue));

//        //temporarily Keep amount sent in the request on first count
//        if (TransactionRepository.statistics.get("count").compareTo(BigDecimal.valueOf(2)) < 0 ) {
//
//        }


        keepCurrentTime.put("currentTime", current=Instant.now());
        if (scheduleJob.isTimeDifferenceGreaterThan30Seconds()){
            transactionRepository.saveTransactionIfOlderThanThirtySeconds(transactionRequest);
        } else {
            transactionRepository.saveTransactionIfLesserThanThirtySeconds(transactionRequest);
        }
    }

    @Override
    public StatisticResponse getStatistics(StatisticResponse response) {
        response.setSum(String.valueOf(TransactionRepository.statistics.get("sum").setScale(2, RoundingMode.HALF_UP)));
        response.setAvg(String.valueOf(TransactionRepository.statistics.get("avg").setScale(2, RoundingMode.HALF_UP)));
        response.setMin(String.valueOf(TransactionRepository.statistics.get("min").setScale(2, RoundingMode.HALF_UP)));
        response.setMax(String.valueOf(TransactionRepository.statistics.get("max").setScale(2, RoundingMode.HALF_UP)));
        response.setCount(String.valueOf(TransactionRepository.statistics.get("count")));
        return response;
    }

    @Override
    public StatisticResponse deleteStatistics(StatisticResponse response) {
        response.setSum(String.valueOf(TransactionRepository.statistics.put("sum", BigDecimal.valueOf(0))));
        response.setAvg(String.valueOf(TransactionRepository.statistics.put("avg", BigDecimal.valueOf(0))));
        response.setMin(String.valueOf(TransactionRepository.statistics.put("min", BigDecimal.valueOf(0))));
        response.setMax(String.valueOf(TransactionRepository.statistics.put("max", BigDecimal.valueOf(0))));
        response.setCount(String.valueOf(TransactionRepository.statistics.put("count", BigDecimal.valueOf(0))));
        return response;
    }
}