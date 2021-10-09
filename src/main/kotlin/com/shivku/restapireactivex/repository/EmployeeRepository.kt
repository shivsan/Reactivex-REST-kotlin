package com.shivku.restapireactivex.repository

import com.shivku.restapireactivex.models.Employee
import org.springframework.data.repository.CrudRepository

interface EmployeeRepository : CrudRepository<Employee, String>
