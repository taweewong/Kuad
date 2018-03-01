package kmitl.taweewong.kuad.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.descriptions.KeyName.USER_ID_EXTRA_NAME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        collectData()
        initializeView()
    }

    private fun collectData() {
        userId = intent.getStringExtra(USER_ID_EXTRA_NAME)
    }

    private fun initializeView() {
        catchBottleButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.catchBottleButton -> {
                startActivity(Intent(this, BottleActivity::class.java))
                finish()
            }
        }
    }
}
