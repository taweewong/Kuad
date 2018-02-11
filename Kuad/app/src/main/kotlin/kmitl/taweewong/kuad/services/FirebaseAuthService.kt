package kmitl.taweewong.kuad.services

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthService {

    interface OnRegisterComplete {
        fun onRegisterSuccess(uid: String)
        fun onRegisterFailed(message: String)
    }

    interface OnSignInComplete {
        fun onSignInSuccess(uid: String)
        fun onSignInFailed(message: String)
    }

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun registerWithEmail(email: String, password: String, listener: OnRegisterComplete) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener({ task ->
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

    fun signInWithEmail(email: String, password: String, listener: OnSignInComplete) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            listener.onSignInSuccess(user.uid)
                        } else {
                            listener.onSignInFailed("User's not exist")
                        }
                    } else {
                        task.addOnFailureListener { exception ->
                            listener.onSignInFailed(exception.message ?: "No error message")
                        }
                    }
                })
    }

    fun isUserSignedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }
}