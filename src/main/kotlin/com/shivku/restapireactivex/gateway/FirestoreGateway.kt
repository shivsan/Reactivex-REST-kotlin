package com.shivku.restapireactivex.gateway

import com.google.cloud.Timestamp
import com.google.cloud.firestore.Firestore
import com.shivku.restapireactivex.models.Employee
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.springframework.stereotype.Component

@Component
class FirestoreGateway(private val firestore: Firestore) {
    private val EMPLOYEES_COLLECTION_NAME = "employees"

    public fun createEmployee(employee: Employee): Single<Timestamp> {
        return Observable.fromFuture(
            firestore
                .collection(EMPLOYEES_COLLECTION_NAME)
                .document(employee.id)
                .set(employee)
        )
            .map { result -> result.updateTime }
            .singleOrError()// TODO: log instead of printing
    }
}
