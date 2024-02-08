package com.example.springbootcoindesk.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Currency {
    @Id
    private String currencyCode;
    private String chineseName;
    private double exchangeRate;

    public Currency(String currencyCode, String chineseName, double exchangeRate) {
        this.setCurrencyCode(currencyCode);
        this.setChineseName(chineseName);
        this.setExchangeRate(exchangeRate);
    }

    public Currency() {

    }

    // Getters and setters
}
