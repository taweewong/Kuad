package kmitl.taweewong.kuad.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.descriptions.KeyName.USER_ID_EXTRA_NAME

class MainActivity : AppCompatActivity() {
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        collectData()
    }

    private fun collectData() {
        userId = intent.getStringExtra(USER_ID_EXTRA_NAME)
    }
}
