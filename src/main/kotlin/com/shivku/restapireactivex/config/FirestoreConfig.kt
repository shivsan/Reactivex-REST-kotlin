package com.shivku.restapireactivex.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import com.google.firebase.messaging.FirebaseMessaging
import java.io.FileInputStream
import kotlin.random.Random
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ClassPathResource

@ConfigurationProperties("spring.application")
@ConstructorBinding
data class SpringApplicationConfiguration(
    val name: String
)

@Configuration
class MessagingServiceConfiguration {
    @Bean("firebaseapp")
    fun firebaseInstance(springApplicationConfiguration: SpringApplicationConfiguration): FirebaseApp {
        val googleCredentials = GoogleCredentials.fromStream(FileInputStream("./src/main/resources/firebase-credentials.json"))
            .createScoped(
                listOf(
                    "https://www.googleapis.com/auth/datastore",
                    "https://www.googleapis.com/auth/cloud-platform"
                )
            )
        val options = FirebaseOptions.builder()
            .setCredentials(googleCredentials)
            .build()
        return FirebaseApp.initializeApp(options, springApplicationConfiguration.name)
    }

    @Bean
    @DependsOn("firebaseapp")
    fun firestore(firebase: FirebaseApp): Firestore {
        return FirestoreClient.getFirestore(firebase)
    }
}
