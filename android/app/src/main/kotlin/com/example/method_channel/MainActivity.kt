package com.example.method_channel

import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val My_CHANNEL = "flutterdelux.com/toast"
    private lateinit var channel: MethodChannel

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger,My_CHANNEL)

        // recivei data from flutter
        channel.setMethodCallHandler { call, result ->
            if (call.method=="showShortToast") {
                showShortToast()
            }else if (call.method=="showShortToastWithArgument") {
                val argument = call.arguments<Map<String,String>>()
                if(argument==null) {
                    result.error("Invalid Argument","there are no argument",null);
                }else{
                    val message = argument["message"] as String
                    showToastWthArgument(message)
                }

            }else if (call.method=="accessData") {
                val argument = call.arguments<Map<String,String>>()
                if(argument==null) {
                    result.error("Invalid Argument","there are no argument",null);
                }else{
                    val name = argument["name"] as String
                    val data = accessData(name)
                    result.success(data)
                }

            }
        }
    }

    private fun showShortToast() {
        Toast.makeText(this,"Hello from Android Kotlin",Toast.LENGTH_SHORT).show();
    }

    private fun showToastWthArgument(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private fun accessData(name: String): String{
        return "Hello $name, from Android Kotlin"
    }
}
