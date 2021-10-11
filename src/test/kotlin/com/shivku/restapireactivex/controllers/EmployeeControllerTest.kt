package com.shivku.restapireactivex.controllers

import com.shivku.restapireactivex.models.Employee
import com.shivku.restapireactivex.service.EmployeeService
import io.reactivex.rxjava3.core.Single
import java.util.*
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.http.HttpStatus

class EmployeeControllerTest {
    private val employeeService = mock(EmployeeService::class.java)
    private val employeeController = EmployeeController(employeeService)

    @Test
    fun `Should create employee`() {
        val employeeToCreate = Employee(name = "employee")
        val createdEmployee = employeeToCreate.copy(id = "newEmployee")
        `when`(employeeService.createEmployee(employeeToCreate)).thenReturn(Single.just(createdEmployee))

        val resultSingle = employeeController.createEmployee(employeeToCreate)

        resultSingle.blockingSubscribe { e -> assertEquals(createdEmployee, e) }
    }

    @Test
    fun `Should get employee`() {
        val id = "employeeId"
        val employee = Employee(name = "employee")
        `when`(employeeService.getEmployee(id)).thenReturn(Single.just(Optional.of(employee)))

        val resultSingle = employeeController.get(id)

        resultSingle.blockingSubscribe { result ->
            run {
                assertEquals(HttpStatus.OK, result.statusCode)
                assertEquals(employee, result.body)
            }
        }
    }
}
