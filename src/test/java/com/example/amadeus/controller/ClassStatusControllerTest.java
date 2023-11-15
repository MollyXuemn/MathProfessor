package com.example.amadeus.controller;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.Impl.ClassStatusServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClassStatusController.class)
@ExtendWith(MockitoExtension.class)
public class ClassStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClassStatusServiceImpl classStatusService;

    @Test
    public void testGetClassStatusEndpoint() throws Exception {
        int threshold = 3;
        int[] times = {-2, -1, 0, 1, 2};

        when(classStatusService.getClassStatus(threshold, times))
                .thenReturn(new ClassStatus("NO"));

        mockMvc.perform(MockMvcRequestBuilders.post("/class-status/single")
                        .param("threshold", String.valueOf(threshold))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(times)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"NO\"}"));
    }

    @Test
    public void testGetWeekStatusEndpoint() throws Exception {
        int threshold = 3;
        int[][] week = {
                {-2, -1, 0, 1, 2},
                {0, 1, 2, 3, 4},
                {-1, 0, 1, 2, 3}
        };

            when(classStatusService.getWeekStatus(threshold, week))
                    .thenReturn(66.67);

        mockMvc.perform(MockMvcRequestBuilders.post("/class-status/weekly")
                        .param("threshold", String.valueOf(threshold))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(week)))
                .andExpect(status().isOk())
                .andExpect(content().string("66.67"));
    }
}
