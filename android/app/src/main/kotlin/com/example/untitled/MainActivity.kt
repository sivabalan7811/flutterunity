package com.example.untitled

import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull;
import com.example.iventure.BaseActivity

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

/*
import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity() {

}
*/

class MainActivity : BaseActivity() {

    private val CHANNEL: String = "unitytrigger"
    private var result: MethodChannel.Result? = null

    companion object {
        private var result: MethodChannel.Result? = null
        private var responseContent: String = ""
    }

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            // Note: this method is invoked on the main thread.
            call, result ->
            this.result = result

            MainActivity.result = result;
            if (call.method == "initiatePayment") {
                Log.d("ERROR", "Entr q")
                val intent = Intent(this, com.unity3d.player.UnityPlayerActivity::class.java)

               startActivity(intent)
            } else {
                result.notImplemented()
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("HERE", "HERE $responseContent")
        result?.success(responseContent)
        super.onActivityResult(requestCode, resultCode, data)
    }
}