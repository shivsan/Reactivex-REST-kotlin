package com.shivku.restapireactivex.service

import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    public fun createEmployee(employee: Employee): Employee {
        return employeeRepository.save(employee)
    }
}
