package ridickle.co.kr.mylittlepet

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import ridickle.co.kr.mylittlepet.MyApplication.MY_PERMISSIONS_REQUEST_FINE_LOCATION

class SplashActivity : AppCompatActivity() {

    var layout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        layout = findViewById(R.id.layout) as LinearLayout;

        requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION);
        requestPermissions(Manifest.permission.ACCESS_COARSE_LOCATION);
        MyApplication.setHeightWidth(applicationContext);

        layout!!.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun requestPermissions(permission : String) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // 이 권한을 필요한 이유를 설명해야하는가?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                // 다이어로그같은것을 띄워서 사용자에게 해당 권한이 필요한 이유에 대해 설명합니다
                // 해당 설명이 끝난뒤 requestPermissions()함수를 호출하여 권한허가를 요청해야 합니다

            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(permission),
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION)
                // 필요한 권한과 요청 코드를 넣어서 권한허가요청에 대한 결과를 받아야 합니다
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode.equals(Manifest.permission.ACCESS_FINE_LOCATION)){
            if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용됬을 경우


            } else {
                // 권한이 거부됬을 경우
            }
            return;
        }
    }
}