package com.example.amadeus.service.Impl;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.ClassStatusService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;

@Service
public class ClassStatusServiceImpl implements ClassStatusService {
    /**
     * @param threshold The number of students that must be on time or early for class to go on
     * @param times The arrival times of each student
     * @return A ClassStatus object with the status of the class
     */
    @Override
    public ClassStatus getClassStatus(int threshold, int[] times) {
        int onTimeOrEarlyStudents;

        onTimeOrEarlyStudents = (int)Arrays.stream(times)
                .filter(arrivalTime -> arrivalTime <= 0)
                .limit(threshold)
                .count();

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
        int cancelledClasses;
        int totalClasses = week.length;

        if (totalClasses == 0) {
            return "0.0%";
        }

        cancelledClasses = Arrays.stream(week)
                .filter(arrivalTimes -> getClassStatus(threshold, arrivalTimes).equals("YES"))
                .toArray().length;

        double percentageOfCancelledClasses = ((double) cancelledClasses / totalClasses) * 100;


        DecimalFormat df = new DecimalFormat("#.##");
        String formattedPercentage = df.format(percentageOfCancelledClasses) + "%";

        return formattedPercentage;
    }
}
