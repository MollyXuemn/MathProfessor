package com.example.amadeus.service;

import com.example.amadeus.model.ClassStatus;
import org.springframework.stereotype.Service;

public interface ClassStatusService {
    ClassStatus getClassStatus(int threshold, int[] times);
    String getWeekStatus(int threshold, int[][] week);
}
