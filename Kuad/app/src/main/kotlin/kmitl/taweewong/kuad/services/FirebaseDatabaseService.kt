package kmitl.taweewong.kuad.services

import com.google.firebase.database.FirebaseDatabase
import kmitl.taweewong.kuad.models.Bottle
import kmitl.taweewong.kuad.models.User

object FirebaseDatabaseService {

    interface OnUpdateUserComplete {
        fun onUpdateUserSuccess(user: User)
        fun onUpdateUserFailed(message: String)
    }

    interface OnCreateBottleComplete {
        fun onCreateBottleSuccess(bottle: Bottle)
        fun onCreateBottleFailed(message: String)
    }

    private const val CHILD_USERS = "users"
    private const val CHILD_BOTTLES = "bottles"

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef = firebaseDatabase.getReference(CHILD_USERS)
    private val bottlesRef = firebaseDatabase.getReference(CHILD_BOTTLES)

    private fun generateBottleId(): String {
        return bottlesRef.push().key
    }

    fun updateUser(user: User, listener: OnUpdateUserComplete) {
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

    fun createBottle(ownerId: String, bottleTitle: String, listener: OnCreateBottleComplete) {
        val bottleId = generateBottleId()
        val newBottle = Bottle(bottleId, bottleTitle, ownerId)

        bottlesRef.child(bottleId).setValue(newBottle).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.onCreateBottleSuccess(newBottle)
            } else {
                task.addOnFailureListener { exception ->
                    listener.onCreateBottleFailed(exception.message?: "No error message")
                }
            }
        }
    }
}