package com.example.springbootcoindesk.controller;

import com.example.springbootcoindesk.service.CoindeskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CoindeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoindeskService coindeskService;

    @Test
    void testGetCoindeskData() throws Exception {
        // 模拟返回的 Coindesk 数据
        String mockCoindeskData = "{\"time\":{\"updated\":\"Feb 7, 2024 16:46:00 UTC\"},\"bpi\":{\"USD\":{\"code\":\"USD\",\"rate\":\"39,840.0200\",\"description\":\"United States Dollar\",\"rate_float\":39840.02}}}";

        // 使用 Mockito 模拟 CoindeskService 中的 getCoindeskData() 方法
        when(coindeskService.getCoindeskData()).thenReturn(mockCoindeskData);

        // 发送 GET 请求并验证返回结果
        mockMvc.perform(MockMvcRequestBuilders.get("/api/coindesk"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mockCoindeskData));
    }
}
