package kmitl.taweewong.kuad.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthService {

    interface OnRegisterComplete {
        fun onRegisterSuccess(uid: String)
        fun onRegisterFailed(message: String)
    }

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun registerWithEmail(email: String, password: String, listener: OnRegisterComplete) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener({ task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            listener.onRegisterSuccess(user.uid)
                        } else {
                            listener.onRegisterFailed("User's not exist")
                        }
                    } else {
                        task.addOnFailureListener { exception ->
                            listener.onRegisterFailed(exception.message ?: "No error message")
                        }
                    }
                })
    }
}