package kmitl.taweewong.kuad.services

import com.google.firebase.auth.FirebaseAuth
import kmitl.taweewong.kuad.descriptions.ErrorMessage.NO_ERROR_MESSAGE
import kmitl.taweewong.kuad.descriptions.ErrorMessage.USER_NOT_EXIST_ERROR_MESSAGE

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
                            listener.onRegisterFailed(USER_NOT_EXIST_ERROR_MESSAGE)
                        }
                    } else {
                        task.addOnFailureListener { exception ->
                            listener.onRegisterFailed(exception.message ?: NO_ERROR_MESSAGE)
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
                            listener.onSignInFailed(USER_NOT_EXIST_ERROR_MESSAGE)
                        }
                    } else {
                        task.addOnFailureListener { exception ->
                            listener.onSignInFailed(exception.message ?: NO_ERROR_MESSAGE)
                        }
                    }
                })
    }

    fun getCurrentUserId(): String? {
        val currentUser = FirebaseAuth.getInstance().currentUser

        return currentUser?.uid
    }
}