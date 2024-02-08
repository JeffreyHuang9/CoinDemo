package com.example.springbootcoindesk.entity;

import lombok.Data;

@Data
public class CurrencyData {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private double rate_float;

    // Constructors, getters, and setters
}
