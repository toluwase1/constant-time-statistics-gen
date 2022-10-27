package com.example.seerbit.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionsDto {
    private BigDecimal amount;
    private String timestamp;
    private String prevTimestamp;
}
