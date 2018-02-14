package kmitl.taweewong.kuad.services

import com.google.firebase.database.FirebaseDatabase
import kmitl.taweewong.kuad.models.User

object FirebaseDatabaseService {

    interface OnUpdateUserComplete {
        fun onUpdateUserSuccess(user: User)
        fun onUpdateUserFailed(message: String)
    }

    private const val CHILD_USERS = "users"

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef = firebaseDatabase.getReference(CHILD_USERS)

    fun createNewUser(user: User, listener: OnUpdateUserComplete) {
        usersRef.child(user.id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.onUpdateUserSuccess(user)
            } else {
                task.addOnFailureListener { exception ->
                    listener.onUpdateUserFailed(exception.message?: "No error message")
                }
            }
        }
    }
}