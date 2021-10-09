package com.shivku.restapireactivex.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "employee")
data class Employee(
    @Id
    val id: String,
    val name: String,
)
