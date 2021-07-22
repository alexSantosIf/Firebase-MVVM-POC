package br.com.alex.firebasemvvmpoc.data.service

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import kotlinx.coroutines.tasks.await

class MainService {

    private lateinit var auth: FirebaseAuth

    suspend fun authenticateUser(email: String): SignInMethodQueryResult? {
        return try {
            auth
                .fetchSignInMethodsForEmail(email)
                .await()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signInOnFirebase(email: String, password: String): AuthResult? {
        return try {
            auth
                .signInWithEmailAndPassword(email, password)
                .await()
        } catch (e: Exception) {
            null
        }
    }
}