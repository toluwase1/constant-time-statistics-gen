package com.example.seerbit.models;
import javax.validation.constraints.NotNull;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions {
//    @NotNull(message = "please provide an amount")
    BigDecimal amount;

    @NotNull(message = "please provide a timestamp")
    private Date timestamp;

    private String prevTimestamp;
}