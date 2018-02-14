package kmitl.taweewong.kuad.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kmitl.taweewong.kuad.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
    }
}
