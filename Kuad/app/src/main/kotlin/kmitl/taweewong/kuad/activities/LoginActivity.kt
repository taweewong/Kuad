package kmitl.taweewong.kuad.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.descriptions.KeyName.USER_ID_EXTRA_NAME
import kmitl.taweewong.kuad.services.FirebaseAuthService
import kmitl.taweewong.kuad.services.FirebaseAuthService.OnSignInComplete
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        checkCurrentUser()
        initializeView()
    }

    private fun initializeView() {
        loginButton.setOnClickListener(this)
        signUpButton.setOnClickListener(this)
    }

    private fun checkCurrentUser() {
        val currentUserId = FirebaseAuthService.getCurrentUserId()

        if (currentUserId != null) {
            startMainActivity(currentUserId)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.loginButton -> login()
            R.id.signUpButton -> startActivity(RegisterActivity())
        }
    }

    private fun login() {
        val email = emailLoginEditText.text.toString()
        val password = passwordLoginEditText.text.toString()

        FirebaseAuthService.signInWithEmail(email, password, object : OnSignInComplete {
            override fun onSignInSuccess(uid: String) {
                startMainActivity(uid)
            }

            override fun onSignInFailed(message: String) {
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun startActivity(activity: AppCompatActivity) {
        startActivity(Intent(this@LoginActivity, activity::class.java))
        finish()
    }

    private fun startMainActivity(uid: String) {
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra(USER_ID_EXTRA_NAME, uid)
        startActivity(intent)
        finish()
    }
}
