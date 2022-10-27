package com.example.seerbit.controllers;

import com.example.seerbit.dto.StatisticResponse;
import com.example.seerbit.dto.TransactionsDto;
import com.example.seerbit.models.Transactions;
import com.example.seerbit.service.serviceimpl.TransactionServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {
    private  final TransactionServiceImplementation serviceImplementation;

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody @Valid TransactionsDto transactionRequest) {
         serviceImplementation.createTransaction(transactionRequest);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponse> getStatistics(StatisticResponse statistics) {
        serviceImplementation.getStatistics(statistics);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
