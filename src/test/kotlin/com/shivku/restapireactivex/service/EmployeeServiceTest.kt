package com.shivku.restapireactivex.service

import com.google.cloud.Timestamp
import com.shivku.restapireactivex.gateway.FirestoreGateway
import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.repository.EmployeeRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class EmployeeServiceTest {
    private val employeeRepository = mock(EmployeeRepository::class.java)
    private val firestoreGateway = mock(FirestoreGateway::class.java)
    private val employeeService = EmployeeService(
        employeeRepository,
        firestoreGateway
    )

    @Test
    fun `testShould create employee`() {
        val employeeToBeCreated = Employee(name = "name")
        val createdEmployee = employeeToBeCreated.copy(id = "createdId")
        `when`(employeeRepository.save(employeeToBeCreated)).thenReturn(createdEmployee)
        `when`(firestoreGateway.createEmployee(createdEmployee)).thenReturn(Single.just(Timestamp.MAX_VALUE))

        val createEmployeeObserver = employeeService.createEmployee(employeeToBeCreated).test()

        createEmployeeObserver.assertComplete()
        createEmployeeObserver.assertNoErrors()

        createEmployeeObserver.assertValue(createdEmployee)
        createEmployeeObserver.dispose()
    }

    @Test
    fun `Should get meployee`() {
        val id = "employeeId"
        val employee = Employee(name = "employeeName")
        `when`(employeeRepository.findById(id)).thenReturn(Optional.of(employee))

        val employeeObserver = employeeService.getEmployee(id).test()

        employeeObserver.assertComplete()
        employeeObserver.assertValue(Optional.of(employee))
        employeeObserver.dispose()
    }
}
