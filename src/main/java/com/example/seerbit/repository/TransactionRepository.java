package com.example.seerbit.repository;

import com.example.seerbit.dto.TransactionsDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class TransactionRepository {
    //I used concurrent hashmap for thread safety in case of multiple concurrent request
    public static final ConcurrentHashMap<String, BigDecimal> statistics = new ConcurrentHashMap<>() {{
            this.put("sum", BigDecimal.valueOf(0));
            this.put("avg", BigDecimal.valueOf(0));
            this.put("min", BigDecimal.valueOf(0));
            this.put("max", BigDecimal.valueOf(0));
            this.put("count", BigDecimal.valueOf(0));

        }
    };
    BigDecimal[] prevAmount = { BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO };
    BigDecimal counter = BigDecimal.valueOf(1);


    public void saveTransactionIfOlderThanThirtySeconds(TransactionsDto transaction) {
        transaction.setPrevTimestamp(String.valueOf(Instant.now()));
        counter.add(BigDecimal.valueOf(1));

        prevAmount[0] = transaction.getAmount(); //prev amount sent as a request: prevAmount[0]
        prevAmount[1] = transaction.getAmount(); //prev amount set as average: prevAmount[1]
        prevAmount[2] = transaction.getAmount(); //prev amount set as minimum : prevAmount[2]
        prevAmount[3] = transaction.getAmount(); //prev amount set as maximum : prevAmount[3]
        prevAmount[4] = transaction.getAmount(); //prev amount set as counter : prevAmount[4]


        statistics.put("sum", (statistics.get("sum").subtract(prevAmount[0]).add(transaction.getAmount())));
        statistics.put("avg", transaction.getAmount().multiply(counter).subtract(prevAmount[1]).add(transaction.getAmount()).divide(counter, 2));
        if (transaction.getAmount().compareTo(prevAmount[2]) < 0) {
            statistics.put("min", transaction.getAmount());
        } else {
            statistics.put("min", transaction.getAmount());
        }
        if (transaction.getAmount().compareTo(prevAmount[3]) > 0) {
            statistics.put("max", transaction.getAmount());
        } else {
            statistics.put("max", transaction.getAmount());
        }
        statistics.put("count", statistics.get("count").subtract(BigDecimal.valueOf(1)).add(counter));
    }
    public void saveTransactionIfLesserThanThirtySeconds(TransactionsDto transaction) {
        prevAmount[2] = transaction.getAmount(); //prev amount set as minimum : prevAmount[2]
        prevAmount[3] = transaction.getAmount(); //prev amount set as maximum : prevAmount[3]

        statistics.put("sum", (statistics.get("sum").add(transaction.getAmount())));
        statistics.put("avg", transaction.getAmount().add(transaction.getAmount()).divide(counter, 2));
        if (transaction.getAmount().compareTo(prevAmount[2]) < 0) {
            statistics.put("min", transaction.getAmount());
        } else {
            statistics.put("min", transaction.getAmount());
        }
        if (transaction.getAmount().compareTo(prevAmount[3]) > 0) {
            statistics.put("max", transaction.getAmount());
        } else {
            statistics.put("max", transaction.getAmount());
        }
        statistics.put("count", statistics.get("count").add(BigDecimal.valueOf(1)));
    }
}
