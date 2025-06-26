package com.example.mongoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.mongoapi.model.Employee;
import com.example.mongoapi.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        repository.save(employee);
        return "redirect:/displayAll";
    }

    @GetMapping("/displayAll")
    public String getAllEmployees(Model model) {
        List<Employee> employees = repository.findAll();
        model.addAttribute("employees", employees);
        return "index"; // Reuse 
    }

    @GetMapping("/display/{id}")
    public String getEmployeeById(@PathVariable String id, Model model) {
        Optional<Employee> emp = repository.findById(id);
        model.addAttribute("employees", emp.map(List::of).orElse(List.of()));
        return "index";
    }
}
