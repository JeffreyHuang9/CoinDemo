package com.example.springbootcoindesk.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FormatCoindeskData {
    private String formatUpdateTime;
    private List<FormatCurrencyData> formatCurrencyDataList;
}
