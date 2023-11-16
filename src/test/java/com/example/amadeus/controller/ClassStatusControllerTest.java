package com.example.amadeus.controller;

import com.example.amadeus.controller.dto.timesDTO;
import com.example.amadeus.controller.dto.weekDTO;
import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.ClassStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassStatusController.class)
public class ClassStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClassStatusService classStatusService;

    @Test
    public void testGetClassStatusEndpoint() throws Exception {
        int threshold = 3;
        int[] times = {-2, -1, 0, 1, 2};

        timesDTO timesDTO = new timesDTO();
        timesDTO.setTimes(times);

        when(classStatusService.getClassStatus(threshold, times))
                .thenReturn(new ClassStatus("NO"));

        mockMvc.perform(post("/class-status/single")
                        .param("threshold", String.valueOf(threshold))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(timesDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"NO\"}"));
    }

}
