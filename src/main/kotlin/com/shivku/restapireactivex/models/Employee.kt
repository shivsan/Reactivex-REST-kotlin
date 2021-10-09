package com.shivku.restapireactivex.models

import java.util.*
import javax.annotation.Generated
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "employee")
data class Employee(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
)
