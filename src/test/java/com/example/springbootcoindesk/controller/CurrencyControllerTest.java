package com.example.springbootcoindesk.controller;

import com.example.springbootcoindesk.entity.Currency;
import com.example.springbootcoindesk.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @Test
    void testGetAllCurrencies() throws Exception {
        Currency currency1 = new Currency("USD", "美元", 1.0);
        Currency currency2 = new Currency("EUR", "欧元", 0.82);
        when(currencyService.getAllCurrencies()).thenReturn(Arrays.asList(currency1, currency2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/currencies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyCode").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currencyCode").value("EUR"));
    }

    @Test
    void testAddCurrency() throws Exception {
        Currency currency = new Currency("GBP", "英镑", 0.73);
        when(currencyService.addCurrency(any(Currency.class))).thenReturn(currency);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currencyCode\":\"GBP\",\"chineseName\":\"英镑\",\"exchangeRate\":0.73}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyCode").value("GBP"));
    }

    @Test
    void testUpdateCurrency() throws Exception {
        Currency currency = new Currency("USD", "美元", 1.0);
        when(currencyService.updateCurrency(any(String.class), any(Currency.class))).thenReturn(currency);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/currencies/USD")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currencyCode\":\"USD\",\"chineseName\":\"美元\",\"exchangeRate\":1.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyCode").value("USD"));
    }

    @Test
    void testDeleteCurrency() throws Exception {
        doNothing().when(currencyService).deleteCurrency("USD");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/currencies/USD"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
