package com.example.amadeus.service.Impl;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.ClassStatusService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

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
        int cancelledClasses = 0;
        int totalClasses = week.length;

        for(int[] arrivalTime : week){
            ClassStatus classStatus = getClassStatus(threshold, arrivalTime);
            if(classStatus.getStatus().equals("YES"))
                cancelledClasses++;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        double pertangeOfCancelledClasses = Double.valueOf(df.format( ((double) cancelledClasses / totalClasses) * 100));

        return pertangeOfCancelledClasses;
    }
}
