package com.shivku.restapireactivex.controllers

import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.service.EmployeeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("employees")
class EmployeeController(private val employeeService: EmployeeService) {
    @PostMapping
    public fun createEmployee(@RequestBody employee: Employee): Employee {
        return employeeService.createEmployee(employee)
    }
}
