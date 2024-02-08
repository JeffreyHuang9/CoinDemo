package com.example.springbootcoindesk.service;

import com.example.springbootcoindesk.entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Currency addCurrency(Currency currency);
    Currency updateCurrency(String currencyCode, Currency currency);
    void deleteCurrency(String currencyCode);
}
