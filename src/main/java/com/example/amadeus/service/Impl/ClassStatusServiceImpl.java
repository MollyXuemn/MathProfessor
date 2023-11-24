package com.example.amadeus.service.Impl;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.ClassStatusService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ClassStatusServiceImpl implements ClassStatusService {
    /**
     * @param threshold The number of students that must be on time or early for class to go on
     * @param times The arrival times of each student
     * @return A ClassStatus object with the status of the class
     */
    @Override
    public ClassStatus getClassStatus(int threshold, int[] times) {
        int onTimeOrEarlyStudents = 0;

        for(int arrivalTime :times){
            if(arrivalTime <= 0){
                onTimeOrEarlyStudents++;
                if(onTimeOrEarlyStudents>= threshold){
                    return new ClassStatus("No");
                }
            }
        }

        String status = onTimeOrEarlyStudents >= threshold ? "NO" : "YES";

        return new ClassStatus(status);
    }
    /**
     * @param threshold The number of students that must be on time or early for class to go on
     * @param week Rows: The different classes
     *            Columns: The arrival times of each student for every class in the week
     * @return A String with the percentage of classes that were cancelled
     */
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
