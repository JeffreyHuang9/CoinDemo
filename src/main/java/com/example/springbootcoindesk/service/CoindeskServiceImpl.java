package com.example.springbootcoindesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CoindeskServiceImpl implements CoindeskService {

    private final WebClient webClient;

    @Autowired
    private DataConversionService dataConversionService;

    public CoindeskServiceImpl() {
        this.webClient = WebClient.create();
    }

    @Override
    public String getCoindeskData() {
        String getCondeskData = webClient.get()
                .uri("https://api.coindesk.com/v1/bpi/currentprice.json")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return dataConversionService.convertCoindeskData(getCondeskData);
    }
}
