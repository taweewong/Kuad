package kmitl.taweewong.kuad.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kmitl.taweewong.kuad.R

class MyBottleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bottle)
        supportActionBar?.hide()
    }
}
