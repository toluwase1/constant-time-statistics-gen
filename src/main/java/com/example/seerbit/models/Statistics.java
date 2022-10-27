package com.example.seerbit.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Statistics {
    private String sum;
    private String avg;
    private String max;
    private String min;
    private String count;
}
