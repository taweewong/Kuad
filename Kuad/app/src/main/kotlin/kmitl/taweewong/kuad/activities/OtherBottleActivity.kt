package kmitl.taweewong.kuad.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.activities.OtherBottleActivity.BottleMenu.FOLLOWING
import kmitl.taweewong.kuad.descriptions.KeyName.BOTTLE_MENU_EXTRA_NAME
import kotlinx.android.synthetic.main.activity_other_bottle.*

class OtherBottleActivity : AppCompatActivity() {
    private lateinit var menu: BottleMenu

    enum class BottleMenu {
        FOLLOWING, HISTORY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_bottle)
        supportActionBar?.hide()

        collectData()

        if (menu == FOLLOWING) {
            otherBottleTitle.text = getString(R.string.following_bottles)
        } else {
            otherBottleTitle.text = getString(R.string.history_bottles)
        }
    }

    private fun collectData() {
        menu = BottleMenu.valueOf(intent.getStringExtra(BOTTLE_MENU_EXTRA_NAME))
    }
}
