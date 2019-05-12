package projekt.e.daftapchallenge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        val dh = DatabaseHelper(this)

        val list = dh.topResults()
        bestResultsList.layoutManager = LinearLayoutManager(this)
        bestResultsList.adapter = ListAdapter(list, this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
