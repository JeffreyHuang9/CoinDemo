package com.example.springbootcoindesk.service;

import com.example.springbootcoindesk.entity.Currency;
import com.example.springbootcoindesk.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(String currencyCode, Currency currency) {
        if (!currencyRepository.existsById(currencyCode)) {
            // Handle not found scenario
            return null;
        }
        currency.setCurrencyCode(currencyCode);
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(String currencyCode) {
        currencyRepository.deleteById(currencyCode);
    }
}
