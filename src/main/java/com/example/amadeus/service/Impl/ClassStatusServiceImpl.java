package com.example.amadeus.service.Impl;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.ClassStatusService;
import org.springframework.stereotype.Service;

@Service
public class ClassStatusServiceImpl implements ClassStatusService {
    @Override
    public ClassStatus getClassStatus(int threshold, int[] times) {
        int onTimeOrEarlyStudents = 0;

        for(int arrivalTime :times){
            if(arrivalTime <= 0){
                onTimeOrEarlyStudents++;
            }
        }
        String status = onTimeOrEarlyStudents >= threshold ? "NO" : "YES";
        return new ClassStatus(status);
    }

    @Override
    public double getWeekStatus(int threshold, int[][] week){
        return 0;
    }
}
