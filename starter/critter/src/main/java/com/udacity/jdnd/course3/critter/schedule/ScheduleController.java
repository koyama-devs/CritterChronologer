package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.utils.ConvertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ConvertDataService convertDataService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertDataService.toScheduleDTO(scheduleService.createSchedule(convertDataService.toSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream().map(schedule -> convertDataService.toScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
       return scheduleService.getScheduleForPet(petId).stream().map(schedule -> convertDataService.toScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId).stream().map(schedule -> convertDataService.toScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream().map(schedule -> convertDataService.toScheduleDTO(schedule)).collect(Collectors.toList());
    }
}
