package kmitl.taweewong.kuad.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kmitl.taweewong.kuad.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }
}
