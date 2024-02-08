package com.example.springbootcoindesk.entity;

import lombok.Data;

import java.util.Map;

@Data
public class CoindeskData {
    private UpdateTime time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrencyData> bpi;
}
