package com.shivku.restapireactivex.controllers

import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.service.EmployeeService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/{id}")
    public fun get(@PathVariable("id") id: String): Single<ResponseEntity<Employee>> {
        return employeeService.getEmployee(id)
            .map { result -> getResponse(result) }.subscribeOn(Schedulers.io())
    }

    private fun getResponse(result: Optional<Employee>) = result
        .map { r -> ResponseEntity.ok(r) }
        .orElse(ResponseEntity.notFound().build())
}

// TODO: testing - unit tests and integration tests
// TODO: external calls to other APIs
