package kmitl.taweewong.kuad.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.adapters.BottleAdapter
import kmitl.taweewong.kuad.models.BottleMessageItem
import kotlinx.android.synthetic.main.activity_bottle.*

class BottleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottle)
        supportActionBar?.hide()

        val bottles = ArrayList<BottleMessageItem>()
        bottles.add(BottleMessageItem("This is Title", "title date"))
        bottles.add(BottleMessageItem("Hello World!", "14 FEB 2018"))
        bottles.add(BottleMessageItem("HI ja", "15 FEB 2018"))
        bottles.add(BottleMessageItem("SAWASDEE KRUB", "16 FEB 2018"))
        bottles.add(BottleMessageItem("tam rai yu", "17 FEB 2018"))
        bottles.add(BottleMessageItem("mai bok", "18 FEB 2018"))
        bottles.add(BottleMessageItem("ow!", "19 FEB 2018"))

        val adapter = BottleAdapter(bottles)

        bottleRecyclerView.layoutManager = LinearLayoutManager(this)
        bottleRecyclerView.adapter = adapter
    }
}
