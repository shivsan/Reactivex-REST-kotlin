package com.shivku.restapireactivex.service

import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.repository.EmployeeRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    public fun createEmployee(employee: Employee): Single<Employee> {
        return Observable.just(employeeRepository.save(employee)).singleOrError()
    }
}
