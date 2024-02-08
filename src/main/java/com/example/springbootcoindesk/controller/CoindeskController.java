package com.example.springbootcoindesk.controller;

import com.example.springbootcoindesk.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @GetMapping
    public String getAllCurrencies() {
        return coindeskService.getCoindeskData();
    }
}
