package com.example.springbootcoindesk.service;

import com.example.springbootcoindesk.entity.*;
import com.example.springbootcoindesk.repository.CurrencyRepository;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataConversionServiceImpl implements DataConversionService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public String convertCoindeskData(String data) {

        try {
            Gson gson = new Gson();
            CoindeskData coindeskData = gson.fromJson(data, CoindeskData.class);

            // Parsing the original date string
            String originalTime = coindeskData.getTime().getUpdatedISO();
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date parsedDate = originalFormat.parse(originalTime);

            // Formatting the date to the desired format
            SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String updatedTime = desiredFormat.format(parsedDate);

            Map<String, CurrencyData> bpi = coindeskData.getBpi();

            FormatCoindeskData formatCoindeskData = new FormatCoindeskData();
            formatCoindeskData.setFormatUpdateTime(updatedTime);
            List<FormatCurrencyData> formatCurrencyDataList = new ArrayList<>();

            Map<String, Currency> currencyMap = currencyRepository.findAll().stream()
                    .collect(Collectors.toMap(Currency::getCurrencyCode, currency -> currency));
            for(CurrencyData currencyData : bpi.values()){
                FormatCurrencyData formatCurrencyData = new FormatCurrencyData();
                formatCurrencyData.setCode(currencyData.getCode());
                formatCurrencyData.setChineseName("未設定");
                if(currencyMap.containsKey(currencyData.getCode())){
                    formatCurrencyData.setChineseName(currencyMap.get(currencyData.getCode()).getChineseName());
                }
                formatCurrencyData.setRate_float(currencyData.getRate_float());
                formatCurrencyDataList.add(formatCurrencyData);
            }
            formatCoindeskData.setFormatCurrencyDataList(formatCurrencyDataList);

            return gson.toJson(formatCoindeskData);

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
