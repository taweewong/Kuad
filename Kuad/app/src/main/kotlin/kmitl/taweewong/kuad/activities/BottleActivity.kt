package kmitl.taweewong.kuad.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.adapters.BottleAdapter
import kmitl.taweewong.kuad.models.Bottle
import kmitl.taweewong.kuad.models.BottleMessageItem
import kmitl.taweewong.kuad.services.RandomBottleService
import kmitl.taweewong.kuad.services.RandomBottleService.OnGetRandomBottleCompleteListener
import kotlinx.android.synthetic.main.activity_bottle.*

class BottleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottle)
        supportActionBar?.hide()

        RandomBottleService.getRandomBottle(object : OnGetRandomBottleCompleteListener {
            override fun onGetRandomBottleSuccess(bottle: Bottle) {
                val bottleMessageItems = convertBottleToAdapterMessageItem(bottle)
                val adapter = BottleAdapter(bottleMessageItems)

                bottleRecyclerView.layoutManager = LinearLayoutManager(this@BottleActivity)
                bottleRecyclerView.adapter = adapter
            }

            override fun onGetRandomBottleFailed(message: String) {
                Toast.makeText(this@BottleActivity, message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun convertBottleToAdapterMessageItem(bottle: Bottle): ArrayList<BottleMessageItem> {
        val bottleMessageItems = ArrayList<BottleMessageItem>()

        bottleMessageItems.add(BottleMessageItem(bottle.bottleTitle, bottle.createdAt))

        for (messageItem in bottle.messages.values) {
            bottleMessageItems.add(BottleMessageItem(messageItem.message, messageItem.date))
        }

        return bottleMessageItems
    }
}
