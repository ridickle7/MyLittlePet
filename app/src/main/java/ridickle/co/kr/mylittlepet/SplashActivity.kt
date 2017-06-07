package ridickle.co.kr.mylittlepet
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout

class SplashActivity : AppCompatActivity() {

    var layout : LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        layout = findViewById(R.id.layout) as LinearLayout;

        layout!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}