package com.sartor.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sartor.R
import com.sartor.TestActivity
import com.sartor.data.db.SharedPreference
import com.sartor.data.model.myModels.MyFavoriteItem
import com.sartor.util.constants.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList
import java.util.HashMap

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreference
    lateinit var video: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        video = findViewById(R.id.videoPLAY)

        sharedPreference = SharedPreference(this)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.intro)
        video.setVideoURI(uri)
        video.setOnPreparedListener {
            it.setVolume(0F, 0F)
        }
        video.start()

        video.visibility = View.VISIBLE
        if (sharedPreference.isLogin()) {
            getFavorites()
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
    startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                finish()
            /*      if (sharedPreference.isLogin()) {
                      startActivity(Intent(this@WelcomeActivity, HomeActivity::class.java))
                      finish()
                  } else {
                      startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                      finish()
                  }
      //            startActivity(Intent(this@WelcomeActivity, TestActivity::class.java))
      //            finish()*/
        }
    }

    override fun onResume() {
        video.start()
        super.onResume()
    }

    override fun onStart() {
        video.start()
        super.onStart()
    }

    private fun getFavorites() {
        var mRequestQueue: RequestQueue? = null
        var mStringRequest: StringRequest? = null


        var token = sharedPreference.getToken()

        var url: String = Constant.BASE_URL + "api/favorite/lists"
        mRequestQueue = Volley.newRequestQueue(this)
        mStringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                var array = JSONArray(response)




                for (n in 0 until array.length()) {
                    try {
                        val obj = array.getJSONObject(n)
                        var myBrand = Gson().fromJson(
                            obj.toString(),
                            MyFavoriteItem::class.java
                        )

                        sharedPreference.save(myBrand.product._id, myBrand.product._id);


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }


            },
            Response.ErrorListener { error ->
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Content-Type"] = "application/json"
                headerMap["Authorization"] = "Bearer $token"
                return headerMap
            }


        }

        mRequestQueue?.add<String>(mStringRequest)
    }

}