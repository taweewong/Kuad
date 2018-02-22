package kmitl.taweewong.kuad.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kmitl.taweewong.kuad.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        signUpButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}
