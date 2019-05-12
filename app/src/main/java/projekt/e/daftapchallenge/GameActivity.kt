package projekt.e.daftapchallenge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_game.*
import java.text.SimpleDateFormat
import java.util.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var alert = AlertDialog.Builder(this)
        alert.setTitle("Result")

        alert.setOnCancelListener({
            startActivity(Intent(this, MainActivity::class.java))
        })

        val dh = DatabaseHelper(this)
        var start = ""
        var cnt = 0

        var clickTimer = object: CountDownTimer(5_000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                var msg = (millisUntilFinished / 1_000).toString() + "." + (millisUntilFinished / 100 % 10).toString()
                clickCircle.setText(msg)
            }

            override fun onFinish() {
                clickCircle.text = "0"
                clickCircle.setOnClickListener({})

                val top5 = dh.topResults()
                if(top5.size < 5 || top5.get(4).score < cnt) {
                    alert.setMessage("You scored " + cnt + when(cnt){
                            1 -> " point."
                            else -> " points."}
                            + "\nCongratulations!")
                }
                else {
                    alert.setMessage("You scored " + cnt + when(cnt){
                            1 -> " point."
                            else -> " points."})
                }

                Handler().postDelayed({
                    try {
                        alert.show()
                        dh.insertScore(cnt, start)
                    }
                    catch (e: WindowManager.BadTokenException) {}
                }, 1_000)
            }
        }

        object:CountDownTimer(4_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                clickCircle.setText((millisUntilFinished / 1_000).toString() + "...")
            }
            override fun onFinish() {
                val ft = SimpleDateFormat("MM-dd HH:mm:ss")
                start = ft.format(Date())

                clickCircle.setOnClickListener {
                    scoreTextView.text = (++cnt).toString() + " " + when(cnt) {
                        1 -> "point"
                        else -> "points"
                    }
                }

                clickTimer.start()
            }
        }.start()
    }
}
