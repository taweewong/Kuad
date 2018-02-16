package kmitl.taweewong.kuad.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.models.User
import kmitl.taweewong.kuad.services.FirebaseAuthService
import kmitl.taweewong.kuad.services.FirebaseAuthService.OnRegisterComplete
import kmitl.taweewong.kuad.services.FirebaseDatabaseService
import kmitl.taweewong.kuad.services.FirebaseDatabaseService.OnUpdateUserComplete
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        registerButton.setOnClickListener {
            val email = registerEmailEditText.text.toString()
            val password = registerPasswordEditText.text.toString()

            FirebaseAuthService.registerWithEmail(email, password, object : OnRegisterComplete {
                override fun onRegisterSuccess(uid: String) {
                    val firstName = registerFirstNameEditText.text.toString()
                    val lastName = registerLastNameEditText.text.toString()
                    val user = User(uid, email, firstName, lastName)

                    FirebaseDatabaseService.updateUser(user, object : OnUpdateUserComplete {
                        override fun onUpdateUserSuccess(user: User) {
                            finish()
                            Toast.makeText(this@RegisterActivity,
                                    "SUCCESS: " + user.toString(),
                                    Toast.LENGTH_LONG).show()
                        }

                        override fun onUpdateUserFailed(message: String) {
                            Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()
                        }
                    })
                }

                override fun onRegisterFailed(message: String) {
                    Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
