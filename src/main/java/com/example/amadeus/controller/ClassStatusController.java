package com.example.amadeus.controller;

import com.example.amadeus.model.ClassStatus;
import com.example.amadeus.service.Impl.ClassStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class-status")
public class ClassStatusController {
    private ClassStatusServiceImpl classStatusService;

    @Autowired
    public ClassStatusController(ClassStatusServiceImpl classStatusService) {
        this.classStatusService = classStatusService;
    }

    @PostMapping("/single")
    public ClassStatus getClassStatus(@RequestParam int threshold, @RequestBody int[] times) {
        return classStatusService.getClassStatus(threshold, times);
    }

    @PostMapping("/weekly")
    public double getWeekStatus(@RequestParam int threshold, @RequestBody int[][] week) {
        return classStatusService.getWeekStatus(threshold, week);
    }
}
