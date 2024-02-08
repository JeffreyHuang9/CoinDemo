package com.example.springbootcoindesk.controller;

import com.example.springbootcoindesk.entity.Currency;
import com.example.springbootcoindesk.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @PostMapping
    public Currency addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/{currencyCode}")
    public Currency updateCurrency(@PathVariable String currencyCode, @RequestBody Currency currency) {
        return currencyService.updateCurrency(currencyCode, currency);
    }

    @DeleteMapping("/{currencyCode}")
    public void deleteCurrency(@PathVariable String currencyCode) {
        currencyService.deleteCurrency(currencyCode);
    }
}
