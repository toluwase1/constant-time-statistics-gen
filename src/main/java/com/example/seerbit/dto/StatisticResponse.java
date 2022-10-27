package com.example.seerbit.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StatisticResponse {
    private String sum;
    private String avg;
    private String max;
    private String min;
    private String count;

}
