package com.example.amadeus.service;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.Impl.ClassStatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClassStatusServiceImplTest {

    @Autowired
    private ClassStatusService classStatusService;

    @Test
    public void testGetClassStatus() {
        int threshold = 3;
        int[] times = {-2, -1, 0, 1, 2};

        ClassStatus classStatus = classStatusService.getClassStatus(threshold, times);

        assertEquals("NO", classStatus.getStatus());
    }

    @Test
    public void testGetClassStatusWithEmptyTimes() {
        int threshold = 3;
        int[] times = {};

        ClassStatus classStatus = classStatusService.getClassStatus(threshold, times);

        assertEquals("YES", classStatus.getStatus());
    }

    @Test
    public void testGetWeekStatus() {
        int threshold = 3;
        int[][] week = {
                {-2, -1, 0, 1, 2},
                {0, 1, 2, 3, 4},
                {-1, 0, 1, 2, 3}
        };

        double weekStatus = classStatusService.getWeekStatus(threshold, week);

        assertEquals(66.67, weekStatus);
    }

    @Test
    public void testGetWeekStatusWithEmptyWeek() {
        int threshold = 3;
        int[][] week = {};

        double weekStatus = classStatusService.getWeekStatus(threshold, week);

        assertEquals(0.0, weekStatus);
    }
}
