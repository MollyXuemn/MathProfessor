package com.example.amadeus.controller;

import com.example.amadeus.controller.dto.timesDTO;
import com.example.amadeus.controller.dto.weekDTO;
import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.ClassStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class-status")
public class ClassStatusController {
    private ClassStatusService classStatusService;

    @Autowired
    public ClassStatusController(ClassStatusService classStatusService) {
        this.classStatusService = classStatusService;
    }

    @PostMapping("/single")
    public ClassStatus getClassStatus(@RequestParam int threshold, @RequestBody timesDTO times) {
        return classStatusService.getClassStatus(threshold, times.getTimes());
    }

    @PostMapping("/weekly")
    public double getWeekStatus(@RequestParam int threshold, @RequestBody weekDTO weekData) {
        return classStatusService.getWeekStatus(threshold, weekData.getWeek());
    }
}
