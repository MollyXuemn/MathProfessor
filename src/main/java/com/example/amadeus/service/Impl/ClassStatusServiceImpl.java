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
    public String getWeekStatus(int threshold, int[][] week){
        int cancelledClasses = 0;
        int totalClasses = week.length;

        if (totalClasses == 0) {
            return "0.0%";
        }

        for(int[] arrivalTimes : week){
            ClassStatus classStatus = getClassStatus(threshold, arrivalTimes);
            if(classStatus.getStatus().equals("YES"))
                cancelledClasses++;
            }

        double percentageOfCancelledClasses = ((double) cancelledClasses / totalClasses) * 100;


        DecimalFormat df = new DecimalFormat("#.##");
        String formattedPercentage = df.format(percentageOfCancelledClasses) + "%";

        return formattedPercentage;
    }
}
