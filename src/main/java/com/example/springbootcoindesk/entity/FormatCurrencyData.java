package com.example.springbootcoindesk.entity;

import lombok.Data;

@Data
public class FormatCurrencyData {
    private String code;
    private String chineseName;
    private double rate_float;

    // Constructors, getters, and setters
}
