package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertDataService {
    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    public Pet toPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer customer = customerService.findById(petDTO.getOwnerId());
        pet.setOwner(customer);
        return pet;
    }

    public PetDTO toPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Long> petIds = customerDTO.getPetIds();
        if(petIds != null) {
            List<Pet> pets = petIds.stream().map(petId -> petService.getPet(petId)).collect(Collectors.toList());
            customer.setPets(pets);
        }
        return customer;
    }

    public CustomerDTO toCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> pets = customer.getPets();
        List<Long> petIds = pets.stream().map(pet -> pet.getId()).collect(Collectors.toList());
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    public Employee toEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public EmployeeDTO toEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public Schedule toSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();
        if(employeeIds != null) {
            List<Employee> employees = employeeIds.stream().map(employeeId -> employeeService.getEmployee(employeeId)).collect(Collectors.toList());
            schedule.setEmployees(employees);
        }
        if(petIds != null){
            List<Pet> pets = petIds.stream().map(petId -> petService.getPet(petId)).collect(Collectors.toList());
            schedule.setPets(pets);
        }
        return schedule;
    }

    public ScheduleDTO toScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Employee> employees = schedule.getEmployees();
        List<Pet> pets = schedule.getPets();
        List<Long> employeeIds = employees.stream().map(employee -> employee.getId()).collect(Collectors.toList());
        List<Long> petIds = pets.stream().map(pet -> pet.getId()).collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    public EmployeeRequest toEmployeeRequest(EmployeeRequestDTO employeeRequestDTO){
        EmployeeRequest employeeRequest = new EmployeeRequest();
        BeanUtils.copyProperties(employeeRequestDTO, employeeRequest);
        return employeeRequest;
    }

    public EmployeeRequestDTO toEmployeeRequestDTO(EmployeeRequest employeeRequest){
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDTO);
        return employeeRequestDTO;
    }
}
