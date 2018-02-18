package kmitl.taweewong.kuad.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kmitl.taweewong.kuad.models.Bottle
import kmitl.taweewong.kuad.models.Message
import kmitl.taweewong.kuad.models.User
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object FirebaseDatabaseService {

    interface OnUpdateUserComplete {
        fun onUpdateUserSuccess(user: User)
        fun onUpdateUserFailed(message: String)
    }

    interface OnCreateBottleComplete {
        fun onCreateBottleSuccess(bottle: Bottle)
        fun onCreateBottleFailed(message: String)
    }

    interface OnAddMessageComplete {
        fun onAddMessageSuccess(message: Message)
        fun onAddMessageFailed(message: String)
    }

    interface OnGetBottleComplete {
        fun onGetBottleSuccess(bottle: Bottle)
        fun onGetBottleFailed(message: String)
    }

    interface OnGetUserBottlesComplete {
        fun onGetUserBottlesSuccess(bottles: ArrayList<Bottle>)
        fun onGetUserBottlesFailed(message: String)
    }

    private const val CHILD_USERS = "users"
    private const val CHILD_BOTTLES = "bottles"
    private const val CHILD_MESSAGES = "messages"
    private const val CHILD_OWNER = "owner"

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val dataRef = firebaseDatabase.reference

    private fun generateId(): String {
        return dataRef.push().key
    }

    fun updateUser(user: User, listener: OnUpdateUserComplete) {
        dataRef.child(CHILD_USERS).child(user.id).setValue(user).addOnCompleteListener { task ->
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
        val bottleId = generateId()
        val newBottle = Bottle(bottleId, bottleTitle, ownerId)

        dataRef.child(CHILD_BOTTLES).child(bottleId).setValue(newBottle).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.onCreateBottleSuccess(newBottle)
            } else {
                task.addOnFailureListener { exception ->
                    listener.onCreateBottleFailed(exception.message?: "No error message")
                }
            }
        }
    }

    fun addMessageToBottle(bottleId: String, message: String, listener: OnAddMessageComplete) {
        val messageId = generateId()
        val date = SimpleDateFormat("dd-MMM-YYYY", Locale.US).format(Date())
        val newMessage = Message(messageId, date, message, "Bangkok, Thailand")

        dataRef.child(CHILD_BOTTLES).child(bottleId).child(CHILD_MESSAGES).child(messageId)
                .setValue(newMessage).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.onAddMessageSuccess(newMessage)
            } else {
                task.addOnFailureListener { exception ->
                    listener.onAddMessageFailed(exception.message?: "No error message")
                }
            }
        }
    }

    fun getBottle(bottleId: String, listener: OnGetBottleComplete) {
        dataRef.child(CHILD_BOTTLES).child(bottleId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listener.onGetBottleSuccess(dataSnapshot.getValue(Bottle::class.java)?: Bottle())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                listener.onGetBottleFailed(databaseError.message)
            }
        })
    }

    fun getUserBottles(userId: String, listener: OnGetUserBottlesComplete) {
        dataRef.child(CHILD_BOTTLES)
                .orderByChild(CHILD_OWNER)
                .equalTo(userId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val bottles = ArrayList<Bottle>()

                        dataSnapshot.children.mapNotNullTo(bottles) { it.getValue(Bottle::class.java) }
                        listener.onGetUserBottlesSuccess(bottles)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        listener.onGetUserBottlesFailed(databaseError.message)
                    }
                })
    }
}