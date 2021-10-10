package com.shivku.restapireactivex.controllers

import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.service.EmployeeService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: EmployeeService) {
    @PostMapping
    public fun createEmployee(@RequestBody employee: Employee): Single<Employee> {
        return employeeService.createEmployee(employee)
            .subscribeOn(Schedulers.io())
    }

    @PostMapping("/non-reactive")
    public fun createEmployeeNonReactive(@RequestBody employee: Employee): Employee {
        return employeeService.createEmployeeNonReactive(employee)
    }
}
// TODO: write to firestore
// TODO: testing - unit tests and integration tests
// TODO: external calls to other APIs
