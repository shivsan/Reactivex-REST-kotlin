package com.shivku.restapireactivex.service

import com.shivku.restapireactivex.gateway.FirestoreGateway
import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.repository.EmployeeRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val firestoreGateway: FirestoreGateway
) {
    public fun createEmployee(employeeToBeCreated: Employee): Single<Employee> {
        return Observable.just(employeeRepository.save(employeeToBeCreated))
            .singleOrError()
            .flatMap { employee ->
                firestoreGateway.createEmployee(employee)
                    .map { employee }
            }
    }

    fun createEmployeeNonReactive(employeeToBeCreated: Employee): Employee {
        employeeRepository.save(employeeToBeCreated)
        firestoreGateway.createEmployee(employeeToBeCreated).blockingSubscribe()
        return employeeToBeCreated
    }
}
